package common.cout970.UltraTech.multipart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.CableInterfaceBlock;
import ultratech.api.util.UT_Utils;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.multipart.client.RenderCablePlane;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageMultiPartUpdate;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.cables.CableInterfaceRibbonCable;

public class MultiPartCable_Ribbon_Down extends BasicPartUT implements IPowerConductor{

	public MultiPartCable_Ribbon_Down() {
		super(MultiPartRegistry_UT.PlaneCable);
	}
	
	@Override
	public Cuboid6[] setupBoundingBoxes() {
		double w = 0.125;
		float h = pixel*3;
		boundingBoxes = new Cuboid6[6];
		boundingBoxes[0] = new Cuboid6(0.5-w, 0,  0.5-w, 0.5+w, h, 0.5+w);	//base
		boundingBoxes[2] = new Cuboid6(0.5-w, 0,  0,     0.5+w, h, 0.5-w);	//north 2
		boundingBoxes[3] = new Cuboid6(0.5-w, 0,  0.5+w, 0.5+w, h, 1);		//south 3
		boundingBoxes[4] = new Cuboid6(0,     0,  0.5-w, 0.5-w, h, 0.5+w);	//west 4
		boundingBoxes[5] = new Cuboid6(0.5+w, 0,  0.5-w, 1,     h, 0.5+w);	//east 5
		return boundingBoxes;
	}

	@Override
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> OB = new ArrayList<Cuboid6>();
		OB.add(boundingBoxes[0]);//base
		for(int i=2; i<6; i++){
			if(connections[i]){
				OB.add(boundingBoxes[i]);
			}
		}
		return OB;
	}
	
	@Override
    public Iterable<IndexedCuboid6> getSubParts() {
        Iterable<Cuboid6> boxList = getCollisionBoxes();
        LinkedList<IndexedCuboid6> partList = new LinkedList<IndexedCuboid6>();
        for (Cuboid6 c : boxList)
            partList.add(new IndexedCuboid6(0, c));
        ((ArrayList<Cuboid6>) boxList).clear();
        return partList;
    }
	
	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[0] });
	}

	private RenderCablePlane render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderCablePlane();
				render.render(this, pos);
		}
	}
	
	//power
	public PowerInterface cond = null;
	public boolean toUpdate = true;
	
	@Override
	public PowerInterface getPower(){
		if(cond == null){
			cond = new PowerInterface(tile(), new CableInterfaceRibbonCable(this));
		}
		return cond;
	}
	
	public void updateConnections(){
		if(tile() == null)return;
		if(!MultipartUtil.isSolid(getTile(), ForgeDirection.DOWN) && !world().isRemote){
			this.tile().dropItems(this.getDrops());
			try{
				this.tile().remPart(this);
			}catch(Exception e){}
			return;
		}
		connections = new boolean[6];
		
		for(ICable c : NetworkManagerRegistry.getConnections(tile())){
			if(c != this.getPower().getCable()){
				ForgeDirection side = c.getInternalConection(CableType.Ribbon, getPower().getCable());
				if(side != ForgeDirection.UNKNOWN){
					connections[side.getOpposite().ordinal()] = true;
				}
			}
		}
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(d == ForgeDirection.UP)continue;
			TileEntity tile = UT_Utils.getRelative(tile(), d);
			for(ICable c : NetworkManagerRegistry.getConnections(tile)){
				if(getPower().getCable().shouldConnectWithThis(c, d)){
					if(c.shouldConnectWithThis(getPower().getCable(), d.getOpposite())){
						connections[d.ordinal()] = true;
					}
				}
			}
		}
	}
	
	@Override
	public void update() {
		super.update();
		if(toUpdate){
			updateConnections();
			toUpdate = false;
		}
		getPower().MachineUpdate();	
	}

	@Override
	public void onNeighborChanged() {
		if(!world().isRemote)Net_Utils.INSTANCE.sendToAll(new MessageMultiPartUpdate(this));
		toUpdate = true;
	}

	@Override
	public void onPartChanged(TMultiPart part) {
		onNeighborChanged();
	}

	@Override
	public void onAdded() {
		onNeighborChanged();
	}

	@Override
	public void onRemoved() {
		if(getPower().getNetwork() != null){
			getPower().getNetwork().excludeAndRecalculate(this);
		}
	}
	
}

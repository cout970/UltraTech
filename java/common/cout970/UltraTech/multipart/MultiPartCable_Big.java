package common.cout970.UltraTech.multipart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.PowerInterface;
import ultratech.api.util.UT_Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import common.cout970.UltraTech.microparts.MicroCablePlane;
import common.cout970.UltraTech.microparts.MicroRegistry;
import common.cout970.UltraTech.multipart.client.RenderCableBig;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageMicroPartUpdate;
import common.cout970.UltraTech.util.LogHelper;

public class MultiPartCable_Big extends BasicPartUT implements ISidedHollowConnect, IPowerConductor{

	public MultiPartCable_Big() {
		super(MicroRegistry.BigCable);
	}

	@Override
	public Cuboid6[] setupBoundingBoxes() {
		boundingBoxes = new Cuboid6[7];
		double w = 0.25;
		boundingBoxes[6] = new Cuboid6(0.5-w,0.5-w,0.5-w,0.5+w,0.5+w,0.5+w);//base
		boundingBoxes[0] = new Cuboid6(0.5-w, 0, 0.5-w, 0.5+w, 0.5-w, 0.5+w);//up
		boundingBoxes[1] = new Cuboid6(0.5-w, 0.5+w, 0.5-w, 0.5+w, 1, 0.5+w);//down
		boundingBoxes[2] = new Cuboid6(0.5-w, 0.5-w, 0, 0.5+w, 0.5+w, 0.5-w);//north
		boundingBoxes[3] = new Cuboid6(0.5-w, 0.5-w, 0.5+w, 0.5+w, 0.5+w, 1);//south
		boundingBoxes[4] = new Cuboid6(0, 0.5-w, 0.5-w, 0.5-w, 0.5+w, 0.5+w);//west
		boundingBoxes[5] = new Cuboid6(0.5+w, 0.5-w, 0.5-w, 1, 0.5+w, 0.5+w);//east
		return boundingBoxes;
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
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> t2 = new ArrayList<Cuboid6>();
		t2.add(boundingBoxes[6]);
		for(int i=0; i<6; i++){
			if(connections[i]){
				t2.add(boundingBoxes[i]);
			}
		}
		return t2;
	}
	
	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[6] });
	}

	@Override
	public int getHollowSize(int arg0) {
		return 8;
	}
	
	private RenderCableBig render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderCableBig();
			render.render(this, pos);
		}
	}
	
	//power interface 

	public PowerInterface cond = null;
	public boolean toUpdate = true;
	
	@Override
	public PowerInterface getPower() {
		if(cond == null){
			cond = new PowerInterface(tile()){
				public CableType getConnectionType(ForgeDirection side){
					return CableType.BIG_CENTER;
				}
			};
		}
		return cond;
	}

	public void updateConnections(){
		if(tile() == null)return;
		connections = new boolean[6];
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			
			if(d == ForgeDirection.DOWN){
				for(TMultiPart t : tile().jPartList())if(t instanceof MicroCablePlane){
					connections[d.ordinal()] = true;
				}
			}
			TileEntity tile = UT_Utils.getRelative(tile(), d);
			if(MultipartUtil.canConect(this,tile,d)){
				if(tile().canAddPart(new NormallyOccludedPart(boundingBoxes[d.ordinal()]))){
					connections[d.ordinal()] = true;
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
		if(!world().isRemote)Net_Utils.INSTANCE.sendToAll(new MessageMicroPartUpdate(this));
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

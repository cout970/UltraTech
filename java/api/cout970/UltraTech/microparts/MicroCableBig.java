package api.cout970.UltraTech.microparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import common.cout970.UltraTech.lib.UT_Utils;
import api.cout970.UltraTech.MeVpower.CableType;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.PowerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.PartMap;
import codechicken.multipart.TFacePart;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TSlottedPart;
import codechicken.multipart.TileMultipart;

public class MicroCableBig extends TMultiPart implements IPowerConductor, JNormalOcclusion, ISidedHollowConnect{

	public static Cuboid6[]boundingBoxes = new Cuboid6[7];
	public Map<ForgeDirection,Boolean> conn = new HashMap<ForgeDirection,Boolean>();
	//boxes
	static {
		double w = 0.25;
		boundingBoxes[6] = new Cuboid6(0.5-w,0.5-w,0.5-w,0.5+w,0.5+w,0.5+w);//base
		boundingBoxes[0] = new Cuboid6(0.5-w, 0, 0.5-w, 0.5+w, 0.5-w, 0.5+w);//up
		boundingBoxes[1] = new Cuboid6(0.5-w, 0.5+w, 0.5-w, 0.5+w, 1, 0.5+w);//down
		boundingBoxes[2] = new Cuboid6(0.5-w, 0.5-w, 0.5-w,0.5+w, 0.5+w, 0);//north
		boundingBoxes[3] = new Cuboid6(0.5-w, 0.5-w, 0.5+w, 0.5+w, 0.5+w, 1);//south
		boundingBoxes[4] = new Cuboid6(0, 0.5-w, 0.5-w, 0.5-w, 0.5+w, 0.5+w);//west
		boundingBoxes[5] = new Cuboid6(1, 0.5-w, 0.5-w, 0.5+w, 0.5+w, 0.5+w);//east
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
			if(isConnectedOnSide(ForgeDirection.getOrientation(i))){
				t2.add(boundingBoxes[i]);
			}
		}
		return t2;
	}

	public boolean isConnectedOnSide(ForgeDirection o) {
		return conn.containsKey(o) && conn.get(o);
	}
	
	@Override
    public boolean occlusionTest(TMultiPart part) {
    
        return NormalOcclusionTest.apply(this, part);
    }

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[6] });
	}
	
	//misc
	
	@Override
	public Iterable<ItemStack> getDrops() {
		LinkedList<ItemStack> items = new LinkedList<ItemStack>();
		items.add(new ItemStack(MicroRegistry.BigCable));
		return items;
	}

	@Override
	public ItemStack pickItem(MovingObjectPosition hit) {

		return new ItemStack(MicroRegistry.BigCable);
	}

	@Override
	public float getStrength(MovingObjectPosition hit, EntityPlayer player) {

		return 1f;
	}
	
	@Override
	public String getType() {
		return MicroRegistry.BigCable.getUnlocalizedName();
	}

	@Override
	public int getHollowSize(int arg0) {
		return 8;
	}

//	@Override
//	public int getSlotMask() {
//		return 6;
//	}

	//power
	public PowerInterface cond = null;
		
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
		conn.clear();
		for(ForgeDirection o : ForgeDirection.VALID_DIRECTIONS){
			boolean a = tile().canAddPart(new NormallyOccludedPart(boundingBoxes[o.ordinal()]));
			boolean b = false;
			TileEntity tile = UT_Utils.getRelative(tile(), o);
			if(MicroPartUtil.canConect(this,tile,o))b = true;
			if(o == ForgeDirection.DOWN){
				for(TMultiPart t : tile().jPartList())if(t instanceof MicroCablePlane){b = true; a = true;}
			}
			conn.put(o, a && b);
		}
	}
	
	@Override
	public void update() {
		super.update();
		getPower().MachineUpdate();	
	}

	@Override
	public void onNeighborChanged() {
		updateConnections();
	}

	@Override
	public void onPartChanged(TMultiPart part) {
		updateConnections();
	}

	@Override
	public void onAdded() {
		getPower().iterate();
		updateConnections();
	}

	@Override
	public void onRemoved() {
		if(getPower().getNetwork() != null){
			getPower().getNetwork().excludeAndRecalculate(this);
		}
	}
	
	//render

	private RenderCableBig render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderCableBig();
			render.render(this, pos);
		}
	}

}

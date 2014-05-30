package common.cout970.UltraTech.microparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.PowerInterface;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.machines.renders.RenderCable;
import codechicken.lib.raytracer.IndexedCuboid6;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Rotation;
import codechicken.lib.vec.Vector3;
import codechicken.microblock.ISidedHollowConnect;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TSlottedPart;

public class MicroCable extends TMultiPart implements IPowerConductor, JNormalOcclusion, TSlottedPart, ISidedHollowConnect{

	public PowerInterface cond = null;

	//boxes
	private static Cuboid6[] boundingBoxes = new Cuboid6[5];

	static {
		double w = 0.125;
		boundingBoxes[0] = new Cuboid6(0.5-w, 0, 0.5-w, 0.5+w, w, 0.5+w);
		boundingBoxes[1] = new Cuboid6(0, 0, 0.5-w, 0.5-w, w, 0.5+w);
		boundingBoxes[2] = new Cuboid6(0.5+w, 0, 0.5-w, 1, w, 0.5+w);
		boundingBoxes[3] = new Cuboid6(0.5-w, 0, 0.5+w, 0.5+w, w, 1);
		boundingBoxes[4] = new Cuboid6(0.5-w, 0, 0, 0.5+w, w, 0.5-w);
	}

	@Override
	public Iterable<Cuboid6> getCollisionBoxes() {
		ArrayList<Cuboid6> t2 = new ArrayList<Cuboid6>();
		t2.add(boundingBoxes[0]);
		for (int i = 0; i < 4; i++) {
			if (isConnectedOnSide(ForgeDirection.getOrientation(i))) {
				t2.add(boundingBoxes[i + 1]);
			}
		}
		return t2;
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
    public boolean occlusionTest(TMultiPart part) {
    
        return NormalOcclusionTest.apply(this, part);
    }

	@Override
	public Iterable<Cuboid6> getOcclusionBoxes() {
		return Arrays.asList(new Cuboid6[] { boundingBoxes[0] });
	}

	private boolean isConnectedOnSide(ForgeDirection o) {
		if(getPower() == null)return false;
		return UT_Utils.getRelative(tile(), o) instanceof IPowerConductor;
//		return getPower().getConections().containsKey(o) && getPower().getConections().get(o);
	}

	//tipe
	@Override
	public String getType() {
		return MicroRegistry.Cable.getUnlocalizedName();
	}

	//energy
	@Override
	public PowerInterface getPower() {
		if(cond == null && tile() != null)cond = new PowerInterface(tile());
		return cond;
	}

	@Override
	public void update() {
		super.update();
		if(getPower().getParent() == null)cond = new PowerInterface(tile());
		getPower().MachineUpdate();
	}

	@Override
	public void onNeighborChanged() {
		if(getPower() == null)return;
		getPower().reconect(tile());
	}

	@Override
	public void onPartChanged(TMultiPart part) {
		if(getPower() == null)return;
		getPower().reconect(tile());
	}

	@Override
	public void onAdded() {
//		if(getPower() == null)return;
//		getPower().iterate();
//		getPower().reconect(tile());
	}

	@Override
	public void onRemoved() {
//		if(getPower() == null)return;
//		getPower().iterate();
	}

	//multipart

	@Override
	public int getSlotMask() {

		return 0x40;
	}

	@Override
	public int getHollowSize(int arg0) {
		return 5;
	}

	@Override
	public Iterable<ItemStack> getDrops() {
		LinkedList<ItemStack> items = new LinkedList<ItemStack>();
		items.add(new ItemStack(MicroRegistry.Cable));
		return items;
	}

	@Override
	public ItemStack pickItem(MovingObjectPosition hit) {

		return new ItemStack(MicroRegistry.Cable);
	}

	@Override
	public float getStrength(MovingObjectPosition hit, EntityPlayer player) {

		return 2;
	}

	//render

	private RenderCable render;

	@Override
	public void renderDynamic(Vector3 pos, float frame, int pass) {
		if (pass == 0) {
			if (render == null) render = new RenderCable();
			render.render(this, pos);
		}
	}
}

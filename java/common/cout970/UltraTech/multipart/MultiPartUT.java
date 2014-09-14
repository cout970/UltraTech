package common.cout970.UltraTech.multipart;

import java.util.LinkedList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.JNormalOcclusion;
import codechicken.multipart.NormalOcclusionTest;
import codechicken.multipart.TMultiPart;

import common.cout970.UltraTech.misc.IUpdatedEntity;

public abstract class MultiPartUT extends TMultiPart implements JNormalOcclusion, IUpdatedEntity{

	public static float pixel = 1f/16f;
	
	public Item base;
	
	public MultiPartUT(Item a){
		base = a;
	}
	
	public Cuboid6[] boundingBoxes = setupBoundingBoxes();

	public boolean[] connections = new boolean[6];

	public abstract Cuboid6[] setupBoundingBoxes();

	@Override
	public boolean occlusionTest(TMultiPart part) {
		return NormalOcclusionTest.apply(this, part);
	}
	
	@Override
	public void onNeigUpdate() {
		onNeighborChanged();
	}
	
	@Override
	public ItemStack pickItem(MovingObjectPosition hit) {
		return new ItemStack(base, 1);
	}
	
	@Override
	public String getType() {
		return base.getUnlocalizedName();
	}
	
	@Override
	public Iterable<ItemStack> getDrops() {
		LinkedList<ItemStack> items = new LinkedList<ItemStack>();
		items.add(new ItemStack(base, 1));
		return items;
	}
	
	@Override
	public float getStrength(MovingObjectPosition hit, EntityPlayer p){
		return 10f;
	}
}

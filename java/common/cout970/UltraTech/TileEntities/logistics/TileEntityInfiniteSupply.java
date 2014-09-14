package common.cout970.UltraTech.TileEntities.logistics;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.util.Inventory;

public class TileEntityInfiniteSupply extends Inventory implements ISidedInventory{

	public TileEntityInfiniteSupply() {
		super(10, "wip");
	}
	
	public void updateEntity(){
		super.updateEntity();
		ItemStack item = this.getStackInSlot(0);
		for(int i = 1;i<10;i++){
			if(item == null){
				this.setInventorySlotContents(i, null);
			}else{
				this.setInventorySlotContents(i, item.copy());
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int s) {
		return new int[]{1,2,3,4,5,6,7,8,9};
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_,int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int s, ItemStack p_102008_2_, int p_102008_3_) {
		return s != 0;
	}

	
}

package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.utility.TileEntityCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryCrafter extends InventoryCrafting{

	public TileEntityCrafter entity;
	
	public InventoryCrafter(TileEntityCrafter e, int par2, int par3) {
		super(new Container(){
			@Override
			public boolean canInteractWith(EntityPlayer entityplayer) {
				return true;
			}}, par2, par3);
		entity = e;
	}
	
	public ItemStack decrStackSize(int par1, int par2)
    {
		super.decrStackSize(par1, par2);
		return null;
    }
	
	public void onInventoryChanged() {
		entity.update();;
	}

	public void blend(InventoryCrafter inventoryCrafter) {
		for(int c = 0; c < 9; c++){
			this.setInventorySlotContents(c, inventoryCrafter.getStackInSlot(c));
		}
	}

}

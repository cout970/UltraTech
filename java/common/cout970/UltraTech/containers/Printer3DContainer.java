package common.cout970.UltraTech.containers;


import common.cout970.UltraTech.TileEntities.utility.Printer3DEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Printer3DContainer extends Container{

	public Printer3DEntity tile;
	
	public Printer3DContainer(InventoryPlayer inventoryPlayer, Printer3DEntity tileEntity2){
		super();
		tile = tileEntity2;
		addSlotToContainer(new Slot(tileEntity2, 0, 11, 11));
		bindPlayerInventory(inventoryPlayer);
	}
	

	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				 addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                         8 + j * 18, 84 + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot == 0){//slot 0 smelt
				if(!mergeItemStack(itemstack, 1, 37, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else{
				if (Item.getIdFromItem(itemstack.getItem())==Item.getIdFromItem(Items.coal))
                {
                    if (!this.mergeItemStack(itemstack, 0, 1, true))
                    {
                        return null;
                    }
                }else if (slot >= 1 && slot < 28)
                {
                    if (!this.mergeItemStack(itemstack, 28, 37, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 28 && slot < 37){
                	if(!this.mergeItemStack(itemstack, 1, 28, false))
                	{
                		return null;
                	}
                }

				current.onSlotChanged();
			}
			if (itemstack.stackSize == 0)
			{
				current.putStack((ItemStack)null);
			}
			if (itemstack.stackSize == aux.stackSize)
			{
				return null;
			}
			current.onPickupFromSlot(player, itemstack);
		}
		return null;
	}
}

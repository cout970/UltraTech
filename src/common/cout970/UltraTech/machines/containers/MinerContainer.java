package common.cout970.UltraTech.machines.containers;


import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MinerContainer extends Container{

	public MinerEntity entity;
	
	private final int CHEST_INVENTORY_ROWS = 4;
	private final int CHEST_INVENTORY_COLUMNS = 13;

	
	public MinerContainer(InventoryPlayer inventoryPlayer, MinerEntity tileEntity2){
		super();
		entity = tileEntity2;
		int x = 0;
		for (int chestRowIndex = 0; chestRowIndex < CHEST_INVENTORY_ROWS; ++chestRowIndex) {
            for (int chestColumnIndex = 0; chestColumnIndex < CHEST_INVENTORY_COLUMNS; ++chestColumnIndex) {
                this.addSlotToContainer(new Slot(tileEntity2, x, 8 + chestColumnIndex * 18, 18 + chestRowIndex * 18));x++;
            }
        }
		bindPlayerInventory(inventoryPlayer);
	}
	
	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		int x = 36;
		int y = 20;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18+x, 84 + i * 18+y));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18+x, 142+y));
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 4*13);
	}

	 public ItemStack transfer(EntityPlayer player, int slot,int inv) {
			ItemStack aux = null;
			Slot current = (Slot)this.inventorySlots.get(slot);
			
			if (current != null && current.getHasStack())
			{
				ItemStack itemstack = current.getStack();
				aux = itemstack.copy();
				
				if(slot <= inv){//slot 0 smelt
					if(!mergeItemStack(itemstack, inv, 36+inv, false)){
						return null;
					}
					current.onSlotChange(itemstack, aux);
					
				}else{
					
					if (slot >= inv && slot < 27+inv)
	                {
	                    if (!this.mergeItemStack(itemstack, 0, inv, false))
	                    {
	                        return null;
	                    }
	                }
	                else if (slot >= 27+inv && slot < 36+inv){
	                	if(!this.mergeItemStack(itemstack, 0, inv, false))
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
	

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}

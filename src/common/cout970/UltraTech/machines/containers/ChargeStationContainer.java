package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ChargeStationContainer extends UT_Container{


	public ChargeStationContainer(InventoryPlayer inventoryPlayer,TileEntity tile){
		super(inventoryPlayer,tile);
		int i = 0;
		for(int y = 0;y<2;y++){
			for(int x = 0;x<3;x++){
				addSlotToContainer(new Slot((IInventory) tile, i, 80+18*x, 16+36*y));i++;
			}
		}
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return transfer(player, slot, 6);
	}
	
	public ItemStack transfer(EntityPlayer player, int slot,int inv) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot < inv){
				if(!mergeItemStack(itemstack, inv, 36+inv, true)){
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

}

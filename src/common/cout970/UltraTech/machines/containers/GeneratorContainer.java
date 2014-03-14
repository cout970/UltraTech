package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class GeneratorContainer extends UT_Container{

	
	public GeneratorContainer(InventoryPlayer inventoryPlayer, TileEntity tileEntity2){
		super(inventoryPlayer, tileEntity2);
		addSlotToContainer(new Slot((IInventory) tileEntity, 0, 63, 25));
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot == 0){
				if(!mergeItemStack(itemstack, 1, 37, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else{
				if (TileEntityFurnace.getItemBurnTime(itemstack) > 0)
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

	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}

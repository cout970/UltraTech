package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;

public class ContainerChemical extends ContainerUT{

	public ContainerChemical(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.addSlotToContainer(new Slot((IInventory) t, 0, 145, 15));
		this.addSlotToContainer(new Slot((IInventory) t, 1, 145, 33));
		this.addSlotToContainer(new Slot((IInventory) t, 2, 145, 51));
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
			
			if(slot == 0 || slot == 1 || slot == 2){//output
				if(!mergeItemStack(itemstack, 3, 39, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else{
				if (slot >= 3 && slot < 30)
                {
                    if (!this.mergeItemStack(itemstack, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 30 && slot < 39){
                	if(!this.mergeItemStack(itemstack, 3, 30, false))
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

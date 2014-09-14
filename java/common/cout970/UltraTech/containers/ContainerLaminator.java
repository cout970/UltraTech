package common.cout970.UltraTech.containers;

import ultratech.api.recipes.Laminator_Recipe;
import ultratech.api.recipes.Purifier_Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityLaminatorT1;

public class ContainerLaminator extends ContainerUT{

	public ContainerLaminator(InventoryPlayer inventoryPlayer, TileEntityLaminatorT1 t) {
		super(inventoryPlayer, t);
		
		addSlotToContainer(new Slot(t, 0, 53, 33));
		addSlotToContainer(new Slot(t, 1, 143, 33){
			@Override
			public boolean isItemValid(ItemStack a){
				return false;
			}
		});
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
			
			if(slot == 0){//slot 0 smelt
				if(!mergeItemStack(itemstack, 2, 38, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else if(slot == 1){//slot 1 result
				if(!mergeItemStack(itemstack, 2, 38, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{
				if (Laminator_Recipe.isIngredient(itemstack))
                {
                    if (!this.mergeItemStack(itemstack, 0, 1, false))
                    {
                        return null;
                    }
                }else if (slot >= 2 && slot < 29)
                {
                    if (!this.mergeItemStack(itemstack, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 29 && slot < 38){
                	if(!this.mergeItemStack(itemstack, 2, 29, false))
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

package common.cout970.UltraTech.containers;

import ultratech.api.recipes.Cutter_Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;

public class CutterContainer extends Container{

	
	private CutterT1_Entity tileEntity;

	public CutterContainer(InventoryPlayer inventory, CutterT1_Entity tileEntity){
		super();
		this.tileEntity = tileEntity;
		addSlotToContainer(new Slot(tileEntity, 0, 61, 32));
		addSlotToContainer(new Slot(tileEntity, 1, 117, 32){
			@Override
			public boolean isItemValid(ItemStack par1ItemStack)
		    {
		        return false;
		    }
		});
		addSlotToContainer(new Slot(tileEntity, 2, 135, 32){
			@Override
			public boolean isItemValid(ItemStack par1ItemStack)
		    {
		        return false;
		    }
		});
		bindPlayerInventory(inventory);
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
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		tileEntity.getGUINetworkData(i, j);
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
				if(!mergeItemStack(itemstack, 3, 39, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
				
			}else if(slot == 1 || slot == 2){//slot 1,2 result
				if(!mergeItemStack(itemstack, 3, 39, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{
				if (Cutter_Recipe.isIngredient(itemstack))
                {
                    if (!this.mergeItemStack(itemstack, 0, 1, false))
                    {
                        return null;
                    }
                }else if (slot > 2 && slot < 30)
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

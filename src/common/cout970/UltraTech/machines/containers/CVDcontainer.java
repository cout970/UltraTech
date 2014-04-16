package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.TileEntities.Tier1.CVD_Entity;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class CVDcontainer extends Container{

	public CVD_Entity tileEntity;
	
	public CVDcontainer(InventoryPlayer inventoryPlayer, CVD_Entity tileEntity2){
		super();
		tileEntity = tileEntity2;
		
		addSlotToContainer(new Slot(tileEntity, 0, 53, 33));
		addSlotToContainer(new Slot(tileEntity, 1, 89, 33));
		addSlotToContainer(new Slot(tileEntity, 2, 143, 33){
			@Override
			public boolean isItemValid(ItemStack a){
				return false;
			}
		});
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
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, tileEntity.progres);
        par1ICrafting.sendProgressBarUpdate(this, 1, (int)tileEntity.getEnergy());
        par1ICrafting.sendProgressBarUpdate(this, 2, tileEntity.speed);
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
		return transfer(player, slot, 3);
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
				if(CVD_Recipe.getResult(itemstack) != null){
					if(!mergeItemStack(itemstack, 0, inv-1, false)){
						return null;
					}
				}else if (slot >= inv && slot < 27+inv)
                {
                    if (!this.mergeItemStack(itemstack, 27+inv, 36+inv, false))
                    {
                        return null;
                    }
                }
                else if (slot >= 27+inv && slot < 36+inv){
                	if(!this.mergeItemStack(itemstack, inv, 27+inv, false))
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

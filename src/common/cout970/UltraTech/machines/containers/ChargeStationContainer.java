package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.machines.tileEntities.ChargeStationEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ChargeStationContainer extends Container{

	private ChargeStationEntity tileEntity;

	public ChargeStationContainer(InventoryPlayer inventoryPlayer,ChargeStationEntity tile){
		tileEntity = tile;
		int i = 0;
		for(int y = 0;y<3;y++){
			for(int x = 0;x<3;x++){
				addSlotToContainer(new Slot(tile, i, 80+18*x, 16+18*y));i++;
			}
		}
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
		par1ICrafting.sendProgressBarUpdate(this, 1, tileEntity.Energy);
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
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return transfer(player, slot, 9);
	}
	
	public ItemStack transfer(EntityPlayer player, int slot,int inv) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot < inv){//slot 0 smelt
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
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}

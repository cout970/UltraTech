package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.machines.tileEntities.EngineEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class EngineContainer extends Container{
	
	public EngineEntity tileEntity;
	
	public EngineContainer(InventoryPlayer inventoryPlayer, EngineEntity tileEntity2){
		super();
		tileEntity = tileEntity2;
		if(inventoryPlayer != null)
		bindPlayerInventory(inventoryPlayer);
	}
	
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 0, (tileEntity.working)? 1 : 0);
        par1ICrafting.sendProgressBarUpdate(this, 1, tileEntity.getEnergy());
        par1ICrafting.sendProgressBarUpdate(this, 2, (int)tileEntity.power.getEnergyStored());
        par1ICrafting.sendProgressBarUpdate(this, 3, tileEntity.direction.ordinal());

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
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);

		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();

			if (slot < 28)
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

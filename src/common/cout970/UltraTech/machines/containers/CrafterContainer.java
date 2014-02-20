package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;
import common.cout970.UltraTech.misc.SlotPhantom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CrafterContainer extends Container{

	
	
	private CrafterEntity tileEntity;

	public CrafterContainer(InventoryPlayer inventoryPlayer, CrafterEntity tileEntity2){
		super();
		tileEntity = tileEntity2;
		int c = 0;
		for (int j = 0; j < 3; j++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new SlotPhantom(tileEntity2.craft, c, 13 + x * 18, 16 + j * 18, true));
				c++;
			}
		}	
		c = 0;
		for (int x = 0; x < 3; x++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntity2, c, 111 + x * 18, 16 + j * 18));
				c++;
			}
		}
		c = 1;
		for (int x = 0; x < 2; x++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new SlotPhantom(tileEntity2, -c, 71 + x * 18, 16 + j * 18, false));
				c++;
			}
		}
		bindPlayerInventory(inventoryPlayer);
		tileEntity2.onInventoryChanged();
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
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		if(slotIndex < 0)return null;
		return transfer(entityPlayer, slotIndex, 9);
	}

	public ItemStack transfer(EntityPlayer player, int slot,int inv) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);

		if (current != null && current.getHasStack())
		{
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();

			if(slot < 9+inv){
				if(!mergeItemStack(itemstack, 9+inv, 36+inv, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);

			}else{

				if (slot >= inv && slot < 27+9+inv)
				{
					if (!this.mergeItemStack(itemstack, 9, inv+9, false))
					{
						return null;
					}
				}
				else if (slot >= 27+inv+9 && slot < 36+inv+9){
					if(!this.mergeItemStack(itemstack, 9, inv+9, false))
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

	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        for(int g=0; g < 9; g++){
        if(tileEntity.found.containsKey(g))par1ICrafting.sendProgressBarUpdate(this, g, (tileEntity.found.get(g))? 1 : 0);
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
	
}

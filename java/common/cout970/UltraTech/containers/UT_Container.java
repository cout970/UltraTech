package common.cout970.UltraTech.containers;

import api.cout970.UltraTech.Vpower.Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class UT_Container extends Container{

	public Machine tileEntity;
	
	public UT_Container(InventoryPlayer inventoryPlayer,TileEntity t){
		super();
		if(t instanceof Machine)tileEntity = (Machine) t;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

	public void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
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
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			if(tileEntity != null)tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		if(tileEntity != null)tileEntity.getGUINetworkData(i, j);
	}
	
	public ItemStack transfer(EntityPlayer player, int slot,int inv) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		
		if (current != null && current.getHasStack()){
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot < inv){//in the machine slots
				if(!mergeItemStack(itemstack, inv, 36+inv, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{//in the inventoryplayer slots
				if (slot >= inv && slot < 27+inv){
                    if (!this.mergeItemStack(itemstack, 0, inv, false)){
                        return null;
                    }
                }else if (slot >= 27+inv && slot < 36+inv){//in the player tiem bar
                	if(!this.mergeItemStack(itemstack, 0, inv, false)){
                		return null;
                	}
                }
				current.onSlotChanged();
			}
			if (itemstack.stackSize == 0){
				current.putStack((ItemStack)null);
			}
			if (itemstack.stackSize == aux.stackSize){
				return null;
			}
			current.onPickupFromSlot(player, itemstack);
		}
		return null;
	}
}

package common.cout970.UltraTech.containers;

import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.misc.SlotPhantom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CrafterContainer extends UT_Container{
	
	private CrafterEntity tileEntity;

	public CrafterContainer(InventoryPlayer inventoryPlayer, CrafterEntity tileEntity2){
		super(inventoryPlayer, tileEntity2);
		tileEntity = tileEntity2;
		int c = 0;
		for (int j = 0; j < 3; j++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new SlotPhantom(tileEntity2.craft, c, 13 + x * 18, 26 + j * 18));
				c++;
			}
		}	
		c = 0;
		addSlotToContainer(new SlotPhantom(tileEntity2, -1, 80, 44));
		for (int x = 0; x < 3; x++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntity2, c, 111 + x * 18, 26 + j * 18));
				c++;
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new SlotPhantom(tileEntity.saves, i, 8 + i * 18, 6));
		}
		bindPlayerInventory(inventoryPlayer);
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
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slot) {
		ItemStack aux = null;
		Slot current = (Slot)this.inventorySlots.get(slot);
		int inv = 19;
		if (current != null && current.getHasStack()){
			ItemStack itemstack = current.getStack();
			aux = itemstack.copy();
			
			if(slot < inv){//in the machine slots
				if(!mergeItemStack(itemstack, inv+9, 36+inv, false)){
					return null;
				}
				current.onSlotChange(itemstack, aux);
			}else{//in the inventoryplayer slots
				if (slot >= inv && slot < 27+inv){
                    if (!this.mergeItemStack(itemstack, inv-9, inv, false)){
                        return null;
                    }
                }else if (slot >= 27+inv && slot < 36+inv+9){//in the player tiem bar
                	if(!this.mergeItemStack(itemstack, inv-9, inv, false)){
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
			current.onPickupFromSlot(entityPlayer, itemstack);
		}
		return null;
	}
	
}

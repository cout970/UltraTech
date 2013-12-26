package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.machines.tileEntities.MolecularAssemblyEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class MAssemblyContainer extends Container{

//	private MolecularAssemblyEntity tileEntity;

	public MAssemblyContainer(InventoryPlayer inventoryPlayer, MolecularAssemblyEntity tileEntity2){
		super();
		//		tileEntity = tileEntity2;
		int i=0;
		for(int y = 0; y < 3 ;y++){
			for(int x = 0; x < 3 ;x++){
				addSlotToContainer(new Slot((IInventory)tileEntity2, i, x*18+16, y*18+17));
				i++;
			}
		}
		for(int y = 0; y < 3 ;y++){
			for(int x = 0; x < 3 ;x++){
				addSlotToContainer(new Slot((IInventory)tileEntity2, i, x*18+107, y*18+17));
				i++;
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
	
//	public void addCraftingToCrafters(ICrafting par1ICrafting)
//    {
//        super.addCraftingToCrafters(par1ICrafting);
//        par1ICrafting.sendProgressBarUpdate(this, 1, tileEntity.Energy);
//    }
//	
//	@Override
//	public void detectAndSendChanges() {
//		super.detectAndSendChanges();
//
//		for (int i = 0; i < crafters.size(); i++) {
//			tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
//		}
//	}
//
//	@Override
//	public void updateProgressBar(int i, int j) {
//		tileEntity.getGUINetworkData(i, j);
//	}
	
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject =(Slot) inventorySlots.get(slot);
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();
			if (slot < 9) {
				 if (!this.mergeItemStack(stackInSlot, 0, 35, true)) {
					 return null;
				 	}
			 	}
				 else if(!this.mergeItemStack(stackInSlot, 0, 9, false)) {
					 return null;
				 }
				 
				 if(stackInSlot.stackSize == 0){
					 slotObject.putStack(null);
				 }else{
					 slotObject.onSlotChanged();
				 }
				 
				 if(stackInSlot.stackSize == stack.stackSize) {
					 return null;			 
				 }
			 slotObject.onPickupFromSlot(player, stackInSlot);
		 }
		 return stack;
	 }

	
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
}

package common.cout970.UltraTech.machines.containers;



import common.cout970.UltraTech.machines.tileEntities.CVDentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class CVDcontainer extends Container{

	public CVDentity tileEntity;
	
	public CVDcontainer(InventoryPlayer inventoryPlayer, CVDentity tileEntity2){
		super();
		tileEntity = tileEntity2;
		bindPlayerInventory(inventoryPlayer);
		addSlotToContainer(new Slot(tileEntity, 0, 53, 33));
		addSlotToContainer(new Slot(tileEntity, 1, 89, 33));
		addSlotToContainer(new Slot(tileEntity, 2, 143, 33){
			@Override
			public boolean isItemValid(ItemStack a){
				return false;
			}
		});
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
        par1ICrafting.sendProgressBarUpdate(this, 1, tileEntity.Energy);
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
}

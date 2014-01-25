package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.machines.containers.PresuricerContaner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class PresuricerEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	public int progres;
	public int speed = 10;

	public PresuricerEntity(){
		super();
		inventory = new ItemStack[4];
	}
	
	public void updateEntity(){
		if(!worldObj.isRemote){
			progres+=speed;
			if(progres > 1000){
				progres = 0;
			}
		}
	}
	
	////////////////Inventory
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {
            if (itemStack.stackSize <= amount) {
                setInventorySlotContents(slot, null);
            }
            else {
                itemStack = itemStack.splitStack(amount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slot, null);
                }
            }
        }
	return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
            ItemStack itemStack = inventory[slot];
            inventory[slot] = null;
            return itemStack;
        }
        else
            return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }

        this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Presuricer";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 3 ? false : true;
	}

	//Sync
	
	public void sendGUINetworkData(PresuricerContaner container,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(container, 0, progres);
		iCrafting.sendProgressBarUpdate(container, 1, Energy);
		iCrafting.sendProgressBarUpdate(container, 2, speed);
	}

	public void getGUINetworkData(int id, int value) {
		switch(id){
		case 0:{
			progres = value;
			break;
		}
		case 1:{
			Energy = value;
			break;
		}
		case 2:{
			speed = value;
			break;
		}
		}
	}
}

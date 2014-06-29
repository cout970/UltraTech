package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.lib.CostData;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MachineWithInventory extends Machine implements IInventory{

	public ItemStack[] inventory;
	public String name;
	
	public MachineWithInventory(int slots,String n,double cap,int tier,MachineTipe t){
		super(cap, tier, t);
		inventory = new ItemStack[slots];
		name = n;
	}
	
	public MachineWithInventory(int slots,String n,double cap,int tier) {
		super(cap, tier);
		inventory = new ItemStack[slots];
		name = n;
	}

	public MachineWithInventory(int slots, String n, CostData a) {
		super(a);
		inventory = new ItemStack[slots];
		name = n;
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inventory[var1];
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
	public ItemStack getStackInSlotOnClosing(int var1) {
		return inventory[var1];
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inventory[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		
		Sync();
	}

	@Override
	public String getInventoryName() {
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory", 10);
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList list = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(nbt);
				list.appendTag(nbt);
			}
		}
		nbtTagCompound.setTag("Inventory", list);
	}

}

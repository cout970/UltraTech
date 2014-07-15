package common.cout970.UltraTech.TileEntities.utility;

import java.util.HashMap;

import common.cout970.UltraTech.network.SyncTile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class ObaltiChest extends SyncTile implements IInventory{

	public HashMap<Integer,ItemStack[]> dim = new HashMap<Integer, ItemStack[]>();
	public int freq = 0;
	private ItemStack[] inv;
	
	public ObaltiChest(){
		setFreq(0);
	}

	public void setFreq(int f){
		saveInv(freq);
		freq = f;
		if(dim.containsKey(f)){
			if(dim.get(f) == null){
				saveInv(f);
			}else{
				loadInv(f);
			}
		}else{
			saveInv(f);
		}
	}
	
	private void loadInv(int f) {

		
	}

	private void saveInv(int freq2) {
		if(inv == null)inv = new ItemStack[3*9];
		
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return inv[var1];
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
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		inv[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		sendNetworkUpdate();
	}

	@Override
	public String getInventoryName() {
		return "Obalti Chest";
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
	public void openInventory() {		
	}

	@Override
	public void closeInventory() {		
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory", 10);
		inv = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inv.length) {
				inv[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList list = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inv.length; ++currentIndex) {
			if (inv[currentIndex] != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("Slot", (byte) currentIndex);
				inv[currentIndex].writeToNBT(nbt);
				list.appendTag(nbt);
			}
		}
		nbtTagCompound.setTag("Inventory", list);
	}
}

package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.energy.api.IItemEnergyEstorage;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ChargeStationEntity extends Machine implements IInventory{

	public ItemStack[] inventory;
	
	public ChargeStationEntity(){
		super();
		tipe = MachineTipe.Storage;
		inventory = new ItemStack[6];
	}
	
	public void updateEntity(){
		super.updateEntity();
		for(int u = 0; u < 3;u++){
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IItemEnergyEstorage){
					IItemEnergyEstorage b = ((IItemEnergyEstorage)inventory[u].getItem());
					if(this.getEnergy() > EnergyCosts.ChargeStationFlow){
						if(b.getEnergy(inventory[u])+EnergyCosts.ChargeStationFlow <= b.getMaxEnergy()){
							b.addEnergy(inventory[u], EnergyCosts.ChargeStationFlow);
							this.removeEnergy(EnergyCosts.ChargeStationFlow);
						}else if(b.getEnergy(inventory[u])+1 <= b.getMaxEnergy()){
							b.addEnergy(inventory[u], 1);
							this.removeEnergy(1);
						}
					}
				}
			}
		}
		
		for(int u = 3; u < 6;u++){
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IItemEnergyEstorage){
					IItemEnergyEstorage b = ((IItemEnergyEstorage)inventory[u].getItem());
					if(maxEnergy() - getEnergy() > EnergyCosts.ChargeStationFlow){
						if(b.getEnergy(inventory[u]) > EnergyCosts.ChargeStationFlow){
							addEnergy(EnergyCosts.ChargeStationFlow);
							b.removeEnergy(inventory[u],EnergyCosts.ChargeStationFlow);
						}else if(b.getEnergy(inventory[u]) > 1){
							addEnergy(1);
							b.removeEnergy(inventory[u],1);
						}
					}
				}
			}
		}
	}
	
	//Inventory
	
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
		return "Charge station";
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
		return i == 1 ? false : true;
	}

	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Inventory", tagList);


	}

}

package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CutterEntity extends Machine implements IInventory,ISpeedUpgradeabel{

	private ItemStack[] inventory;
	public int progres = 0;
	public int speed = 10;
	public int speedUpgrades = 0;
	public boolean hasEnergy = false;

	public CutterEntity(){
		super();
		inventory = new ItemStack[2];
		this.tier = MachineTier.Tier2;
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){
			boolean flag = false;

			if(!hasEnergy){
				hasEnergy = getEnergy() >= EnergyCosts.CutterCost;
			}

			if(progres > 0){
				removeEnergy(EnergyCosts.CutterCost*speed/1000);
			}
			if (hasEnergy && Cuter_Recipes.matches(this))
			{
				this.progres += speed;
				if (this.progres >= 1000)
				{
					this.progres = 0;
					this.craft();
					flag = true;
					hasEnergy = false;
				}
			}
			else
			{
				this.progres = 0;
			}
			if (flag)
			{
				this.onInventoryChanged();		
			}
		}
	}

	private void craft() {
		if(Cuter_Recipes.matches(this)){
			ItemStack itemstack = Cuter_Recipes.getCraftingResult(this);

			if (this.inventory[1] == null)
			{
				this.inventory[1] = itemstack.copy();
			}
			else if (this.inventory[1].isItemEqual(itemstack))
			{
				inventory[1].stackSize += itemstack.stackSize;
			}
			--this.inventory[0].stackSize;
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
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
		return "Cutter";
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
		return true;
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
		 NBTTagList tagList2 = nbtTagCompound.getTagList("Upgrades");
			NBTTagCompound tagCompound2 = (NBTTagCompound) tagList2.tagAt(0);
			speed = tagCompound2.getInteger("Speed");
			NBTTagCompound tagCompound3 = (NBTTagCompound) tagList2.tagAt(1);
			speedUpgrades = tagCompound3.getInteger("Speedupgrades");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("speed", speed);
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
		
		NBTTagList tagList2 = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger("Speed", this.speed);
		tagList2.appendTag(tagCompound);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("Speedupgrades", this.speedUpgrades);
		tagList2.appendTag(tagCompound2);
		nbtTagCompound.setTag("Upgrades", tagList2);
	}

	//Synchronization
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
    	super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, progres);
    	c.sendProgressBarUpdate(cont, 3, speed);
    }
    
    public void getGUINetworkData(int id, int value) {
    	super.getGUINetworkData(id, value);
    	if(id == 2)progres = value;
    	if(id == 3)speed = value;
	}

    //Upgrades
    
	@Override
	public boolean upgrade() {
		
		if(this.speed < 100){	
			this.speed += 20;
			speedUpgrades +=1;
			return true;
		}
		return false;
	}


	@Override
	public ItemStack getDrop() {
		if(speedUpgrades !=0){
			return new ItemStack(ItemManager.ItemName.get("SpeedUpgrade"),speedUpgrades);
		}
		return null;
	}
	
}

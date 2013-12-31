package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CVDentity extends Machine implements IInventory,ISpeedUpgradeabel{

	private ItemStack[] inventory;
	public int progres = 0;
	public int speed = 10;
	private int speedUpgrades;
	public static final int INVENTORY_SIZE = 3;

	public CVDentity(){
		super();		
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	@Override
	public void updateEntity(){

		boolean flag = progres > 0;
		boolean flag1 = this.getEnergy() > 0;
		boolean flag2=false;

		if(flag){
			this.loseEnergy(speed/5);//spend energy when ir runing
		}
		if(!this.worldObj.isRemote){
			if(flag1 && CVD_Recipe.matches(this)){

				progres+=speed;
				if(progres >= 1000){
					progres = 0;
					craft();
					flag2 = true;
				}
			}else{
				progres=0;
			}
		}
		if(flag2){
			onInventoryChanged();
		}
	}


	private void craft() {
		if(CVD_Recipe.matches(this)){
			ItemStack itemstack = CVD_Recipe.getCraftingResult(this);

			if (this.inventory[2] == null)
			{
				this.inventory[2] = itemstack.copy();
			}
			else if (this.inventory[2].isItemEqual(itemstack))
			{
				inventory[2].stackSize += itemstack.stackSize;
			}
			--this.inventory[0].stackSize;
			--this.inventory[1].stackSize;
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
			if (this.inventory[1].stackSize <= 0)
			{
				this.inventory[1] = null;
			}
		}
	}

	public SyncObject getSinc(){
		SyncObject a = new SyncObject();
		a.setVar1(progres*24/1000);
		a.setVar2(Energy);
		return a;
	}

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
		return "CVD";
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
		return i ==2 ? false : true;
	}
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

	@Override
	public boolean upgrade() {
		
		if(this.speed < 100){	
			this.speed += 20;
			speedUpgrades +=1;
			return true;
		}
		return false;
	}
	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
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
    
    @Override
	public ItemStack getDrop() {
		if(speedUpgrades !=0){
			return new ItemStack(UltraTech.ItemName.get("SpeedUpgrade"),speedUpgrades);
		}
		return null;
	}

}

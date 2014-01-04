package common.cout970.UltraTech.machines.tileEntities;


import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class GeneratorEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	private int size = 1;
	public int progres = 0;
	public Machine[] machines;
	public int speed = 64;
	private int maxpro = 800;

	public GeneratorEntity(){
		super();
		inventory = new ItemStack[size];
		this.EnergyMax = 2500;
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
		return "Generator";
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
		if(itemstack != null && itemstack.itemID == Item.coal.itemID)return true;
		return false;
	}
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		progres = nbtTagCompound.getInteger("progres");
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
		nbtTagCompound.setInteger("progres", progres);
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

	@Override
	public void updateEntity(){
		if(machines == null){
			machines = new Machine[6];
			check();
		}
		refill();
		if(inventory[0] != null){
			boolean flag = inventory[0].itemID == Item.coal.itemID;
			if(flag && progres <= 0 && this.Energy < EnergyMax-64){
				progres = 600;
				--inventory[0].stackSize;
				if(inventory[0].stackSize <= 0){
					inventory[0] = null;
				}
			}
		}
		if(progres > 0){
			if(this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 0){
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 0);
				this.worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			}
		}else{
			if(this.worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1){
				this.worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 0);
				this.worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
			}
		}
		if(progres > 0){
			progres--;
			this.gainEnergy(3);
		}
	}

	public void refill(){
		for(Machine a : machines){
			if(a != null){
				if(a instanceof GeneratorEntity || a instanceof SolarPanelEntity || a instanceof SateliteEntity || a instanceof ReciverEntity){

				}else{
					if(a.Energy+speed <= a.EnergyMax && this.Energy >= speed){
						float e = a.gainEnergy(speed);
						this.Energy -= speed-e;
					}
				}
			}
		}
	}

	public void check(){
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);

		int i= 0;
		for(TileEntity y : t){
			if(y instanceof Machine){
				machines[i] = (Machine) y;
			}else{
				machines[i] = null;
			}
			i++;
		}
	}
	
	public SyncObject getSync(){
		SyncObject a = new SyncObject();
		a.setVar1(progres*12/maxpro);
		a.setVar2(Energy);
		return a;
	}
	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
    	iCrafting.sendProgressBarUpdate(container, 1, Energy);
    }
    
    public void getGUINetworkData(int id, int value) {
    	switch(id){
    	case 1:{
    		Energy = value;
    		break;
    		}
    	case 2:{
    		progres = value;
    		break;
    		}
    	}
	}
}

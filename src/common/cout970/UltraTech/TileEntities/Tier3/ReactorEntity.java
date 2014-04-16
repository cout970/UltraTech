package common.cout970.UltraTech.TileEntities.Tier3;


import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;


public class ReactorEntity extends TileEntity implements IInventory,IReactorPart{

	public Machine[] machines;
	public static final int INVENTORY_SIZE = 6;
	private ItemStack[] inventory;
	public float heat = 25;
	public int maxHeat = 1200;
	public int water;
	public int steam;
	public int MaxSteam = 64000;
	public int MaxWater;
	public List<ReactorTankEntity> tanks;
	public List<ReactorControllerEntity> control;
	public boolean work = true;


	public ReactorEntity(){
		super();
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	@Override
	public void updateEntity(){
		if(Reactor == null){
			onNeighChange();
		}
		
		if(!this.worldObj.isRemote){
			if(heat > 25)heat -= heat/maxHeat;
			if(heat < 100)steam--;
			if(control == null)work = getControl();
			if(work){
				float e = 0.25f;
				int c = 100;
				int id = ItemManager.ItemName.get("RadioniteCell").itemID;
				afect(0,e,c,id);
				afect(1,e,c,id);
				afect(2,e,c,id);
				afect(3,e,c,id);
				afect(4,e,c,id);
				afect(5,e,c,id);
			}
			if(tanks == null)tanks = getTanks();

			MaxWater = 0;
			water = 0;
			for(ReactorTankEntity t : tanks){
				if(t.getFluidAmount() == 0){
					MaxWater += t.getCapacity();
				}else if(t.getFluid().getFluid().getBlockID() == Block.waterStill.blockID){
					MaxWater += t.getCapacity();
					water += t.getFluidAmount();
				}
			}
			if(steam < MaxSteam)vaporice();

			if(heat > 1200){
				this.blownUp();
			}
		}
	}

	private boolean getControl() {
		control = new ArrayList<ReactorControllerEntity>();
		for(TileEntity te : UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord)){
			if(te instanceof ReactorControllerEntity){
				control.add((ReactorControllerEntity) te);
			}
		}
		for(ReactorControllerEntity c : control){
			if(!c.on)return false;
		}
		return true;
	}

	public boolean vaporice(){
		if(heat > 101)
			for(ReactorTankEntity t : tanks){
				if(t.getFluidAmount() > 0){
					t.drain((int) (EnergyCosts.ReactorWaterCost*heat/maxHeat), true);
					this.steam += (EnergyCosts.ReactorSteamProduct*heat/maxHeat);//20steam/tick
					heat -= (heat/maxHeat);
					return true;
				}
			}
		return false;
	}

	public int getSteamAmount(){
		return steam;
	}

	private List<ReactorTankEntity> getTanks() {
		List<ReactorTankEntity> tanks = new ArrayList<ReactorTankEntity>();
		for(TileEntity te : UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord)){
			if(te instanceof ReactorTankEntity){
				tanks.add((ReactorTankEntity) te);
			}
		}
		return tanks;
	}

	private void afect(int i, float e, int c, int id) {
		if(inventory[i] != null && inventory[i].itemID == id){
			heat+=e;
			if(worldObj.getTotalWorldTime()%c == 0){
				if(inventory[i].getItemDamage() > 500){
					inventory[i] = null;
				}else{
					inventory[i].setItemDamage(inventory[i].getItemDamage()+1);
				}
			}
		}
	}

	private void blownUp(){
		float f = 20.0f;
		this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, f, true);
		this.worldObj.createExplosion(null, this.xCoord, this.yCoord+1, this.zCoord, f, true);
		this.worldObj.createExplosion(null, this.xCoord, this.yCoord-1, this.zCoord, f, true);
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
		return "Ultra Tech Reactor";
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
		return false;
	}

	//Save & Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		setStructure(nbtTagCompound.getBoolean("multi"));
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}

		NBTTagList tagList2 = nbtTagCompound.getTagList("Properties");
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList2.tagAt(0);
		heat = tagCompound2.getFloat("Heat");
		NBTTagCompound tagCompound3 = (NBTTagCompound) tagList2.tagAt(1);
		water = tagCompound3.getInteger("Water");
		steam = nbtTagCompound.getInteger("Steam");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("multi", isStructure());
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
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setFloat("Heat", heat);
		tagList2.appendTag(tagCompound2);
		NBTTagCompound tagCompound3 = new NBTTagCompound();
		tagCompound3.setInteger("Water", water);
		tagList2.appendTag(tagCompound3);
		nbtTagCompound.setTag("Properties", tagList2);
		nbtTagCompound.setInteger("Steam", steam);
	}



	//Synchronization

	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(container, 0, (int)heat);
		iCrafting.sendProgressBarUpdate(container, 1, steam);
		iCrafting.sendProgressBarUpdate(container, 2, water);
		iCrafting.sendProgressBarUpdate(container, 3, MaxWater);
		iCrafting.sendProgressBarUpdate(container, 4, work ? 1 : 0);
	}

	public void getGUINetworkData(int id, int value) {
		switch(id){
		case 0:{
			heat = value;
			break;
		}
		case 1:{
			steam = value;
			break;
		}
		case 2:{
			water = value;
			break;
		}
		case 3:{
			MaxWater = value;
			break;
		}
		case 4:{
			if(value == 0)work = false;else work = true;
			break;
		}
		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}

	//Reactor Part


	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;


	@Override
	public void SearchReactor() {
		Reactor = this;
		found = true;
	}

	@Override
	public ReactorEntity getReactor() {
		return Reactor;
	}

	@Override
	public void onNeighChange() {
		tanks = null;
		SearchReactor();
		if(found){
			checkStructure();
			if(Structure){
				activateBlocks();
			}else{
				desactivateBlocks();
			}
		}		
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k);
					current++;
				}
			}
		}
		this.Structure = false;

		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		this.Structure = true;
	}

	@Override
	public void activateBlocks(){
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k) instanceof IReactorPart){
						((IReactorPart)worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k)).setStructure(true);
						((IReactorPart)worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k)).setReactor(this);
						worldObj.markBlockForRenderUpdate(xCoord+i, yCoord+j, zCoord+k);
					}
				}
			}
		}
	}

	@Override
	public void desactivateBlocks() {
		if(Structure){
			Structure = false;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k)).setStructure(false);
							((IReactorPart)worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k)).setReactor(null);
							UT_Utils.sendPacket(worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k));
							worldObj.markBlockForRenderUpdate(xCoord+i, yCoord+j, zCoord+k);
						}
					}
				}
			}
		}
	}

	@Override
	public void setStructure(boolean structure) {
		Structure = structure;
	}

	@Override
	public void setReactor(ReactorEntity e) {
		Reactor = e;
	}

	@Override
	public boolean isStructure() {
		return Structure;
	}

}

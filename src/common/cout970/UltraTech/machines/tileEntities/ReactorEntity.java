package common.cout970.UltraTech.machines.tileEntities;


import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.managers.BlockManager;
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
import net.minecraft.tileentity.TileEntity;


public class ReactorEntity extends TileEntity implements IInventory,IReactorPart{
	
	
	public boolean check = true;
	public Machine[] machines;
	public int speed = 64;
	public static final int INVENTORY_SIZE = 6;
	private ItemStack[] inventory;
	private int time=0;
	public float heat;
	public int maxHeat = 1000;
	public int water;
	public int steam;
	public int MaxSteam = 64000;
	public int MaxWater;
	public List<ReactorTankEntity> tanks;
	public boolean work = true;

	
	public ReactorEntity(){
		super();
		inventory = new ItemStack[INVENTORY_SIZE];
	}
	
	@Override
	public void updateEntity(){
		if(check){
			check = false;
			this.checkStructure();
		}
		//produce steam
		if(!this.worldObj.isRemote){
			if(heat > 0)heat-=0.01;
			if(work){
				int e = 6;
				int c = 100;
				this.time++;
				int id = ItemManager.ItemName.get("RadioniteCell").itemID;
				if(inventory[0] != null && inventory[0].itemID == id)afect(0,e,c);
				if(inventory[1] != null && inventory[1].itemID == id)afect(1,e,c);
				if(inventory[2] != null && inventory[2].itemID == id)afect(2,e,c);
				if(inventory[3] != null && inventory[3].itemID == id)afect(3,e,c);
				if(inventory[4] != null && inventory[4].itemID == id)afect(4,e,c);
				if(inventory[5] != null && inventory[5].itemID == id)afect(5,e,c);
				if(time >= c){
					time = 0;
				}
			}
			if(tanks == null)tanks = this.getTanks();

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

			if(heat > 1000){
				this.blownUp();
			}
		}
	}
	
	public boolean vaporice(){
		if(heat > 501)
		for(ReactorTankEntity t : tanks){
			if(t.getFluidAmount() != 0){
			t.drain(10, true);
			this.steam += 300;
			heat -= 0.5;
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
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		for(TileEntity te : t){
			if(te instanceof ReactorTankEntity){
				tanks.add((ReactorTankEntity) te);
			}
		}
		return tanks;
	}

	private void afect(int i, int e, int c) {
		heat+=0.05;
		if(time >= c){
			if(inventory[i].getItemDamage() > 1000){
				inventory[i] = null;
			}else{
				inventory[i].setItemDamage(inventory[i].getItemDamage()+1);
			}
		}
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
	}

		private void blownUp(){
			float f = 20.0f;
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, f, true);
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord+1, this.zCoord, f, true);
			this.worldObj.createExplosion(null, this.xCoord, this.yCoord-1, this.zCoord, f, true);
		}

		public void sendGUINetworkData(Container container, ICrafting iCrafting) {
	    	iCrafting.sendProgressBarUpdate(container, 0, (int)heat);
	    	iCrafting.sendProgressBarUpdate(container, 2, steam);
	    	iCrafting.sendProgressBarUpdate(container, 3, water);
	    	iCrafting.sendProgressBarUpdate(container, 4, MaxWater);
	    }
	    
	    public void getGUINetworkData(int id, int value) {
	    	switch(id){
	    	case 0:{
	    		heat = value;
	    		break;
	    	}
	    	case 2:{
	    		steam = value;
	    		break;
	    	}
	    	case 3:{
	    		water = value;
	    		break;
	    	}
	    	case 4:{
	    		MaxWater = value;
	    		break;
	    	}
	    	}
		}

	    //Reactor Part
	    

		public boolean found = false;
		public ReactorEntity Reactor;
		public boolean Structure = false;


		public void setUp() {
				SearchReactor();
				if(found){
					checkStructure();
					if(Structure){
						activateBlocks();
					}
				}
		}
		
		@Override
		public void SearchReactor() {
			int[] ids = new int[27];
			int current = 0;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
						if(ids[current] == BlockManager.Reactor.blockID && worldObj.getBlockMetadata(xCoord+i, yCoord+j, zCoord+k) == 0){
							Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
							found = true;
							return;
						}
						current++;
					}
				}
			}
			found = false;
		}

		@Override
		public ReactorEntity getReactor() {
			return this.Reactor;
		}

		@Override
		public void onNeighChange() {
			setUp();		
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
		public void activateBlocks() {
			Reactor.setStructure(true);
			int x,y,z;
			x = Reactor.xCoord;
			y = Reactor.yCoord;
			z = Reactor.zCoord;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(true);
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setReactor(Reactor);
						}
					}
				}
			}
		}

		@Override
		public void desactivateBlocks() {
			if(Structure){
				Structure = false;
				int x,y,z;
				x = Reactor.xCoord;
				y = Reactor.yCoord;
				z = Reactor.zCoord;
				for(int j = -1;j<2;j++){
					for(int i = -1;i<2;i++){
						for(int k = -1;k<2;k++){
							if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
								((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(false);
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
		}
		
		@Override
		public boolean isStructure() {
			return this.Structure;
		}

}

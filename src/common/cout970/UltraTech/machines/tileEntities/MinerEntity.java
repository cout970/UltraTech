package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;

import common.cout970.UltraTech.machines.containers.MinerContainer;
import common.cout970.UltraTech.misc.Mining;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MinerEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	private int size = 13 * 4;;
	private int proces = 0;
	public int speed = 10;
	boolean flag = false ;
	public int current = 0;
	private ArrayList<Mining> mining;
	public boolean hasMine = false;
	public int height = 2;
	public int widht = 2;
	public boolean working = true;
	private ItemStack waiting;
	public Mode mode = Mode.Horizontal;
	public IInventory[] machines;
	
	public boolean eject = false;
	public boolean hasSpeedUpgrades;
	public boolean hasRangeUpgrades;
	public boolean hasFortuneUpgrades;
	public int speedUpgrades=0;
	public int rangeUpgrades=0;
	public int fortuneUpgrades=0;
	
	
 	
	public MinerEntity(){
		super();
		this.EnergyMax = 50000;
		inventory = new ItemStack[size];
	}
	
	public enum Mode{
		Vertical,Horizontal,two
	}
	
	public boolean addItemStack(ItemStack i){
		for(int s = 0;s < this.getSizeInventory();s++){	
			if (this.inventory[s] == null)
			{
				this.inventory[s] = i.copy();
				return true;
			}
			else if (this.inventory[s].isItemEqual(i))
			{
				if(inventory[s].stackSize + i.stackSize <= getInventoryStackLimit()){
				inventory[s].stackSize += i.stackSize;
				return true;
				}
			}

		}
		waiting = i;
		return false;
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
		return "Miner";
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
		NBTTagCompound tagCompound1 = (NBTTagCompound) tagList2.tagAt(0);
		height = tagCompound1.getInteger("tam");
		widht = height;
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList2.tagAt(1);
		speed = tagCompound2.getInteger("speed");
		NBTTagCompound tagCompound3 = (NBTTagCompound) tagList2.tagAt(2);
		eject = tagCompound3.getBoolean("eject");
		NBTTagCompound tagCompound4 = (NBTTagCompound) tagList2.tagAt(3);
		switch(tagCompound4.getInteger("mode")){
		case 0:{
			mode = Mode.Horizontal;
			break;
		}
		case 1:{
			mode = Mode.Vertical;
			break;
		}
		case 2:{
			mode = Mode.two;
			break;
		}		
		}
		NBTTagCompound tagCompound5 = (NBTTagCompound) tagList2.tagAt(4);
		rangeUpgrades = tagCompound5.getInteger("rangeUpgrades");

		NBTTagCompound tagCompound6 = (NBTTagCompound) tagList2.tagAt(5);
		speedUpgrades = tagCompound6.getInteger("speedUpgrades");
		
		NBTTagCompound tagCompound7 = (NBTTagCompound) tagList2.tagAt(6);
		fortuneUpgrades = tagCompound7.getInteger("fortuneUpgrades");
		
		if(speedUpgrades > 0)hasSpeedUpgrades = true;
		if(rangeUpgrades > 0)hasRangeUpgrades = true;
		if(fortuneUpgrades > 0)hasFortuneUpgrades = true;
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
		
		NBTTagList tagList2 = new NBTTagList();
		NBTTagCompound tagCompound1 = new NBTTagCompound();
		tagCompound1.setInteger("tam", height);
		tagList2.appendTag(tagCompound1);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("speed", speed);
		tagList2.appendTag(tagCompound2);
		NBTTagCompound tagCompound3 = new NBTTagCompound();
		tagCompound3.setBoolean("eject", eject);
		tagList2.appendTag(tagCompound3);
		NBTTagCompound tagCompound4 = new NBTTagCompound();
		switch(mode){
		case Horizontal:{
			tagCompound4.setInteger("mode", 0);
			break;
		}
		case Vertical:{
			tagCompound4.setInteger("mode", 1);
			break;
		}
		case two:{
			tagCompound4.setInteger("mode", 2);
			break;
		}		
		}
		tagList2.appendTag(tagCompound4);
		
		NBTTagCompound tagCompound5 = new NBTTagCompound();
		tagCompound5.setInteger("rangeUpgrades", rangeUpgrades);
		tagList2.appendTag(tagCompound5);
		NBTTagCompound tagCompound6 = new NBTTagCompound();
		tagCompound6.setInteger("speedUpgrades", speedUpgrades);
		tagList2.appendTag(tagCompound6);
		NBTTagCompound tagCompound7 = new NBTTagCompound();
		tagCompound7.setInteger("fortuneUpgrades", fortuneUpgrades);
		tagList2.appendTag(tagCompound7);
		nbtTagCompound.setTag("Upgrades", tagList2);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	//                                WORK
	

	@Override
	public void updateEntity(){
		
		if(machines == null){
			check();
		}
		
		if(eject && !this.worldObj.isRemote && !isEmpty()){
			refill();
		}
		
		if(working){
			if(!hasMine){
				CreateMining();
				hasMine = true;
			}
			if(!flag){
				flag = this.Energy >= 100;
			}
			boolean flag1 = false;
			boolean workdo = current >= mining.size(); 
			if(flag && !workdo){
				proces += speed;
				if(proces >= 100){
					proces = 0;
					flag1 = true;
					flag = false;
					BreakNextBlock();
				}
			}

			if (flag1)
			{
				this.onInventoryChanged();
			}
		}else{
			working = addItemStack(waiting);
		}
	}
	
	private boolean isEmpty() {
		for(int d = getSizeInventory()-1; d >= 0; d--){
			if(this.getStackInSlot(d) != null)return false;
		}
		return true;
	}

	private ItemStack[] getItemStackFromId(World par1World, int x, int y, int z, int metadata, int fortune){
		ArrayList<ItemStack> items = Block.blocksList[this.worldObj.getBlockId(x, y, z)].getBlockDropped(par1World, x, y, z, metadata, fortune);
		ItemStack[] retu = new ItemStack[items.size()]; 
		int i = 0;
		for (ItemStack item : items)
		{
			retu[i] = item;
			i++;
		}
		return retu;
	}

	private void BreakNextBlock() {
		if(mining.get(current) != null){
			this.loseEnergy(500);
			int x = mining.get(current).x;
			int y = mining.get(current).y;
			int z = mining.get(current).z;
			if(this.worldObj.getBlockId(x, y, z) > 0 && (this.worldObj.getBlockId(x, y, z) < 7 || this.worldObj.getBlockId(x, y, z) > 11)){
				ItemStack[] a = getItemStackFromId(worldObj, x, y, z, worldObj.getBlockMetadata(x, y, z), this.fortuneUpgrades);
				addItemStackArray(a);
				this.worldObj.setBlockToAir(x, y, z);//less lag
			}
		}
		current++;
	}
	
	private void addItemStackArray(ItemStack[] a) {
		for(int n = 0; n < a.length;n++){
			working = addItemStack(a[n]);
		}
	}

	private void CreateMining() {
		mining = new ArrayList<Mining>();
		if(mode == Mode.Horizontal){
			for(int j = this.yCoord-1; j >= 1;j--){
				for(int i = -height;i <= height; i++){
					for(int k = -widht;k <= widht; k++){
						if(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k) !=0){
							mining.add(new Mining(this.xCoord + i,j , this.zCoord + k));
						}
					}
				}
			}
		}else if(mode == Mode.Vertical){
			for(int i = -height;i <= height; i++){
				for(int k = -widht;k <= widht; k++){
					for(int j = this.yCoord-1; j >= 1;j--){
						if(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k) !=0){
							mining.add(new Mining(this.xCoord + i,j , this.zCoord + k));
						}
					}
				}
			}
		}else{
			for(int i = -height;i <= height; i++){
				for(int j = this.yCoord-1; j >= 1;j--){
					for(int k = -widht;k <= widht; k++){

						if(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k) !=0){
							mining.add(new Mining(this.xCoord + i,j , this.zCoord + k));
						}
					}
				}
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////upgrade
	
	public void refill(){
		for(IInventory a : machines){
			if(a != null){
				ItemStack b;
				if((b = this.getStack()) != null){
					for(int x = 0; x < a.getSizeInventory() ;x++){
						if(a.isItemValidForSlot(x, b))if(a.getStackInSlot(x) == null){
							a.setInventorySlotContents(x, b);
							return;
						}else{
							if(a.getStackInSlot(x).itemID == b.itemID){
								if((b.stackSize + a.getStackInSlot(x).stackSize) <= a.getInventoryStackLimit()){
									b.stackSize += a.getStackInSlot(x).stackSize;
									a.setInventorySlotContents(x, b);
									return;
								}
							}
						}
					}
					this.addItemStack(b);
				}
			}
		}
	}

	private ItemStack getStack() {
		for(int d = getSizeInventory()-1; d >= 0; d--){
			if(inventory[d] != null){
				ItemStack d1 = inventory[d];
				inventory[d] = null;
				return d1;
			}
		}
		return null;
	}

	public void check(){
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);

		machines = new IInventory[6];
		int i= 0;
		for(TileEntity y : t){
			if(y instanceof IInventory){
				machines[i] = (IInventory) y;
			}else{
				machines[i] = null;
			}
			i++;
		}
	}

	//Sync

	public void sendGUINetworkData(MinerContainer minerContainer,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(minerContainer, 0, widht);
		iCrafting.sendProgressBarUpdate(minerContainer, 1, Energy);
		iCrafting.sendProgressBarUpdate(minerContainer, 2, speedUpgrades);
	}

	public void getGUINetworkData(int id, int value) {
		switch(id){
		case 0:{
			widht = value;
			height = value;
			break;
		}
		case 1:{
			Energy = value;
			break;
		}
		case 2:{
			speedUpgrades = value;
			break;
		}
		}
	}
}

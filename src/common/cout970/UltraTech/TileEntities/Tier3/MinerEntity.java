package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.ArrayList;
import java.util.List;

import buildcraft.api.transport.IPipeTile;
import buildcraft.api.transport.IPipeTile.PipeType;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;
import common.cout970.UltraTech.machines.containers.MinerContainer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

public class MinerEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	private ItemStack waiting;
	private int progres = 0;
	public int speed = 10;
	boolean hasEnergy = false ;
	public int current = 0;
	private ArrayList<int[]> mining;
	public boolean hasMine = false;
	public int height = 4;
	public int widht = 4;
	public boolean blocked = false;
	
	public Mode mode = Mode.Horizontal;
	public List<IInventory> ex_Inv;
	//upgrades
	public boolean eject = false;
	public boolean hasSpeedUpgrades;
	public boolean hasRangeUpgrades;
	public boolean hasFortuneUpgrades;
	public int speedUpgrades=0;
	public int rangeUpgrades=0;
	public int fortuneUpgrades=0;
	private boolean mineFinish;
	private int mineSize;
	
 	
	public MinerEntity(){
		super();
		inventory = new ItemStack[52];
		tier = MachineTier.Tier3;
	}
	

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote)return;
		
		if(ex_Inv == null){
			searchInventories();
		}
		if(eject && !isEmpty()){
			expulse();
		}
		
		if(!blocked){
			if(!hasMine){
				CreateMining();
				hasMine = true;
			}
			if(!hasEnergy){
				hasEnergy = this.getEnergy() >= GraficCost.MinerCost;
			}
			boolean changes = false;
			
			if(hasEnergy && current < mining.size()){
				progres += speed;
				if(progres >= 100){
					progres = 0;
					changes = true;
					hasEnergy = false;
					BreakNextBlock();
				}
			}

			if (changes)
			{
				this.onInventoryChanged();
			}
		}else{
			blocked = !addItemStack(waiting);
		}
	}
	
	private boolean isEmpty() {
		for(int d = getSizeInventory()-1; d >= 0; d--){
			if(this.getStackInSlot(d) != null)return false;
		}
		return true;
	}


	private void BreakNextBlock() {
		for(int r = 0 ; r < 10;r++){
			if(mining.size() > current){
				int x = mining.get(current)[0];
				int y = mining.get(current)[1];
				int z = mining.get(current)[2];
				if(canBreak(worldObj.getBlockId(x, y, z))){
					removeEnergy(GraficCost.MinerCost);
					ArrayList<ItemStack> items = Block.blocksList[worldObj.getBlockId(x, y, z)].getBlockDropped(worldObj, x, y, z, worldObj.getBlockMetadata(x, y, z), fortuneUpgrades);
					for(int n = 0; n < items.size();n++){
						blocked = !addItemStack(items.get(n));
					}
					worldObj.setBlockToAir(x, y, z);
					break;
				}
			}else{
				if(!mineFinish){
					mineFinish = true;
					hasMine = false;
					current = 0;
				}
			}
			current++;
		}
	}

	public boolean canBreak(int id){
		if(id == 0)return false;
		if(id >= 7 && id <= 11)return false;
		if(id == 52)return false;
		if(id == 90)return false;
		if(id == 119)return false;
		if(id == 120)return false;
		if(Block.blocksList[id].blockHardness == -1)return false;
		return true;
	}


	public void CreateMining() {
		mining = new ArrayList<int[]>();
		if(mode == Mode.Horizontal){
			for(int j = this.yCoord-1; j >= 1;j--){
				for(int i = -height;i <= height; i++){
					for(int k = -widht;k <= widht; k++){
						if(canBreak(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}else if(mode == Mode.VerticalY){
			for(int i = -height;i <= height; i++){
				for(int k = -widht;k <= widht; k++){
					for(int j = this.yCoord-1; j >= 1;j--){
						if(canBreak(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}else{
			for(int i = -height;i <= height; i++){
				for(int j = this.yCoord-1; j >= 1;j--){
					for(int k = -widht;k <= widht; k++){
						if(canBreak(worldObj.getBlockId(this.xCoord + i,j , this.zCoord + k))){
							mining.add(new int[]{this.xCoord + i,j , this.zCoord + k});
						}
					}
				}
			}
		}
		mineSize = mining.size();
	}

	public void expulse(){
		ItemStack b;
		if((b = this.getStack()) != null){
			for(IInventory a : ex_Inv){
				for(int x = 0; x < a.getSizeInventory() ;x++){
					if(a.isItemValidForSlot(x, b) && a.getStackInSlot(x) == null){
						a.setInventorySlotContents(x, b);
						return;
					}else{
						if(OreDictionary.itemMatches(a.getStackInSlot(x), b, true)){
							if((b.stackSize + a.getStackInSlot(x).stackSize) <= a.getInventoryStackLimit() && (b.stackSize + a.getStackInSlot(x).stackSize) <= b.getItem().getItemStackLimit(b)){
								b.stackSize += a.getStackInSlot(x).stackSize;
								a.setInventorySlotContents(x, b);
								return;
							}
						}
					}
				}
			}
			if(b != null)for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				TileEntity t = worldObj.getBlockTileEntity(xCoord+d.offsetX, yCoord+d.offsetY, zCoord+d.offsetZ);
				if(t instanceof IPipeTile){
					IPipeTile a = (IPipeTile) t;
					if(a.getPipeType() == PipeType.ITEM){
						a.injectItem(b, true, d.getOpposite());
						return;
					}
				}
			}
			this.addItemStack(b);
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

	public void searchInventories(){
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);

		ex_Inv = new ArrayList<IInventory>();
		for(TileEntity y : t){
			if(y instanceof IInventory)ex_Inv.add((IInventory) y);
		}
	}
	
	public enum Mode{
		VerticalY,Horizontal,VerticalX
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
	
	public void ChangeMode(){
		hasMine = false;
		current = 0;
		switch(mode){
		case Horizontal:{
			mode = Mode.VerticalY;
			break;
		}
		case VerticalY:{
			mode = Mode.VerticalX;
			break;
		}
		case VerticalX:{
			mode = Mode.Horizontal;
			break;
		}
		}
	}
	
	public int MinigSize(){
		if(mineSize <= 0)return 0;
		return mineSize;
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
			mode = Mode.VerticalY;
			break;
		}
		case 2:{
			mode = Mode.VerticalX;
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
		case VerticalY:{
			tagCompound4.setInteger("mode", 1);
			break;
		}
		case VerticalX:{
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

	
	//Synchronization

	public void sendGUINetworkData(MinerContainer minerContainer,
			ICrafting iCrafting) {
		super.sendGUINetworkData(minerContainer, iCrafting);
		iCrafting.sendProgressBarUpdate(minerContainer, 2, widht);
		iCrafting.sendProgressBarUpdate(minerContainer, 3, speed);
		iCrafting.sendProgressBarUpdate(minerContainer, 4, mineSize);
		iCrafting.sendProgressBarUpdate(minerContainer, 5, current);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2){
			widht = value;
			height = value;
		}
		if(id == 3)speed = value;
		if(id == 4)mineSize = value;
		if(id == 5)current = value;
	}
}

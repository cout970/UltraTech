package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class UTfurnaceEntity extends Machine implements IInventory,ISpeedUpgradeabel{

	
	private ItemStack[] inventory;
	public int proces = 0;
	public int speed = 10;
	private int speedUpgrades;
	public static final int INVENTORY_SIZE = 2;
	public boolean flag1 = false;
	
	
	public UTfurnaceEntity(){
		super();		
		inventory = new ItemStack[INVENTORY_SIZE];
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
		return "Ultra Tech Furnace";
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
	  
	  
	///////////////////////////////////////////////////////////////////////////////////////
	//WORK
	  

	  private boolean canSmelt()
	  {
		  if (this.inventory[0] == null)
		  {
			  return false;
		  }
		  else
		  {
			  ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
			  if (itemstack == null) return false;
			  if (this.inventory[1] == null) return true;
			  if (!this.inventory[1].isItemEqual(itemstack)) return false;
			  int result = inventory[1].stackSize + itemstack.stackSize;
			  return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		  }
	  }


	    public void smeltItem()
	    {
	    	if (this.canSmelt())
	    	{
	    		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
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

	    @Override
	    public void updateEntity()
	    {
	    	
	    	boolean flag = false;
	    	if(!flag1){
				flag1 = this.Energy >= 1000f/5f;
			}

	    	if (flag && this.proces > 0)
	    	{
	    		this.loseEnergy((int)((float)speed/5));
	    	}
	    	
	    	if(!this.worldObj.isRemote){
	    	if(proces > 0){
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
	    		if (flag1 && this.canSmelt())
	    		{
	    			this.proces += speed;
	    			if (this.proces >= 1000 )
	    			{
	    				if(speed <= 0)speed=10;
	    				this.proces = 0;
	    				this.smeltItem();
	    				flag = true;
	    				flag1 = false;
	    			}
	    		}
	    		else
	    		{
	    			this.proces = 0;
	    		}

	    	}
	    	if (flag)
	    	{
	    			this.onInventoryChanged();		
	    		}
	    	}

	    	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
	    		iCrafting.sendProgressBarUpdate(container, 0, proces);
	    		iCrafting.sendProgressBarUpdate(container, 1, Energy);
	    		iCrafting.sendProgressBarUpdate(container, 2, speed);
	    	}

	    	public void getGUINetworkData(int id, int value) {
	    		switch(id){
	    		case 0:{
	    			proces = value;
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


		public SyncObject getSinc() {
			SyncObject a = new SyncObject();
			a.setVar1(proces* 24 / 1000);
			a.setVar2(Energy);
			 return a;
		}

		@Override
		public boolean upgrade() {
			
			if(this.speed < 100){	
				this.speed += 20;
				speedUpgrades += 1;
				return true;
			}
			return false;
		}
		
		@Override
		public ItemStack getDrop() {
			if(speedUpgrades !=0){
				return new ItemStack(UltraTech.ItemName.get("SpeedUpgrade"),speedUpgrades);
			}
			return null;
		}

}

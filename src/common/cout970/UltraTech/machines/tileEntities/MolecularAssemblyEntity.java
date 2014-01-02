package common.cout970.UltraTech.machines.tileEntities;


import common.cout970.UltraTech.lib.recipes.Assembly_Recipes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class MolecularAssemblyEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	public static final int INVENTORY_SIZE = 11;
	public int Progres = 0;
	public boolean hasrecipe= false;
	private int speed = 10;
	private boolean flag= false;
	
	public MolecularAssemblyEntity(){
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	public void updateEntity(){

		if(!hasrecipe){
			if(Assembly_Recipes.matches(this)){
				ItemStack a = Assembly_Recipes.getCraftingResult(this);
				setInventorySlotContents(10, a);
				hasrecipe = true;
			}else if(this.getStackInSlot(10) != null){
				setInventorySlotContents(10, null);
			}
		}else{
			if(!flag){
				flag = this.Energy >= (1000f/speed)*5f;
			}
		}

		if(flag){
			Progres+=speed;
			this.loseEnergy(5);
			if(Progres >= 1000){
				Progres = 0;
				craft();
				flag = false;
			}
		}

	}


	private void craft() {
		if(Assembly_Recipes.matches(this)){
			ItemStack itemstack = Assembly_Recipes.getCraftingResult(this);

			if (this.inventory[9] == null)
			{
				this.inventory[9] = itemstack.copy();
			}
			else if (this.inventory[9].isItemEqual(itemstack))
			{
				inventory[9].stackSize += itemstack.stackSize;
			}
			for(int x=0;x<9;x++){
				if(this.inventory[x] != null){
					--this.inventory[x].stackSize;
					if (this.inventory[x].stackSize <= 0)
					{
						this.inventory[x] = null;
					}
				}
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
		hasrecipe = false;
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

	  public void sendGUINetworkData(Container container, ICrafting iCrafting) {
  		iCrafting.sendProgressBarUpdate(container, 1, Energy);
  		iCrafting.sendProgressBarUpdate(container, 2, Progres);
  	}

  	public void getGUINetworkData(int id, int value) {
  		switch(id){
  		
  		case 1:{
  			Energy = value;
  			break;
  		}
  	case 2:{
  		Progres = value;
  		break;
  	}
  	}
	}

}

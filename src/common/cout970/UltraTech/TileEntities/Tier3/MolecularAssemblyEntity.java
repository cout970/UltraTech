package common.cout970.UltraTech.TileEntities.Tier3;


import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
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
	public int progres = 0;
	public boolean hasrecipe= false;
	private int speed = 10;
	private boolean hasEnergy;
	
	public MolecularAssemblyEntity(){
		inventory = new ItemStack[INVENTORY_SIZE];
	}

	public void updateEntity(){
		if(worldObj.isRemote)return;
		boolean flag = false;

		if(!hasEnergy){
			hasEnergy = getEnergy() >= GraficCost.FurnaceCost;
		}

		if(progres > 0){
			removeEnergy(GraficCost.FurnaceCost*speed/1000);
		}
		if (hasEnergy && hasrecipe){
			this.progres += speed;
			if (this.progres >= 1000)
			{
				this.progres = 0;
				craft();
				flag = true;
				hasEnergy = false;
			}
		}else{
			this.progres = 0;
		}
		if (flag){
			this.onInventoryChanged();		
		}
		if(!hasrecipe){
			if(Assembly_Recipes.matches(this)){
				ItemStack a = Assembly_Recipes.getCraftingResult(this);
				setInventorySlotContents(10, a);
				hasrecipe = true;
			}else if(this.getStackInSlot(10) != null){
				setInventorySlotContents(10, null);
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
			hasrecipe = false;
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
		return "Molecular Assembly";
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
		return i == 10 || i == 11 ? false : true;
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

	//Synchronization
	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
		super.sendGUINetworkData(container, iCrafting);
		iCrafting.sendProgressBarUpdate(container, 2, progres);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)progres = value;
	}

}

package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import common.cout970.UltraTech.lib.recipes.Pressurizer_Recipes;
import common.cout970.UltraTech.machines.containers.PresuricerContaner;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class PresuricerEntity extends Machine implements ISidedInventory,ISpeedUpgradeabel{

	private ItemStack[] inventory;
	public int progres;
	public int speed = 10;
	private int speedUpgrades;
	public boolean hasEnergy = false;

	public PresuricerEntity(){
		super();
		inventory = new ItemStack[4];
		this.tier = MachineTier.Tier2;
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		boolean flag = false;
		if(!hasEnergy){
			hasEnergy = getEnergy() >= EnergyCosts.PresuricerCost;
		}
		if(progres > 0){
			removeEnergy(EnergyCosts.PresuricerCost*speed/1000);
		}
		if (hasEnergy && Pressurizer_Recipes.matches(this))
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
		else{
			this.progres = 0;
		}
		if (flag){
			this.onInventoryChanged();		
		}
	}
	
	private void craft() {
		if (Pressurizer_Recipes.matches(this))
		{
			ItemStack itemstack = Pressurizer_Recipes.getCraftingResult(this);
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
		return "Presuricer";
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
		return i == 3 ? false : true;
	}

	//Synchronization
	
	public void sendGUINetworkData(PresuricerContaner container,
			ICrafting iCrafting) {
		super.sendGUINetworkData(container, iCrafting);
		iCrafting.sendProgressBarUpdate(container, 2, progres);
		iCrafting.sendProgressBarUpdate(container, 3, speed);
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
			speedUpgrades += 1;
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
	
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[]{0,1,2,3};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return i == 0;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i==1 || i==2 || i==3;
	}
}

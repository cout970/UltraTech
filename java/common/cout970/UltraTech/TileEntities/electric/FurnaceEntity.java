package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Wpower.IPowerConductor;
import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.Wpower.PowerInterface;
import api.cout970.UltraTech.Wpower.StorageInterface;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class FurnaceEntity extends MachineWithInventory implements ISidedInventory,ISpeedUpgradeabel,IPowerConductor{

	public int progres = 0;
	public int speed = 10;
	public int speedUpgrades;
	public boolean hasEnergy = false;
	public StorageInterface cond = new StorageInterface(this, 2400, 2);

	public FurnaceEntity(){
		super(2,"Furnace",CostData.Furnace);		
	}

	@Override
	public void updateEntity()
	{
		if(!this.worldObj.isRemote){
			boolean flag = false;

			if(!hasEnergy){
				hasEnergy = cond.getCharge() >= CostData.Furnace.use;
			}

			if(progres > 0){
				cond.removeCharge(CostData.Furnace.use*speed/1000);
			}
			if (hasEnergy && this.canSmelt())
			{
				this.progres += speed;
				if (this.progres >= 1000)
				{
					this.progres = 0;
					this.smeltItem();
					flag = true;
					hasEnergy = false;
				}
			}
			else
			{
				this.progres = 0;
			}
			if (flag)
			{
				Sync();		
			}
		}
	}

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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack != null && FurnaceRecipes.smelting().getSmeltingResult(itemstack) != null && i == 0)return true;
		return false;
	}

	//Save & Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		speed = nbtTagCompound.getInteger("Speed");
		speedUpgrades = nbtTagCompound.getInteger("Speedupgrades");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);

		nbtTagCompound.setInteger("Speed", this.speed);
		nbtTagCompound.setInteger("Speedupgrades", this.speedUpgrades);
	}

	//Synchronization

	public void sendGUINetworkData(Container cont, ICrafting c) {
    	super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, progres);
    	c.sendProgressBarUpdate(cont, 3, speed);
    }
    
    public void getGUINetworkData(int id, int value) {
    	super.getGUINetworkData(id, value);
    	if(id == 2)progres = value;
    	if(id == 3)speed = value;
	}
    
    @Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[]{0,1};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return i == 0;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i==1;
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
	public PowerInterface getPower() {
		return cond;
	}

}

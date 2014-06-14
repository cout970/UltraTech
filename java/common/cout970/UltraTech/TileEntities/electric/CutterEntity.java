package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Vpower.Machine;
import api.cout970.UltraTech.Vpower.StorageInterface.MachineTipe;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class CutterEntity extends MachineWithInventory implements ISidedInventory,ISpeedUpgradeabel{

	public int progres = 0;
	public int speed = 10;
	public int speedUpgrades = 0;
	public boolean hasEnergy = false;

	public CutterEntity(){
		super(2,"Cutter",CostData.Cutter);
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){
			boolean flag = false;

			if(!hasEnergy){
				hasEnergy = getEnergy() >= CostData.Cutter.use;
			}

			if(progres > 0){
				removeEnergy(CostData.Cutter.use*speed/1000);
			}
			if (hasEnergy && Cuter_Recipes.matches(this))
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

	private void craft() {
		if(Cuter_Recipes.matches(this)){
			ItemStack itemstack = Cuter_Recipes.getCraftingResult(this);

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
		return i == 0;
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

    //Upgrades
    
	@Override
	public boolean upgrade() {
		
		if(this.speed < 100){	
			this.speed += 20;
			speedUpgrades +=1;
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
	
}

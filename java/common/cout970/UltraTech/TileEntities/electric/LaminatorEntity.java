package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Laminator_Recipe;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.MachineWithInventory;
import api.cout970.UltraTech.Wpower.Machine;

public class LaminatorEntity extends MachineWithInventory implements ISidedInventory,ISpeedUpgradeabel{

	public int progres = 0;
	public int speed = 10;
	public int speedUpgrades;
	public boolean hasEnergy = false;
	
	public LaminatorEntity() {
		super(2,"Laminator",CostData.Laminator);
	}
	
	@Override
	public void updateEntity(){

		if(!this.worldObj.isRemote){

			boolean flag = false;

			if(progres > 0){
				removeEnergy(CostData.CVD.use*speed/1000);
			}

			if(!hasEnergy){
				hasEnergy = getEnergy() >= CostData.CVD.use;
			}

			if(hasEnergy && Laminator_Recipe.matches(this)){

				progres+=speed;

				if(progres >= 1000){
					progres = 0;
					craft();
					flag = true;
					hasEnergy = false;
				}
			}else{
				progres = 0;
			}
			if(flag){
				Sync();
			}
		}
	}


	private void craft() {
		if(Laminator_Recipe.matches(this)){
			ItemStack itemstack = Laminator_Recipe.getCraftingResult(this);

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
		return i == 1 ? false : true;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);

		speed = nbt.getInteger("Speed");
		speedUpgrades = nbt.getInteger("Speedupgrades");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		nbt.setInteger("Speed", this.speed);
		nbt.setInteger("Speedupgrades", this.speedUpgrades);
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
			return new ItemStack(ItemManager.ItemName.get("SpeedUpgrade"), speedUpgrades);
		}
		return null;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		if(var1 == 0 || var1 == 1)return new int[]{0};
		else return new int[]{1};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return i==0;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i==1;
	}

}

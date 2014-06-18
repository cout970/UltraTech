package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.Wpower.StorageInterface.MachineTipe;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
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

public class CVD_Entity extends MachineWithInventory implements ISidedInventory,ISpeedUpgradeabel{

	public int progres = 0;
	public int speed = 10;
	public int speedUpgrades;
	public boolean hasEnergy = false;

	public CVD_Entity(){
		super(3,"CVD",CostData.CVD);
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

			if(hasEnergy && CVD_Recipe.matches(this)){

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
		if(CVD_Recipe.matches(this)){
			ItemStack itemstack = CVD_Recipe.getCraftingResult(this);

			if (this.inventory[2] == null)
			{
				this.inventory[2] = itemstack.copy();
			}
			else if (this.inventory[2].isItemEqual(itemstack))
			{
				inventory[2].stackSize += itemstack.stackSize;
			}
			--this.inventory[0].stackSize;
			--this.inventory[1].stackSize;
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
			if (this.inventory[1].stackSize <= 0)
			{
				this.inventory[1] = null;
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : true;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		NBTTagList tagList2 = nbtTagCompound.getTagList("Upgrades",11);
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList2.getCompoundTagAt(0);
		speed = tagCompound2.getInteger("Speed");
		NBTTagCompound tagCompound3 = (NBTTagCompound) tagList2.getCompoundTagAt(1);
		speedUpgrades = tagCompound3.getInteger("Speedupgrades");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		
		NBTTagList tagList2 = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger("Speed", this.speed);
		tagList2.appendTag(tagCompound);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("Speedupgrades", this.speedUpgrades);
		tagList2.appendTag(tagCompound2);
		nbtTagCompound.setTag("Upgrades", tagList2);
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
		if(var1 == 0 || var1 == 1)return new int[]{0,2};
		else return new int[]{1,2};
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return i==0 || i==1;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return i==2;
	}

}

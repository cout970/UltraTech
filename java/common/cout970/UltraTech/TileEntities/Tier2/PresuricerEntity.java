package common.cout970.UltraTech.TileEntities.Tier2;

import api.cout970.UltraTech.FTpower.Machine;
import common.cout970.UltraTech.containers.PresuricerContaner;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.Pressurizer_Recipes;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.ISpeedUpgradeabel;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class PresuricerEntity extends MachineWithInventory implements ISidedInventory,ISpeedUpgradeabel{

	public int progres;
	public int speed = 10;
	private int speedUpgrades;
	public boolean hasEnergy = false;

	public PresuricerEntity(){
		super(4,"Presuricer",2400,2);
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
			Sync();	
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

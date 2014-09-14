package common.cout970.UltraTech.TileEntities.electric.tiers;

import ultratech.api.power.interfaces.ISpeeded;
import ultratech.api.recipes.Cutter_Recipe;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.managers.MachineData;

public class TileEntityFurnaceT2 extends TileEntityFurnaceT1 implements ISpeeded{

	public int upgrades = 0;

	public TileEntityFurnaceT2() {
		super();
		maxProgres = 60 - 10*upgrades;
	}

	@Override
	public void updateEntity(){

		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = MachineData.Furnace.use/maxProgres;
			else extract = MachineData.Furnace.use;
			removeCharge(extract);
		}
		if(!hasEnergy && shouldWork()){
			hasEnergy = this.getCharge() >= MachineData.Furnace.use;
		}
		if(hasEnergy && canSmelt()){
			Progres++;
			if(Progres >= maxProgres){
				Progres = 0;
				craft();
				changes = true;
				hasEnergy = false;
			}
		}else{
			Progres = 0;
		}
		if(changes){
			this.markDirty();
		}
	}
	
	private boolean canSmelt()
	{
		if (this.inventory[0] == null){
			return false;
		}else{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);
			if (itemstack == null) return false;
			if (this.inventory[1] == null) return true;
			if (!this.inventory[1].isItemEqual(itemstack)) return false;
			int result = inventory[1].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}


	private void craft() {
		if(canSmelt()){
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

			if (this.inventory[1] == null){
				this.inventory[1] = itemstack.copy();
			}else if (this.inventory[1].isItemEqual(itemstack)){
				inventory[1].stackSize += itemstack.stackSize;
			}
			--this.inventory[0].stackSize;
			if (this.inventory[0].stackSize <= 0){
				this.inventory[0] = null;
			}
		}
	}

	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		upgrades = nbt.getInteger("Up");
		maxProgres = 60 - 10*upgrades;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("Up", upgrades);
	}

	@Override
	public boolean upgrade() {
		if(upgrades < 5){
			upgrades++;
			maxProgres = 60 - 10*upgrades;
			return true;
		}
		return false;
	}

	@Override
	public int getUpgrades() {
		return upgrades;
	}
}

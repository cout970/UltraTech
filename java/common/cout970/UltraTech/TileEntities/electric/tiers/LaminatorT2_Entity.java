package common.cout970.UltraTech.TileEntities.electric.tiers;

import ultratech.api.power.interfaces.ISpeeded;
import ultratech.api.recipes.CVD_Recipe;
import ultratech.api.recipes.Laminator_Recipe;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.managers.MachineData;

public class LaminatorT2_Entity extends LaminatorT1_Entity implements ISpeeded{

	public int upgrades = 0;
	
	public LaminatorT2_Entity() {
		super();
		maxProgres = 60 - 10*upgrades;
	}

	@Override
	public void updateEntity(){

		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = MachineData.Laminator.use/maxProgres;
			else extract = MachineData.Laminator.use;
			removeCharge(extract);
		}
		if(!hasEnergy){
			hasEnergy = this.getCharge() >= MachineData.Laminator.use;
		}
		if(hasEnergy && Laminator_Recipe.canCraft(this)){
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

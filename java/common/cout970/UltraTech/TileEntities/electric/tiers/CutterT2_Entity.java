package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Cutter_Recipe;
import common.cout970.UltraTech.lib.recipes.Laminator_Recipe;
import common.cout970.UltraTech.misc.ISpeeded;

public class CutterT2_Entity extends CutterT1_Entity implements ISpeeded{

	public int upgrades = 0;

	public CutterT2_Entity() {
		super();
		maxProgres = 60 - 10*upgrades;
	}

	@Override
	public void updateEntity(){

		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = CostData.Cutter.use/maxProgres;
			else extract = CostData.Cutter.use;
			removeEnergy(extract);
		}
		if(!hasEnergy && shouldWork()){
			hasEnergy = this.getEnergy() >= CostData.Cutter.use;
		}
		if(hasEnergy && Cutter_Recipe.INSTANCE.matches(this)){
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
		nbt.setFloat("progres", Progres);
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

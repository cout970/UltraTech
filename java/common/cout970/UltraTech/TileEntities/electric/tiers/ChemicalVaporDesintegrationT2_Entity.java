package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.misc.ISpeeded;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.MachineWithInventory;

public class ChemicalVaporDesintegrationT2_Entity extends ChemicalVaporDesintegrationT1_Entity implements ISpeeded{

	public int upgrades;

	public ChemicalVaporDesintegrationT2_Entity(){
		super();
		maxProgres = 60 - 10*upgrades;
	}

	@Override
	public void updateEntity(){

		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = CostData.CVD.use/maxProgres;
			else extract = CostData.CVD.use;
			removeEnergy(extract);
		}
		if(!hasEnergy && shouldWork()){
			hasEnergy = this.getEnergy() >= CostData.CVD.use;
		}
		if(hasEnergy && CVD_Recipe.INSTANCE.matches(this)){
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
		if(worldObj != null)worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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

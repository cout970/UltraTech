package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.Random;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ultratech.api.power.interfaces.ISpeeded;
import ultratech.api.recipes.Cutter_Recipe;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;

public class TileEntityGrindingMillT2 extends TileEntityGrindingMillT1 implements ISpeeded{

	public int upgrades = 0;
	
	public TileEntityGrindingMillT2() {
		super();
		maxProgres = 60 - 10*upgrades;
	}
	
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

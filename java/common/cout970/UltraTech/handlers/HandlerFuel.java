package common.cout970.UltraTech.handlers;

import common.cout970.UltraTech.items.ItemBottle;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import buildcraft.api.fuels.IronEngineFuel;
import buildcraft.api.fuels.IronEngineFuel.Fuel;
import cpw.mods.fml.common.IFuelHandler;

public class HandlerFuel implements IFuelHandler{

	/**
	 * only for the sulfur (2400)
	 * and liquid fuels
	 */
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel == null)return 0;
		int sulfur = OreDictionary.getOreID("dustSulfur");
		for(int f : OreDictionary.getOreIDs(fuel)){
			if(f == sulfur) return 800*3;
		}
		if(fuel.getItem() instanceof ItemBottle){
			FluidStack f = FluidContainerRegistry.getFluidForFilledItem(fuel);
			if(f != null){
				Fuel fu = IronEngineFuel.getFuelForFluid(f.getFluid());
				if(fu != null){
					return (int) (fu.powerPerCycle*fu.totalBurningTime);
				}
			}
		}
		return 0;
	}
}

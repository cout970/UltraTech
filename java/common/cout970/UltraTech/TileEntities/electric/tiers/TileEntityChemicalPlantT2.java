package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.MachineWithInventory;
import common.cout970.UltraTech.util.fluids.TankUT;
import common.cout970.UltraTech.util.power.Machine;

public class TileEntityChemicalPlantT2 extends TileEntityChemicalPlantT1 implements IFluidHandler{

	public TileEntityChemicalPlantT2(){
		super();
		maxProgres = 100;
	}
}

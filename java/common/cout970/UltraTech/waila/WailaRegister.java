package common.cout970.UltraTech.waila;

import mcp.mobius.waila.api.impl.ModuleRegistrar;

import common.cout970.UltraTech.blocks.common.FermenterBlock;
import common.cout970.UltraTech.blocks.models.FluidPipeBlock;
import common.cout970.UltraTech.blocks.models.FluidTank;
import common.cout970.UltraTech.blocks.models.PumpBlock;
import common.cout970.UltraTech.blocks.models.SteamTurbine;
import common.cout970.UltraTech.blocks.tiers.ChemicalPlantT1;
import common.cout970.UltraTech.blocks.tiers.ChemicalPlantT2;
import common.cout970.UltraTech.blocks.tiers.FluidGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.FluidGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.LavaGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.LavaGeneratorT2;
import common.cout970.UltraTech.util.power.BlockConductor;

public class WailaRegister {

	
	public static void init(){
		ModuleRegistrar.instance().registerBodyProvider(new HUDQuantumPower(), BlockConductor.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), FluidTank.class);//
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), FermenterBlock.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), FluidPipeBlock.class);//
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), PumpBlock.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), SteamTurbine.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), ChemicalPlantT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), FluidGeneratorT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), LavaGeneratorT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), ChemicalPlantT2.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), FluidGeneratorT2.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), LavaGeneratorT2.class);
	}
}

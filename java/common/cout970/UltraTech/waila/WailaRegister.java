package common.cout970.UltraTech.waila;

import mcp.mobius.waila.api.impl.ModuleRegistrar;

import common.cout970.UltraTech.blocks.common.BlockFermenter;
import common.cout970.UltraTech.blocks.models.BlockFluidPipe;
import common.cout970.UltraTech.blocks.models.BlockFluidTank;
import common.cout970.UltraTech.blocks.models.BlockPump;
import common.cout970.UltraTech.blocks.models.BlockSteamTurbine;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalPlantT1;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalPlantT2;
import common.cout970.UltraTech.blocks.tiers.BlockFluidGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.BlockFluidGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.BlockThermoelectricGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.BlockThermoelectricGeneratorT2;
import common.cout970.UltraTech.util.power.BlockConductor;

public class WailaRegister {

	
	public static void init(){
		ModuleRegistrar.instance().registerBodyProvider(new HUDQuantumPower(), BlockConductor.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockFluidTank.class);//
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockFermenter.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockFluidPipe.class);//
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockPump.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockSteamTurbine.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockChemicalPlantT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockFluidGeneratorT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockThermoelectricGeneratorT1.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockChemicalPlantT2.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockFluidGeneratorT2.class);
		ModuleRegistrar.instance().registerBodyProvider(new HUDFluids(), BlockThermoelectricGeneratorT2.class);
	}
}

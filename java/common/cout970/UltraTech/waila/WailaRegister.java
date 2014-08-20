package common.cout970.UltraTech.waila;

import common.cout970.UltraTech.blocks.tiers.LaminatorT1;
import common.cout970.UltraTech.util.power.BlockConductor;
import mcp.mobius.waila.api.impl.ModuleRegistrar;

public class WailaRegister {

	
	public static void init(){
		ModuleRegistrar.instance().registerBodyProvider(new HUDQuantumPower(), BlockConductor.class);
	}
}

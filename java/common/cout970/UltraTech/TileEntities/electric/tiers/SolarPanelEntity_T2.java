package common.cout970.UltraTech.TileEntities.electric.tiers;

import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.LogHelper;


public class SolarPanelEntity_T2 extends SolarPanelEntity_T1 implements IPowerConductor{

	public double getProduction(){
		return MachineData.Solar_Panel.use*8;
	}
	
}
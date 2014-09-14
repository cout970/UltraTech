package common.cout970.UltraTech.TileEntities.electric.tiers;

import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.LogHelper;


public class TileEntitySolarPanelT2 extends TileEntitySolarPanelT1 implements IPowerConductor{

	public double getProduction(){
		return MachineData.Solar_Panel.use*8;
	}
	
}
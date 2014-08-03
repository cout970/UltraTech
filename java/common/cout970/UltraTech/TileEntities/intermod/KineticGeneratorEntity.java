package common.cout970.UltraTech.TileEntities.intermod;

import buildcraft.api.mj.MjBattery;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.power.Machine;

public class KineticGeneratorEntity extends Machine{
	
	@MjBattery()
	public double MJ = 0;
	
	public KineticGeneratorEntity() {
		super(MachineData.Kinetic_Generator);
	}


	
}

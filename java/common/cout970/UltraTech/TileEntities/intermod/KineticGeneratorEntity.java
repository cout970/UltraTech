package common.cout970.UltraTech.TileEntities.intermod;

import buildcraft.api.mj.MjBattery;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.PowerExchange;

public class KineticGeneratorEntity extends Machine{
	
	@MjBattery()
	public double MJ = 0;
	
	public KineticGeneratorEntity() {
		super(MachineData.Kinetic_Generator);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		int transform = (int) Math.min(MJ, PowerExchange.QPtoMJ(getCapacity()-getCharge()));
		if(transform > 0){
			int limited = Math.min(transform, 16);
			MJ -= limited;
			addCharge(PowerExchange.MJtoQP(limited));
		}
	}
}

package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.energy.api.Machine;

public class SenderEntity extends Machine{

	public SenderEntity(){
		super();
		this.setMaxEnergy(1000);
		this.tipe = MachineTipe.Input;
	}
}

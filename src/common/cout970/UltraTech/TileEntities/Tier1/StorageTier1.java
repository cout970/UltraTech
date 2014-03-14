package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;

public class StorageTier1 extends Machine{

	public StorageTier1(){
		super();
		setMaxEnergy(1000000);
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier1;
	}
}

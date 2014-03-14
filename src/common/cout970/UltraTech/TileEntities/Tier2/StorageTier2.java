package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;

public class StorageTier2 extends Machine{

	public StorageTier2(){
		super();
		setMaxEnergy(10000000);
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier2;
	}
}

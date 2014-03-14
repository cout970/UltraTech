package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;


public class StorageTier3 extends Machine{

	public StorageTier3(){
		super();
		setMaxEnergy(160000000);
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier3;
	}

}

package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;


public class StorageTier3 extends Machine{

	public StorageTier3(){
		super();
		setMaxEnergy(EnergyCosts.Tier3Battery);
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier3;
	}

}

package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;

public class StorageTier2 extends Machine{

	public StorageTier2(){
		super();
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier2;
	}
	
	@Override
	public float maxEnergy() {
		return EnergyCosts.Tier2Battery;
	}
}

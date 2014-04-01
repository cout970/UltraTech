package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;


public class ClimateEntity extends Machine{

	public ClimateEntity(){
		tier = MachineTier.Tier3;
		this.setMaxEnergy(EnergyCosts.ClimateEstationCost);
	}
	
	
}

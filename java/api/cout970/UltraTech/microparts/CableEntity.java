package api.cout970.UltraTech.microparts;

import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.network.SyncTile;

public class CableEntity extends SyncTile implements IPowerConductor{

	public PowerInterface p;
	
	@Override
	public PowerInterface getPower() {
		if(p == null)p = new PowerInterface(this);
		return p;
	}

}

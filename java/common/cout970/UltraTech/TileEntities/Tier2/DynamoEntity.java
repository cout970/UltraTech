package common.cout970.UltraTech.TileEntities.Tier2;

import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;

public class DynamoEntity extends Machine{

	
	public DynamoEntity() {
		super(2400, 2, MachineTipe.Nothing);
	}

	public boolean isWorking() {
		return false;
	}

}

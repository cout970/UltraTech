package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Vpower.Machine;
import api.cout970.UltraTech.Vpower.StorageInterface;
import api.cout970.UltraTech.Vpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;

public class StorageTier2 extends Machine{

	public StorageTier2(){
		super(CostData.Storage_2);
	}
}

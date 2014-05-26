package common.cout970.UltraTech.TileEntities.Tier3;

import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.lib.EnergyCosts;

public class StorageTier3 extends Machine{

	public StorageTier3(){
		super(EnergyCosts.Tier3Battery,3,MachineTipe.Storage);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.getTotalWorldTime()%20 == 0){
			Net_Utils.sendUpdate(this);
		}
	}
}

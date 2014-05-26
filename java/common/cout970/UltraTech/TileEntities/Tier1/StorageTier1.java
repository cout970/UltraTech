package common.cout970.UltraTech.TileEntities.Tier1;

import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.lib.EnergyCosts;

public class StorageTier1 extends Machine{

	public StorageTier1(){
		super(EnergyCosts.Tier1Battery,1,MachineTipe.Storage);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.getTotalWorldTime()%20 == 0){
			Net_Utils.sendUpdate(this);
		}
	}
}

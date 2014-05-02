package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;

public class StorageTier1 extends Machine{

	public StorageTier1(){
		super();
		this.tipe = MachineTipe.Storage;
		tier = MachineTier.Tier1;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.getTotalWorldTime()%20 == 0){
			UT_Utils.sendPacket(this);
		}
	}
	
	@Override
	public float maxEnergy() {
		return EnergyCosts.Tier1Battery;
	}
}

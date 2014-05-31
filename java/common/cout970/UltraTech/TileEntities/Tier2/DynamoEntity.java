package common.cout970.UltraTech.TileEntities.Tier2;

import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.FTpower.ConnType;
import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;

public class DynamoEntity extends Machine{

	
	public DynamoEntity() {
		super(2400, 2, MachineTipe.Nothing,true);
	}
	
	public ConnType getConection(ForgeDirection side) {
		if(side == ForgeDirection.DOWN)return ConnType.ONLY_BIG;
		return ConnType.ONLY_SMALL;
	}

	public boolean isWorking() {
		return false;
	}

}

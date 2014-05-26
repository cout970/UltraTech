package common.cout970.UltraTech.TileEntities.Tier1;

import net.minecraft.tileentity.TileEntity;
import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.PowerInterface;

public class CableEntity extends TileEntity implements IPowerConductor{
	
	public PowerInterface cond = new PowerInterface(this);

	@Override
	public PowerInterface getPower() {
		return cond;
	}
	
}

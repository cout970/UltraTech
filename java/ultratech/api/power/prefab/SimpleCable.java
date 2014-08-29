package ultratech.api.power.prefab;

import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.IPowerConductor;
import net.minecraft.tileentity.TileEntity;
/**
 * 
 * @author Cout970
 *
 */
public class SimpleCable extends TileEntity implements IPowerConductor{

	public PowerInterface p;

	@Override
	public PowerInterface getPower() {
		if(p == null)p = new PowerInterface(this, new CableInterfaceBlock(this));
		return p;
	}
}

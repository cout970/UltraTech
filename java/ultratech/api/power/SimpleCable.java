package ultratech.api.power;

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
		if(p == null)p = new PowerInterface(this);
		return p;
	}
}

package common.cout970.UltraTech.util.fluids;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public class TankConection {

	public IFluidHandler tank;
	public ForgeDirection side;
	
	public TankConection(IFluidHandler t,ForgeDirection f){
		tank = t;
		side = f;
	}
}

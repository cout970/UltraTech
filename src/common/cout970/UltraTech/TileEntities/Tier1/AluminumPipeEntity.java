package common.cout970.UltraTech.TileEntities.Tier1;


import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.fluid.api.Pipe;

public class AluminumPipeEntity extends Pipe implements IFluidHandler{

	


	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		int amoun = 0;
		if(getNetwork() != null){
			for(IFluidHandler h : getNetwork().getTanks()){
				amoun = h.fill(null, resource, true);
				if(amoun > 0)break;
			}
		}
		return amoun;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}
	
}

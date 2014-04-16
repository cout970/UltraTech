package common.cout970.UltraTech.TileEntities.Tier1;


import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.multiblocks.TileRefinery;

public class RefineryOutEntity extends TileRefinery implements IFluidHandler{
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(structure != null){
			int d = Math.min(maxDrain*10,structure.getGasTank(this).getFluidAmount());
			FluidStack c = structure.getGasTank(this).drain(d, false);
			if(c != null && Cooling_Recipes.getResult(c,getHeight())!= null && c.amount >= 10){
				structure.getGasTank(this).drain(maxDrain*10, doDrain);
				return new FluidStack(Cooling_Recipes.getResult(c,getHeight()), c.amount/10);
			}
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}

}

package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.multiblocks.TileRefinery;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class RefineryInEntity extends TileRefinery implements IFluidHandler{

	public UT_Tank getTank(){
		if(getMulti() == null)return null;
		return getMulti().getGasTank(this);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || getTank() == null)return 0;
		if(Cooling_Recipes.hasRecipe(resource.getFluid())){
			return getTank().fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(Cooling_Recipes.hasRecipe(fluid))return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(getTank() == null)return new FluidTankInfo[]{}; 
		return new FluidTankInfo[]{new FluidTankInfo(getTank())}; 
	}
}

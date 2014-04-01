package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.recipes.Boiler_Recipes;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class CoolingEntity extends TileEntity implements IFluidHandler{

	public UT_Tank gas;
	public UT_Tank liquid;
	
	public void updateEntity(){
		super.updateEntity();
		if(gas == null)gas = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(liquid == null)liquid = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(gas != null){
			if(gas.getFluid() != null && gas.getFluidAmount() > 100){
				gas.drain(100, true);
				FluidStack t = new FluidStack(Cooling_Recipes.getResult(gas.getFluid().getFluid()),10);
				liquid.fill(t, true);
			}
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || gas == null)return 0;
		if(Cooling_Recipes.hasRecipe(resource.getFluid())){
			return gas.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource == null || liquid == null)return null;
		if(liquid.getFluid() != null && Boiler_Recipes.isEqual(liquid.getFluid().getFluid(),resource.getFluid())){
			return liquid.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(maxDrain == 0 || liquid == null)return null;
		return liquid.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(Cooling_Recipes.hasRecipe(fluid))return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(liquid.getFluid() != null && Cooling_Recipes.isEqual(fluid,liquid.getFluid().getFluid()))return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(liquid == null || gas == null)return new FluidTankInfo[]{}; 
		return new FluidTankInfo[]{liquid.getInfo(),gas.getInfo()}; 
	}
}

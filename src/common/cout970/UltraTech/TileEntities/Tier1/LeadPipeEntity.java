package common.cout970.UltraTech.TileEntities.Tier1;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.fluid.api.IFluidTransport;
import common.cout970.UltraTech.fluid.api.Pipe;
import common.cout970.UltraTech.lib.UT_Utils;

public class LeadPipeEntity extends Pipe implements IFluidHandler{

	public List<IFluidHandler> tanks;
	
	public void updateEntity(){
		super.updateEntity();
		if(tanks == null){
			tanks = new ArrayList<IFluidHandler>();
			for(TileEntity t :UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord)){
				if(t instanceof IFluidHandler && !(t instanceof IFluidTransport)){
					tanks.add((IFluidHandler) t);
				}
			}
		}
		if(getNetwork() != null)for(IFluidHandler f : tanks){
			if(getNetwork().buffer == null){
				this.fill(ForgeDirection.UP, f.drain(ForgeDirection.UP, 100, true), true);
			}else if(getNetwork().capacity <= getNetwork().buffer.amount + 100)this.fill(ForgeDirection.UP, f.drain(ForgeDirection.UP, 100, true), true);
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null)return 0;
		if(getNetwork() == null)return 0;
		if(getNetwork().buffer == null){
			int a = Math.min(getNetwork().capacity, resource.amount);
			if(doFill)getNetwork().buffer = new FluidStack(resource, a);
			return a;
		}else if(resource.isFluidEqual(getNetwork().buffer)){
			int a = Math.min(getNetwork().capacity, resource.amount+getNetwork().buffer.amount);
			if(doFill)getNetwork().buffer.amount = a;
			return a-getNetwork().buffer.amount;
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(getNetwork() == null)updateEntity();
		if(resource == null)return null;
		if(getNetwork().buffer == null)return null;
		if(getNetwork().buffer.isFluidEqual(resource))return drain(from, resource.amount, doDrain);
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(getNetwork() == null)updateEntity();
		if(getNetwork().buffer == null)return null;
		if(getNetwork().buffer.amount <= maxDrain){
			FluidStack ret = getNetwork().buffer;
			if(doDrain)getNetwork().buffer = null;
			return ret;
		}else{
			int a = getNetwork().buffer.amount - maxDrain;
			if(doDrain)getNetwork().buffer.amount = a;
			return new FluidStack(getNetwork().buffer, maxDrain);
		}
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)updateEntity();
		if(fluid == null)return false;
		if(getNetwork().buffer == null){
			return true;
		}else if(fluid.equals(getNetwork().buffer.getFluid())){
			return true;
		}
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)updateEntity();
		if(fluid == null)return false;
		if(getNetwork().buffer == null){
			return false;
		}else if(fluid.equals(getNetwork().buffer.getFluid())){
			return true;
		}
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(getNetwork() == null || getNetwork().buffer == null){
			return new FluidTankInfo[]{};
		}
		return new FluidTankInfo[]{new FluidTankInfo(getNetwork().buffer, getNetwork().capacity)};
	}
}

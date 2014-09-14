package common.cout970.UltraTech.util.fluids;

import common.cout970.UltraTech.util.LogHelper;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class FluidNetWorkManager implements IFluidHandler{

	public FluidNetwork net;
	
	public FluidNetWorkManager(FluidNetwork fluidNetwork) {
		net = fluidNetwork;
	}
	
	public void computingFluids(){
		int amount = net.getFluidAmount();
		if(amount <=0 || net.fluid == null)return;
		this.drain(null, amount, true);
		this.fill(null, new FluidStack(net.fluid, amount), true);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		
		if(resource == null)return 0;//chech if is null
		if(!this.canFill(from, resource.getFluid()))return 0;//check if can enter
		int pipes = net.getPipes().size();//number of pipes
		if(pipes <= 0)return 0;//error
		int space = net.getCapacity()-net.getFluidAmount();
		int toFill = Math.min(resource.amount, space);//min fluid, space
		int aceptPerPipe = toFill/pipes;//divided amount per tank
		int acepted = 0;
		
		if(aceptPerPipe > 0){
			for(IFluidTransport t : net.getPipes()){
				FluidStack f = new FluidStack(resource.fluidID, aceptPerPipe);
				int lSpace = t.getTank().getFluidAmount();
				int filled = t.getTank().fill(f, doFill);
				acepted += filled;
			}
		}
		if(aceptPerPipe*pipes != toFill){
			for(IFluidTransport t : net.getPipes()){
				if(toFill - acepted > 0){
					FluidStack f = new FluidStack(resource.fluidID, 1);
					int filled = t.getTank().fill(f, doFill);
					acepted += filled;
				}
			}
		}
		return acepted;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(!canDrain(from, resource.getFluid()))return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		int pipes = net.getPipes().size();//number of pipes
		if(pipes <= 0)return null;//error
		int output = Math.min(maxDrain, net.getFluidAmount());
		if(output <= 0)return null;//empty
		int outPerPipe = output/pipes;//divided amount per tank
		int drained = 0;
		if(outPerPipe > 0){
			for(IFluidTransport t : net.getPipes()){
				FluidStack d = t.getTank().drain(outPerPipe, doDrain);//outPerPipe
				if(d != null){
					drained += d.amount;				
				}
			}
		}
		if(output-drained > 0){//one by one until finish
			for(IFluidTransport t : net.getPipes()){
				if(output-drained > 0){
					FluidStack d = t.getTank().drain(1, doDrain);
					if(d != null){drained += d.amount;
					}
				}
			}
		}
		if(drained > 0){
			FluidStack f = new FluidStack(net.fluid, drained);
			if(net.getFluidAmount() <= 0){
				net.fluid = null;
			}
			return f;
		}else return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(fluid == null)return false;
		if(net.fluid == null)return true;
		if(net.fluid.getID() == fluid.getID())return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(net.fluid == null)return false;
		if(net.fluid == fluid)return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return null;
	}

}

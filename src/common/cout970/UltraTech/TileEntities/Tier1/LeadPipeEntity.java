package common.cout970.UltraTech.TileEntities.Tier1;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.fluid.api.FluidUtils;
import common.cout970.UltraTech.fluid.api.Pipe;

public class LeadPipeEntity extends Pipe implements IFluidHandler{

	public Map<ForgeDirection,IFluidHandler> tanks;
	
	public void updateEntity(){
		super.updateEntity();
		if(tanks == null){
			tanks = new HashMap<ForgeDirection,IFluidHandler>();
			for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
				TileEntity te = FluidUtils.getRelative(this, d);
				if(te instanceof IFluidHandler){
					tanks.put(d,(IFluidHandler) te);
				}
			}
		}

		if(getNetwork() != null)for(ForgeDirection f : ForgeDirection.VALID_DIRECTIONS){
			if(tanks.containsKey(f) && tanks.get(f) != null){
				for(IFluidHandler h : getNetwork().getTanks()){
					if(tanks.get(f).getTankInfo(f.getOpposite()) != null && tanks.get(f).getTankInfo(f.getOpposite()).length > 0)if(tanks.get(f).getTankInfo(f.getOpposite())[0].fluid != null)
					if(!tanks.containsValue(h) && h.canFill(f, tanks.get(f).getTankInfo(f.getOpposite())[0].fluid.getFluid())){
						int toD = Math.min(tanks.get(f).getTankInfo(f.getOpposite())[0].fluid.amount, 100);
						FluidStack drained = tanks.get(f).drain(f.getOpposite(), toD, true);
						if(drained != null){
							h.fill(null, drained, true);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void onNetworkUpdate() {
		tanks = null;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
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

package common.cout970.UltraTech.TileEntities.fluid;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.fluids.Pipe;
import common.cout970.UltraTech.util.fluids.TankConection;

public class CopperPipeEntity extends Pipe implements IFluidHandler{

	public List<TankConection> connections = new ArrayList<TankConection>();
	public List<ForgeDirection> pipes = new ArrayList<ForgeDirection>();
	public boolean mode;//false out, true in
	public boolean up;
	public boolean lock;
	public static final int MAX_ACCEPT = 200;
	public static final int MAX_EXTRACT = 100;
	
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(worldObj.getTotalWorldTime()%20 == 3)sendNetworkUpdate();
		if(!up){onNeighUpdate(); up = true;}
		if(connections.size() == 0)return;
		if(!mode){
			int part = getNetwork().getFluidAmount();
			int toD = Math.min(MAX_ACCEPT, part);
			if(toD <= 0)return;
			if(getNetwork().getFluid() == null)return;
			FluidStack f = new FluidStack(getNetwork().getFluid(), toD);
			for(TankConection t : connections){
				int filled = t.tank.fill(t.side, f, true);
				FluidStack df = drain(t.side.getOpposite(), filled, true);
			}
		}else{
			for(TankConection t : connections){
				FluidStack f = t.tank.drain(t.side, MAX_EXTRACT, false);
				if(f != null && (getNetwork().getFluid() == null || getNetwork().getFluid().getID() == f.fluidID)){
					int space = getNetwork().getCapacity()-getNetwork().getFluidAmount();
					int transfer = Math.min(f.amount, space);
					int toD = Math.min(transfer, MAX_EXTRACT);
					if(toD > 0){
						FluidStack c = t.tank.drain(t.side, toD, true);
						this.fill(null, c, true);
					}
				}
			}
		}
	}
	
	public void onNeighUpdate(){
		pipes.clear();
		connections.clear();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity t = UT_Utils.getRelative(this, d);
			if(t instanceof IFluidTransport){
				pipes.add(d);
			}else if(t instanceof IFluidHandler){
				pipes.add(d);
				connections.add(new TankConection((IFluidHandler) t, d.getOpposite()));
			}
		}
		if(!lock){
			boolean last = mode;
			mode = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			if(mode != last)sendNetworkUpdate();
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(getNetwork() == null)return 0;
		return getNetwork().manager.fill(from, resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		if(getNetwork() == null)return null;
		return getNetwork().manager.drain(from, resource, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(getNetwork() == null)return null;
		return getNetwork().manager.drain(from, maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)return false;
		return getNetwork().manager.canFill(from, fluid);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null)return false;
		return getNetwork().manager.canDrain(from, fluid);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}
	
	public void readFromNBT(NBTTagCompound p_145839_1_)
    {
		super.readFromNBT(p_145839_1_);
        mode = p_145839_1_.getBoolean("mode");
        lock = p_145839_1_.getBoolean("lock");
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
    	super.writeToNBT(p_145841_1_);
    	p_145841_1_.setBoolean("mode", mode);
    	p_145841_1_.setBoolean("lock", lock);
    }
}

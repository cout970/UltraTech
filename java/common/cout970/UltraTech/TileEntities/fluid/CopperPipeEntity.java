package common.cout970.UltraTech.TileEntities.fluid;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import api.cout970.UltraTech.fluids.IFluidTransport;
import api.cout970.UltraTech.fluids.Pipe;
import api.cout970.UltraTech.fluids.TankConection;

public class CopperPipeEntity extends Pipe implements IFluidHandler{

	public List<TankConection> connections = new ArrayList<TankConection>();
	public List<ForgeDirection> pipes = new ArrayList<ForgeDirection>();
	public boolean mode;//false out, true in
	public boolean up;
	public boolean lock;
	
	public void updateEntity(){
		super.updateEntity();
		if(!up){onNeighUpdate(); up = true;}
		if(connections.size() == 0 || getNetwork() == null)return;
		if(!mode){
			int part = getNetwork().getBuffer().getFluidAmount()/connections.size();
			int toD = Math.min(150, part);
			if(toD <=0)return;
			FluidStack f = new FluidStack(getNetwork().getBuffer().getFluid(), toD);
			for(TankConection t : connections){
				FluidStack df = drain(t.side.getOpposite(), t.tank.fill(t.side, f, true), true);
			}
		}else{
			
			for(TankConection t : connections){
				FluidStack f = t.tank.drain(t.side, 50, false);
				if(f != null && (getNetwork().getBuffer().getFluid() == null || getNetwork().getBuffer().getFluid().fluidID == f.fluidID)){
					int space;
					if(getNetwork().getBuffer().getFluid() != null)space = getNetwork().getBuffer().getCapacity()-getNetwork().getBuffer().getFluidAmount();
					else space = 2000;
					FluidStack c = t.tank.drain(t.side, 50, true);
					this.fill(null, c, true);
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
			mode = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			Sync();
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(getNetwork() == null || getNetwork().getBuffer() == null)return 0;
		return getNetwork().getBuffer().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		if(getNetwork() == null || getNetwork().getBuffer() == null)return null;
		return getNetwork().getBuffer().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(getNetwork() == null || getNetwork().getBuffer() == null)return null;
		return getNetwork().getBuffer().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null || getNetwork().getBuffer() == null)return false;
		return getNetwork().getBuffer().getFluid() != null && getNetwork().getBuffer().getFluid().fluidID == fluid.getID();
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(getNetwork() == null || getNetwork().getBuffer() == null)return false;
		return getNetwork().getBuffer().getFluid() != null && getNetwork().getBuffer().getFluid().fluidID == fluid.getID();
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(getNetwork() == null || getNetwork().getBuffer() == null)return new FluidTankInfo[]{};
		return new FluidTankInfo[]{new FluidTankInfo(getNetwork().getBuffer())};
	}
	
	public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        mode = p_145839_1_.getBoolean("mode");
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
    	super.writeToNBT(p_145841_1_);
    	p_145841_1_.setBoolean("mode", mode);
    	
    }
}

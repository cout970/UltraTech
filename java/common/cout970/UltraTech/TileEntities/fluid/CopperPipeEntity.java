package common.cout970.UltraTech.TileEntities.fluid;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.fluids.Pipe;
import common.cout970.UltraTech.util.fluids.TankConection;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class CopperPipeEntity extends Pipe implements IFluidHandler{

	public List<TankConection> connections = new ArrayList<TankConection>();
	public List<ForgeDirection> pipes = new ArrayList<ForgeDirection>();
	public boolean mode;//false out, true in
	public boolean up;
	public boolean lock;
	public boolean hasChanged;
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
//		if(hasChanged && worldObj.getWorldTime()%20 == 0){
//			hasChanged = false;
//			Sync();
//		}
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
				FluidStack f = t.tank.drain(t.side, 100, false);
				if(f != null && (getNetwork().getBuffer().getFluid() == null || getNetwork().getBuffer().getFluid().fluidID == f.fluidID)){
					int space;
					if(getNetwork().getBuffer().getFluid() != null)space = getNetwork().getBuffer().getCapacity()-getNetwork().getBuffer().getFluidAmount();
					else space = 2000;
					int toD = Math.min(space, 100);
					if(toD >0){
						FluidStack c = t.tank.drain(t.side, toD, true);
						this.fill(null, c, true);
						hasChanged = true;
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
			mode = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			sendNetworkUpdate();
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
//		if(getNetwork() != null){
////			if(!getNetwork().alreadyLoad){			
//				LogHelper.log("loaded liquid: "+getNetwork().getBuffer().getFluidAmount());
//				getNetwork().getBuffer().readFromNBT(p_145839_1_, "net");
////				getNetwork().alreadyLoad = true;
////			}
//		}
        mode = p_145839_1_.getBoolean("mode");
        lock = p_145839_1_.getBoolean("lock");
    }

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
    	super.writeToNBT(p_145841_1_);
//    	if(getNetwork() != null && getNetwork().getBuffer().getFluidAmount() > 0){
//    		getNetwork().getBuffer().writeToNBT(p_145841_1_,"net");
//    	}
    	p_145841_1_.setBoolean("mode", mode);
    	p_145841_1_.setBoolean("lock", lock);
    }
}

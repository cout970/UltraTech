package common.cout970.UltraTech.util.fluids;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

public class Pipe extends SyncTile implements IFluidTransport{

	private FluidNetwork net;
	public  TankUT      buffer;
	
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(net == null){
			if(!(worldObj.getTileEntity(xCoord, yCoord, zCoord) instanceof IFluidTransport))return;
			IFluidTransport te = (IFluidTransport) worldObj.getTileEntity(xCoord, yCoord, zCoord);
			boolean hasNetwork = false;
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
				TileEntity e = UT_Utils.getRelative(te.getTileEntity(), dir);
				if(e instanceof IFluidTransport){
						if(((IFluidTransport) e).getNetwork() != null){
							if(!hasNetwork){
								te.setNetwork(((IFluidTransport) e).getNetwork());
								te.getNetwork().getPipes().add(te);
								hasNetwork = true;
							}else{
								te.getNetwork().mergeWith(((IFluidTransport) e).getNetwork());
							}		
						}

				}
			}
			if(!hasNetwork){
				te.setNetwork(FluidNetwork.create(te,this));
			}
			te.getNetwork().refresh();
		}
	}


	@Override
	public TileEntity getTileEntity() {
		return this;
	}

	@Override
	public void setNetwork(FluidNetwork fluidNetwork) {
		net = fluidNetwork;
	}

	@Override
	public void onNetworkUpdate() {}

	@Override
	public FluidNetwork getNetwork() {
		return net;
	}
	
	public void readFromNBT(NBTTagCompound p_145839_1_)
	{
		super.readFromNBT(p_145839_1_);
		((TankUT) getTank()).readFromNBT(p_145839_1_, "net");
		if(net != null && net.fluid == null){
			if(getTank().getFluid() != null)net.fluid = getTank().getFluid().getFluid();
		}
	}

    public void writeToNBT(NBTTagCompound p_145841_1_)
    {
    	super.writeToNBT(p_145841_1_);
    	((TankUT) getTank()).writeToNBT(p_145841_1_, "net");
    }


	public IFluidTank getTank() {
		if(buffer == null)buffer = new TankUT(100, this);
		return buffer;
	}


	@Override
	public boolean canConectOnSide(ForgeDirection d) {
		return true;
	}

}

package common.cout970.UltraTech.TileEntities.fluid;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.fluids.TankUT;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;

public class TileEntityFluidTank extends SyncTile implements IFluidHandler{

	private TankUT storage;
	private boolean FluidChange = true;
	public CubeRenderer_Util FR = new CubeRenderer_Util();

	public TankUT getTank(){
		if(storage == null)storage = new TankUT(32000, this);
		return storage;
	}
	
	public void updateEntity(){
		if(worldObj.getTotalWorldTime()%20 == 0 && FluidChange){
			FluidChange = false;
			sendNetworkUpdate();
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		int f = getTank().fill(resource, doFill);
		if(f > 0){
			FluidChange = true;
		}
		return f;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack f = getTank().drain(maxDrain, doDrain);
		if(f != null && f.amount > 0 && doDrain){
			FluidChange = true;
		}
		return f;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return getTank().fill(new FluidStack(fluid, 1), false) != 0;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return getTank().drain(1, false) != null;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		getTank().readFromNBT(nbtTagCompound, "liquid");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound, "liquid");
		nbtTagCompound.setBoolean("SavedData", true);
	}

}

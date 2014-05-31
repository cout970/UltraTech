package common.cout970.UltraTech.TileEntities.Tier2;

import api.cout970.UltraTech.FTpower.ConnType;
import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.fluids.UT_Tank;
import api.cout970.UltraTech.network.Net_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.lib.EnergyCosts;

public class SteamTurbineEntity extends Machine implements IFluidHandler{

	public UT_Tank tank;
	public ForgeDirection facing = ForgeDirection.UP;
	
	//render
	public boolean work = false;
	public float angle;
	public long oldTime = -1;
	public long updateTime = -1;
	public float speed = 0;
	
	
	public SteamTurbineEntity(){
		super(2400,2,MachineTipe.Output,true);
	}
	
	public ConnType getConection(ForgeDirection side) {
		if(side == facing)return ConnType.ALL_CABLES;
		return ConnType.ONLY_SMALL;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(tank == null){
			tank = new UT_Tank(16000, this);
		}
		if(!worldObj.isRemote){
			if(tank.getFluidAmount() >= 10 && getEnergy()+EnergyCosts.SteamTurbineProduct <= maxEnergy()){
				tank.drain(10, true);
				this.addEnergy(EnergyCosts.SteamTurbineProduct);
				if(worldObj.getTotalWorldTime()%10 == 0)Net_Utils.sendUpdate(this);
			}
			if(tank.getFluidAmount() > 20){
				if(!work){
					work = true;
					Net_Utils.sendUpdate(this);
				}
			}else{
				if(work){
					work = false;
					Net_Utils.sendUpdate(this);
				}
			}
		}
	}
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(tank == null)return 0;
		if(from != facing && from != null)return 0;
		return tank.fill(resource, doFill);
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
		return from == facing;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(tank == null)return new FluidTankInfo[]{};
		return new FluidTankInfo[]{new FluidTankInfo(tank)};
	}

	public void switchOrientation() {
		if(facing.ordinal() < 5)facing = ForgeDirection.getOrientation(facing.ordinal()+1);
		else facing = ForgeDirection.DOWN;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		facing = ForgeDirection.getOrientation(nbtTagCompound.getInteger("direction"));
		boolean aux = work;
		work = nbtTagCompound.getBoolean("w");
		if(work != aux && worldObj != null)updateTime = worldObj.getTotalWorldTime();
		if(tank != null)tank.readFromNBT(nbtTagCompound, "Steam");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("direction", facing.ordinal());
		nbtTagCompound.setBoolean("w", work);
		if(tank != null)tank.writeToNBT(nbtTagCompound, "Steam");
	}

}

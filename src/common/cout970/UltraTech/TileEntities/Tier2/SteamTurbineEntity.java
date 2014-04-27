package common.cout970.UltraTech.TileEntities.Tier2;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import common.cout970.UltraTech.lib.UT_Utils;

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
		super();
		tipe = MachineTipe.Output;
		tier = MachineTier.Tier2;
	}
	
	public ForgeDirection[] getConnectableSides(){
		return new ForgeDirection[]{facing.getOpposite()};
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(tank == null){
			tank = new UT_Tank(16000, this);
		}
		if(!worldObj.isRemote){
			if(tank.getFluidAmount() > 180 && getEnergy()+EnergyCosts.SteamTurbineProduct <= maxEnergy()){
				tank.drain(80, true);
				this.addEnergy(EnergyCosts.SteamTurbineProduct);
				if(worldObj.getTotalWorldTime()%10 == 0)UT_Utils.sendPacket(this);
			}else tank.drain(1,true);
			if(tank.getFluidAmount() > 100){
				if(!work){
					work = true;
					UT_Utils.sendPacket(this);
				}
			}else{
				if(work){
					work = false;
					UT_Utils.sendPacket(this);
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

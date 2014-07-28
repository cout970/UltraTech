package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ultratech.api.power.CableType;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.power.Machine;

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
		super(MachineData.Turbine,true);
	}
	
	public CableType getConection(ForgeDirection side) {
		if(side == facing)return CableType.BLOCK;
		return CableType.RIBBON_BOTTOM;
	}
	
	public void updateEntity(){
		super.updateEntity();
		
		if(tank == null){
			tank = new UT_Tank(16000, this);
		}
		if(!worldObj.isRemote){
			double space = getCapacity()-getCharge();
			int work = (int) Math.min(space/MachineData.Turbine.use, tank.getFluidAmount());
			if(work > 0){
				tank.drain(work, true);
				this.addCharge(MachineData.Turbine.use*work);
			}
		}
	}
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(tank == null)return 0;
		if(resource.fluidID != FluidRegistry.getFluidID("steam"))return 0;
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

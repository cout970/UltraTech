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

	public UT_Tank steam;
	public ForgeDirection facing = ForgeDirection.UP;
	private int maxProduction = 80;

	//render
	public boolean on = false;
	public float angle;
	public long oldTime = -1;
	
	
	public UT_Tank getTank(){
		if(steam == null)steam = new UT_Tank(2000, this);
		return steam;
	}
	
	public SteamTurbineEntity(){
		super(MachineData.Turbine,true);
	}
	
	public CableType getConection(ForgeDirection side) {
		if(side == facing)return CableType.BLOCK;
		return CableType.RIBBON_BOTTOM;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(!worldObj.isRemote){
			double space = getCapacity()-getCharge();
			int work = (int) Math.min(space/MachineData.Turbine.use, getTank().getFluidAmount());
			if(work > 0){
				int allow = Math.min(work, maxProduction);
				getTank().drain(allow, true);
				this.addCharge(MachineData.Turbine.use*allow);
			}
			if(worldObj.getTotalWorldTime()% 10 == 1){
				if(!on && work > 0){
					on = true;
					sendNetworkUpdate();
				}
				if(on && work <= 0){
					on = false;
					sendNetworkUpdate();
				}
			}
		}
	}
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource.fluidID != FluidRegistry.getFluidID("steam"))return 0;
		return getTank().fill(resource, doFill);
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
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	public void switchOrientation() {
		if(facing.ordinal() < 5)facing = ForgeDirection.getOrientation(facing.ordinal()+1);
		else facing = ForgeDirection.DOWN;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		facing = ForgeDirection.getOrientation(nbtTagCompound.getInteger("direction"));
		on = nbtTagCompound.getBoolean("w");
		getTank().readFromNBT(nbtTagCompound, "Steam");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("direction", facing.ordinal());
		nbtTagCompound.setBoolean("w", on);
		getTank().writeToNBT(nbtTagCompound, "Steam");
	}

}

package common.cout970.UltraTech.TileEntities.utility;


import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import common.cout970.UltraTech.multiblocks.TileReactorPart;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;

public class ReactorTankEntity extends TileReactorPart implements IFluidHandler,IFluidTank,IReactorPart{

	public FluidStack liquid;
	private final int capacity;
	private int last = 69;
	

	public ReactorTankEntity(){
		super();
		this.capacity = 16000;
	}

	@Override
	public void updateEntity(){
		if(liquid != null && !worldObj.isRemote){
			if(liquid.amount != last && worldObj.getTotalWorldTime()%20 == 0){
				Net_Utils.sendUpdate(this);
				last = liquid.amount;
			}
		}
	}

	//Fluid Tank

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {

		if (resource == null) {
			return 0;
		}
		if(resource.getFluid().getBlock() != Blocks.water)return 0;
		if (!doFill)
		{
			if (liquid == null)
			{
				return Math.min(capacity, resource.amount);
			}

			if (!liquid.isFluidEqual(resource))
			{
				return 0;
			}

			return Math.min(capacity - liquid.amount, resource.amount);
		}

		if (liquid == null)
		{

			liquid = new FluidStack(resource, Math.min(capacity, resource.amount));


			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

			return liquid.amount;
		}

		if (!liquid.isFluidEqual(resource))
		{
			return 0;
		}
		int filled = capacity - liquid.amount;
		if (resource.amount < filled)
		{
			liquid.amount += resource.amount;
			filled = resource.amount;
		}
		else
		{
			liquid.amount = capacity;
		}


		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));

		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if (liquid == null)
		{
			return null;
		}

		int drained = maxDrain;
		if (liquid.amount < drained)
		{
			drained = liquid.amount;
		}

		FluidStack stack = new FluidStack(liquid, drained);
		if (doDrain)
		{
			liquid.amount -= drained;
			if (liquid.amount <= 0)
			{
				liquid = null;
			}
			FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(liquid, this.worldObj, this.xCoord, this.yCoord, this.zCoord, (IFluidTank) this));
		}
		return stack;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if (resource == null)
			return null;
		if (!resource.isFluidEqual(liquid))
			return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection direction) {

		return new FluidTankInfo[]{new FluidTankInfo(liquid, capacity)};
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	public int getFluidLightLevel() {
		FluidStack tankFluid = liquid;
		return tankFluid == null ? 0 : tankFluid.getFluid().getLuminosity(tankFluid);
	}

	public FluidStack getFluid() {
		return liquid;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public int getFluidAmount() {
		if(liquid == null)return 0;
		return liquid.amount;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(liquid, capacity);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		return this.fill(null, resource, doFill);
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return this.drain(null, maxDrain, doDrain);
	}

	//Save & Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);

		int amount = nbtTagCompound.getInteger("Amount");
		if(amount != 0){
			liquid = new FluidStack(nbtTagCompound.getInteger("ID"),amount);
		}
	}


	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);

		if(liquid == null){
			nbtTagCompound.setInteger("Amount", 0);
			nbtTagCompound.setInteger("ID", 0);
		}else{
			nbtTagCompound.setInteger("Amount", liquid.amount);
			nbtTagCompound.setInteger("ID", liquid.fluidID);
		}
	}

}
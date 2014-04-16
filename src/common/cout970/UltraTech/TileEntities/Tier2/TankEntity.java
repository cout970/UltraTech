package common.cout970.UltraTech.TileEntities.Tier2;

import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TankEntity extends TileEntity implements IFluidHandler{

	public UT_Tank storage;

	public void createTank(){
		storage = new UT_Tank(16000, this);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(storage == null)createTank();
		int f = storage.fill(resource, doFill);
		if(f != 0)UT_Utils.sendPacket(this);
		return f;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		if(storage == null)createTank();
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(storage == null)createTank();
		FluidStack f = storage.drain(maxDrain, doDrain);
		if(f != null)UT_Utils.sendPacket(this);
		return f;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(storage == null)createTank();
		return storage.fill(new FluidStack(fluid, 1), false) != 0;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(storage == null)createTank();
		return storage.drain(1, false) != null;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(storage == null)createTank();
		UT_Utils.sendPacket(this);
		return new FluidTankInfo[]{new FluidTankInfo(storage)};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if(storage == null)createTank();
		storage.readFromNBT(nbtTagCompound, "liquid");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		if(storage == null)createTank();
		storage.writeToNBT(nbtTagCompound, "liquid");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		this.readFromNBT(pkt.data);
	}

}

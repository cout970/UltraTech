package common.cout970.UltraTech.TileEntities.multiblocks.refinery;

import ultratech.api.refinery.IRefineryCoreEntity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.TankUT;

public class Refinery_IO_Entity extends Refinery_Entity_Base implements IFluidHandler{

	public TankUT tank;
	public int mode = 0;
	public boolean update;
	
	public void updateEntity(){
		super.updateEntity();
		if(core != null && !update){
			update= true;
			core.updateComponents();
		}
	}
	
	@Override
	public void refreshCore() {
		TileEntity t = worldObj.getTileEntity(x, y, z);
		if(t instanceof IRefineryCoreEntity){
			setCore((IRefineryCoreEntity) t);
		}
	}
	
	@Override
	public void RestaureBlock() {
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
	}
	
	public void setMode(int m){
		mode = m;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, m, 2);
		if(core != null)core.updateComponents();
		sendNetworkUpdate();
	}
	
	public int getMode(){
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
	}
	
	public TankUT getTank(){
		if(tank == null)tank = new TankUT(4000, this);
		return tank;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return getTank().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		return getTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		mode = nbt.getInteger("mode");
		getTank().readFromNBT(nbt, "fluid");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("mode", mode);
		getTank().writeToNBT(nbt, "fluid");
	}

	public void onNeigUpdate() {
		if(core != null){
			core.updateComponents();
		}
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		if(getTank().getFluid() != null){
			c.sendProgressBarUpdate(cont, 5, getTank().getFluid().fluidID);
			c.sendProgressBarUpdate(cont, 2, getTank().getFluidAmount());
		}
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)getTank().setFluidAmount(value);
		if(id == 5)getTank().setFluid(new FluidStack(value, 1));
	}
}

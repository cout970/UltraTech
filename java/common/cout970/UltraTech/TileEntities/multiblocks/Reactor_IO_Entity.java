package common.cout970.UltraTech.TileEntities.multiblocks;

import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.TankConection;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class Reactor_IO_Entity extends Reactor_Entity_Base implements IFluidHandler,IInventory{

	public UT_Tank tank;
	public int mode = 1;//0 water in, 1 steam out, 2 material in,3 material out
	
	public Reactor_IO_Entity(){
		super();
	}
	
	public void updateEntity(){
		if(mode == 1 && getCore() != null){
			if(getCore().getTank(1).getFluidAmount() > 0){
				int toDrain = Math.min(getCore().getTank(1).getFluidAmount(), 500);
				for(TankConection tc : FluidUtils.getTankConections(this)){
					int tr = tc.tank.fill(tc.side, new FluidStack(FluidRegistry.getFluid("steam"), toDrain), false);
					if(tr > 0){
						int trans = Math.min(toDrain, tr);
						tc.tank.fill(tc.side, new FluidStack(FluidRegistry.getFluid("steam"), trans), true);
						getCore().getTank(1).drain(trans, true);
					}
				}
			}
		}
	}
	
	@Override
	public void onNeigUpdate() {
		super.onNeigUpdate();
		if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord))mode = 0;
		else mode = 1;
	}
	
	@Override
	public int getSizeInventory() {
		if(getCore() != null)return ((IInventory) getCore()).getSizeInventory();
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		if(getCore() != null)return ((IInventory) getCore()).getStackInSlot(var1);
		return null;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(getCore() != null)return ((IInventory) getCore()).decrStackSize(slot, amount);
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if(getCore() != null)return ((IInventory) getCore()).getStackInSlotOnClosing(var1);
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if(getCore() != null)((IInventory) getCore()).setInventorySlotContents(slot, itemStack);
	}

	@Override
	public String getInventoryName() {
		return "ReactorIO";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource != null && resource.fluidID == FluidRegistry.WATER.getID()){
			if(getCore() != null){
				int f = getCore().getTank(0).fill(resource, doFill);
				if(f > 0 && doFill)Net_Utils.sendUpdate((TileEntity) getCore());
				return f;
			}
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return this.drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(getCore() != null){
			 return getCore().getTank(1).drain(maxDrain, doDrain);
		}
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid == FluidRegistry.WATER;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
	}
}

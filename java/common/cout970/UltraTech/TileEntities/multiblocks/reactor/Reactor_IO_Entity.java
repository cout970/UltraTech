package common.cout970.UltraTech.TileEntities.multiblocks.reactor;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.TankConection;
import common.cout970.UltraTech.util.fluids.TankUT;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
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

public class Reactor_IO_Entity extends Reactor_Entity_Base implements IFluidHandler,IInventory,ISidedInventory{

	public TankUT tank;
	
	public Reactor_IO_Entity(){
		super();
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(getCore() != null){
			if(getCore().getTank(1).getFluidAmount() > 0){
				int toDrain = Math.min(getCore().getTank(1).getFluidAmount(), 1000);
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
		if(getCore() != null)return ((IInventory) getCore()).isItemValidForSlot(var1, var2);
		return false;
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

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		if(getCore() != null){
			int size = getCore().getSize();
			List<Integer> s = new ArrayList<Integer>();
			int space = 0;
			if(size == 1)space = 1;
			if(size == 2)space = 9;
			if(size == 3)space = 25;
			for(int g=0;g<25;g++){
				if(getCore().isSlotinSpace(g,space))s.add(g);
			}
			int[] h = new int[s.size()];
			for(int j=0;j<h.length;j++){
				h[j] = s.get(j);
			}
			return h;
		}
		return new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side){
		if(getCore() != null){
			int size = getCore().getSize();
			int space = 0;
			if(size == 1)space = 1;
			if(size == 2)space = 9;
			if(size == 3)space = 25;
			if(getCore().isSlotinSpace(slot,space))	return true;
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item,
			int side) {
		return true;
	}
}

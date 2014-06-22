package common.cout970.UltraTech.multiblocks.refinery;

import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.fluids.UT_Tank;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.managers.BlockManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;


public class RefineryBase extends TileGag implements IFluidHandler{

	public UT_Tank tank;
	public int mode = 0;
	/**
	 * mode
	 * 0 nothing
	 * 1 fluid in
	 * 2 fluid out1
	 * 3 fluid out2
	 * 4 fluid out3
	 */
	
	//TANK
	
	public UT_Tank getTank(){
		if(tank == null)tank = new UT_Tank(4000, this);
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
	
	//work

	public void changeMode() {
		if(mode < 4){
			mode += 1;
		}else mode = 0;
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
}

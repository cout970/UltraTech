package common.cout970.UltraTech.multiblocks.refinery;

import common.cout970.UltraTech.managers.BlockManager;

import api.cout970.UltraTech.fluids.UT_Tank;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class OutRef extends TileGag implements IFluidHandler{

	public void restaureBlock(){}
	public int height = -1;

	public UT_Tank getTank(){
		if(main == null)return null;
		return main.getTank(getLevel());
	}

	private int getLevel() {
		if(height == -1)height = getHeight(this);
		if(height == 1 || height == 2 || height == 3)return 0;
		if(height == 4 || height == 5 || height == 6)return 1;
		if(height == 7 || height == 8)return 2;
		return 0;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(main == null)return null;
		return getTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(main == null)return null;
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(main == null)return new FluidTankInfo[]{};
		return new FluidTankInfo[]{new FluidTankInfo(getTank())};
	}

	public int getHeight(OutRef t) {
		for(int j=0;j<8;j++){
			if(worldObj.getBlock(t.xCoord, t.yCoord-j, t.zCoord) == BlockManager.Refinery){
				if(worldObj.getBlockMetadata(t.xCoord, t.yCoord-j, t.zCoord) == 5){
					return j;
				}
			}
		}
		return -1;
	}
}

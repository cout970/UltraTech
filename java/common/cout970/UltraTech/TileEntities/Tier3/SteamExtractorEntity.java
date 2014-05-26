package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.fluids.UT_Tank;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.multiblocks.TileReactorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class SteamExtractorEntity extends TileReactorPart implements IFluidHandler{

	private UT_Tank steam;
	public List<IFluidHandler> Tanks = null;
	
	
	public UT_Tank getTank(){
		if(steam == null)steam = new UT_Tank(5000, this);
		return steam;
	}

	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			if(Tanks == null)getTanks();
			if(Reactor != null){
				for(int v=0;v<10;v++)if(Reactor.steam >= 100){
					Reactor.steam -= getTank().fill(new FluidStack(FluidRegistry.getFluid("steam"),100), true);
				}
			}
			
			for(int v=0;v<10;v++)for(IFluidHandler b : Tanks){
				if(getTank().getFluidAmount() > 100)
					getTank().drain(b.fill(ForgeDirection.UP , new FluidStack(FluidRegistry.getFluid("steam"),100), true),true);
			}
			
		}
		super.updateEntity();
	}

	private void getTanks() {
		Tanks = new ArrayList<IFluidHandler>();
		for(TileEntity te : UT_Utils.getTiles(getWorldObj(), xCoord, yCoord, zCoord)){
			if(te instanceof IFluidHandler){
				Tanks.add((IFluidHandler) te);
			}
		}
	}
	
	@Override 
	public void onNeighChange(){
		super.onNeighChange();
		Tanks = null;
	}

	//tank

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource != null && resource.fluidID == FluidRegistry.getFluidID("steam"))return getTank().fill(resource, doFill);
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		if(resource.fluidID == FluidRegistry.getFluidID("steam"))return getTank().drain(resource.amount, doDrain);
		return null;
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

	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if(worldObj != null)getTank().readFromNBT(nbtTagCompound, "steam");
	}


	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound, "steam");
	}
}

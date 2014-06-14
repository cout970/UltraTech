package common.cout970.UltraTech.TileEntities.utility;

import java.util.HashMap;
import java.util.Map;

import api.cout970.UltraTech.fluids.FluidUtils;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.multiblocks.TileReactorPart;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class WaterBlockEntity extends TileReactorPart implements IFluidHandler{

	Map<ForgeDirection,IFluidHandler> tanks;

	public WaterBlockEntity(){
		super();
	}

	public void updateEntity(){
		if(tanks == null)updateTanks();
		if(FluidRegistry.WATER != null)
			for(ForgeDirection f : ForgeDirection.VALID_DIRECTIONS){
				if(tanks.containsKey(f))tanks.get(f).fill(f.getOpposite(), new FluidStack(FluidRegistry.WATER,EnergyCosts.WaterBlockProduct), true);
			}
	}

	private void updateTanks() {
		tanks = new HashMap<ForgeDirection,IFluidHandler>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity te = FluidUtils.getRelative(this, d);
			if(te instanceof IFluidHandler){
				tanks.put(d,(IFluidHandler) te);
			}
		}
	}
	
	//fluids

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
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
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{};
	}
}

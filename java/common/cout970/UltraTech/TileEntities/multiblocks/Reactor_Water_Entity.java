package common.cout970.UltraTech.TileEntities.multiblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.cout970.UltraTech.managers.InformationManager;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.TankConection;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class Reactor_Water_Entity extends Reactor_Entity_Base implements IFluidHandler{

	List<TankConection> tanks;

	public Reactor_Water_Entity(){
		super();
	}

	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(tanks == null)updateTanks();
		if(FluidRegistry.WATER != null)
			for(TankConection tc : tanks){
				tc.tank.fill(tc.side, new FluidStack(FluidRegistry.WATER,InformationManager.WaterBlockProduct), true);
			}
	}
	
	@Override
	public void onNeigUpdate() {
		super.onNeigUpdate();
		updateTanks();
	}

	private void updateTanks() {
		tanks = new ArrayList<TankConection>();
		tanks.addAll(FluidUtils.getTankConections(this));
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

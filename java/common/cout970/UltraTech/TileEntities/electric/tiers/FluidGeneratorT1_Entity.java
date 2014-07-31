package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.HashMap;
import java.util.Map;

import buildcraft.api.fuels.IronEngineFuel;
import buildcraft.api.fuels.IronEngineFuel.Fuel;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.PowerExchange;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.ConfigurableMachine;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.power.Machine;

public class FluidGeneratorT1_Entity extends ConfigurableMachine implements IFluidHandler{

	public UT_Tank storage;
	public float progres = 0;
	public PowerExchange pe = new PowerExchange();
	public double production;
	public boolean updated;
	
	public FluidGeneratorT1_Entity(){
		super(MachineData.Fluid_Generator);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(progres <= 0){
			if(getTank().getFluidAmount() >= 10){
				Fuel f = IronEngineFuel.getFuelForFluid(getTank().getFluid().getFluid());
				if(f != null){
					double prod = pe.FTtoMev(f.powerPerCycle)*(f.totalBurningTime/100);
					if(spaceForCharge(prod) || getCapacity() < prod){
						progres = f.totalBurningTime/100;
						production = pe.FTtoMev(f.powerPerCycle);
						getTank().drain(10, true);
					}
				}
			}
			if(progres <= 0 && updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
				updated = false;
			}
		}
		if(progres > 0){
			if(!updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
				updated = true;
			}
			if(progres >= 1){
				progres -= 1;
				addCharge(production);
			}else{
				addCharge(production*progres);
				progres = 0;
			}
		}
	}

	protected UT_Tank getTank() {
		if(storage == null)storage = new UT_Tank(2000, this);
		return storage;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null)return 0;
		if(IronEngineFuel.getFuelForFluid(resource.getFluid()) == null)return 0;
		return getTank().fill(resource, doFill);
	}	

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource == null)return null;
		return drain(from, resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return IronEngineFuel.getFuelForFluid(fluid) != null;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{getTank().getInfo()};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		getTank().readFromNBT(nbtTagCompound, "liquid");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound, "liquid");
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		if(getTank().getFluidAmount() > 0){
			c.sendProgressBarUpdate(cont, 2, getTank().getFluid().fluidID);
			c.sendProgressBarUpdate(cont, 3, getTank().getFluidAmount());
		}
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)getTank().setFluid(new FluidStack(value,0));
		if(id == 3)getTank().setFluidAmount(value);
	}

}

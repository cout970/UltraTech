package common.cout970.UltraTech.TileEntities.electric;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.power.Machine;

public class FluidGeneratorEntity extends Machine implements IFluidHandler{

	public UT_Tank storage;
	public boolean on;
	public int delay = 0;
	public static Map<String,Integer> fuels = new HashMap<String,Integer>();//{"bioethanol","fuel"};

	public void createTank(){
		storage = new UT_Tank(8000, this);
	}
	
	public FluidGeneratorEntity(){
		super(MachineData.Dynamo);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(storage == null)createTank();
		if(worldObj.isRemote)return;
		delay++;
		if(storage.getFluidAmount() >= 10 && getCharge()+fuels.get(storage.getFluid().getFluid().getName()) <= getCapacity()){
			if(!on && delay > 20){
				delay = 0;
				on = true;
				Net_Utils.sendUpdate(this);
			}
			this.addCharge(fuels.get(storage.getFluid().getFluid().getName()));
			this.storage.drain(10, true);
		}else{
			if(on && delay > 20){
				delay = 0;
				on = false;
				Net_Utils.sendUpdate(this);
			}
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(storage == null)createTank();
		boolean canAcept = false;
			if(fuels.containsKey((resource.getFluid().getName())))canAcept = true;
		if(!canAcept)return 0;
		return storage.fill(resource, doFill);
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
		return new FluidTankInfo[]{new FluidTankInfo(storage)};
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if(storage == null)createTank();
		storage.readFromNBT(nbtTagCompound, "liquid");
		on = nbtTagCompound.getBoolean("on");
		if(worldObj != null)worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		if(storage == null)createTank();
		storage.writeToNBT(nbtTagCompound, "liquid");
		nbtTagCompound.setBoolean("on", on);
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		if(storage.getFluid() != null)c.sendProgressBarUpdate(cont, 4, storage.getFluid().fluidID);
		c.sendProgressBarUpdate(cont, 5, storage.getFluidAmount());
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 4)storage.setFluid(new FluidStack(value,0));
		if(id == 5)storage.setFluidAmount(value);
	}

}

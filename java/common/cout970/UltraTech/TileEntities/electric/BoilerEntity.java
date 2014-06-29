package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.MeVpower.CableType;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.fluids.UT_Tank;
import api.cout970.UltraTech.network.SyncTile;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.lib.recipes.Boiler_Recipes;
import common.cout970.UltraTech.misc.IUpdatedEntity;

public class BoilerEntity extends SyncTile implements IFluidHandler,IUpdatedEntity{

	public UT_Tank result;
	public UT_Tank storage;
	public Heater_Entity heatProvider;
	
	public void updateEntity(){
		super.updateEntity();
		if(result == null)result = new UT_Tank(16000, worldObj, xCoord, yCoord, zCoord);
		if(storage == null)storage = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(worldObj.isRemote)return;

		if(heatProvider != null && heatProvider.Heat >= 100 && storage != null){
			if(storage.getFluidAmount() >= 10 && result.getFluidAmount()+100 <= result.getCapacity()){
				Fluid s = Boiler_Recipes.getResult(storage.getFluid());
				if(s != null){
					storage.drain(10, true);
					FluidStack t = new FluidStack(s,100);
					result.fill(t, true);
					heatProvider.Heat -= 1;
				}
			}
		}
	}
	
	//Fluids
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || storage == null)return 0;
		if(Boiler_Recipes.hasRecipe(resource)){
			return storage.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource == null || result == null)return null;
		if(result.getFluid() != null && Boiler_Recipes.isEqual(result.getFluid().getFluid(),resource.getFluid())){
			return result.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(maxDrain == 0 || result == null)return null;
		return result.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(Boiler_Recipes.hasRecipe(new FluidStack(fluid, 0)))return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(result.getFluid() != null && Boiler_Recipes.isEqual(fluid,result.getFluid().getFluid()))return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(result == null || storage == null)return new FluidTankInfo[]{}; 
		return new FluidTankInfo[]{result.getInfo(),storage.getInfo()}; 
	}
		
	//Save & Load
	
		@Override
		public void readFromNBT(NBTTagCompound nbtTagCompound) {
			super.readFromNBT(nbtTagCompound);
			if(storage == null)storage = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
			if(result == null)result = new UT_Tank(16000, worldObj, xCoord, yCoord, zCoord);
			storage.readFromNBT(nbtTagCompound, "storage");
			result.readFromNBT(nbtTagCompound, "result");
		}

		@Override
		public void writeToNBT(NBTTagCompound nbtTagCompound) {
			super.writeToNBT(nbtTagCompound);
			if(storage == null)storage = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
			if(result == null)result = new UT_Tank(16000, worldObj, xCoord, yCoord, zCoord);
			storage.writeToNBT(nbtTagCompound, "storage");
			result.writeToNBT(nbtTagCompound, "result");
		}
		
		//Synchronization

		public void sendGUINetworkData(Container cont, ICrafting c) {
			super.sendGUINetworkData(cont, c);
			if(storage.getFluid() != null)c.sendProgressBarUpdate(cont, 5, storage.getFluid().fluidID);
			if(result.getFluid() != null)c.sendProgressBarUpdate(cont, 6, result.getFluid().fluidID);
			c.sendProgressBarUpdate(cont, 2, storage.getFluidAmount());
			c.sendProgressBarUpdate(cont, 3, result.getFluidAmount());
		}

		public void getGUINetworkData(int id, int value) {
			super.getGUINetworkData(id, value);
			if(id == 2)storage.setFluidAmount(value);
			if(id == 3)result.setFluidAmount(value);
			if(id == 5)storage.setFluid(new FluidStack(value, 1));
			if(id == 6)result.setFluid(new FluidStack(value, 1));
		}

		public CableType getConection(ForgeDirection side) {
			return CableType.BIG_CENTER;
		}

		@Override
		public void onNeigUpdate() {
			for(TileEntity t : UT_Utils.getTiles(this)){
				if(t instanceof Heater_Entity)heatProvider = (Heater_Entity) t;
			}
			System.out.println("update! "+(heatProvider != null));
		}
}

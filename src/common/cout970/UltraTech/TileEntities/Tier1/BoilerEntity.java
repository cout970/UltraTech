package common.cout970.UltraTech.TileEntities.Tier1;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.Boiler_Recipes;

public class BoilerEntity extends Machine implements IFluidHandler{

	public UT_Tank result;
	public UT_Tank storage;
	public float heat = 25;
	
	public void updateEntity(){
		super.updateEntity();
		if(result == null)result = new UT_Tank(16000, worldObj, xCoord, yCoord, zCoord);
		if(storage == null)storage = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(worldObj.isRemote)return;
		if(heat > 25)heat -= heat/1200;
		if(heat > 100 && storage != null){
			if(storage.getFluid() != null && storage.getFluidAmount() > 10){
				Fluid s = Boiler_Recipes.getResult(storage.getFluid());
				if(s != null){
					storage.drain(10, true);
					FluidStack t = new FluidStack(s,100);
					result.fill(t, true);
					heat-= 0.2f;
				}
			}
		}
		if(heat <= 150){
			if(getEnergy() >= EnergyCosts.BoilerCost){
				heat+= 0.3f;
				this.removeEnergy(EnergyCosts.BoilerCost);
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
			c.sendProgressBarUpdate(cont, 4, (int) heat);
			

		}

		public void getGUINetworkData(int id, int value) {
			super.getGUINetworkData(id, value);
			if(id == 2)storage.setFluidAmount(value);
			if(id == 3)result.setFluidAmount(value);
			if(id == 4)heat = value;
			if(id == 5)storage.setFluid(new FluidStack(value, 1));
			if(id == 6)result.setFluid(new FluidStack(value, 1));
		}

}

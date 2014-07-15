package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.managers.FluidManager;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.recipes.Fermenter_Recipes;
import common.cout970.UltraTech.util.MachineWithInventory;
import common.cout970.UltraTech.util.fluids.UT_Tank;

public class FermenterEntity extends MachineWithInventory implements  IFluidHandler{

	public FermenterEntity() {
		super(1, "Fermenter",MachineData.Fermenter);
	}

	public UT_Tank water = null;
	public UT_Tank juice = null;
	public int progres = 0;
	public int maxProgres = 1;
	
	
	public void updateEntity(){
		if(water == null)water = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(juice == null)juice = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(worldObj.isRemote)return;
		if(progres <= 0){
			if(getStackInSlot(0) != null && Fermenter_Recipes.hasRecipe(getStackInSlot(0))){
				progres = Fermenter_Recipes.getTicks(getStackInSlot(0));
				maxProgres = Fermenter_Recipes.getTicks(getStackInSlot(0));
				decrStackSize(0, 1);
			}
		}
		if(progres > 0){
			if(juice.getFluidAmount() + 5 <= juice.getCapacity() && water.getFluidAmount() >= 10 && getCharge() >= MachineData.Fermenter.use){
				progres--;
				juice.fill(new FluidStack(FluidManager.Juice, 5), true);
				water.drain(10, true);
				removeCharge(MachineData.Fermenter.use*5);
			}
		}
	}

	//Fluids

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || water == null)return 0;
		if(FluidRegistry.getFluidName(resource).equals(FluidRegistry.WATER.getName())){
			return water.fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		if(resource == null || juice == null)return null;
		if(FluidRegistry.getFluidName(resource.fluidID) == "juice"){
			return juice.drain(resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(maxDrain == 0 || juice == null)return null;
		return juice.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(fluid == null)return false;
		if(fluid.getName() == "water"){
			if(water.getFluidAmount() < water.getCapacity()) return true;
		}
		
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(fluid.getName() == "juice")return true;
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(water == null || juice == null)return new FluidTankInfo[]{}; 
		return new FluidTankInfo[]{water.getInfo(),juice.getInfo()}; 
	}

	//Inventory

	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(Fermenter_Recipes.hasRecipe(itemstack))return true;
		return false;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		if(water == null)water = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(juice == null)juice = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		water.readFromNBT(nbtTagCompound, "water");
		juice.readFromNBT(nbtTagCompound, "juice");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		if(water == null)water = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(juice == null)juice = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		water.writeToNBT(nbtTagCompound, "water");
		juice.writeToNBT(nbtTagCompound, "juice");
	}
	
	//Synchronization

	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, water.getFluidAmount());
		c.sendProgressBarUpdate(cont, 3, juice.getFluidAmount());
		c.sendProgressBarUpdate(cont, 4, progres);
		c.sendProgressBarUpdate(cont, 5, maxProgres);
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)water.setFluid(new FluidStack(FluidRegistry.WATER, value));
		if(id == 3)juice.setFluid(new FluidStack(FluidRegistry.getFluid("juice"), value));
		if(id == 4)progres = value;
		if(id == 5)maxProgres = value;
	}
}

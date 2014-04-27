package common.cout970.UltraTech.TileEntities.Tier1;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.fluid.api.UT_Tank;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.Fermenter_Recipes;
import common.cout970.UltraTech.managers.BlockManager;

public class FermenterEntity extends Machine implements IInventory, IFluidHandler{

	public UT_Tank water = null;
	public UT_Tank juice = null;
	private ItemStack item;
	public int progres = 0;
	public int maxProgres = 1;
	
	
	public void updateEntity(){
		if(water == null)water = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(juice == null)juice = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(worldObj.isRemote)return;
		if(progres <= 0){
			if(item != null && Fermenter_Recipes.hasRecipe(item)){
				progres = Fermenter_Recipes.getTicks(item);
				maxProgres = Fermenter_Recipes.getTicks(item);
				decrStackSize(0, 1);
			}
		}
		if(progres > 0){
			if(juice.getFluidAmount() + 10 <= juice.getCapacity() && water.getFluidAmount() > 10 && getEnergy() >= EnergyCosts.FermenterCost){
				progres--;
				juice.fill(new FluidStack(BlockManager.Juice, 10), true);
				water.drain(10, true);
				removeEnergy(EnergyCosts.FermenterCost);
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
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return item;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack itemStack = getStackInSlot(0);
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			}
			else {
				itemStack = itemStack.splitStack(amount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (item != null) {
			ItemStack itemStack = item;
			item = null;
			return itemStack;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		item = itemstack;
	}

	@Override
	public String getInvName() {
		return "Fermenter";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(Fermenter_Recipes.hasRecipe(item))return true;
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
		NBTTagCompound nbt = nbtTagCompound.getCompoundTag("Inv");
		if(nbt != null)item = ItemStack.loadItemStackFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		if(water == null)water = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		if(juice == null)juice = new UT_Tank(8000, worldObj, xCoord, yCoord, zCoord);
		water.writeToNBT(nbtTagCompound, "water");
		juice.writeToNBT(nbtTagCompound, "juice");
		if(item != null){
			NBTTagCompound nbt = new NBTTagCompound();
			item.writeToNBT(nbt);
			nbtTagCompound.setTag("Inv", nbt);
		}
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

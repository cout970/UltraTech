package common.cout970.UltraTech.fluid.api;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;

public class UT_Tank implements IFluidTank{

	private FluidStack fluid;
	private int capacity = 0;
	private int x,y,z;
	private World w; 
	
	public UT_Tank(int cap ,World w,int x, int y, int z){
		capacity = cap;
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public UT_Tank(int cap, TileEntity entity) {
		capacity = cap;
		this.w = entity.worldObj;
		this.x = entity.xCoord;
		this.y = entity.yCoord;
		this.z = entity.zCoord;
	}

	@Override
	public FluidStack getFluid() {
		return fluid;
	}

	@Override
	public int getFluidAmount() {
		if(fluid == null)return 0;
		return fluid.amount;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public FluidTankInfo getInfo() {
		return new FluidTankInfo(this);
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		if (resource == null)return 0;
		if (!doFill){
			if (fluid == null){
				return Math.min(capacity, resource.amount);
			}
			if (!fluid.isFluidEqual(resource)){
				return 0;
			}
			return Math.min(capacity - fluid.amount, resource.amount);
		}
		if (fluid == null){
			fluid = new FluidStack(resource, Math.min(capacity, resource.amount));
			
			FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.w, this.x, this.y, this.z, this));

			return fluid.amount;
		}
		if (!fluid.isFluidEqual(resource)){
			return 0;
		}
		int filled = capacity - fluid.amount;
		if (resource.amount < filled)
		{
			fluid.amount += resource.amount;
			filled = resource.amount;
		}else{
			fluid.amount = capacity;
		}

		FluidEvent.fireEvent(new FluidEvent.FluidFillingEvent(fluid, this.w, this.x, this.y, this.z, this));
		return filled;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (fluid == null)return null;
		int drained = maxDrain;

		if (fluid.amount < drained){
			drained = fluid.amount;
		}
		FluidStack stack = new FluidStack(fluid, drained);
		if (doDrain){
			fluid.amount -= drained;
			if (fluid.amount <= 0){
				fluid = null;
			}
			FluidEvent.fireEvent(new FluidEvent.FluidDrainingEvent(fluid, this.w, this.x, this.y, this.z, this));
		}
		return stack;
	}

	public void readFromNBT(NBTTagCompound nbt, String liq) {
		NBTTagList tagList = nbt.getTagList("UT_Tank"+liq);
		if(tagList == null)return;
		if(tagList.tagCount() == 0){
			fluid = null;
			return;
		}
		NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(0);
		fluid = new FluidStack(tag.getInteger("Fluid"), tag.getInteger("Amount"));
	}
	
	public void writeToNBT(NBTTagCompound nbt,String liq) {
		if(fluid != null){
			NBTTagList list = new NBTTagList();
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("Fluid", fluid.fluidID);
			tag.setInteger("Amount", fluid.amount);
			list.appendTag(tag);
			nbt.setTag("UT_Tank"+liq, list);
		}
	}

	public void setFluid(FluidStack fluidStack) {
		fluid = fluidStack;
	}

	public void setFluidAmount(int value) {
		if(fluid != null)fluid.amount = value;
	}

}
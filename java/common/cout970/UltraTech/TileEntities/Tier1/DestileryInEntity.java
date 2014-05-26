package common.cout970.UltraTech.TileEntities.Tier1;

import api.cout970.UltraTech.fluids.UT_Tank;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.multiblocks.TileDestilery;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class DestileryInEntity extends TileDestilery implements IFluidHandler{

	public UT_Tank Gas;

	public DestileryInEntity(){
		super();
		Gas = new UT_Tank(8000,this);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(getMulti() != null && getMulti().getIn().getTank().getFluid() !=null){
			int ini = Cooling_Recipes.getInit(getMulti().getIn().getTank().getFluid());
			FluidStack[] res = Cooling_Recipes.getResult(getMulti().getIn().getTank().getFluid());
			if(ini != 0 && res != null){
				if(getMulti().getIn().getTank().getFluid().amount < ini)return;
				int a1 = 0,a2 = 0,a3 = 0;
				if(res[0]!= null){
					boolean c = false;
					for(DestileryOutEntity o : getMulti().getOut(0)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() > res[0].amount){
							c = true;
						}
					}
					if(!c)return;
				}
				if(res[1]!= null){
					boolean c = false;
					for(DestileryOutEntity o : getMulti().getOut(1)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() > res[1].amount){
							c = true;
						}
					}
					if(!c)return;
				}
				if(res[2]!= null){
					boolean c = false;
					for(DestileryOutEntity o : getMulti().getOut(2)){
						if(o.getTank().getCapacity()-o.getTank().getFluidAmount() > res[2].amount){
							c = true;
						}
					}
					if(!c)return;
				}
				//all good
				getMulti().getIn().getTank().drain(ini, true);
				if(getMulti().getOut(0).size()>0)getMulti().getOut(0).get(a1).getTank().fill(res[0], true);
				if(getMulti().getOut(1).size()>0)getMulti().getOut(1).get(a2).getTank().fill(res[1], true);
				if(getMulti().getOut(2).size()>0)getMulti().getOut(2).get(a3).getTank().fill(res[2], true);
			}
		}
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource == null || getTank() == null)return 0;
		if(Cooling_Recipes.hasRecipe(resource)){
			return getTank().fill(resource, doFill);
		}
		return 0;
	}

	public UT_Tank getTank(){
		if(Gas == null){
			Gas = new UT_Tank(8000,this);
		}
		return Gas;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if(Cooling_Recipes.hasRecipe(new FluidStack(fluid,0)))return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(getTank() == null)return new FluidTankInfo[]{}; 
		return new FluidTankInfo[]{new FluidTankInfo(getTank())}; 
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		getTank().readFromNBT(nbtTagCompound, "gas");
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		getTank().writeToNBT(nbtTagCompound, "gas");
	}
}

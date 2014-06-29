package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.misc.MachineWithInventory;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.fluids.UT_Tank;

public class ChemicalPlantEntity extends MachineWithInventory implements IFluidHandler{

	public UT_Tank tank;
	public int Progres = 0;
	public boolean fuel = false;
	
	public ChemicalPlantEntity() {
		super(3, "Chemical Plant", CostData.ChemicalPlant);
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(!fuel){
			FluidStack f = getTank().getFluid();
			if(f != null && FluidRegistry.getFluid("plastic") == f.getFluid() && f.amount >= 1000){
				if(getEnergy() >= CostData.ChemicalPlant.use)fuel = true;
			}
		}
		if(fuel)Progres +=1;
		if(Progres == 40){
			Progres = 0;
			fuel = false;
			craft();
		}
	}
	
	public void craft(){
		getTank().drain(1000, true);
		setInventorySlotContents(0, new ItemStack(ItemManager.ItemName.get("Sulfur"),1));
		setInventorySlotContents(1, new ItemStack(ItemManager.ItemName.get("Rubber"),2));
		setInventorySlotContents(2, new ItemStack(ItemManager.ItemName.get("Plastic"),2));
	}
	
	public UT_Tank getTank(){
		if(tank == null)tank = new UT_Tank(4000, this);
		return tank;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return getTank().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return getTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{getTank().getInfo()};
	}

	
}

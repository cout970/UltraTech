package common.cout970.UltraTech.TileEntities.electric.tiers;

import scala.sys.process.processInternal;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.MachineWithInventory;
import common.cout970.UltraTech.util.fluids.TankUT;
import common.cout970.UltraTech.util.power.Machine;

public class TileEntityChemicalPlantT1 extends ConfigurableMachineWithInventory implements IFluidHandler{

	public TankUT tank;
	public int Progres = 0;
	public boolean fuel = false;
	public int maxProgres = 500;
	
	public TileEntityChemicalPlantT1() {
		super(3, "Chemical Plant", MachineData.ChemicalPlant);
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(!fuel){
			FluidStack f = getTank().getFluid();
			if(f != null && FluidRegistry.getFluid("plastic") == f.getFluid() && f.amount >= 1000){
				if(getCharge() >= MachineData.ChemicalPlant.use && shouldWork())
					if(getStackInSlot(0) == null || getStackInSlot(0).stackSize+2 <= 64)
						if(getStackInSlot(1) == null || getStackInSlot(1).stackSize+2 <= 64)
							if(getStackInSlot(2) == null || getStackInSlot(2).stackSize+1 <= 64){
								fuel = true;
								Progres = maxProgres;
							}
			}
		}
		if(fuel){
			Progres -=1;
			removeCharge(MachineData.ChemicalPlant.use/maxProgres);
			getTank().drain(1000/maxProgres, true);
			if(Progres <= 0){
				fuel = false;
				craft();
				markDirty();
			}
		}
	}
	
	public void craft(){
		ItemStack a = getStackInSlot(0);
		if(a == null)setInventorySlotContents(0, new ItemStack(ItemManager.ItemName.get("Sulfur"),1));
		else{
			int a1 = a.stackSize + 1;
			setInventorySlotContents(0, new ItemStack(ItemManager.ItemName.get("Sulfur"),a1));
		}
		ItemStack b = getStackInSlot(1);
		if(b == null)setInventorySlotContents(1, new ItemStack(ItemManager.ItemName.get("Rubber"),2));
		else{
			int b1 = b.stackSize + 2;
			setInventorySlotContents(1, new ItemStack(ItemManager.ItemName.get("Rubber"),b1));
		}
		ItemStack c = getStackInSlot(2);
		if(c == null)setInventorySlotContents(2, new ItemStack(ItemManager.ItemName.get("Plastic"),1));
		else{
			int c1 = c.stackSize + 1;
			setInventorySlotContents(2, new ItemStack(ItemManager.ItemName.get("Plastic"),c1));
		}
	}
	
	public TankUT getTank(){
		if(tank == null)tank = new TankUT(4000, this);
		return tank;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return getTank().fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(fuel)return null;
		return getTank().drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		if(fuel)return null;
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		if(fuel)return false;
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{getTank().getInfo()};
	}

	//Synchronization

	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		if(getTank().getFluid() != null)c.sendProgressBarUpdate(cont, 2, getTank().getFluid().fluidID);
		c.sendProgressBarUpdate(cont, 3, getTank().getFluidAmount());
		c.sendProgressBarUpdate(cont, 4, Progres);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 3)getTank().setFluidAmount(value);
		if(id == 2)getTank().setFluid(new FluidStack(value, 1));
		if(id == 4)Progres = value;
	}
}

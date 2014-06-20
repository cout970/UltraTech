package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.lib.CostData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.fluids.UT_Tank;

public class LavaGeneratorEntity extends Machine implements IFluidHandler{

	public UT_Tank lava;
	private int Proces;
	
	public LavaGeneratorEntity() {
		super(CostData.LavaGenerator);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(Proces <=0 && getTank().getFluidAmount() >= 50){
			Proces = 25;
			getTank().drain(50, true);
		}
		if(Proces > 0){
			double space = maxEnergy()-getEnergy();
			if(space >= CostData.LavaGenerator.use*2){
				Proces--;
				addEnergy(CostData.LavaGenerator.use*2);
			}
		}
	}
	
	public UT_Tank getTank(){
		if(lava == null)lava = new UT_Tank(4000,this);
		return lava;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(resource != null && resource.fluidID == FluidRegistry.getFluidID("lava")){
			return getTank().fill(resource, doFill);
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		if(resource != null){
			return this.drain(from, resource.amount, doDrain);
		}
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return getTank().drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == FluidRegistry.getFluidID("lava");
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[]{getTank().getInfo()};
	}

}

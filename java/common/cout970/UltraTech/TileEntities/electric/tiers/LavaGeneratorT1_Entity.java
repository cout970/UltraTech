package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class LavaGeneratorT1_Entity extends Machine implements IFluidHandler,IUpdatedEntity{

	public UT_Tank lava;
	private int Proces;
	public float coolant = 0;//kelvin degrees
	public int heat = 295;
	
	public LavaGeneratorT1_Entity() {
		super(MachineData.LavaGenerator);
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(Proces <=0 && getTank().getFluidAmount() >= 20){
			Proces = 20;
			getTank().drain(20, true);
		}
		if(Proces > 0){
			double space = getCapacity()-getCharge();
			if(space >= MachineData.LavaGenerator.use){
				Proces--;
				addCharge(MachineData.LavaGenerator.use);
			}
		}
		
		if(heat > 0){
			float product = 0;
			float cal = heat-295;
			float dif = cal*coolant;
			product = dif/4000;
			addCharge(MachineData.LavaGenerator.use*0.05f*product);
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

	@Override
	public void onNeigUpdate() {
		heat = 295;
		coolant = 0;
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			Block b = worldObj.getBlock(xCoord+d.offsetX, yCoord+d.offsetY, zCoord+d.offsetZ);
			Fluid f = FluidRegistry.lookupFluidForBlock(b);
			if(f != null){
				int temp = f.getTemperature();
				if(temp > 295){
					heat = Math.max(heat, temp);
				}else{
					if(temp != 0)coolant += 295/temp;
				}
			}
		}
		
	}

}

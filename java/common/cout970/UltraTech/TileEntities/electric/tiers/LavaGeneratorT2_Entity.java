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

public class LavaGeneratorT2_Entity extends LavaGeneratorT1_Entity implements IFluidHandler,IUpdatedEntity{

	private int Proces;
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(Proces <=0 && getTank().getFluidAmount() >= 100){
			Proces = 20;
			getTank().drain(100, true);
		}
		if(Proces > 0){
			double space = getCapacity()-getCharge();
			if(space >= MachineData.LavaGenerator.use*5){
				Proces--;
				addCharge(MachineData.LavaGenerator.use*5);
			}
		}
		
		if(heat > 0){
			float product = 0;
			float cal = heat-295;
			float dif = cal*coolant;
			product = dif/4000;
			addCharge(MachineData.LavaGenerator.use*0.1f*product);
		}
	}

}

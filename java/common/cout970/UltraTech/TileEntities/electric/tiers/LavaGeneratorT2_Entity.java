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

	public void applyProduction(){
		double space = getCapacity()-getCharge();
		if(space >= MachineData.LavaGenerator.use*5){
			Proces-= 5;
			addCharge(MachineData.LavaGenerator.use*5);
		}
	}
	
	public float getProduction(){
		float cal = heat-295;
		float dif = cal*coolant;
		return (dif/4000)*0.1f;
	}

}

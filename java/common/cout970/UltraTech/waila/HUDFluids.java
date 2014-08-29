package common.cout970.UltraTech.waila;

import java.util.List;

import ultratech.api.util.UT_Utils;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import common.cout970.UltraTech.TileEntities.electric.tiers.LavaGeneratorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LavaGeneratorT2_Entity;
import common.cout970.UltraTech.managers.MachineData;

public class HUDFluids implements IWailaDataProvider{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {

		if(accessor.getTileEntity() instanceof IFluidHandler){
			IFluidHandler p = ((IFluidHandler) accessor.getTileEntity());
			FluidTankInfo[] info = p.getTankInfo(accessor.getSide());
			for(FluidTankInfo f : info){
				FluidStack fl = f.fluid;
				if(fl == null){
					currenttip.add("0/"+f.capacity+" mB");
				}else{
					currenttip.add("Fluid: "+fl.getFluid().getLocalizedName(fl)+" "+fl.amount+"/"+f.capacity+" mB");
				}
			}
		}
		if(accessor.getTileEntity() instanceof LavaGeneratorT1_Entity){
			LavaGeneratorT1_Entity l = (LavaGeneratorT1_Entity) accessor.getTileEntity();
			currenttip.add("Heat: "+(l.heat-273)+" Coolant multiplier: "+l.coolant+" Production: "+UT_Utils.removeDecimals(l.getProduction()*MachineData.LavaGenerator.use)+" QP/t");
		}
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

}
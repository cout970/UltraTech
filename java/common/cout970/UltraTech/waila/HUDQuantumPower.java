package common.cout970.UltraTech.waila;

import java.util.List;

import common.cout970.UltraTech.TileEntities.intermod.TileEntityTransformer;
import common.cout970.UltraTech.util.LogHelper;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.StorageInterface;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.util.UT_Utils;
import net.minecraft.item.ItemStack;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;

public class HUDQuantumPower implements IWailaDataProvider{

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
		
		if(accessor.getTileEntity() instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) accessor.getTileEntity()).getPower();
			if(p instanceof StorageInterface){
				double charge = accessor.getNBTData().getDouble("Charge");
				currenttip.add(UT_Utils.removeDecimals(charge)+"/"+p.getCapacity()+" QP");
			}
			if(accessor.getTileEntity() instanceof TileEntityTransformer){
				TileEntityTransformer e = (TileEntityTransformer) accessor.getTileEntity();
				currenttip.add(UT_Utils.removeDecimals(e.EU)+"/"+e.maxEU+" EU");
			}
		}
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

}

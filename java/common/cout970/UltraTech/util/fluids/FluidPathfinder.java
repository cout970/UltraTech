package common.cout970.UltraTech.util.fluids;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public class FluidPathfinder {

	private List<IFluidTransport> visited = new ArrayList<IFluidTransport>();
	
	private IFluidTransport to;
	
	public FluidPathfinder(IFluidTransport from, IFluidTransport to) {
		visited.add(from);
		list(from);
		this.to = to;
	}
	
	private void list(IFluidTransport f){
		TileEntity from = f.getTileEntity();
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity tile = UT_Utils.getRelative(from, dir);
			if(tile != null){
				if(FluidUtils.isPipe(tile)){
					IFluidTransport e = FluidUtils.getFluidTransport(tile);
					if(e.canConectOnSide(dir.getOpposite()) && f.canConectOnSide(dir)){
						if(!visited.contains(e)){
							visited.add(e);
							list(e);
						}
					}
				}
			}
		}
	}

	public boolean canEnergyGoToEnd(){
		if(to == null)return false;
		return visited.contains(to);
	}
	
	
	public List<IFluidTransport> getPipes(){
		return visited;
	}
}

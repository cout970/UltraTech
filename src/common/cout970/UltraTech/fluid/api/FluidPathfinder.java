package common.cout970.UltraTech.fluid.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public class FluidPathfinder {

	private List<IFluidTransport> visited = new ArrayList<IFluidTransport>();
	private List<IFluidHandler> tanks = new ArrayList<IFluidHandler>();
	
	private IFluidTransport to;
	
	public FluidPathfinder(IFluidTransport from, IFluidTransport to) {
		visited.add(from);
		list(from);
		this.to = to;
	}
	
	private void list(IFluidTransport f){
		TileEntity from = f.getTileEntity();
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity tile = FluidUtils.getRelative(from, dir);
			if(tile != null){
				if(tile instanceof IFluidTransport){
					IFluidTransport e = (IFluidTransport) tile;
					if(!visited.contains(e)){
						visited.add(e);
						list(e);
					}
				}else if(tile instanceof IFluidHandler && !(tile instanceof IFluidTransport)){
				tanks.add((IFluidHandler) tile);
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
	
	public List<IFluidHandler> getTanks(){
		return tanks;
	}
}

package ultratech.api.power;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class DefaultPathFinder implements IPathFinder{

	public List<IPowerConductor> visited = new ArrayList<IPowerConductor>();
	public List<PowerInterface> excluded = new ArrayList<PowerInterface>();
	public IPowerConductor end;
	public IPowerConductor base;
	

	public void search(IPowerConductor base, IPowerConductor end) {
		this.end = end;
		this.base = base;
		visited.add(base);
		list(base.getPower());
	}
	
	public DefaultPathFinder() {}

	@Override
	public void list(PowerInterface t) {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
				if(t.getConnectionType(dir) != CableType.NOTHING){
					TileEntity tile = PowerUtils.getRelative(t.getParent(), dir);
					if(tile instanceof IPowerConductor){
						IPowerConductor e = (IPowerConductor) tile;
						if(e.getPower().isConnectableSide(dir.getOpposite(), t.getConnectionType(dir))){
							if(!excluded.contains(e.getPower()) && !visited.contains(e)){
								visited.add(e);
								this.list(e.getPower());
							}
						}
					}
				}
			}
	}

	@Override
	public List<IPowerConductor> getFinding() {
		return visited;
	}

	@Override
	public boolean canGoToTheEnd() {
		if(end == null)return false;
		return visited.contains(end);
	}

	@Override
	public IPowerConductor getEnd() {
		return end;
	}

	@Override
	public IPowerConductor getBase() {
		return base;
	}

	@Override
	public void clear() {
		visited.clear();
		excluded.clear();
		base = null;
		end = null;
	}

	@Override
	public void excludeAndRecalculate(IPowerConductor p) {
		for(TileEntity t : UT_Utils.getTiles(p.getPower().getParent())){
			if(t instanceof IPowerConductor){
				((IPowerConductor) t).getPower().getNetwork().refresh();
			}
		}
	}

}

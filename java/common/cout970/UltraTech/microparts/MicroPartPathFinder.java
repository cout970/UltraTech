package common.cout970.UltraTech.microparts;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import ultratech.api.power.CableType;
import ultratech.api.power.IPathFinder;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.PowerUtils;
import ultratech.api.util.UT_Utils;

public class MicroPartPathFinder implements IPathFinder{

	public List<IPowerConductor> visited = new ArrayList<IPowerConductor>();
	public List<PowerInterface> excluded = new ArrayList<PowerInterface>();
	public IPowerConductor end;
	public IPowerConductor base;
	
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
							list(e.getPower());
						}
					}
				}else{
					if(tile instanceof TileMultipart){
						TileMultipart m = (TileMultipart) tile;
						for(TMultiPart g : m.jPartList()){
							if(g instanceof IPowerConductor){
								IPowerConductor e = (IPowerConductor) g;
								if(e.getPower().isConnectableSide(dir.getOpposite(), t.getConnectionType(dir))){
									if(!excluded.contains(e.getPower()) && !visited.contains(e)){
										visited.add(e);
										list(e.getPower());
									}
								}
							}
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
	

	public void search(IPowerConductor base, IPowerConductor end) {
		this.end = end;
		this.base = base;
		visited.add(base);
		list(base.getPower());
	}
	
	public void excludeAndRecalculate(IPowerConductor p) {
		for(TileEntity t : UT_Utils.getTiles(p.getPower().getParent())){
			if(t instanceof IPowerConductor){
				((IPowerConductor) t).getPower().getNetwork().refresh();
			}else if(t instanceof TileMultipart){
				TileMultipart tm = (TileMultipart) t;
				for(TMultiPart s : tm.jPartList()){
					if(s instanceof IPowerConductor){
						((IPowerConductor) s).getPower().getNetwork().refresh();
						s.onNeighborChanged();
					}
				}
			}
		}
	}

}

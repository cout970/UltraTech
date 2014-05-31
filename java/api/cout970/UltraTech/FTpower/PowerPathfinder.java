package api.cout970.UltraTech.FTpower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import common.cout970.UltraTech.microparts.MicroCablePlane;

import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
/**
 * 
 * @author Cout970
 *
 */
public class PowerPathfinder {

	public List<IPowerConductor> visited = new ArrayList<IPowerConductor>();
	public List<PowerInterface> excluded = new ArrayList<PowerInterface>();
	public IPowerConductor end;
	
	public PowerPathfinder(IPowerConductor base, IPowerConductor end) {
		this.end = end;
		visited.add(base);
		list(base.getPower());
	}
	
	public PowerPathfinder(IPowerConductor base, IPowerConductor end,List<PowerInterface> excluded) {
		this.end = end;
		visited.add(base);
		list(base.getPower());
	}

	private void list(PowerInterface t){
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			if(t.getConnectionType(dir) != ConnType.NOTHING){
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


	public List<IPowerConductor> getFinding() {
		return visited;
	}

	public boolean canGoToTheEnd() {
		if(end == null)return false;
		return visited.contains(end);
	}

}

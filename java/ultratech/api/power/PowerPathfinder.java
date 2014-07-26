package ultratech.api.power;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.power.multipart.MicroPartUtil;
import ultratech.api.power.multipart.MultipartReference;
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

	public void list(PowerInterface t){
		if(MultipartReference.isMicroPartActived)MicroPartUtil.list(t,this);
		else{
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

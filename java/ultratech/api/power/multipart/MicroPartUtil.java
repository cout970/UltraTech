package ultratech.api.power.multipart;

import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.PowerPathfinder;
import ultratech.api.power.PowerUtils;
import common.cout970.UltraTech.microparts.MicroCableBig;
import common.cout970.UltraTech.microparts.MicroCablePlane;
import common.cout970.UltraTech.util.UT_Utils;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class MicroPartUtil {
	
	public static boolean isMicroPartActived = false;
	
	public static void list(PowerInterface t, PowerPathfinder p) {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			if(t.getConnectionType(dir) != CableType.NOTHING){
				TileEntity tile = PowerUtils.getRelative(t.getParent(), dir);
				if(tile instanceof IPowerConductor){
					IPowerConductor e = (IPowerConductor) tile;
					if(e.getPower().isConnectableSide(dir.getOpposite(), t.getConnectionType(dir))){
						if(!p.excluded.contains(e.getPower()) && !p.visited.contains(e)){
							p.visited.add(e);
							p.list(e.getPower());
						}
					}
				}else{
					if(tile instanceof TileMultipart){
						TileMultipart m = (TileMultipart) tile;
						for(TMultiPart g : m.jPartList()){
							if(g instanceof IPowerConductor){
								IPowerConductor e = (IPowerConductor) g;
								if(e.getPower().isConnectableSide(dir.getOpposite(), t.getConnectionType(dir))){
									if(!p.excluded.contains(e.getPower()) && !p.visited.contains(e)){
										p.visited.add(e);
										p.list(e.getPower());
									}
								}
							}
						}
					}
				}
			}
		}
		
	}

	public static boolean canConect(MicroCablePlane m,
			TileEntity tile, ForgeDirection o) {
		if(tile instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) tile).getPower();
			return p.isConnectableSide(o.getOpposite(), CableType.RIBBON_BOTTOM);
		}else if(tile instanceof TileMultipart){
			TileMultipart t = (TileMultipart) tile;
			for(TMultiPart s : t.jPartList()){
				if(s instanceof MicroCablePlane){
					return t.canAddPart(new NormallyOccludedPart(MicroCablePlane.boundingBoxes[o.getOpposite().ordinal()]));
				}
			}
		}
		return false;
	}

	public static boolean canConect(MicroCableBig m,
			TileEntity tile, ForgeDirection o) {
		if(tile instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) tile).getPower();
			return p.isConnectableSide(o.getOpposite(), CableType.BIG_CENTER);
		}else if(tile instanceof TileMultipart){
			TileMultipart t = (TileMultipart) tile;
			for(TMultiPart s : t.jPartList()){
				if(s instanceof MicroCableBig){
					return t.canAddPart(new NormallyOccludedPart(MicroCableBig.boundingBoxes[o.getOpposite().ordinal()]));
				}
			}
		}
		return false;
	}

	public static void excludeAndRecalculate(IPowerConductor p) {
		for(TileEntity t : UT_Utils.getTiles(p.getPower().getParent())){
			if(t instanceof IPowerConductor){
				((IPowerConductor) t).getPower().getNetwork().refresh();
			}else if(t instanceof TileMultipart){
				TileMultipart tm = (TileMultipart) t;
				for(TMultiPart s : tm.jPartList()){
					if(s instanceof IPowerConductor){
						((IPowerConductor) s).getPower().getNetwork().refresh();
					}
				}
			}
		}
	}
}

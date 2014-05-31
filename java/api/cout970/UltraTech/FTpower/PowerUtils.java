package api.cout970.UltraTech.FTpower;

import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import common.cout970.UltraTech.microparts.MicroCableBig;
import common.cout970.UltraTech.microparts.MicroCablePlane;
import api.cout970.UltraTech.fluids.UT_Tank;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * 
 * @author Cout970
 *
 */
public class PowerUtils {

	/**
	 * 
	 * @param a machine that lose charge
	 * @param b machine that gain charge
	 */
	public static void MoveCharge(IPowerConductor a, IPowerConductor b){
		if(a == null || b == null)return;
		StorageInterface from = (StorageInterface) a.getPower();
		StorageInterface to = (StorageInterface) b.getPower();
		if(from == null || to == null)return;
		if(from.equals(to))return;
		if(from.getCharge() > 0){
			double space = to.maxCharge()-to.getCharge();
			double flow = Math.min(from.getFlow(), to.getFlow());
			if(space > 0){
				if(from.getNetwork().canPowerGoToEnd(a,b)){
					if(from.getCharge() > flow && space > flow){
						from.removeCharge(flow);
						to.addCharge(flow);
					}else if(from.getCharge() >= space){
						from.removeCharge(space);
						to.addCharge(space);
					}else{
						to.addCharge(from.getCharge());
						from.removeCharge(from.getCharge());
					}
				}
			}
		}
	}

	public static TileEntity getRelative(TileEntity from, ForgeDirection d){
		return from.getWorldObj().getTileEntity(from.xCoord + d.offsetX, from.yCoord + d.offsetY, from.zCoord + d.offsetZ);
	}

	public static boolean canConnectTo(TileEntity a,
			TileEntity b) {
		if(a!=null && b!=null){
			if(a instanceof IPowerConductor && b instanceof IPowerConductor){
				return true;
			}
		}
		return false;
	}

	public static boolean isConductor(TileEntity tile) {
		if(tile == null)return false;
		if(tile instanceof IPowerConductor){
			return true;
		}
		return false;
	}

	public static boolean canConect(TMultiPart mc, TileEntity tile, ForgeDirection side) {
		if(tile == null || mc == null)return false;
		if(isConductor(tile)){
			if(((IPowerConductor) tile).getPower().isConnectableSide(side.getOpposite(), ((IPowerConductor) mc).getPower().getConnectionType(side)))
			if (areConectables(((IPowerConductor) mc),((IPowerConductor) tile), side))return true;
		}else{
			if(tile instanceof TileMultipart){
				TileMultipart m = (TileMultipart) tile;
				for(TMultiPart g : m.jPartList()){
					if(g instanceof MicroCablePlane && mc instanceof MicroCablePlane)return true;
					if(g instanceof MicroCableBig && mc instanceof MicroCableBig)return true;
				}
			}
		}
		return false;
	}
	
	public static boolean areConectables(IPowerConductor a , IPowerConductor b, ForgeDirection side){
		return ConnType.isCompatible(a.getPower().getConnectionType(side.getOpposite()), b.getPower().getConnectionType(side));
	}
}

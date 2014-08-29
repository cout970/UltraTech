package ultratech.api.power;

import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
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
			double space = to.getCapacity()-to.getCharge();
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

	/**
	 * @param tileEntity for position
	 * @param direction
	 * @return the tileEntity at the side  
	 */
	public static TileEntity getRelative(TileEntity from, ForgeDirection d){
		return from.getWorldObj().getTileEntity(from.xCoord + d.offsetX, from.yCoord + d.offsetY, from.zCoord + d.offsetZ);
	}

	/**
	 * check if the connection is allowed
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean canConnectTo(TileEntity a, TileEntity b) {
		if(a!=null && b!=null){
			if(a instanceof IPowerConductor && b instanceof IPowerConductor){
				return true;
			}
		}
		return false;
	}

	public static TileEntity getRelative(ICable t, ForgeDirection dir) {
		if(t.getWorldObj() == null || t.getCoords() == null)return null;
		int x = t.getCoords()[0];
		int y = t.getCoords()[1];
		int z = t.getCoords()[2];
		return t.getWorldObj().getTileEntity(x+dir.offsetX, y+dir.offsetY, z+dir.offsetZ);
	}
}

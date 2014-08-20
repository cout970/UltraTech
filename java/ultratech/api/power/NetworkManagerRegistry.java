package ultratech.api.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.util.UT_Utils;

/**
 * 
 * @author Cout970
 *
 */
public class NetworkManagerRegistry {

	public static INetworkManager actual;
	
	public static INetworkManager getPathFinder(){
		return actual;
	}
	
	public static void setPathFinder(INetworkManager p){
		actual = p;
	}
	
	public static void loadDefaultPathFinder(){
		setPathFinder(new DefaultNetworkManager());
	}

	public static INetworkManager search(IPowerConductor base, IPowerConductor end) {
		if(actual == null)loadDefaultPathFinder();
		actual.clear();
		actual.search(base, end);
		return actual;
	}

	public static void excludeAndRecalculate(IPowerConductor p) {
		if(actual == null)loadDefaultPathFinder();
		actual.excludeAndRecalculate(p);
	}
	
	public static void iterate(PowerInterface conn){
		if(actual == null)loadDefaultPathFinder();
		actual.iterate(conn);
	}
	
	public static boolean canConnectOnThisSide(PowerInterface p, ForgeDirection dir, PowerInterface cond){
		if(actual == null)loadDefaultPathFinder();
		return actual.canConnectOnThisSide(p, dir, cond);
	}
}

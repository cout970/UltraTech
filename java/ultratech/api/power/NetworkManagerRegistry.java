package ultratech.api.power;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.INetworkManager;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.DefaultNetworkManager;

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

	public static List<ICable> getConnections(TileEntity tile) {
		if(actual == null)loadDefaultPathFinder();
		return actual.getConnections(tile);
	}
}

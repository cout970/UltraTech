package ultratech.api.power;

import net.minecraft.tileentity.TileEntity;
import ultratech.api.util.UT_Utils;

/**
 * 
 * @author Cout970
 *
 */
public class PathFinderRegistry {

	public static IPathFinder actual;
	
	public static IPathFinder getPathFinder(){
		return actual;
	}
	
	public static void setPathFinder(IPathFinder p){
		actual = p;
	}
	
	public static void loadDefaultPathFinder(){
		setPathFinder(new DefaultPathFinder());
	}

	public static IPathFinder search(IPowerConductor base, IPowerConductor end) {
		if(actual == null)loadDefaultPathFinder();
		actual.clear();
		actual.search(base, end);
		return actual;
	}

	public static void excludeAndRecalculate(IPowerConductor p) {
		if(actual == null)loadDefaultPathFinder();
		actual.excludeAndRecalculate(p);
	}
}

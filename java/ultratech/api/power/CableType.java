package ultratech.api.power;
/**
 * 
 * @author Cout970
 *
 */
public enum CableType {
	
	BLOCK,      	//MACHINE
	BIG_CENTER,		//BIG CABLE
	RIBBON_BOTTOM,  //RIBBON CABLE
	NOTHING;	    //No Connection

	public static boolean isCompatible(CableType a, CableType b) {
		if(a == b)return true;
		if(a == BLOCK || b == BLOCK)return true;
		if((a == RIBBON_BOTTOM && b == RIBBON_BOTTOM) || (b == BIG_CENTER && a == BIG_CENTER))return true;
		return false;
	}
}

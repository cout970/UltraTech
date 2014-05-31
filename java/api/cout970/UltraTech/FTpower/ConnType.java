package api.cout970.UltraTech.FTpower;

public enum ConnType {
	
	ALL_CABLES,
	ONLY_SMALL,
	ONLY_BIG,
	SMALL_CABLE, 
	NOTHING;

	public static boolean isCompatible(ConnType a, ConnType b) {
		if(a == b)return true;
		if(a == ALL_CABLES || b == ALL_CABLES)return true;
		if((a == ONLY_SMALL && b == SMALL_CABLE) || (b == ONLY_SMALL && a == SMALL_CABLE))return true;
		return false;
	}
}

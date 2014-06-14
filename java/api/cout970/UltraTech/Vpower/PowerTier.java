package api.cout970.UltraTech.Vpower;
/**
 * 
 * @author Cout970
 *
 */
public enum PowerTier {

	Tier1(20),
	Tier2(50),
	Tier3(100);

	private static PowerTier[] t = {null,Tier1,Tier2,Tier3};
	private int flow;
	
	private PowerTier(int flow){
		this.flow = flow;
	}
	
	public int getFlow(){
		return flow;
	}
	
	public static PowerTier getTier(int l){
		return t[l];
	}

	public static int getPosition(PowerTier tier) {
		return tier.ordinal()+1;
	}
}

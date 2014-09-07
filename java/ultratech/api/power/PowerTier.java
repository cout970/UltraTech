package ultratech.api.power;
/**
 * 
 * @author Cout970
 *
 */
public enum PowerTier {

	Tier1(25),// 500 RF/t
	Tier2(50),// 1000 RF/t
	Tier3(250),// 5000 RF/t
	Tier4(1000);// 20000 RF/t

	private static PowerTier[] t = {null,Tier1,Tier2,Tier3,Tier4};
	private int flow;
	
	private PowerTier(int flow){
		this.flow = flow;
	}
	
	/**
	 * max transfer rate for energy 
	 * @return
	 */
	public int getFlow(){
		return flow;
	}
	
	/**
	 * 
	 * @param l position of the tier +1 
	 * tier 1 => 1
	 * tier 2 => 2
	 * tier 3 => 3
	 * tier 4 => 4
	 * @return
	 */
	public static PowerTier getTier(int l){
		return t[l];
	}

	/**
	 * 
	 * @param tier
	 * @return position of the tier (tier1 => 1,etc)
	 */
	public static int getPosition(PowerTier tier) {
		return tier.ordinal()+1;
	}
}

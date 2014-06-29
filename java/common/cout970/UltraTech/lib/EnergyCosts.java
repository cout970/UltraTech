package common.cout970.UltraTech.lib;

public class EnergyCosts {

	/**
	 *	1Mj = 0.125V
	 *	1EU = 0.05V
	 *
	 *	1MJ = 0.32 EU
	 *	1EU =  3.125 MJ
	 *
	 *	1FT = 6,25 Steam
	 *
	 *	1coal = 1600 FT Always
	 *  1000steam = 160FT
	*/
	public static final String E = " MeV";
	public static final float k = 1/8f;
	
	//production
	public static final   int Engine_MJ_Produced = 4;
	public static final   int ReactorSteamProduct = 160;//960
	//consume
	public static final   int ReactorWaterCost = 20;
	public static final float Engine_EnergyConsume = 0.8f*k;
	public static final float PumpCost = 200*k;
	
	//storage
	public static final int WaterBlockProduct = 20;

	public static double toEnergy(double extract) {
		return extract/2;
	}



	

	//200EU/tick = 320steam/tick == 64 MJ/tick
	
		/**
		 * Base Fuel Usage Per Tick (base) = ( (6.4 - numTanks * 0.08) / ( 16 LP or 8 HP ) ) * numTanks
		 *	Heat Adjust Fuel Usage Per Tick = base + base * (8 - 8 * heat%)
		 *	Steam/tick = ( 10 LP or 20 HP ) * numTanks
		 *	1 MJ = 5 Steam
		 *	1 mB Water per 160 mB of Steam
		 */
}

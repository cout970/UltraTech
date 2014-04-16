package common.cout970.UltraTech.lib;

public class EnergyCosts {

	/**
	 *	1Mj = 0.8 FT
	 *	1EU = 2.5 FT
	 *
	 *	1MJ = 0.32 EU
	 *	1EU =  3.125 MJ
	 *
	 *	1coal = 1600 FT Always
	 *  1000steam = 640FT
	*/
	
	public static final float k = 1;//FT per burning time
	
	//production
	public static final float SolarPanelProduct = 0.2f*k;
	public static final float SteamTurbineProduct = 12.5f*k;//80 steam = 120.0FT/t
	public static final float WindMillProduct = 2.5f*k;
	public static final  int Engine_MJ_Produced = 4;
	public static final float FluidGenProduct = 16f*k;
	public static final int ReactorSteamProduct = 160;//960
	//consume
	public static final float MinerCost = 100.0f*k;
	public static final int ReactorWaterCost = 20;
	public static final float PurifierCost = 200f*k;
	public static final float FurnaceCost = 200f*k;
	public static final float CutterCost = 200f*k;
	public static final float CVD_Cost = 200f*k;	
	public static final float PresuricerCost = 200f*k;
	public static final float ChargeStationFlow = 100f*k;
	public static final float FermenterCost = 2.5f*k;
	public static final float Engine_EnergyConsume = 0.8f*k;
	public static final float ClimateEstationCost = 200000f*k;
	public static final float BoilerCost = 5f*k;
	//storage
	public static final int Tier1Battery = 64000;
	public static final int Tier2Battery = 500000;
	public static final int Tier3Battery = 8000000;
	public static final int FluidGenGast = 10;
	public static final int WaterBlockProduct = 20;
	
	public static enum MachineTier{
		Tier1(50,4000,1),Tier2(100,8000,2),Tier3(500,16000,3);
		private int flow,storage,level;
		private MachineTier(int flow,int storage,int level){
			this.flow = flow;
			this.storage = storage;
		}
		public int getFlow() {
			return flow;
		}
		public int getStorage() {
			return storage;
		}
		public int getLevel(){
			return level;
		}
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

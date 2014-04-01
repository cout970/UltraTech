package common.cout970.UltraTech.lib;

public class EnergyCosts {

	/**
	 *	1Mj = 75 EnergyBlue; energy base
	 *	1EU = 235 EnergyBlue
	 *
	 *	1MJ = 0.32 EU
	 *	1EU =  3.125 MJ
	 *
	 *	1coal = 1232/1600
	 * 320steam = 4800
	*/
	
	public static final int baseContant = 75;//per 1MJ
	
	public static final int MinerCost = 1200;
	public static final int WaterBlockProduct = 20;
	public static final int SteamTurbineProduct = 600;//80 steam = 1200EB/t
	public static final int ReactorWaterCost = 20;
	public static final int ReactorSteamProduct = 160;//960
	public static final float WindMillProduct = 10;
	public static final int SolarPanelDivisor_Height = 20;
	public static final int PurifierCost = 1540;
	public static final int FurnaceCost = 1540;
	public static final int CutterCost = 1540;
	public static final int CVD_Cost = 1540;	
	public static final int PresuricerCost = 1540;
	public static final int ChargeStationFlow = 100;
	public static final int FermenterCost = 25;
	public final static int Engine_MJ_Produced = 8;
	public final static int Engine_EnergyConsume = 600;
	public static final int ClimateEstationCost = 200000;
	public static final int BoilerCost = 50;
	public static final int Tier1Battery = 640000;
	public static final int Tier2Battery = 5000000;
	public static final int Tier3Battery = 80000000;
	
	
	public static enum MachineTier{
		Tier1(400,50000,1),Tier2(2000,160000,2),Tier3(10000,320000,3);
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

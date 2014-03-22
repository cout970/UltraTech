package common.cout970.UltraTech.lib;

public class GraficCost {

	public static final int MinerCost = 1350;
	public static final int WaterBlockProduct = 20;
	public static final int SteamTurbineProduct = 250;//80 steam = 250E/t
	public static final int ReactorWaterCost = 20;
	public static final int ReactorSteamProduct = 200;
	public static final float WindMillProduct = 10;
	public static final int SolarPanelDivisor_Height = 50;
	public static final int PurifierCost = 2000;
	public static final int FurnaceCost = 2000;
	public static final int CutterCost = 2000;
	public static final int CVD_Cost = 2000;
	public static final int ChargeStationFlow = 100;
	public static final int FermenterCost = 5;
	public final static int Engine_MJ_Produced = 8;
	public final static int Engine_EnergyConsume = 64;
	public static final int PresuricerCost = 2000;
	public static final int ClimateEstationCost = 200000;
	
	
	public static enum MachineTier{
		Tier1(400,64000,1),Tier2(2000,128000,2),Tier3(10000,256000,3);
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

	






	//200EU/tick = 320steam/tick
	
		/**
		 * Base Fuel Usage Per Tick (base) = ( (6.4 - numTanks * 0.08) / ( 16 LP or 8 HP ) ) * numTanks
		 *	Heat Adjust Fuel Usage Per Tick = base + base * (8 - 8 * heat%)
		 *	Steam/tick = ( 10 LP or 20 HP ) * numTanks
		 *	1 MJ = 5 Steam
		 *	1 mB Water per 160 mB of Steam
		 */
}

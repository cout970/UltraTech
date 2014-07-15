package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;

public class PowerExchange {
	
	public static final int MeVtoRF = 20;//RF => MeV

	public static float FTtoHeat(float ft){
		return ft/10;
	}
	
	public static float HeattoFT(float h){
		return h*10;
	}

	public static float HeatPerFluid(int f) {
		//1000mb <= 20C
		return 0.02f*f;
	}
	
	public static float FluidPerHeat(int h) {
		//1000mb => 20C
		return h*50;
	}

	public int MeVtoRF(double cap) {
		return (int) (cap*MeVtoRF);
	}

	public double RFtoMev(int i) {
		return i/MeVtoRF;
	}

	public double FTtoMev(float progres) {
		return progres/2;
	}
}

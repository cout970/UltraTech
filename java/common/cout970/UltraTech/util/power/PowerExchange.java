package common.cout970.UltraTech.util.power;

import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;

public class PowerExchange {
	
	public static final int QPtoRF = 20;//RF => QP
	public static final int QPtoMJ = 2;//RF => QP
	
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

	public static int QPtoRF(double cap) {
		return (int) (cap*QPtoRF);
	}

	public static double RFtoQP(int i) {
		return i/QPtoRF;
	}

	public static double FTtoQP(float progres) {
		return progres/2;
	}
	
	public static int QPtoMJ(double cap) {
		return (int) (cap*QPtoMJ);
	}

	public static double MJtoQP(int i) {
		return i/QPtoMJ;
	}
	
}

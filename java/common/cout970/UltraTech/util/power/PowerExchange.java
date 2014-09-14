package common.cout970.UltraTech.util.power;

import common.cout970.UltraTech.TileEntities.intermod.TileEntityDynamo;

public class PowerExchange {
	
	public static final int QPtoRF = 20;//RF => QP
	public static final int QPtoMJ = 2;//RF => QP
	public static final int HeattoFT = 20;//RF => QP
	public static final int HeattoFluid = 10;//RF => QP
	public static final int QPtoEU = 5;//QP => EU
	
	public static float FTtoHeat(float ft){
		return ft/HeattoFT;
	}
	
	public static float HeattoFT(float h){
		return h*HeattoFT;
	}

	public static float HeatPerFluid(int f) {
		return ((float)HeattoFluid*f)/1000;
	}
	
	public static float FluidPerHeat(int h) {
		//1000mb => 10C
		return ((float)h*1000)/HeattoFluid;
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
	
	public static double QPtoMJ(double cap) {
		return (cap*QPtoMJ);
	}

	public static double MJtoQP(double i) {
		return i/QPtoMJ;
	}

	public static double EUtoQP(double eu) {
		return eu/QPtoEU;
	}
	
	public static double QPtoEU(double eu) {
		return eu*QPtoEU;
	}
}

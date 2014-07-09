package common.cout970.UltraTech.misc;

public class PowerExchange {

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
}

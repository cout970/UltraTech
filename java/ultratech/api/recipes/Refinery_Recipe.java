package ultratech.api.recipes;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

public class Refinery_Recipe {

	public FluidStack input;
	public Fluid out1;
	public float amount1;
	public Fluid out2;
	public float amount2;
	public Fluid out3;
	public float amount3;	
	
	public Refinery_Recipe(FluidStack result,Fluid out1,float amount1,Fluid out2,float amount2,Fluid out3,float amount3){
		input = result;
		this.out1 = out1;
		this.out2 = out2;
		this.out3 = out3;
		
		this.amount1 = amount1;
		this.amount2 = amount2;
		this.amount3 = amount3;
	}
	
	public boolean matches(FluidStack input){
		if(isEqual(input, this.input))return true;
		return false;
	}
	
	public static boolean isEqual(FluidStack a, FluidStack b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.fluidID == b.fluidID)return true;
		if(a.isFluidEqual(b))return true;
		return false;
	}

	public static Refinery_Recipe getResult(IFluidTank in) {
		if(in == null || in.getFluid() == null)return null;
		for(Refinery_Recipe b : RecipeRegistry.refinery)
			if(isEqual(in.getFluid(), b.input))return b;
		return null;
	}
}

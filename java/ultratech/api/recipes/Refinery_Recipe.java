package ultratech.api.recipes;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;

public class Refinery_Recipe {

	public FluidStack input;
	public FluidStack[] product = new FluidStack[3];
	
	
	public Refinery_Recipe(FluidStack result,FluidStack res1,FluidStack res2,FluidStack res3){
		input = result;
		product[0] = res1;
		product[1] = res2;
		product[2] = res3;
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

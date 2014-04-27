package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class Cooling_Recipes {

	public static List<Cooling_Recipes> recipes = new ArrayList<Cooling_Recipes>();
	
	public FluidStack input;
	public FluidStack[] product = new FluidStack[3];
	
	
	public Cooling_Recipes(FluidStack result,FluidStack res1,FluidStack res2,FluidStack res3){
		input = result;
		product[0] = res1;
		product[1] = res2;
		product[2] = res3;
	}
	
	public static boolean isEqual(Fluid a, Fluid b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.getID() == b.getID())return true;
		return false;
	}
	
	public static boolean isEqual(FluidStack a, FluidStack b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.fluidID == b.fluidID)return true;
		if(a.isFluidEqual(b))return true;
		return false;
	}
	
	public static boolean isResult(FluidStack a){
		for(Cooling_Recipes b : recipes)
		for(FluidStack f : b.product)if(isEqual(a, f))return true;
		return false;
	}
	
	public static boolean hasRecipe(FluidStack a){
		for(Cooling_Recipes b : recipes)
		if(isEqual(a, b.input))return true;
		return false;
	}

	public static FluidStack[] getResult(FluidStack c) {
		for(Cooling_Recipes b : recipes)
			if(isEqual(c, b.input))return b.product;
		return null;
	}

	public static int getInit(FluidStack fluid) {
		if(fluid == null)return 0;
		for(Cooling_Recipes b : recipes)
			if(isEqual(fluid, b.input))return b.input.amount;
		return 0;
	}
}

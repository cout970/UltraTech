package common.cout970.UltraTech.lib.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Boiler_Recipes {

	public static Map<String,String> recipes = new HashMap<String,String>();
	
	public static boolean isEqual(Fluid a, Fluid b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.getName() == b.getName())return true;
		return false;
	}
	
	public static boolean hasRecipe(FluidStack resource){
		if(resource == null)return false;
		if(recipes.containsKey(FluidRegistry.getFluidName(resource))){
			return true;
		}
		return false;
	}
	
	public static Fluid getResult(FluidStack a){
		if(a == null)return null;
		if(recipes.containsKey(FluidRegistry.getFluidName(a))){
			return FluidRegistry.getFluid(recipes.get(FluidRegistry.getFluidName(a)));
		}
		return null;
	}
}

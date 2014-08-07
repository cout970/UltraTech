package ultratech.api.recipes;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Boiler_Recipes {

	public static boolean isEqual(Fluid a, Fluid b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.getName() == b.getName())return true;
		return false;
	}
	
	public static boolean hasRecipe(FluidStack resource){
		if(resource == null)return false;
		if(RecipeRegistry.boiler.containsKey(FluidRegistry.getFluidName(resource))){
			return true;
		}
		return false;
	}
	
	public static Fluid getResult(FluidStack a){
		if(a == null)return null;
		if(RecipeRegistry.boiler.containsKey(FluidRegistry.getFluidName(a))){
			return FluidRegistry.getFluid(RecipeRegistry.boiler.get(FluidRegistry.getFluidName(a)));
		}
		return null;
	}
}

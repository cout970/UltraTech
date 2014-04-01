package common.cout970.UltraTech.lib.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Cooling_Recipes {

public static Map<String,String> recipes = new HashMap<String,String>();
	
	public static boolean isEqual(Fluid a, Fluid b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.getName() == b.getName())return true;
		return false;
	}
	
	public static boolean hasRecipe(Fluid a){
		if(recipes.containsKey(a.getName())){
			return true;
		}
		return false;
	}
	
	public static Fluid getResult(Fluid a){
		if(recipes.containsKey(a.getName())){
			return FluidRegistry.getFluid(recipes.get(a.getName()));
		}
		return null;
	}
}

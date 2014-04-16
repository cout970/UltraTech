package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Cooling_Recipes {

	public static List<Cooling_Recipes> recipes = new ArrayList<Cooling_Recipes>();
	
	public Fluid input;
	public Fluid[] product = new Fluid[3];
	
	public Cooling_Recipes(String ResultName,String Product1,String Product2,String Product3){
		input = FluidRegistry.getFluid(ResultName);
		product[0] = (Product1 != null) ? FluidRegistry.getFluid(Product1) : null;
		product[1] = (Product2 != null) ? FluidRegistry.getFluid(Product2) : null;
		product[2] = (Product3 != null) ? FluidRegistry.getFluid(Product3) : null;
	}
	
	public static boolean isEqual(Fluid a, Fluid b){
		if(a == null && b == null)return true;
		if(a == null || b == null)return false;
		if(a.getID() == b.getID())return true;
		return false;
	}
	
	public static boolean isResult(Fluid a){
		for(Cooling_Recipes b : recipes)
		for(Fluid f : b.product)if(isEqual(a, f))return true;
		return false;
	}
	
	public static boolean hasRecipe(Fluid a){
		for(Cooling_Recipes b : recipes)
		if(isEqual(a, b.input))return true;
		return false;
	}

	public static Fluid getResult(FluidStack c, int height) {
		for(Cooling_Recipes b : recipes)
			if(isEqual(c.getFluid(), b.input))return b.product[(int)height/2];
		return null;
	}
}

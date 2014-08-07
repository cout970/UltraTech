package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Chemical_Recipe{
	
	public FluidStack input;
	public ItemStack out_1;
	public ItemStack out_2;
	public ItemStack out_3;
	
	public Chemical_Recipe(FluidStack f, ItemStack a, ItemStack b, ItemStack c){
		input = f;
		out_1 = a;
		out_2 = b;
		out_3 = c;
	}
	
	public void addRecipe(Chemical_Recipe r) {
		Chemical_Recipe a = (Chemical_Recipe) r;
		if(a.out_1 != null && a.out_1.stackSize == 0)a.out_1.stackSize = 1;
		if(a.out_2 != null && a.out_2.stackSize == 0)a.out_2.stackSize = 1;
		if(a.out_3 != null && a.out_3.stackSize == 0)a.out_3.stackSize = 1;
		if(!RecipeRegistry.chemical.contains(a))
			RecipeRegistry.chemical.add(a);
	}

	public boolean matches(FluidStack f) {
		if(f.fluidID == input.fluidID)return true;
		return false;
	}
	
	public ItemStack getResult(FluidStack f, int n) {
		if(f == null)return null;
		for(Chemical_Recipe r : RecipeRegistry.chemical){
			if(f.fluidID == r.input.fluidID){
				if(n == 0)return r.out_1;
				if(n == 1)return r.out_2;
				if(n == 2)return r.out_3;
			}
		}
		return null;
	}

}

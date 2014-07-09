package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.lib.UT_Utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class Chemical_Recipe implements IRecipeHandler{

	public static List<Chemical_Recipe> recipes = new ArrayList<Chemical_Recipe>();

	public static Chemical_Recipe INSTANCE = new Chemical_Recipe(null,null,null,null);
	
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
	
	@Override
	public void addRecipe(IRecipeHandler r) {
		Chemical_Recipe a = (Chemical_Recipe) r;
		if(a.out_1 != null && a.out_1.stackSize == 0)a.out_1.stackSize = 1;
		if(a.out_2 != null && a.out_2.stackSize == 0)a.out_2.stackSize = 1;
		if(a.out_3 != null && a.out_3.stackSize == 0)a.out_3.stackSize = 1;
		if(!recipes.contains(a))
			recipes.add(a);
	}

	@Override
	public boolean matches(IInventory inv) {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(IInventory inv) {
		return null;
	}

	@Override
	public ItemStack getResult(ItemStack itemstack) {
		return null;
	}
	
	public ItemStack getResult(FluidStack f, int n) {
		if(f == null)return null;
		for(Chemical_Recipe r : recipes){
			if(f.fluidID == r.input.fluidID){
				if(n == 0)return r.out_1;
				if(n == 1)return r.out_2;
				if(n == 2)return r.out_3;
			}
		}
		return null;
	}

}

package ultratech.api.recipes;

import java.util.HashMap;
import java.util.Map;

import ultratech.api.util.UT_Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Fermenter_Recipe {
	
	public ItemStack input;
	public int amount;
	
	public Fermenter_Recipe(ItemStack input, int amount) {
		this.input = input;
		this.amount = amount;
	}

	public static int getTicks(ItemStack item) {
		if(item == null)return 0;
		for(Fermenter_Recipe f : RecipeRegistry.fermenter)
			if(f.maches(item)){
				return f.amount;
			}
		return 0;
	}

	public static boolean isIngredient(ItemStack itemstack) {
		for(Fermenter_Recipe f : RecipeRegistry.fermenter)
			if(f.maches(itemstack)){
				return true;
			}
		return false;
	}

	public boolean maches(ItemStack itemstack) {
		if(UT_Utils.areEcuals(itemstack, input, true))return true;
		return false;
	}

}

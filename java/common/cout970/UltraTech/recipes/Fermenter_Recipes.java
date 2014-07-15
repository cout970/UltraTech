package common.cout970.UltraTech.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Fermenter_Recipes {

	public static Map<Item,Integer> recipes = new HashMap<Item,Integer>();
	
	public static boolean hasRecipe(ItemStack item) {
		if(item == null)return false;
		if(recipes.containsKey(item.getItem()))return true;
		return false;
	}

	public static int getTicks(ItemStack item) {
		if(item == null)return 0;
		if(recipes.containsKey(item.getItem())){
			return recipes.get(item.getItem());
		}
		return 0;
	}

}

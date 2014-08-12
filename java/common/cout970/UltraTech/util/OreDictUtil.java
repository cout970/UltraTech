package common.cout970.UltraTech.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictUtil {

	public static ItemStack getOre(String name){
		for(ItemStack i : OreDictionary.getOres(name)){
			return i;
		}
		return null;
	}
}

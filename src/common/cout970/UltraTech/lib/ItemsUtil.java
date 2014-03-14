package common.cout970.UltraTech.lib;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemsUtil {

	/**
	 * 
	 * @param a itemstack 1
	 * @param b itemstack 1
	 * @param meta if want detect metadata
	 * @return if are equals
	 */
	public static boolean areEcuals(ItemStack a, ItemStack b, boolean meta){
		if(a == null && b == null)return true;
		if(a != null && b != null){
			if(a.itemID == b.itemID && (!meta || (a.getItemDamage() == b.getItemDamage())))return true;
			if(OreDictionary.getOreID(a) != -1 && OreDictionary.getOreID(b) != -1){
				if(OreDictionary.getOreID(a) == OreDictionary.getOreID(b))return true;
			}
		}
		return false;
	}

}

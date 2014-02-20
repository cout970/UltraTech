package common.cout970.UltraTech.lib;

import net.minecraft.item.ItemStack;

public class ItemsUtil {

	/**
	 * 
	 * @param a itemstack 1
	 * @param b itemstack 1
	 * @param meta if want detect metadata
	 * @return if are equals
	 */
	public static boolean areEcuals(ItemStack a, ItemStack b, boolean meta){
		if(a.itemID == b.itemID){
			if(meta){
				return true;
			}else{
				if(a.getItemDamage() == b.getItemDamage())
					return true;
			}
		}
		return false;
	}
}

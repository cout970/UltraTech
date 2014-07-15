package common.cout970.UltraTech.handlers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler{

	/**
	 * only for the sulfur (2400)
	 */
	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel == null)return 0;
		int sulfur = OreDictionary.getOreID("dustSulfur");
		for(int f : OreDictionary.getOreIDs(fuel)){
			if(f == sulfur) return 800*3;
		}
		return 0;
	}

}

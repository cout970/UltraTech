package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockRefinery extends UT_ItemBlock{

	public UT_ItemBlockRefinery(int par1) {
		super(par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "refinery"+itemstack.getItemDamage();
	}
}

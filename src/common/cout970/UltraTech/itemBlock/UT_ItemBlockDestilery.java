package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockDestilery extends UT_ItemBlock{

	public UT_ItemBlockDestilery(int par1) {
		super(par1);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "destilery"+itemstack.getItemDamage();
	}
}

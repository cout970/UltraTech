package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockTier1 extends UT_ItemBlock{

	public UT_ItemBlockTier1(int par1) {
		super(par1);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "tier1"+itemstack.getItemDamage();
	}
}

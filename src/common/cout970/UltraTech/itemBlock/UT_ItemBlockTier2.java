package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockTier2 extends UT_ItemBlock{

	public UT_ItemBlockTier2(int par1) {
		super(par1);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "tier2"+itemstack.getItemDamage();
	}
}

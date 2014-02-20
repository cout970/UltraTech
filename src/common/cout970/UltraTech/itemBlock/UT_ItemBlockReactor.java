package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockReactor extends UT_ItemBlock{

	public UT_ItemBlockReactor(int par1) {super(par1);}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "reactor"+itemstack.getItemDamage();
	}
}

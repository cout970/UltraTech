package common.cout970.UltraTech.itemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UT_ItermBlockModels extends UT_ItemBlock{

	public UT_ItermBlockModels(Block par1) {
		super(par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "model"+itemstack.getItemDamage();
	}
}

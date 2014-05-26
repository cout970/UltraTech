package common.cout970.UltraTech.itemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UT_ItemBlockMisc extends UT_ItemBlock{

	public UT_ItemBlockMisc(Block par1) {
		super(par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "misc"+itemstack.getItemDamage();
	}

}

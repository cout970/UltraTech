package common.cout970.UltraTech.itemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UT_ItemBlockCable extends UT_ItemBlock{

	public UT_ItemBlockCable(Block par1) {
		super(par1);
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "cable"+itemstack.getItemDamage();
	}
}

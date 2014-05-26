package common.cout970.UltraTech.itemBlock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class UT_ItemBlockOre extends UT_ItemBlock{

	public final static String[] subNames = {
		"Radionite","Aluminum","Copper","Tin","Lead","Silver"
	};
	
	public UT_ItemBlockOre(Block par1) {
		super(par1);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "ore"+itemstack.getItemDamage();
	}

}

package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockOre extends UT_ItemBlock{

	public final static String[] subNames = {
		"Radionite","Aluminum","Copper","Tin","Lead","Silver"
	};
	
	public UT_ItemBlockOre(int par1) {
		super(par1);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + ".ore";
	}
	
	@Override
	public String getItemDisplayName(ItemStack is) {
		return subNames[is.getItemDamage()]+" Ore";
	}

}

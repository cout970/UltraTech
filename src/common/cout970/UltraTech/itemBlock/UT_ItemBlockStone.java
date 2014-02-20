package common.cout970.UltraTech.itemBlock;

import net.minecraft.item.ItemStack;

public class UT_ItemBlockStone extends UT_ItemBlock{
	
	public UT_ItemBlockStone(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public final static String[] subNames = {
		"Black" ,"Black Bricks","White","White Bricks"
	};
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack,
			ItemStack par2ItemStack) {
		return false;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + ".deco";
	}
	
	@Override
	public String getItemDisplayName(ItemStack is) {
		return "Purified Stone Block "+subNames[is.getItemDamage()];
	}

}

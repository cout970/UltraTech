package common.cout970.UltraTech.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class UT_itemBlock extends ItemBlock{

	public UT_itemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public final static String[] subNames = {
		"White", "Black",  "Red", "Green", "Blue", "Yellow",
		"Cyan","Purple","Light Green","Steal Blue","Pink",
		"Orange","Blue Violet","Sea Green"
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
		return "Decorative Block "+subNames[is.getItemDamage()];
//		return "debug";
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public int getItemEnchantability() {
		return 0;
	}
	
	@Override
	public boolean isRepairable() {
		return false;
	}
}

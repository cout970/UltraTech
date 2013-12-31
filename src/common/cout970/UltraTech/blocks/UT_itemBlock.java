package common.cout970.UltraTech.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class UT_itemBlock extends ItemBlock{

	public UT_itemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	private final static String[] subNames = {
		"White", "Black",  "Red", "Green", "Blue", "Yellow",
		"Light Blue","Dark Green","Purple", "Pink","Orange"
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

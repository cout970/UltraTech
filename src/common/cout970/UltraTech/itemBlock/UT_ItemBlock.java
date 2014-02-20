package common.cout970.UltraTech.itemBlock;


import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class UT_ItemBlock extends ItemBlock{

	public UT_ItemBlock(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + "chasis"+itemstack.getItemDamage();
	}

}

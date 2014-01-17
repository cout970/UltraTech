package common.cout970.UltraTech.blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class UT_ItemBlockStone extends ItemBlock{
	
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
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void addInformation(ItemStack is, EntityPlayer par2EntityPlayer, List l, boolean par4) {
//		super.addInformation(is, par2EntityPlayer, l, par4);
//		try{
//			l.add("Colored in a 3D printer");
//		}catch(Exception e){}
//	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
}

package common.cout970.UltraTech.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class UT_ItemBlockDeco extends ItemBlock{

	public UT_ItemBlockDeco(int par1) {
		super(par1);
		setHasSubtypes(true);
	}

	public final static String[] subNames = {
		"White","Black","Blue","Steal Blue","Cyan","Sea Green","Green","Light Green","Yellow","Orange","Red","Purple","Pink","Blue Violet"
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer par2EntityPlayer, List l, boolean par4) {
		super.addInformation(is, par2EntityPlayer, l, par4);
		try{
			l.add("Colored in a 3D printer");
		}catch(Exception e){}
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

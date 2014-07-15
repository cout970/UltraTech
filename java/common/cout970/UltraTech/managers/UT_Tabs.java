package common.cout970.UltraTech.managers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UT_Tabs {

	public static final CreativeTabs ResourceTab = new CreativeTabs("UltraTech Resources"){
	
		public ItemStack getIconItemStack() {
			return new ItemStack(ItemManager.ItemName.get("MetalPlate"), 1, 4);
		}
	
		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech Resources";
		}
	
		@Override
		public Item getTabIconItem() {
			return null;
		};
	};

	public static final CreativeTabs techTab = new CreativeTabs("UltraTech"){
	
		public ItemStack getIconItemStack() {
			return new ItemStack(BlockManager.Chasis, 1, 2);
		}
	
		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech";
		}
	
		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech";
		}
	
		@Override
		public Item getTabIconItem() {
			return null;
		}
	};
	
	public static final CreativeTabs DecoTab = new CreativeTabs("UltraTech"){
	
		public ItemStack getIconItemStack() {
			return new ItemStack(BlockManager.deco.get(5), 1, 0);
		}
	
		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
		{
			return "UltraTech Decoration";
		}
	
		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech Decoration";
		}
	
		@Override
		public Item getTabIconItem() {
			return null;
		}
	};

}

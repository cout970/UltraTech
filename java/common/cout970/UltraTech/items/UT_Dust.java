package common.cout970.UltraTech.items;

import java.util.List;






import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UT_Dust extends Item{

	private IIcon[] ico;
	public static String[] names = {"Aluminum Dust","Copper Dust","Tin Dust","Lead Dust","Silver Dust","Obalti Dust",
		"Iron Dust","Gold Dust","Diamond Dust","Obsidian Dust"};

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
	public UT_Dust() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("UT_Dust");
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IIconRegister iconRegister){
		ico = new IIcon[names.length];
		ico[0] = iconRegister.registerIcon("ultratech:metal/aluminum"+"dust");
		ico[1] = iconRegister.registerIcon("ultratech:metal/copper"+"dust");
		ico[2] = iconRegister.registerIcon("ultratech:metal/tin"+"dust");
		ico[3] = iconRegister.registerIcon("ultratech:metal/lead"+"dust");
		ico[4] = iconRegister.registerIcon("ultratech:metal/silver"+"dust");
		ico[5] = iconRegister.registerIcon("ultratech:metal/obalti"+"dust");
		ico[6] = iconRegister.registerIcon("ultratech:metal/iron"+"dust");
		ico[7] = iconRegister.registerIcon("ultratech:metal/gold"+"dust");
		ico[8] = iconRegister.registerIcon("ultratech:metal/diamond"+"dust");
		ico[9] = iconRegister.registerIcon("ultratech:metal/obsidian"+"dust");
		this.itemIcon = ico[0];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
    {
		for(int i=0;i<names.length;i++){
			subItems.add(new ItemStack(this, 1, i));
		}
    }
	
	public IIcon getIconFromDamage(int par1)
    {
		if(par1 >= names.length)return ico[0];
		return ico[par1];
    }
}

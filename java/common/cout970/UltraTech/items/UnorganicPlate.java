package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UnorganicPlate extends Item{

	public static String[] names = {"Diamond","Grafeno","Silicon","Redstone","Radionite","Obsidian"};
	public IIcon[] i = new IIcon[names.length];
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()].toLowerCase();
	}
	
	public UnorganicPlate() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("unorganicplate");
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
	}
	

	public void registerIcons(IIconRegister ic){
		for(int x=0;x<names.length;x++)i[x] = ic.registerIcon("ultratech:metal/"+"unorganicplate_"+names[x].toLowerCase());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
    {
		for(int x=0;x<names.length;x++)subItems.add(new ItemStack(this, 1, x));
    }
	
	
	public IIcon getIconFromDamage(int m)
    {
		if(m >= names.length)return i[0];
		return i[m];
    }
}

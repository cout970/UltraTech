package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.managers.FluidManager;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;


public class Bottle extends UT_Item{

	public static String[] names = {"Empty","Steam","Juice","Ethanol Gas","Ethanol","Gasoline","Oil Gas","Liquid Plastic","Fuel","Sulfuric Acid"};
	public IIcon[] icons;
	
	public Bottle() {
		super("Bottle");
		setHasSubtypes(true);
	}

	public void registerIcons(IIconRegister iconRegister){
		icons = new IIcon[names.length]; 
		for(int x=0; x < icons.length; x++){
			icons[x] = iconRegister.registerIcon("ultratech:fluids/bottle_"+names[x].toLowerCase());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
    {
		for(int x=0; x < icons.length; x++)
		subItems.add(new ItemStack(this, 1, x));
    }
	
	public IIcon getIconFromDamage(int par1)
    {
		if(par1 >= icons.length)return icons[0];
		return icons[par1];
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
}

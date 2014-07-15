package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UT_Ingot extends Item{

	private IIcon itemIcon1;
	private IIcon itemIcon2;
	private IIcon itemIcon3;
	private IIcon itemIcon4;
	public static String[] names = {"Aluminum Ingot","Copper Ingot","Tin Ingot","Lead Ingot","Silver Ingot"};

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
	public UT_Ingot() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("UT_Ingot");
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:metal/aluminum"+"ingot");
		this.itemIcon1 = iconRegister.registerIcon("ultratech:metal/copper"+"ingot");
		this.itemIcon2 = iconRegister.registerIcon("ultratech:metal/tin"+"ingot");
		this.itemIcon3 = iconRegister.registerIcon("ultratech:metal/lead"+"ingot");
		this.itemIcon4 = iconRegister.registerIcon("ultratech:metal/silver"+"ingot");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
    {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
    }

	
	public IIcon getIconFromDamage(int par1)
    {
		switch(par1){
		case 0:{
			 return this.itemIcon;
		}
		case 1:{
			 return this.itemIcon1;
		}
		case 2:{
			 return this.itemIcon2;
		}
		case 3:{
			 return this.itemIcon3;
		}
		case 4:{
			 return this.itemIcon4;
		}
		}
		return this.itemIcon;
    }

}

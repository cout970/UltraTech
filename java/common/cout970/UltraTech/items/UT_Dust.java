package common.cout970.UltraTech.items;

import java.util.List;



import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UT_Dust extends Item{

	private IIcon itemIcon1;
	private IIcon itemIcon2;
	private IIcon itemIcon3;
	private IIcon itemIcon4;
	private IIcon itemIcon5;
	private IIcon itemIcon6;
	private IIcon itemIcon7;
	private IIcon itemIcon8;
	public static String[] names = {"Aluminum Dust","Copper Dust","Tin Dust","Lead Dust","Silver Dust","Alloy Dust",
		"Iron Dust","Gold Dust","Diamond Dust"};

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
	public UT_Dust() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("UT_Dust");
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:metal/aluminum"+"dust");
		this.itemIcon1 = iconRegister.registerIcon("ultratech:metal/copper"+"dust");
		this.itemIcon2 = iconRegister.registerIcon("ultratech:metal/tin"+"dust");
		this.itemIcon3 = iconRegister.registerIcon("ultratech:metal/lead"+"dust");
		this.itemIcon4 = iconRegister.registerIcon("ultratech:metal/silver"+"dust");
		this.itemIcon5 = iconRegister.registerIcon("ultratech:metal/alloy"+"dust");
		this.itemIcon6 = iconRegister.registerIcon("ultratech:metal/iron"+"dust");
		this.itemIcon7 = iconRegister.registerIcon("ultratech:metal/gold"+"dust");
		this.itemIcon8 = iconRegister.registerIcon("ultratech:metal/diamond"+"dust");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List subItems)
    {
		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
		subItems.add(new ItemStack(this, 1, 5));
		subItems.add(new ItemStack(this, 1, 6));
		subItems.add(new ItemStack(this, 1, 7));
		subItems.add(new ItemStack(this, 1, 8));
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
		case 5:{
			 return this.itemIcon5;
		}
		case 6:{
			 return this.itemIcon6;
		}
		case 7:{
			 return this.itemIcon7;
		}
		case 8:{
			 return this.itemIcon8;
		}
		}
		return this.itemIcon;
    }
}

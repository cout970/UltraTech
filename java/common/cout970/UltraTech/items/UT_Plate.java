package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UT_Plate extends Item{
	
	private IIcon itemIcon1;
	private IIcon itemIcon2;
	private IIcon itemIcon3;
	private IIcon itemIcon4;
	private IIcon itemIcon5;
	private IIcon itemIcon6;
	private IIcon itemIcon7;
	private IIcon itemIcon8;
	private IIcon itemIcon9;
	private IIcon itemIcon10;
	private IIcon itemIcon11;
	private IIcon itemIcon12;
	public static String[] names = {"Aluminum Plate","Copper Plate","Tin Plate","Lead Plate","Silver Plate","Alloy Plate","Iron Plate","Gold Plate","Diamond Plate",
		"Grafeno Plate","Silicon Plate","Redstone Plate","Radionite Plate"};

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}
	
	public UT_Plate() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("UT_Plate");
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:metal/aluminum"+"plate");
		this.itemIcon1 = iconRegister.registerIcon("ultratech:metal/copper"+"plate");
		this.itemIcon2 = iconRegister.registerIcon("ultratech:metal/tin"+"plate");
		this.itemIcon3 = iconRegister.registerIcon("ultratech:metal/lead"+"plate");
		this.itemIcon4 = iconRegister.registerIcon("ultratech:metal/silver"+"plate");
		this.itemIcon5 = iconRegister.registerIcon("ultratech:metal/alloy"+"plate");
		this.itemIcon6 = iconRegister.registerIcon("ultratech:metal/iron"+"plate");
		this.itemIcon7 = iconRegister.registerIcon("ultratech:metal/gold"+"plate");
		this.itemIcon8 = iconRegister.registerIcon("ultratech:metal/diamond"+"plate");
		this.itemIcon9 = iconRegister.registerIcon("ultratech:metal/"+"grafeno"+"plate");
		this.itemIcon10 = iconRegister.registerIcon("ultratech:metal/"+"silicon"+"plate");
		this.itemIcon11 = iconRegister.registerIcon("ultratech:metal/"+"redstone"+"plate");
		this.itemIcon12 = iconRegister.registerIcon("ultratech:metal/"+"radionite"+"plate");

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
		subItems.add(new ItemStack(this, 1, 9));
		subItems.add(new ItemStack(this, 1, 10));
		subItems.add(new ItemStack(this, 1, 11));
		subItems.add(new ItemStack(this, 1, 12));
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
		case 9:{
			 return this.itemIcon9;
		}
		case 10:{
			 return this.itemIcon10;
		}
		case 11:{
			 return this.itemIcon11;
		}
		case 12:{
			 return this.itemIcon12;
		}
		}
		return this.itemIcon;
    }
}

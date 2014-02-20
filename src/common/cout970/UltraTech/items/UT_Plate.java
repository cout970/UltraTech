package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class UT_Plate extends Item{
	
	private Icon itemIcon1;
	private Icon itemIcon2;
	private Icon itemIcon3;
	private Icon itemIcon4;
	private Icon itemIcon5;
	private Icon itemIcon6;
	private Icon itemIcon7;
	private Icon itemIcon8;
	private Icon itemIcon9;
	private Icon itemIcon10;
	private Icon itemIcon11;
	private Icon itemIcon12;

	public UT_Plate(int par1) {
		super(par1);
		setHasSubtypes(true);
		setUnlocalizedName("UT_Plate");
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IconRegister iconRegister){
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
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List subItems)
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
	
	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		switch(par1ItemStack.getItemDamage()){
		case 0:{
			return "Aluminum Plate";
		}
		case 1:{
			return "Copper Plate";
		}
		case 2:{
			return "Tin Plate";
		}
		case 3:{
			return "Lead Plate";
		}
		case 4:{
			return "Silver Plate";
		}
		case 5:{
			return "Alloy Plate";
		}
		case 6:{
			return "Iron Plate";
		}
		case 7:{
			return "Gold Plate";
		}
		case 8:{
			return "Diamond Plate";
		}
		case 9:{
			return "Grafeno Plate";
		}
		case 10:{
			return "Silicon Plate";
		}
		case 11:{
			return "Redstone Plate";
		}
		case 12:{
			return "Radionite Plate";
		}
		}
		
		return "Plate";
	}
	
	public Icon getIconFromDamage(int par1)
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

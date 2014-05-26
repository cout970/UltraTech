package common.cout970.UltraTech.items;

import java.util.List;

import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UT_Chunk extends Item{

	public IIcon[] i;
	public static String[] names = {"Aluminum Chunk","Copper Chunk","Tin Chunk","Lead Chunk","Silver Chunk",
		"Iron Chunk","Gold Chunk","Radionite Chunk"};
	
	public UT_Chunk() {
		super();
		setHasSubtypes(true);
		setUnlocalizedName("UT_Chunk");
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
	}
	
	public void registerIcons(IIconRegister iconRegister){
		String tipe = "chunk";
		i = new IIcon[8];
		i[0] = iconRegister.registerIcon("ultratech:metal/aluminum"+tipe);
		i[1] = iconRegister.registerIcon("ultratech:metal/copper"+tipe);
		i[2] = iconRegister.registerIcon("ultratech:metal/tin"+tipe);
		i[3] = iconRegister.registerIcon("ultratech:metal/lead"+tipe);
		i[4] = iconRegister.registerIcon("ultratech:metal/silver"+tipe);
		i[5] = iconRegister.registerIcon("ultratech:metal/iron"+tipe);
		i[6] = iconRegister.registerIcon("ultratech:metal/gold"+tipe);
		i[7] = iconRegister.registerIcon("ultratech:metal/radionite"+tipe);
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
    }
	
	public IIcon getIconFromDamage(int par1)
    {
		switch(par1){
		case 0:{
			 return i[0];
		}
		case 1:{
			 return i[1];
		}
		case 2:{
			 return i[2];
		}
		case 3:{
			 return i[3];
		}
		case 4:{
			 return i[4];
		}
		case 5:{
			 return i[5];
		}
		case 6:{
			 return i[6];
		}
		case 7:{
			 return i[7];
		}
		}
		return i[0];
    }
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + names[itemstack.getItemDamage()];
	}

}

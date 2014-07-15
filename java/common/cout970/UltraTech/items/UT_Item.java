package common.cout970.UltraTech.items;

import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class UT_Item extends Item{

	private String name;
	
	public UT_Item(String name){
		super();
		setUnlocalizedName(name);
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(64);
		this.name = name;
	}
	
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:"+name.toLowerCase());
	}
	
	public String getName(){
		return name;
	}
}

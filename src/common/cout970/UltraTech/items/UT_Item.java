package common.cout970.UltraTech.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import common.cout970.UltraTech.core.UltraTech;

public class UT_Item extends Item{

	private String name;
	
	public UT_Item(int id,String name){
		super(id);
		setUnlocalizedName(name);
		setCreativeTab(UltraTech.techTab);
		setMaxStackSize(64);
		this.name = name;
	}
	
	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:"+name.toLowerCase());
	}
	
	public String getName(){
		return name;
	}
}

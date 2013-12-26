package common.cout970.UltraTech.items;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;

public class ProcesedFood extends ItemFood{

	public ProcesedFood(int par1) {
		super(par1, 2, true);
		setCreativeTab(UltraTech.techTab);
		setMaxStackSize(64);
		setUnlocalizedName("ProcesedFood");
		setTextureName("ProcesedFood");
	}

	public void registerIcons(IconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:procesedfood");
	}
}

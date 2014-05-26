package common.cout970.UltraTech.items;

import common.cout970.UltraTech.core.UltraTech;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

public class ProcesedFood extends ItemFood{

	public ProcesedFood() {
		super(4, true);
		setCreativeTab(UltraTech.ResourceTab);
		setMaxStackSize(64);
		setUnlocalizedName("ProcesedFood");
		setTextureName("ProcesedFood");
	}

	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:procesedfood");
	}
}

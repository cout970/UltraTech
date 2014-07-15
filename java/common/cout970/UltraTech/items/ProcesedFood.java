package common.cout970.UltraTech.items;

import common.cout970.UltraTech.managers.UT_Tabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemFood;

public class ProcesedFood extends ItemFood{

	public ProcesedFood() {
		super(6, true);
		setCreativeTab(UT_Tabs.ResourceTab);
		setMaxStackSize(64);
		setUnlocalizedName("ProcesedFood");
		setTextureName("ProcesedFood");
	}

	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon("ultratech:procesedfood");
	}
}

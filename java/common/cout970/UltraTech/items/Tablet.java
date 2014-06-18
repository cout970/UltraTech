package common.cout970.UltraTech.items;

import common.cout970.UltraTech.core.UltraTech;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Tablet extends UT_Item{

	public Tablet(String name) {
		super(name);
		setMaxStackSize(1);
	}

	public boolean onItemUseFirst(ItemStack stack, EntityPlayer p, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(p.isSneaking())return true;
		p.openGui(UltraTech.instance, 20, w, x, y, z);
		return false;
	}

}

package common.cout970.UltraTech.items;

import common.cout970.UltraTech.managers.UltraTech;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemTablet extends ItemUT{

	public ItemTablet(String name) {
		super(name);
		setMaxStackSize(1);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World w, EntityPlayer p)
    {
		if(!p.isSneaking())	p.openGui(UltraTech.instance, 20, w, p.serverPosX, p.serverPosY, p.serverPosZ);
        return par1ItemStack;
    }

}

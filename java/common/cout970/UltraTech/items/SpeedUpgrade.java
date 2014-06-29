package common.cout970.UltraTech.items;

import common.cout970.UltraTech.misc.ISpeeded;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SpeedUpgrade extends UT_Item {

	public SpeedUpgrade(String name) {
		super(name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if(par3World.getTileEntity(x, y, z) instanceof ISpeeded){
			ISpeeded te = (ISpeeded) par3World.getTileEntity(x, y, z);
			if(te.upgrade()){
				par1ItemStack.splitStack(1);
			return true;
			}
			return false;
		}
		return false;
	}

}

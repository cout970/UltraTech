package common.cout970.UltraTech.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import common.cout970.UltraTech.machines.tileEntities.MinerEntity;

public class MiningUpgrade extends UT_Item{

	
	public MiningUpgrade(int id,String name) {
		super(id, name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if(par3World.getBlockTileEntity(x, y, z) instanceof MinerEntity){

			MinerEntity te = (MinerEntity) par3World.getBlockTileEntity(x, y, z);
			if(te.speed < 50){	
				te.speed += 10;
				te.hasSpeedUpgrades = true;
				te.speedUpgrades += 1;
				par1ItemStack.splitStack(1);
				return true;
			}
		}
		return false;
	}
}

package common.cout970.UltraTech.items;

import common.cout970.UltraTech.TileEntities.Tier3.MinerEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RangeUpgrade extends UT_Item{

	public RangeUpgrade(int id,String name) {
		super(id, name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if(par3World.getBlockTileEntity(x, y, z) instanceof MinerEntity){

			MinerEntity te = (MinerEntity) par3World.getBlockTileEntity(x, y, z);
			if(te.height < 31 && te.widht < 31){	
				te.height += 1;
				te.widht += 1;
				te.hasMine = false;
				te.current = 0;
				te.hasRangeUpgrades = true;
				te.rangeUpgrades += 1;
				par1ItemStack.splitStack(1);
				return true;
			}
		}
		return false;
	}
}

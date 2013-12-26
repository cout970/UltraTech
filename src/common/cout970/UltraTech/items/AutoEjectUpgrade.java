package common.cout970.UltraTech.items;

import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AutoEjectUpgrade extends UT_Item{

	public AutoEjectUpgrade(int id,String name) {
		super(id, name);
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if(par3World.getBlockTileEntity(x, y, z) instanceof MinerEntity){

			MinerEntity te = (MinerEntity) par3World.getBlockTileEntity(x, y, z);
			if(!te.eject){	
				te.eject = true;
				if(par1ItemStack.stackSize > 1){
					par1ItemStack.stackSize -= 1;
				}else{
					par1ItemStack = null;
				}
				return true;
			}
		}
		return false;
	}
}

package common.cout970.UltraTech.items;

import common.cout970.UltraTech.machines.tileEntities.EnergyIOentity;
import common.cout970.UltraTech.machines.tileEntities.IDSentity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Linker extends UT_Item{
	
	public int x=0,y=0,z=0;
 
	public Linker(int id,String name) {
		super(id, name);
	}
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int x, int y, int z, int par7, float par8, float par9, float par10)
	{

		if(par3World.getBlockTileEntity(x, y, z) instanceof IDSentity){
			IDSentity entity = (IDSentity) par3World.getBlockTileEntity(x, y, z);
			this.x = entity.xCoord;
			this.y = entity.yCoord;
			this.z = entity.zCoord;
			return true;
		}else if(par3World.getBlockTileEntity(x, y, z) instanceof EnergyIOentity){
			if(x != 0 && y!= 0 && z != 0){
				((EnergyIOentity)par3World.getBlockTileEntity(x, y, z)).setCoord(this.x,this.y,this.z);
			}
			return true;
		}
		return false;
	}

}

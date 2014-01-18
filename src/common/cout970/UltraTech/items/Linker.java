package common.cout970.UltraTech.items;

import common.cout970.UltraTech.machines.tileEntities.ReciverEntity;
import common.cout970.UltraTech.machines.tileEntities.SenderEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Linker extends UT_Item{


	public Linker(int id,String name) {
		super(id, name);
		this.setMaxStackSize(1);
	}

	@Override
	public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		//sender and reciver
		if(i == null)return false;
		if( i.stackTagCompound == null){
			i.setTagCompound( new NBTTagCompound());
			i.stackTagCompound.setIntArray("Coords", new int[]{0,0,0});
		}
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te != null){
			if(te instanceof ReciverEntity){
				((ReciverEntity)te).setFrom(i.stackTagCompound.getIntArray("Coords"));
				return true;
			}else if(te instanceof SenderEntity){
				i.stackTagCompound.setIntArray("Coords", new int[]{x,y,z});
				return true;
			}
		}

		return false;
	}
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if( par1ItemStack.stackTagCompound == null)
			par1ItemStack.setTagCompound( new NBTTagCompound());
		par1ItemStack.stackTagCompound.setIntArray("Coords", new int[]{0,0,0});
	}
}

package common.cout970.UltraTech.multiblocks.refinery;

import api.cout970.UltraTech.network.SyncTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileGag extends SyncTile{

	public int tipe = -1;
	public int x,y,z;
	
	public void restaureBlock(){
		if(tipe != -1){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, tipe, 2);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			worldObj.removeTileEntity(xCoord, yCoord, zCoord);
		}
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		x = nbt.getInteger("xC");
		y = nbt.getInteger("yC");
		z = nbt.getInteger("zC");
		tipe = nbt.getInteger("Tipe");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("xC", x);
		nbt.setInteger("yC", y);
		nbt.setInteger("zC", z);
		nbt.setInteger("Tipe", tipe);
	}
	
}

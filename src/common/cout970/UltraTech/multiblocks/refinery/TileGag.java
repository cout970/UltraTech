package common.cout970.UltraTech.multiblocks.refinery;

import net.minecraft.tileentity.TileEntity;

public class TileGag extends TileEntity{

	public int tipe = -1;
	public int x,y,z;
	
	public void restaureBlock(){
		if(tipe != -1){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, tipe, 1);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
}

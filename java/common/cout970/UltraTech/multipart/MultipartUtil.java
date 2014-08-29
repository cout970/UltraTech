package common.cout970.UltraTech.multipart;

import common.cout970.UltraTech.util.LogHelper;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class MultipartUtil{
	
	public static boolean isSolid(TileEntity t, ForgeDirection d){
		if(t == null || t.getWorldObj() == null || d == null)return false;
		Block b = t.getWorldObj().getBlock(t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ);
		if(b == null)return false;
		if(!b.isBlockSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d.ordinal()))return false;
		if(!b.isSideSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d))return false;
		return true;
	}
}

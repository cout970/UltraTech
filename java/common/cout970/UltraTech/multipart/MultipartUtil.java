package common.cout970.UltraTech.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class MultipartUtil{
	
	public static boolean isSolid(TileEntity t, ForgeDirection d){
		if(t == null || t.getWorldObj() == null || d == null)return false;
		Block b = t.getWorldObj().getBlock(t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ);
		if(b == null)return false;
		if(!b.isBlockSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d.getOpposite().ordinal()))return false;
		if(!b.isSideSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d.getOpposite()))return false;
		return true;
	}
	
	public static boolean isSolid(World w,int x, int y, int z, ForgeDirection d){
		if(w == null || d == null)return false;
		Block b = w.getBlock(x+d.offsetX, y+d.offsetY, z+d.offsetZ);
		if(b == null)return false;
		if(!b.isBlockSolid(w, x+d.offsetX, y+d.offsetY, z+d.offsetZ, d.getOpposite().ordinal()))return false;
		if(!b.isSideSolid(w, x+d.offsetX, y+d.offsetY, z+d.offsetZ, d.getOpposite()))return false;
		return true;
	}

	public static boolean isMultiPartPipe(TileEntity g) {
		if(g instanceof IFluidTransport)return true;
		if(g instanceof TileMultipart){
			TileMultipart mp = (TileMultipart) g;
			for(TMultiPart p : mp.jPartList()){
				if(p instanceof IFluidTransport)return true;
			}
		}
		return false;
	}
	
	public static boolean isHited(Cuboid6 c, Vector3 v){
		if(c == null || v == null)return false;
		if((float) c.max.y == (float) v.y || (float) c.min.y == (float) v.y){
			if((c.min.x <= v.x) && (c.max.x >= v.x)){
				if((c.min.z <= v.z) && (c.max.z >= v.z)){
					return true;
				}
			}
		}
		if((float) c.max.x == (float) v.x || (float) c.min.x == (float) v.x){
			if((c.min.y <= v.y) && (c.max.y >= v.y)){
				if((c.min.z <= v.z) && (c.max.z >= v.z)){
					return true;
				}
			}
		}
		if((float) c.max.z == (float) v.z || (float) c.min.z == (float) v.z){
			if((c.min.x <= v.x) && (c.max.x >= v.x)){
				if((c.min.y <= v.y) && (c.max.y >= v.y)){
					return true;
				}
			}
		}
		return false;
	}

	public static IFluidTransport getFluidTransport(TileEntity e) {
		if(e instanceof IFluidTransport)return (IFluidTransport) e;
		if(e instanceof TileMultipart){
			TileMultipart mp = (TileMultipart) e;
			for(TMultiPart p : mp.jPartList()){
				if(p instanceof IFluidTransport)return (IFluidTransport) p;
			}
		}
		return null;
	}
}

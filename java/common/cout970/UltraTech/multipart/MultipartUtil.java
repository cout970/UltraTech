package common.cout970.UltraTech.multipart;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.NetworkManagerRegistry;
import ultratech.api.power.PowerInterface;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import common.cout970.UltraTech.microparts.MicroCablePlane;

public class MultipartUtil{
	
	public static boolean isSolid(TileEntity t, ForgeDirection d){
		if(t == null || t.getWorldObj() == null || d == null)return false;
		Block b = t.getWorldObj().getBlock(t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ);
		if(b == null)return false;
		if(!b.isBlockSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d.ordinal()))return false;
		if(!b.isSideSolid(t.getWorldObj(), t.xCoord+d.offsetX, t.yCoord+d.offsetY, t.zCoord+d.offsetZ, d))return false;
		return true;
	}

	public static boolean canConect(MicroCablePlane m, TileEntity tile, ForgeDirection o) {
		if(tile instanceof IPowerConductor){
			PowerInterface p = ((IPowerConductor) tile).getPower();
			return NetworkManagerRegistry.canConnectOnThisSide(p, o, m.getPower());
		}else if(tile instanceof TileMultipart){
			TileMultipart t = (TileMultipart) tile;
			for(TMultiPart s : t.jPartList()){
				if(s instanceof MicroCablePlane){
					return t.canAddPart(new NormallyOccludedPart(MicroCablePlane.boundingBoxes[o.getOpposite().ordinal()]));
				}
			}
		}
		return false;
	}

	public static boolean canConect(MultiPartCable_Big m, TileEntity tile, ForgeDirection o) {
			if(tile instanceof IPowerConductor){
				PowerInterface p = ((IPowerConductor) tile).getPower();
				return NetworkManagerRegistry.canConnectOnThisSide(m.getPower(), o, p);
			}else if(tile instanceof TileMultipart){
				TileMultipart t = (TileMultipart) tile;
				for(TMultiPart s : t.jPartList()){
					if(s instanceof MultiPartCable_Big){
						return NetworkManagerRegistry.canConnectOnThisSide(m.getPower(), o, ((MultiPartCable_Big) s).getPower());
//						return t.canAddPart(new NormallyOccludedPart(m.boundingBoxes[o.getOpposite().ordinal()]));
					}
				}
			}
			return false;
	}


}

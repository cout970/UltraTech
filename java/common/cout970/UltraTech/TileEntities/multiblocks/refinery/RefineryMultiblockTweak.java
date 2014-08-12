package common.cout970.UltraTech.TileEntities.multiblocks.refinery;

import common.cout970.UltraTech.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ultratech.api.reactor.IReactorCoreBlock;
import ultratech.api.refinery.IRefineryBlock;
import ultratech.api.refinery.IRefineryComponent;
import ultratech.api.refinery.IRefineryCoreBlock;
import ultratech.api.refinery.IRefineryCoreEntity;

public class RefineryMultiblockTweak {

	

	public static void onStructureActivate(World w, int x, int y, int z,
			Block a) {
		if(w.isRemote)return;
		for(int j=0;j>-10;j--){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					if(w.getBlock(x+i-1, y+j+1, z+k-1) instanceof IRefineryCoreBlock){
						boolean t = checkStructure(w, x+i-1, y+j+1, z+k-1);
						if(t){
							setStructure(w, x+i-1, y+j+1, z+k-1);
							return;
						}
					}
				}
			}
		}
	}

	private static boolean checkStructure(World w, int x, int y, int z) {
		if(w.getBlock(x, y, z) instanceof IRefineryCoreBlock){
			boolean good = true;
			for(int j = 0; j < 2; j++){
				for(int i = 0; i < 3; i++){
					for(int k = 0; k < 3; k++){
						if(!(w.getBlock(x+i-1, y-j, z+k-1) instanceof IRefineryBlock)){
							good = false;
							break;
						}else{
							int layer = ((IRefineryBlock)w.getBlock(x+i-1, y-j, z+k-1)).getLayer();
							if(layer != 1 && layer != 0){
								good = false;
								break;
							}

						}
					}
					if(!good)break;
				}
				if(!good)break;
			}
			if(!good)return false;
			for(int j = 1; j < 9; j++){
				for(int i = 0; i < 3; i++){
					for(int k = 0; k < 3; k++){
						if(!(w.getBlock(x+i-1, y+j, z+k-1) instanceof IRefineryBlock)){
							good = false;
							break;
						}else{
							int layer = ((IRefineryBlock)w.getBlock(x+i-1, y+j, z+k-1)).getLayer();
							if(layer != 1 && layer != 2){
								good = false;
								break;
							}
						}
					}
					if(!good)break;
				}
				if(!good)break;
			}
			return good;
		}
		return false;
	}

	private static void setStructure(World w, int x, int y, int z) {
		w.setBlockMetadataWithNotify(x, y, z, 1, 2);
		TileEntity tile = w.getTileEntity(x, y, z);
		IRefineryCoreEntity core = null;
		if(tile instanceof IRefineryCoreEntity){
			core = (IRefineryCoreEntity) tile;
		}
		for(int j=0;j<10;j++){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					w.setBlockMetadataWithNotify(x+i-1, y+j-1, z+k-1, 1, 2);
					TileEntity t = w.getTileEntity(x+i-1, y+j-1, z+k-1);
					if(t instanceof IRefineryComponent){
						((IRefineryComponent) t).setCoreCoords(x, y, z);
						((IRefineryComponent) t).setCore(core);
					}
				}
			}
		}
	}

	public static void onStructureDesactivate(World w, int x, int y, int z,
			Block a) {
		if(w.isRemote)return;
		for(int j=0;j>-10;j--){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					if(w.getBlock(x+i-1, y+j+1, z+k-1) instanceof IRefineryCoreBlock){
						removeStructure(w, x+i-1, y+j+1, z+k-1);
						return;
					}
				}
			}
		}
	}

	private static void removeStructure(World w, int x, int y, int z) {
		for(int j=0;j<10;j++){
			for(int i=0;i<3;i++){
				for(int k=0;k<3;k++){
					w.setBlockMetadataWithNotify(x+i-1, y+j-1, z+k-1, 0, 2);
				}
			}
		}
	}

}

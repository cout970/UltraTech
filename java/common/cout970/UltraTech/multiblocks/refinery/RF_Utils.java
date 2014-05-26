package common.cout970.UltraTech.multiblocks.refinery;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.managers.BlockManager;

public class RF_Utils {

	/**
	 * metas
	 * 0 base
	 * 1 structure
	 * 2 core
	 * 3 invisible
	 * 4 out
	 * 5 in (base inv)
	 */
	public static int[][][] refinery ={
		{{0,0,0},{0,0,0},{0,0,0}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}},
		{{1,1,1},{1,1,1},{1,1,1}}};

	
	public static boolean refreshRefinery(World worldObj, int x,int y,int z){
		Block id = BlockManager.Refinery;
		if(worldObj.getBlock(x, y, z) != id)return false;
		int index[] = getPos(worldObj,x,y,z,id);
		boolean hasStructure = true;
		for(int k=-index[0];k<3-index[0];k++){
			for(int j=0;j<8;j++){
				for(int i=-index[1];i<3-index[1];i++){
					if(worldObj.getBlock(x+i, y+j, z+k) != id || (worldObj.getBlockMetadata(x+i, y+j, z+k) != refinery[j][index[0]+k][index[1]+i]) && worldObj.getBlockMetadata(x+i, y+j, z+k) != 4){
						hasStructure = false;
					}
				}
			}
		}
		return hasStructure;
	}
	
	public static int[] getPos(World worldObj, int x,int y,int z,Block id){
		boolean[] b = new boolean[4];
		if(worldObj.getBlock(x+1, y, z) == id)b[0]= true;
		if(worldObj.getBlock(x-1, y, z) == id)b[1]= true;
		if(worldObj.getBlock(x, y, z+1) == id)b[2]= true;
		if(worldObj.getBlock(x, y, z-1) == id)b[3]= true;
		int index[] = new int[2];
		if(b[0] && b[1] && b[2] && b[3]){index[0]=1;index[1]=1;}
		if(!b[0] && b[1] && b[2] && b[3]){index[0]=1;index[1]=2;}
		if(b[0] && !b[1] && b[2] && b[3]){index[0]=1;index[1]=0;}
		if(b[0] && b[1] && !b[2] && b[3]){index[0]=2;index[1]=1;}
		if(b[0] && b[1] && b[2] && !b[3]){index[0]=0;index[1]=1;}
		
		if(!b[0] && b[1] && !b[2] && b[3]){index[0]=2;index[1]=2;}
		if(b[0] && !b[1] && b[2] && !b[3]){index[0]=0;index[1]=0;}
		if(b[0] && !b[1] && !b[2] && b[3]){index[0]=2;index[1]=0;}
		if(!b[0] && b[1] && b[2] && !b[3]){index[0]=0;index[1]=2;}
		return index;
	}
	
	public static  void removeRefinery(World worldObj, int x,int y,int z){
		for(int i=-1;i<2;i++){
			for(int j=0;j<8;j++){
				for(int k=-1;k<2;k++){
					TileEntity t = worldObj.getTileEntity(x+i, y+j, z+k);
					if(t instanceof TileGag)((TileGag) t).restaureBlock();
				}
			}
		}
	}

	public static void setRefinery(World worldObj, int x,int y,int z){
		for(int i=-1;i<2;i++){
			for(int j=0;j<8;j++){
				for(int k=-1;k<2;k++){
					int meta = worldObj.getBlockMetadata(x+i, y+j, z+k);
					if(meta == 1){
						worldObj.setBlockMetadataWithNotify(x+i, y+j, z+k, 3, 2);
						worldObj.markBlockForUpdate(x+i, y+j, z+k);
					}else if(meta == 0){
						worldObj.setBlockMetadataWithNotify(x+i, y+j, z+k, 5, 2);
						worldObj.markBlockForUpdate(x+i, y+j, z+k);
					}
				}
			}
		}
		for(int i=-1;i<2;i++){
			for(int j=0;j<8;j++){
				for(int k=-1;k<2;k++){
					if(worldObj.getTileEntity(x+i, y+j, z+k) instanceof TileGag){
						((TileGag)worldObj.getTileEntity(x+i, y+j, z+k)).tipe = (j == 0)? 0 : 1;
						((TileGag)worldObj.getTileEntity(x+i, y+j, z+k)).x = x;
						((TileGag)worldObj.getTileEntity(x+i, y+j, z+k)).y = y+1;
						((TileGag)worldObj.getTileEntity(x+i, y+j, z+k)).z = z;
					}
				}}}
		worldObj.setBlockMetadataWithNotify(x, y+1, z, 2, 2);
	}

	public static int[] getCenter(World w, int x, int y, int z) {
		int[] a = getPos(w, x, y, z, BlockManager.Refinery);
		int[] b = {1-a[1],0,1-a[0]};
		return b;
	}
}

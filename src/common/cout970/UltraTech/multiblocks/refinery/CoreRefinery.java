package common.cout970.UltraTech.multiblocks.refinery;

import common.cout970.UltraTech.managers.BlockManager;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CoreRefinery extends TileRefinery{
	
	public boolean u = true;
	
	public void updateEntity(){
		if(u){
			u = false;
			setStructure(xCoord, yCoord, zCoord);
		}
	}
	
	public void removeStructure(int x,int y,int z){
		System.out.println("removing");
		for(int i=-1;i<2;i++){
			for(int j=1;j<8;j++){
				for(int k=-1;k<2;k++){
					TileEntity t = worldObj.getBlockTileEntity(x+i, y+j, z+k);
					if(t instanceof TileGag)((TileGag) t).restaureBlock();
				}
			}
		}
		for(int i=-1;i<2;i++){for(int k=-1;k<2;k++){
			if(i == 0 && k == 0){}else{TileEntity t = worldObj.getBlockTileEntity(x+i, y, z+k);
			if(t instanceof TileGag)((TileGag) t).restaureBlock();
			}}}
		System.out.println("removing core");
		worldObj.setBlock(x, y, z, BlockManager.Refinery.blockID);
	}

	public void setStructure(int x,int y,int z){

		for(int i=-1;i<2;i++){
			for(int j=1;j<8;j++){
				for(int k=-1;k<2;k++){
					worldObj.setBlockMetadataWithNotify(x+i, y+j, z+k, 3, 0);
					worldObj.markBlockForUpdate(x+i, y+j, z+k);
				}
			}
		}
		for(int i=-1;i<2;i++){for(int k=-1;k<2;k++){
			if(i == 0 && k == 0){}else{worldObj.setBlockMetadataWithNotify(x+i, y, z+k, 3, 1);
			worldObj.markBlockForUpdate(x+i, y, z+k);
			}}}
		for(int i=-1;i<2;i++){
			for(int j=0;j<8;j++){
				for(int k=-1;k<2;k++){
					if(worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof TileGag){
						((TileGag)worldObj.getBlockTileEntity(x+i, y+j, z+k)).tipe = 1;
					}
				}}}
		worldObj.setBlockMetadataWithNotify(x, y, z, 2, 1);
		for(int i=-1;i<2;i++){
			for(int j=1;j<8;j++){
				for(int k=-1;k<2;k++){
					if(worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof TileGag){
						((TileGag)worldObj.getBlockTileEntity(x+i, y+j, z+k)).x = x;
						((TileGag)worldObj.getBlockTileEntity(x+i, y+j, z+k)).y = y;
						((TileGag)worldObj.getBlockTileEntity(x+i, y+j, z+k)).z = z;
					}
				}}}
	}

	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        return bb;
    }
}

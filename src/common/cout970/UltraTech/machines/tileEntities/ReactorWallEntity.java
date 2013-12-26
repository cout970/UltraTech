package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.lib.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ReactorWallEntity extends TileEntity{

	public boolean reactor = false;
	public int x,y,z;
	public boolean multi = false;

	public ReactorWallEntity(){
		super();
	}
	
	public boolean checkMulti() {
			int[] ids = new int[27];
			int current = 0;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						ids[current] = worldObj.getBlockId(x+i, y+j, z+k);
						current++;
					}
				}
			}for(int id : ids) {
				if(id != UltraTech.ReactorWall.blockID && id != UltraTech.Reactor.blockID && id != UltraTech.IDS.blockID && id != UltraTech.ReactorTank.blockID && id != UltraTech.SteamTurbine.blockID && id != UltraTech.WaterBlock.blockID){
					return false;
				}
			}
			return true;
	}

	public void check()
	{
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == Reference.Reactor){
						x = xCoord+i;
						y = yCoord+j;
						z = zCoord+k;
						reactor = true;
						return;
					}
					current++;
				}
			}
		}reactor = false;
	}

	public void work() {
			check();
			if(reactor){
				multi = checkMulti();
				if(multi){
					setCoords();
				}
			}
	}

	private void setCoords() {
		((ReactorEntity)this.worldObj.getBlockTileEntity(x, y, z)).multi = true;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof ReactorWallEntity){
						((ReactorWallEntity)this.worldObj.getBlockTileEntity(x+i, y+j, z+k)).x = x;
						((ReactorWallEntity)this.worldObj.getBlockTileEntity(x+i, y+j, z+k)).y = y;
						((ReactorWallEntity)this.worldObj.getBlockTileEntity(x+i, y+j, z+k)).z = z;
						((ReactorWallEntity)this.worldObj.getBlockTileEntity(x+i, y+j, z+k)).multi = true;
						this.worldObj.markBlockForRenderUpdate(x+i, y+j, z+k);
					}
				}
			}
		}
	}

	public void dell() {
		if(multi){
			if(((ReactorEntity)this.worldObj.getBlockTileEntity(x, y, z)) != null)
			((ReactorEntity)this.worldObj.getBlockTileEntity(x, y, z)).multi = false;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof ReactorWallEntity){
							((ReactorWallEntity)this.worldObj.getBlockTileEntity(x+i, y+j, z+k)).multi = false;
						}
					}
				}
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        multi = nbtTagCompound.getBoolean("multi");
        x = nbtTagCompound.getInteger("xR");
        y = nbtTagCompound.getInteger("yR");
        z = nbtTagCompound.getInteger("zR");
	}
	
	  @Override
	    public void writeToNBT(NBTTagCompound nbtTagCompound) {

	        super.writeToNBT(nbtTagCompound);
	        nbtTagCompound.setBoolean("multi", multi);
	        nbtTagCompound.setInteger("xR", x);
	        nbtTagCompound.setInteger("yR", y);
	        nbtTagCompound.setInteger("zR", z);
	  }
}

package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.core.UltraTech;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.tileentity.TileEntity;

public class ReactorWallEntity extends TileEntity implements IReactorPart{

	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;

	public ReactorWallEntity(){
		super();
	}

	public void setUp(){
		
			SearchReactor();
			if(found){
				checkStructure();
				if(Structure){
					activateBlocks();
				}
			}
	}
	
	@Override
	public void SearchReactor() {
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == UltraTech.Reactor.blockID){
						Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
						found = true;
						return;
					}
					current++;
				}
			}
		}
		found = false;
	}

	@Override
	public ReactorEntity getReactor() {
		return this.Reactor;
	}

	@Override
	public void onNeighChange() {
		setUp();	
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(Reactor.xCoord+i, Reactor.yCoord+j, Reactor.zCoord+k);
					current++;
				}
			}
		}
		
		this.Structure = false;

		for(TileEntity t : ids) {
			if(!(t instanceof IReactorPart))return;
		}
		this.Structure = true;
	}

	@Override
	public void activateBlocks() {
		Reactor.setStructure(true);
		int x,y,z;
		x = Reactor.xCoord;
		y = Reactor.yCoord;
		z = Reactor.zCoord;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(true);
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setReactor(Reactor);
					}
				}
			}
		}
	}

	@Override
	public void desactivateBlocks() {
		if(Structure){
			Structure = false;
			int x,y,z;
			x = Reactor.xCoord;
			y = Reactor.yCoord;
			z = Reactor.zCoord;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(false);
						}
					}
				}
			}
		}		
	}

	@Override
	public void setStructure(boolean structure) {
		Structure = structure;		
	}

	@Override
	public void setReactor(ReactorEntity e) {
		Reactor = e;
	}
	
	@Override
	public boolean isStructure() {
		return this.Structure;
	}
	
//	@Override
//	public void readFromNBT(NBTTagCompound nbtTagCompound) {
//        super.readFromNBT(nbtTagCompound);
//        multi = nbtTagCompound.getBoolean("multi");
//        x = nbtTagCompound.getInteger("xR");
//        y = nbtTagCompound.getInteger("yR");
//        z = nbtTagCompound.getInteger("zR");
//	}
//	
//	  @Override
//	    public void writeToNBT(NBTTagCompound nbtTagCompound) {
//
//	        super.writeToNBT(nbtTagCompound);
//	        nbtTagCompound.setBoolean("multi", multi);
//	        if(Reactor != null){
//	        nbtTagCompound.setInteger("xR", x);
//	        nbtTagCompound.setInteger("yR", y);
//	        nbtTagCompound.setInteger("zR", z);
//	        }
//	  }
}

package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.blocks.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ReactorControllerEntity extends TileEntity implements IReactorPart{

	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;
	public int meta;
	public boolean update = false;

	public void setUp(){
		SearchReactor();
		if(Reactor != null){
			Reactor.work = !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		}
		if(found){
			checkStructure();
			if(Structure){
				activateBlocks();
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
				meta = 1;
			}
		}
	}
	
	public void updateEntity(){
		if(!update){
			setUp();
			update = true;
		}
		if(!Structure && meta == 1){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
			meta = 0;
		}else if(Structure && meta == 0){
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
			meta = 1;
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
					if(ids[current] == BlockManager.Reactor.blockID){
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
			if(getReactor() == null)return;
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

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		Structure = nbtTagCompound.getBoolean("Structure");
		int x,y,z;
		x = nbtTagCompound.getInteger("xR");
		y = nbtTagCompound.getInteger("yR");
		z = nbtTagCompound.getInteger("zR");
		if(worldObj != null){
			Reactor = (ReactorEntity) worldObj.getBlockTileEntity(x,y,z);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("Structure", Structure);
		if(Reactor != null){
			nbtTagCompound.setInteger("xR", Reactor.xCoord);
			nbtTagCompound.setInteger("yR", Reactor.yCoord);
			nbtTagCompound.setInteger("zR", Reactor.zCoord);
		}
	}

}

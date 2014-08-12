package common.cout970.UltraTech.TileEntities.multiblocks.refinery;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ultratech.api.reactor.IReactorCoreEntity;
import ultratech.api.refinery.IRefineryComponent;
import ultratech.api.refinery.IRefineryCoreEntity;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;

public class Refinery_Entity_Base extends SyncTile implements IRefineryComponent,IUpdatedEntity{

	public IRefineryCoreEntity core;
	public int x,y,z;
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(core == null)refreshCore();
	}
	
	@Override
	public void onNeigUpdate() {		
	}

	@Override
	public void RestaureBlock() {
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
		worldObj.removeTileEntity(xCoord, yCoord, zCoord);
	}

	@Override
	public boolean isFormed() {
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) != 0;
	}

	@Override
	public IRefineryCoreEntity getCore() {
		return core;
	}

	@Override
	public void setCore(IRefineryCoreEntity c) {
		core = c;
	}

	@Override
	public void refreshCore() {
		TileEntity t = worldObj.getTileEntity(x, y, z);
		if(t instanceof IRefineryCoreEntity){
			setCore((IRefineryCoreEntity) t);
		}else{
			RestaureBlock();
		}
	}

	@Override
	public void setCoreCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		markDirty();
	}
	
	public void writeToNBT(NBTTagCompound NBT){
		super.writeToNBT(NBT);
		NBT.setInteger("xCore", x);
		NBT.setInteger("yCore", y);
		NBT.setInteger("zCore", z);
	}

	public void readFromNBT(NBTTagCompound NBT){
		super.readFromNBT(NBT);
		x = NBT.getInteger("xCore");
		y = NBT.getInteger("yCore");
		z = NBT.getInteger("zCore");
	}	
}

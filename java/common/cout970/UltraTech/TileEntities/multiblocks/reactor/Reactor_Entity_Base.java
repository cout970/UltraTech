package common.cout970.UltraTech.TileEntities.multiblocks.reactor;

import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.network.SyncTile;
import ultratech.api.reactor.IReactorComponent;
import ultratech.api.reactor.IReactorCoreEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class Reactor_Entity_Base extends SyncTile implements IReactorComponent,IUpdatedEntity{

	public IReactorCoreEntity core;
	public int x,y,z;
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(core == null)refreshCore();
	}
	
	@Override
	public void RestaureBlock(){
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
	}
	
	@Override
	public boolean isFormed(){
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1;
	}

	@Override
	public IReactorCoreEntity getCore() {
		return core;
	}

	@Override
	public void setCore(IReactorCoreEntity c) {
		core = c;
	}

	@Override
	public void onNeigUpdate() {
		ReactorMultiblockTweak.onBlockUpdate(this);
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

	@Override
	public void refreshCore() {
		TileEntity t = worldObj.getTileEntity(x, y, z);
		if(t instanceof IReactorCoreEntity){
			setCore((IReactorCoreEntity) t);
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
}

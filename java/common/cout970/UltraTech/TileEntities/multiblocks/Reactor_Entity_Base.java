package common.cout970.UltraTech.TileEntities.multiblocks;

import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.network.SyncTile;
import ultratech.api.reactor.IReactorComponent;
import ultratech.api.reactor.IReactorCoreEntity;
import net.minecraft.tileentity.TileEntity;

public class Reactor_Entity_Base extends SyncTile implements IReactorComponent,IUpdatedEntity{

	public IReactorCoreEntity core;
	
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
}

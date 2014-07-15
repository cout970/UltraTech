package common.cout970.UltraTech.TileEntities.multiblocks;

import common.cout970.UltraTech.misc.IUpdatedEntity;

import ultratech.api.reactor.IReactorComponent;
import net.minecraft.tileentity.TileEntity;

public class Reactor_Entity_Base extends TileEntity implements IReactorComponent,IUpdatedEntity{

	public IReactorComponent core;
	
	@Override
	public void RestaureBlock(){
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
	}
	
	@Override
	public boolean isFormed(){
		return worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1;
	}

	@Override
	public IReactorComponent getCore() {
		return core;
	}

	@Override
	public void setCore(IReactorComponent c) {
		core = c;
	}

	@Override
	public void onNeigUpdate() {
		ReactorMultiblockTweak.onBlockUpdate(this);
	}
}

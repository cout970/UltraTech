package common.cout970.UltraTech.TileEntities.multiblocks.reactor;

import common.cout970.UltraTech.util.LogHelper;

public class Reactor_Redstone_Entity extends Reactor_Entity_Base{
	
	public void onNeigUpdate(){
		super.onNeigUpdate();
		if(getCore() != null){
			getCore().upadateRedstoneSignal();
		}
	}
}

package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.TileEntities.Tier2.EngineEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class EngineContainer extends UT_Container{
	
	public EngineEntity tileEntity;
	
	public EngineContainer(InventoryPlayer inventoryPlayer, EngineEntity tileEntity2){
		super(inventoryPlayer, tileEntity2);
		tileEntity = tileEntity2;
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}


}

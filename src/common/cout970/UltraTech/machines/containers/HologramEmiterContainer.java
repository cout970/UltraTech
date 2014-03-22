package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class HologramEmiterContainer extends UT_Container{

	public HologramEmiterContainer(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		bindPlayerInventory(inventoryPlayer);
	}

}

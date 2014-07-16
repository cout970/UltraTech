package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class Reactor_Container extends UT_Container{

	public Reactor_Container(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.bindPlayerInventory(inventoryPlayer);
	}

}

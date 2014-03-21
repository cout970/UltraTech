package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.TileEntities.Tier1.FermenterEntity;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public class FermenterContainer extends UT_Container {

	FermenterEntity tile;
	
	public FermenterContainer(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		tile = (FermenterEntity) t;
		this.addSlotToContainer(new Slot(tile,0,82,33));
		bindPlayerInventory(inventoryPlayer);
	}

}

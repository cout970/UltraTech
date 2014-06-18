package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class TabletContainer extends Container{

	public TabletContainer(InventoryPlayer inventory) {
		super();
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}

package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class ContainerTablet extends Container{

	public ContainerTablet(InventoryPlayer inventory) {
		super();
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}

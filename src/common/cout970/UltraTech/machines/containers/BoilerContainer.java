package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class BoilerContainer extends UT_Container{

	public BoilerContainer(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 0);
	}

}

package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TesseractContainer extends UT_Container{

	public TesseractContainer(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 0);
	}
}

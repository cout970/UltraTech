package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class Chemical_Container extends UT_Container{

	public Chemical_Container(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.addSlotToContainer(new Slot((IInventory) t, 0, 145, 15));
		this.addSlotToContainer(new Slot((IInventory) t, 1, 145, 33));
		this.addSlotToContainer(new Slot((IInventory) t, 2, 145, 51));
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 3);
	}
}

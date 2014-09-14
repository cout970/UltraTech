package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_IO_Entity;

public class ContainerRefinery extends ContainerUT{

	Refinery_IO_Entity entity;
	
	public ContainerRefinery(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		entity = (Refinery_IO_Entity) t;
		this.bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 0);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			entity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		entity.getGUINetworkData(i, j);
	}
}
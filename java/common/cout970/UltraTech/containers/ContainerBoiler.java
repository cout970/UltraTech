package common.cout970.UltraTech.containers;

import common.cout970.UltraTech.TileEntities.fluid.TileEntityBoiler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerBoiler extends ContainerUT{

	private TileEntityBoiler tile;

	public ContainerBoiler(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.bindPlayerInventory(inventoryPlayer);
		tile = (TileEntityBoiler) t;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 0);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			tile.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		tile.getGUINetworkData(i, j);
	}

}

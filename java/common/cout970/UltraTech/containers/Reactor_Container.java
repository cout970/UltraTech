package common.cout970.UltraTech.containers;

import ultratech.api.reactor.IReactorCoreEntity;
import common.cout970.UltraTech.misc.SlotPhantom;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class Reactor_Container extends UT_Container{

	private SyncTile tile;

	public Reactor_Container(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		tile = (SyncTile) t;		
		this.bindPlayerInventorywithheight(inventoryPlayer);
		int pos = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				this.addSlotToContainer(new SlotPhantom((IInventory) t,pos++,114+i*18-36,46+j*18-36));
			}
		}
	}
	
	public void bindPlayerInventorywithheight(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 160 + i * 18));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 218));
		}
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
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return null;
	}

}

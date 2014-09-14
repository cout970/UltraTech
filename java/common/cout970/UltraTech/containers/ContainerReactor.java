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

public class ContainerReactor extends ContainerUT{

	private SyncTile tile;

	public ContainerReactor(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		tile = (SyncTile) t;	
		int size = ((IReactorCoreEntity) tile).getSize();
		int space = 0;
		if(size == 1)space = 1;
		if(size == 2)space = 9;
		if(size == 3)space = 25;
		int pos = 0;
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 5; i++) {
				if(((IReactorCoreEntity) tile).isSlotinSpace(pos,space)){
					this.addSlotToContainer(new Slot((IInventory) t,pos++,114+i*18-36,46+j*18-36));
				}else{
					this.addSlotToContainer(new SlotPhantom((IInventory) t,pos++,114+i*18-36,46+j*18-36));
				}
			}
		}		
		this.bindPlayerInventorywithheight(inventoryPlayer);
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

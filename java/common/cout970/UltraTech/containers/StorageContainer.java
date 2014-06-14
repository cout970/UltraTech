package common.cout970.UltraTech.containers;

import api.cout970.UltraTech.Vpower.Machine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class StorageContainer extends UT_Container{

public Machine tileEntity;
	
	public StorageContainer(InventoryPlayer inventoryPlayer, Machine tileEntity2){
		super(inventoryPlayer, tileEntity2);
		tileEntity = tileEntity2;
		bindPlayerInventory(inventoryPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}

}

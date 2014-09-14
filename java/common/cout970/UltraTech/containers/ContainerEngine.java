package common.cout970.UltraTech.containers;

import common.cout970.UltraTech.TileEntities.intermod.TileEntityEngine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerEngine extends ContainerUT{
	
	public TileEntityEngine tileEntity;
	
	public ContainerEngine(InventoryPlayer inventoryPlayer, TileEntityEngine tileEntity2){
		super(inventoryPlayer, tileEntity2);
		tileEntity = tileEntity2;
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
		return transfer(entityPlayer, slotIndex, 0);
	}
}

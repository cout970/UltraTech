package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ContainerInfiniteSupply extends ContainerUT{

	public ContainerInfiniteSupply(InventoryPlayer inventoryPlayer, TileEntity t) {
		super(inventoryPlayer, t);
		this.bindPlayerInventory(inventoryPlayer);
		this.addSlotToContainer(new Slot((IInventory) t, 0, 8, 8));
		for(int d=0;d<9;d++)
		this.addSlotToContainer(new Slot((IInventory) t, d+1, 8+18*d, 30));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}

}

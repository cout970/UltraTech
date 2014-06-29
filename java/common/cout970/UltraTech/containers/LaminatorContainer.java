package common.cout970.UltraTech.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT1_Entity;

public class LaminatorContainer extends UT_Container{

	public LaminatorContainer(InventoryPlayer inventoryPlayer, LaminatorT1_Entity t) {
		super(inventoryPlayer, t);
		
		addSlotToContainer(new Slot(t, 0, 53, 33));
		addSlotToContainer(new Slot(t, 1, 143, 33){
			@Override
			public boolean isItemValid(ItemStack a){
				return false;
			}
		});
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return transfer(player, slot, 2);
	}
}

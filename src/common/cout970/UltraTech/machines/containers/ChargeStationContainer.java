package common.cout970.UltraTech.machines.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class ChargeStationContainer extends UT_Container{


	public ChargeStationContainer(InventoryPlayer inventoryPlayer,TileEntity tile){
		super(inventoryPlayer,tile);
		int i = 0;
		for(int y = 0;y<2;y++){
			for(int x = 0;x<3;x++){
				addSlotToContainer(new Slot((IInventory) tile, i, 80+18*x, 16+36*y));i++;
			}
		}
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return transfer(player, slot, 6);
	}

}

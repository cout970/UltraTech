package common.cout970.UltraTech.machines.containers;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import common.cout970.UltraTech.misc.SlotPhantom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CrafterContainer extends UT_Container{
	
	private CrafterEntity tileEntity;

	public CrafterContainer(InventoryPlayer inventoryPlayer, CrafterEntity tileEntity2){
		super(inventoryPlayer, tileEntity2);
		tileEntity = tileEntity2;
		int c = 0;
		for (int j = 0; j < 3; j++) {
			for (int x = 0; x < 3; x++) {
				addSlotToContainer(new SlotPhantom(tileEntity2.craft, c, 13 + x * 18, 26 + j * 18));
				c++;
			}
		}	
		c = 0;
		addSlotToContainer(new SlotPhantom(tileEntity2, c, 81, 44));
		c++;
		for (int x = 0; x < 3; x++) {
			for (int j = 0; j < 3; j++) {
				addSlotToContainer(new Slot(tileEntity2, c, 111 + x * 18, 26 + j * 18));
				c++;
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new SlotPhantom(tileEntity.saves, i, 8 + i * 18, 6));
		}
		bindPlayerInventory(inventoryPlayer);
	}


	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);
        for(int g=0; g < 9; g++){
        if(tileEntity.found.containsKey(g))par1ICrafting.sendProgressBarUpdate(this, g, (tileEntity.found.get(g))? 1 : 0);
        }
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < crafters.size(); i++) {
			tileEntity.sendGUINetworkData(this, (ICrafting) crafters.get(i));
		}
	}

	@Override
	public void updateProgressBar(int i, int j) {
		tileEntity.getGUINetworkData(i, j);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}
	
}

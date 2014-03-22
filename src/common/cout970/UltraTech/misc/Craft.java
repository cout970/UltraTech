package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class Craft {

	public ItemStack[] items;
	
	public Craft(ItemStack[] i){
		items = i;
	}
	
	public Craft(InventoryCrafting i){
		items = new ItemStack[9];
		for(int x = 0;x<9;x++){
			items[x] = i.getStackInSlot(x);
		}
				
	}

	public InventoryCrafter getInventoryCrafter(CrafterEntity e) {
		InventoryCrafter i = new InventoryCrafter(e, 3, 3);
		for(int y =0;y<9;y++){
			i.setInventorySlotContents(y, items[y]);
		}
		return i;
	}
}

package common.cout970.UltraTech.misc;

import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Craft {

	public ItemStack[] items = new ItemStack[9];
	
	public Craft(ItemStack[] i){
		items = i;
	}
	
	public Craft(InventoryCrafting i){
		for(int x = 0;x<9;x++){
			items[x] = i.getStackInSlot(x);
		}		
	}

	public Craft(NBTTagCompound tagCompound) {
		this.readFromNBT(tagCompound);
	}

	public InventoryCrafter getInventoryCrafter(CrafterEntity e) {
		InventoryCrafter i = new InventoryCrafter(e, 3, 3);
		for(int y =0;y<9;y++){
			i.setInventorySlotContents(y, items[y]);
		}
		return i;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for(int h =0;h<9;h++){
			if(items[h] != null){
			NBTTagCompound tag = new NBTTagCompound();
			tag.setByte("Slot", (byte) h);
			items[h].writeToNBT(tag);
			list.appendTag(tag);
			}
		}
		nbt.setTag("Recipe", list);
	}

	public void readFromNBT(NBTTagCompound tagCompound) {
		NBTTagList list = tagCompound.getTagList("Recipe",10);
		for (int i = 0; i < list.tagCount(); ++i) {
			NBTTagCompound tag = (NBTTagCompound) list.getCompoundTagAt(0);
			int slot = tag.getByte("Slot");
			if(slot >= 0 && slot <9){
				ItemStack a = ItemStack.loadItemStackFromNBT(tag);
				items[slot]= a;
			}
		}
	}
}

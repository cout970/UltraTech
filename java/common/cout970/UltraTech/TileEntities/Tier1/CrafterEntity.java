package common.cout970.UltraTech.TileEntities.Tier1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.containers.CrafterContainer;
import common.cout970.UltraTech.gui.CrafterGui;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.misc.Craft;
import common.cout970.UltraTech.misc.CrafterRecipe;
import common.cout970.UltraTech.misc.InventoryCrafter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;

public class CrafterEntity extends SyncTile implements IInventory{

	public ItemStack[] inventory;
	public ItemStack result;
	public InventoryCrafter craft;
	public CrafterRecipe saves;
	public boolean[] found = new boolean[9];
	private boolean loop;

	public CrafterEntity(){
		inventory = new ItemStack[9];
		craft = new InventoryCrafter(this, 3, 3);
		saves = new CrafterRecipe();
	}

	public void update(){
		if(worldObj.isRemote)return;
		loop = false;
		result = CraftingManager.getInstance().findMatchingRecipe(craft, worldObj);
		canCraft();
		this.markDirty();
		Sync();
	}

	public void canCraft(){
		found = new boolean[9];
		Map<Integer,Integer> already = new HashMap<Integer,Integer>();
		for(int c = 0; c < 9;c++){
			if(craft.getStackInSlot(c)== null){
				found[c] = true;
			}else{
				for(int i = 0; i < this.getSizeInventory();i++){//searching in this inventory
					if(equal(this.getStackInSlot(i),craft.getStackInSlot(c),c)){
						boolean cant;
						if(already.containsKey(i)){
							cant = false;
							int aux = already.get(i);
							if(aux < getStackInSlot(i).stackSize){
								already.remove(i);
								already.put(i, aux+1);
								cant = true;
							}
						}else{
							already.put(i, 1);
							cant = true;
						}
						if(cant){
							found[c] = true;
							break;
						}
					}
				}
			}
		}
		List<TileEntity> t = UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord);
		List<IInventory> in = new ArrayList<IInventory>();
		for(TileEntity tile : t)if(tile instanceof IInventory)in.add((IInventory) tile);
		for(IInventory inv : in){
			Map<Integer,Integer> alreadyInv = new HashMap<Integer,Integer>();
			for(int c = 0; c < 9;c++){
				if(!found[c]){
					for(int i = 0; i < inv.getSizeInventory();i++){//searching on inventorys
						if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c),c)){
							boolean cant = true;
							if(alreadyInv.containsKey(i)){
								cant = false;
								int aux = alreadyInv.get(i);
								if(aux < inv.getStackInSlot(i).stackSize){
									alreadyInv.remove(i);
									alreadyInv.put(i, aux+1);
									cant = true;
								}
							}else{
								alreadyInv.put(i, 1);
								cant = true;
							}
							if(cant){
								found[c] = true;
								break;
							}
						}
					}
					if(found[c])break;
					if(inv instanceof CrafterEntity && !loop){
						loop = true;
						if(equal(inv.getStackInSlot(-1),craft.getStackInSlot(c),c)){
							((CrafterEntity) inv).canCraft();
							if(((CrafterEntity) inv).allFound())found[c] = true;
							break;
						}
					}
				}
			}
		}
	}
	
	public boolean allFound() {
		for(int x=0;x<9;x++) if(!found[x])return false;
		if(result == null)return false;
		return true;
	}
	
	private boolean equal(ItemStack a, ItemStack b,int slot) {
		if(a == null && b == null)return true;
		if(a != null && b == null)return false;
		if(a == null && b != null)return false;
		if(OreDictionary.itemMatches(a, b, true))return true;
		if(OreDictionary.getOreID(a)!= -1 && OreDictionary.getOreID(b)!= -1){
			if(OreDictionary.getOreID(a) == OreDictionary.getOreID(b))return true;
		}
		//remplace and check
//		InventoryCrafter c = new InventoryCrafter(this, 3, 3);
//		for(int r = 0;r<9;r++){
//			c.setInventorySlotContents(r, craft.getStackInSlot(r));
//		}
//		c.setInventorySlotContents(slot, a);
//		ItemStack g = CraftingManager.getInstance().findMatchingRecipe(c, worldObj);
//		ItemStack h = CraftingManager.getInstance().findMatchingRecipe(craft, worldObj);
//		if(g == null)return false;
//		if(h == null)return false;
//		if(OreDictionary.itemMatches(g, h, true))return true;
		return false;
	}

	public void craft() {
		if(allFound() && addItemStack(result)){
			for(int c = 0; c < 9;c++){
				if(craft.getStackInSlot(c)!= null){
					for(int i = 0; i < this.getSizeInventory();i++){
						if(equal(this.getStackInSlot(i),craft.getStackInSlot(c),c)){
							useItemToCraft(this, i);
							break;
						}
					}
					List<TileEntity> t = UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord);
					List<IInventory> in = new ArrayList<IInventory>();
					for(TileEntity tile : t)if(tile instanceof IInventory)in.add((IInventory) tile);
					for(IInventory inv : in){
						for(int i = 0; i < inv.getSizeInventory();i++){
							if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c),c)){
								useItemToCraft(inv, i);
								break;
							}
							if(inv instanceof CrafterEntity){
								if(equal(inv.getStackInSlot(-1),craft.getStackInSlot(c),c)){
									((CrafterEntity) inv).canCraft();
									((CrafterEntity) inv).craft();
									for(int j = 0; j < inv.getSizeInventory();j++){
										if(equal(inv.getStackInSlot(j),craft.getStackInSlot(c),c)){
											useItemToCraft(inv, j);
											break;
										}
									}
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void useItemToCraft(IInventory inv, int slot){
		ItemStack item = inv.getStackInSlot(slot);
		if(item != null){
			inv.decrStackSize(slot, 1);
			if (item.getItem().hasContainerItem(item))
			{
				ItemStack itemContainer = item.getItem().getContainerItem(item);
				if(itemContainer == null){
					return;
				}else if(itemContainer.isItemStackDamageable() && itemContainer.getItemDamage() > itemContainer.getMaxDamage()){
					itemContainer = null;
					inv.setInventorySlotContents(slot, itemContainer);
					return;
				}else{
					inv.setInventorySlotContents(slot, itemContainer);
				}
			}
		}
	}

	public boolean addItemStack(ItemStack i){
		if(i == null)return false;
		for(int s = 0;s < this.getSizeInventory();s++){	
			if (this.inventory[s] == null)
			{
				this.inventory[s] = i.copy();
				return true;
			}
			else if (this.inventory[s].isItemEqual(i) && i.getMaxStackSize() >= this.inventory[s].stackSize+i.stackSize)
			{
				if(inventory[s].stackSize + i.stackSize <= getInventoryStackLimit()){
				inventory[s].stackSize += i.stackSize;
				return true;
				}
			}
		}
		return false;
	}
	
	public void emptyCraft() {
		for(int i = 0; i < 9;i++){
			craft.setInventorySlotContents(i, null);
		}
	}
	
	public void saveRecipe(){
		int slot = 0;
		boolean f = false;
		for(;slot < 9;slot++){
			if(saves.getStackInSlot(slot) == null){
				f = true;
				break;
			}
		}
		if(!f){
			
			if(saves.slot++ > 9)saves.slot = 0;
			slot = saves.slot;
		}
		if(slot >= 9)slot = 0;
		saves.setInventorySlotContents(slot, result);
		saves.recipes[slot] = new Craft(this.craft);
	}
	
	public void loadRecipes(int r){
		craft.blend(saves.recipes[r].getInventoryCrafter(this));
		update();
	}
	
	public void DellRecipe(int slot) {
		this.saves.setInventorySlotContents(slot, null);
		saves.recipes[slot] = null;
	}
	
	//Inventory
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}


	@Override
	public ItemStack getStackInSlot(int i) {
		if(i == -1)return result;
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack itemStack = getStackInSlot(slot);
		if (itemStack != null) {
			if (itemStack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			}
			else {
				itemStack = itemStack.splitStack(amount);
				if (itemStack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return itemStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack itemStack = inventory[slot];
			inventory[slot] = null;
			return itemStack;
		}
		else
			return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemStack) {
		if(slot == -1){
			result = itemStack;
			return;
		}
		inventory[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}
		update();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}
	
	//Save & Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		//inv 1
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory",10);
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
		//inv 2
		NBTTagList tagList2 = nbtTagCompound.getTagList("Craft",10);
		craft = new InventoryCrafter(this, 3, 3);
		for (int i = 0; i < tagList2.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList2.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < craft.getSizeInventory()) {
				craft.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tagCompound));
			}
		}
		//saves
		NBTTagList list = nbtTagCompound.getTagList("Saves", 10);
		saves = new CrafterRecipe();
		for (int i = 0; i < list.tagCount(); ++i){
			NBTTagCompound tagCompound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < craft.getSizeInventory()) {
				saves.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(tagCompound));
				saves.recipes[slot] = new Craft(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		//inv 1
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Inventory", tagList);
		//inv 2
		NBTTagList tagList2 = new NBTTagList();
		for (int currentIndex = 0; currentIndex < craft.getSizeInventory(); ++currentIndex) {
			if (craft.getStackInSlot(currentIndex) != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				craft.getStackInSlot(currentIndex).writeToNBT(tagCompound);
				tagList2.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Craft", tagList2);
		
		//saves
		NBTTagList list = new NBTTagList();
		for (int currentIndex = 0; currentIndex < saves.getSizeInventory(); ++currentIndex) {
			if (saves.getStackInSlot(currentIndex) != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				saves.getStackInSlot(currentIndex).writeToNBT(tagCompound);
				saves.recipes[currentIndex].writeToNBT(tagCompound);
				list.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Saves", list);
	}


	//Synchronization

	public void sendGUINetworkData(CrafterContainer crafterContainer,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(crafterContainer, -1, 0);
		for(int g=0; g < 9; g++){
			iCrafting.sendProgressBarUpdate(crafterContainer, g, (found[g])? 1 : 0);
		}
	}

	public void getGUINetworkData(int i, int j) {
		if(i == -1){
			found = new boolean[9];
		}else found[i] = j==1;
	}

	@Override
	public String getInventoryName() {
		return "Crafter";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {		
	}

	@Override
	public void closeInventory() {		
	}

}

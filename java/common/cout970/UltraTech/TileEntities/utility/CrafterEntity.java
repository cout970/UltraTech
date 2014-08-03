package common.cout970.UltraTech.TileEntities.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codechicken.nei.ItemList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.containers.CrafterContainer;
import common.cout970.UltraTech.misc.Craft;
import common.cout970.UltraTech.misc.CrafterRecipe;
import common.cout970.UltraTech.misc.IRedstoneControl;
import common.cout970.UltraTech.misc.InventoryCrafter;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.TankConection;
import cpw.mods.fml.common.FMLCommonHandler;

public class CrafterEntity extends SyncTile implements IInventory,IRedstoneControl{

	public ItemStack[] inventory;
	public ItemStack result;
	public InventoryCrafter craft;
	public CrafterRecipe saves;
	public boolean[] found = new boolean[9];
	private boolean redstone;
	private EntityPlayer player;
	public boolean restrictMode = false;

	public CrafterEntity(){
		inventory = new ItemStack[9];
		craft = new InventoryCrafter(this, 3, 3);
		saves = new CrafterRecipe();
	}

	public void update(){
		if(worldObj.isRemote)return;
		result = CraftingManager.getInstance().findMatchingRecipe(craft, worldObj);
		canCraft();
		this.markDirty();
		sendNetworkUpdate();
	}

	public void canCraft(){
		
		found = new boolean[9];
		Map<Integer,Integer> already = new HashMap<Integer,Integer>();
		for(int c = 0; c < 9;c++){//serach in own inv for crafting things
			if(craft.getStackInSlot(c)== null){//not have to search because is empty
				found[c] = true;
			}else{
				for(int i = 0; i < this.getSizeInventory();i++){//searching in this inventory
					if(equal(this.getStackInSlot(i),craft.getStackInSlot(c))){//is item valid
						boolean cant;
						if(already.containsKey(i)){//has enought
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
			for(int c = 0; c < 9;c++){//check the crafting grid
				if(!found[c]){//if item is not found
					for(int i = 0; i < inv.getSizeInventory();i++){//searching on inventorys
						if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c))){//is item valid
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
				}
			}
		}
		if(!allFound() && hasFluidInCraft()){
			
			List<TankConection> tanks = FluidUtils.getTankConections(this);
			Map<Integer,Integer> liq = new HashMap<Integer,Integer>();
			
			for(int c = 0; c < 9;c++){
				if(FluidContainerRegistry.isContainer(craft.getStackInSlot(c))){
					FluidStack f = FluidContainerRegistry.getFluidForFilledItem(craft.getStackInSlot(c));
					if(f != null){
						for(TankConection tan : tanks){
							int toD = 1000;
							if(liq.containsKey(f.fluidID)){
								toD = liq.get(f.fluidID)+1000;
							}
							FluidStack drain = tan.tank.drain(tan.side, toD, false);
							if(drain != null && drain.fluidID == f.fluidID && drain.amount >= toD){
								found[c] = true;
								liq.put(f.fluidID,toD);
								break;
							}
						}
					}
				}
			}
		}
	}

	private boolean hasFluidInCraft() {
		for(int c = 0; c < 9;c++){
			if(FluidContainerRegistry.isContainer(craft.getStackInSlot(c))){
				return true;
			}
		}
		return false;
	}

	public boolean allFound() {
		for(int x=0;x<9;x++) if(!found[x])return false;
		if(result == null)return false;
		return true;
	}
	
	private boolean equal(ItemStack a, ItemStack b) {
		if(a == null && b == null)return true;
		if(a != null && b == null)return false;
		if(a == null && b != null)return false;
		if(OreDictionary.itemMatches(a, b, true))return true;
		if(b.getItemDamage() == OreDictionary.WILDCARD_VALUE)
			if(OreDictionary.itemMatches(a, b, false))return true;
		
		//restrictive mode
		if(!restrictMode){
			if(OreDictionary.getOreIDs(a).length != 0 && OreDictionary.getOreIDs(b).length != 0){
				for(int i : OreDictionary.getOreIDs(a)){
					for(int j : OreDictionary.getOreIDs(b))if(i == j)return true;
				}
			}
		}
		return false;
	}
	
//	public boolean remplace(int slot, ItemStack a){
//		IRecipe recipe = null;
//		for (int j = 0; j < CraftingManager.getInstance().getRecipeList().size(); ++j){
//			IRecipe irecipe = (IRecipe)CraftingManager.getInstance().getRecipeList().get(j);
//			if (irecipe.matches(craft, worldObj)){
//				recipe = irecipe;
//			}
//		}
//		if(recipe == null)return false;
//		ItemStack result = recipe.getCraftingResult(craft);
//		if(result == null)return false;
//		//fake craft grid
//		InventoryCrafter c = new InventoryCrafter(this, 3, 3);
//		for(int r = 0;r<9;r++){
//			c.setInventorySlotContents(r, craft.getStackInSlot(r));
//		}
//		c.setInventorySlotContents(slot, a);
//		//remplace
//		
//		if(recipe.matches(c, worldObj))return true;
//		return false;
//	}

	public void craft() {
		if(allFound() && addItemStack(result)){
			if(player != null)FMLCommonHandler.instance().firePlayerCraftingEvent(player, getStackInSlot(-1), craft);
			for(int c = 0; c < 9;c++){
				if(craft.getStackInSlot(c)!= null){
					boolean used = false;
					for(int i = 0; i < this.getSizeInventory();i++){
						if(equal(this.getStackInSlot(i),craft.getStackInSlot(c))){
							useItemToCraft(this, i);
							used = true;
							break;
						}
					}
					if(used)continue;
					List<TileEntity> t = UT_Utils.getTiles(worldObj, xCoord, yCoord, zCoord);
					List<IInventory> in = new ArrayList<IInventory>();
					for(TileEntity tile : t)if(tile instanceof IInventory)in.add((IInventory) tile);
					for(IInventory inv : in){
						for(int i = 0; i < inv.getSizeInventory();i++){
							if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c))){
								useItemToCraft(inv, i);
								used = true;
								break;
							}
						}
						if(used)break;
					}

					if(!used && hasFluidInCraft()){
						if(FluidContainerRegistry.isContainer(craft.getStackInSlot(c))){

							List<TankConection> tanks = FluidUtils.getTankConections(this);

							FluidStack f = FluidContainerRegistry.getFluidForFilledItem(craft.getStackInSlot(c));
							if(f != null){
								for(TankConection tan : tanks){
									FluidStack drain = tan.tank.drain(tan.side, 1000, false);
									if(drain != null && drain.fluidID == f.fluidID && drain.amount >= 1000){
										tan.tank.drain(tan.side, 1000, true);
										break;
									}
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
				
				if(itemContainer != null && itemContainer.isItemStackDamageable() && itemContainer.getItemDamage() > itemContainer.getMaxDamage()){
					 if(player != null)MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, itemContainer));
					return;
				}
				addItemStack(itemContainer);
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
		player = entityplayer;
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
		restrictMode = nbtTagCompound.getBoolean("mode");
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
		NBTTagList main = new NBTTagList();
		for (int ind = 0; ind < saves.getSizeInventory(); ++ind) {
			if (saves.getStackInSlot(ind) != null) {//save not empty
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte("Slot", (byte) ind);
				saves.getStackInSlot(ind).writeToNBT(nbt);
				saves.recipes[ind].writeToNBT(nbt);
				main.appendTag(nbt);
			}
		}
		nbtTagCompound.setTag("Saves", main);
		nbtTagCompound.setBoolean("mode", restrictMode);
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

	@Override
	public void onNeigChange(boolean power) {
		if(redstone && !power)redstone = false;
		if(!redstone && power){
			redstone = true;
			if(!worldObj.isRemote){
				canCraft();
				craft();
			}
		}
	}

	@Override
	public boolean getSignal() {
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

}

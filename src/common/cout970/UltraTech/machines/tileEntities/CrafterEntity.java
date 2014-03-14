package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.cout970.UltraTech.machines.containers.CrafterContainer;
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

public class CrafterEntity extends TileEntity implements IInventory{

	public ItemStack[] inventory;
	public InventoryCrafter craft;
	public ItemStack[] result;
	public Map<Integer,Boolean> found = new HashMap<Integer,Boolean>();


	public CrafterEntity(){
		inventory = new ItemStack[9];
		craft = new InventoryCrafter(this, 3, 3);
		result = new ItemStack[6];
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}
	
	 public void onInventoryChanged(){
		 super.onInventoryChanged();
		 result[0] = CraftingManager.getInstance().findMatchingRecipe(craft, worldObj);
		 if(!worldObj.isRemote)hasResources();
	 }
	 
	 public void hasResources(){
		 found = new HashMap<Integer,Boolean>();
		 Map<Integer,Integer> already = new HashMap<Integer,Integer>();
		 for(int c = 0; c < 9;c++){
			 if(craft.getStackInSlot(c)== null){
				 found.put(c, false);
			 }else{
				 for(int i = 0; i < this.getSizeInventory();i++){
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
							 found.put(c, false);
							 break;
						 }
					 }
				 }
				 if(!found.containsKey(c)){
					 List<IInventory> in = getInventorys();
					 int aux2 = 9;

					 for(IInventory inv : in){
						 for(int i = 0; i < inv.getSizeInventory();i++){

							 if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c),c)){
								 boolean cant = true;
								 if(already.containsKey(i+aux2)){
									 cant = false;
									 int aux = already.get(i+aux2);
									 if(aux < inv.getStackInSlot(i).stackSize){
										 already.remove(i+aux2);
										 already.put(i, aux+1);
										 cant = true;
									 }
								 }else{
									 already.put(i+aux2, 1);
									 cant = true;
								 }
								 
								 if(cant){
									 found.put(c, false);
									 break;
								 }
							 }
						 }
						 aux2 += inv.getSizeInventory();
						 
						 if(found.containsKey(c))break;
					 }
				 }

				 if(!found.containsKey(c)){
					 found.put(c, true);
				 }
			 }
		 }
	 }


	private List<IInventory> getInventorys() {
		TileEntity[] tile = new TileEntity[4];
		tile[0] = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		tile[1] = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		tile[2] = worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);
		tile[3] = worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		List<IInventory> g = new ArrayList<IInventory>();
		for(TileEntity t : tile){
			if(t instanceof IInventory){
				g.add((IInventory) t);
			}
		}
		return g;
	}

	private boolean equal(ItemStack a, ItemStack b,int slot) {
		if(a == null && b == null)return true;
		if(a != null && b == null)return false;
		if(a == null && b != null)return false;
		boolean meta = false;
		if(a.itemID == b.itemID && (a.getItemDamage() == b.getItemDamage() || meta))return true;
		if(OreDictionary.itemMatches(a, b, true))return true;
		InventoryCrafter c = new InventoryCrafter(this, 3, 3);
		for(int r = 0;r<9;r++){
			c.setInventorySlotContents(r, craft.getStackInSlot(r));
		}
		c.setInventorySlotContents(slot, a);
		ItemStack g = CraftingManager.getInstance().findMatchingRecipe(c, worldObj);
		ItemStack h = CraftingManager.getInstance().findMatchingRecipe(craft, worldObj);
		if(g == null)return false;
		if(h == null)return false;
		if(g.itemID == h.itemID && (g.getItemDamage() == h.getItemDamage() || meta))return true;
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if(i < 0){
			return result[-i-1];
		}
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {

		if(slot < 0){
			return result[-slot-1];
		}
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
		if(slot < 0){
			return null;
		}
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
		if(slot < 0 ){
			result[-slot-1] = itemStack;
			return;
		}
		inventory[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Crafter";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
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
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i < 0 ? false : true;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
		NBTTagList tagList = nbtTagCompound.getTagList("Inventory");
		inventory = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < inventory.length) {
				inventory[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
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
	}

	public boolean allFound(int i) {
		for(int x=0;x<9;x++){
			if(found.containsKey(x)){
				if(found.get(x))return false;
			}
		}
		return true;
	}

	public void craft() {
		if(allFound(-1)){
			for(int c = 0; c < 9;c++){
				if(craft.getStackInSlot(c)!= null){
					for(int i = 0; i < this.getSizeInventory();i++){
						if(equal(this.getStackInSlot(i),craft.getStackInSlot(c),c)){
							useItemToCraft(getStackInSlot(i), i);
							break;
						}
					}
					List<IInventory> in = getInventorys();

					for(IInventory inv : in){
						for(int i = 0; i < inv.getSizeInventory();i++){

							if(equal(inv.getStackInSlot(i),craft.getStackInSlot(c),c)){
								useItemToCraft(inv.getStackInSlot(i), i);
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void useItemToCraft(ItemStack item, int slot){
		this.decrStackSize(slot, 1);
		if(item != null){
			if (item.getItem().hasContainerItem())
            {
                ItemStack itemstack2 = item.getItem().getContainerItemStack(item);

                if (itemstack2.isItemStackDamageable() && itemstack2.getItemDamage() > itemstack2.getMaxDamage())
                {
                    itemstack2 = null;
                }

                if (itemstack2 != null && (!item.getItem().doesContainerItemLeaveCraftingGrid(item) || !addItemStack(itemstack2)))
                {
                    if (getStackInSlot(slot) == null)
                    {
                        setInventorySlotContents(slot, itemstack2);
                    }
                    else
                    {
                    	addItemStack(itemstack2);//in case that cant put in inventory
                    }
                }
            }
		}
	}
	public boolean addItemStack(ItemStack i){
		for(int s = 0;s < this.getSizeInventory();s++){	
			if (this.inventory[s] == null)
			{
				this.inventory[s] = i.copy();
				return true;
			}
			else if (this.inventory[s].isItemEqual(i))
			{
				if(inventory[s].stackSize + i.stackSize <= getInventoryStackLimit()){
				inventory[s].stackSize += i.stackSize;
				return true;
				}
			}
		}
		return false;
	}

	public void sendGUINetworkData(CrafterContainer crafterContainer,
			ICrafting iCrafting) {
		for(int g=0; g < 9; g++){
			if(found.containsKey(g))iCrafting.sendProgressBarUpdate(crafterContainer, g, (found.get(g))? 1 : 0);
		}
	}

	public void getGUINetworkData(int i, int j) {
		if(found.containsKey(i)){
			found.remove(i);
			found.put(i, (j==1));
		}else{
			found.put(i, (j==1));
		}
	}

}

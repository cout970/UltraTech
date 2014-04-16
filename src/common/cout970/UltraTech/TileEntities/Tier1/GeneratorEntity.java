package common.cout970.UltraTech.TileEntities.Tier1;


import common.cout970.UltraTech.energy.api.Machine;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

public class GeneratorEntity extends Machine implements IInventory{

	private ItemStack[] inventory;
	public float Progres = 0;
	public boolean on;
	public int maxProgres;
	public float heat = 25;
	public float maxHeat = 1200;

	public GeneratorEntity(){
		super();
		inventory = new ItemStack[1];
		this.tipe = MachineTipe.Output;
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!worldObj.isRemote){
			boolean change = false;
			if(Progres > 0){
				if(!on){
					change = true;
				}
				if(Progres - (int)(heat*20/maxHeat) < 0){
					addEnergy(Progres);
					Progres = 0;
				}else{
				Progres-= (int)(heat*20/maxHeat);
				this.addEnergy((int)(heat*20/maxHeat));
				}
				if(heat < maxHeat)heat+=1.3-heat/maxHeat;
			}else{
				if(heat > 25)heat-=0.1+heat/maxHeat;
			}
			
			if(Progres <= 0){
				if(inventory[0] != null){
					int fuel = TileEntityFurnace.getItemBurnTime(inventory[0]);
					if(fuel > 0 && (getEnergy()+fuel <= maxEnergy() || (fuel > maxEnergy()&& getEnergy() < maxEnergy()))){
						Progres = fuel;
						maxProgres = fuel;
						if(inventory[0] != null){
							inventory[0].stackSize--;
							if(inventory[0].stackSize <= 0){
								inventory[0] = inventory[0].getItem().getContainerItemStack(inventory[0]);
							}
						}
						PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
					}else{
						if(on){
							change = true;
						}
					}
				}else{
					if(on){
						change = true;
					}
				}
			}
			
			if(change){
				on = Progres > 0;
				PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
			}
		}
	}


    //Inventory
    
    @Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
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
		inventory[slot] = itemStack;

		if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
			itemStack.stackSize = this.getInventoryStackLimit();
		}

		this.onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "Generator";
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
		return true;
	}
	
	//Synchronization
	
	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, (int)Progres);
		c.sendProgressBarUpdate(cont, 3, maxProgres);
		c.sendProgressBarUpdate(cont, 4, on ? 1:0);
		c.sendProgressBarUpdate(cont, 5, (int) heat);
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)Progres = value;
		if(id == 3)maxProgres = value;
		if(id == 4){
			if(value == 0)on = false;
			else on = true; 
		}
		if(id == 5)heat = value;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		if(worldObj != null)worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
		super.readFromNBT(nbt);
		Progres = nbt.getFloat("progres");
		on = nbt.getBoolean("on");
		heat = nbt.getInteger("Heat");
		NBTTagList tagList = nbt.getTagList("Inventory");
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
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		nbt.setFloat("progres", Progres);
		nbt.setInteger("Heat", (int) heat);
		nbt.setBoolean("on", on);
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < inventory.length; ++currentIndex) {
			if (inventory[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte) currentIndex);
				inventory[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}

		nbt.setTag("Inventory", tagList);
	}
	
	
}

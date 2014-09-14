package common.cout970.UltraTech.TileEntities.electric;


import ultratech.api.recipes.Assembly_Recipes;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.MachineWithInventory;

public class TileEntityMolecularAssembly extends MachineWithInventory implements IInventory{

	public int progres = 0;
	public boolean hasrecipe= false;
	private int speed = 10;
	private boolean hasEnergy;
	
	public TileEntityMolecularAssembly(){
		super(11,"Molecular Assembly",MachineData.MAssembly);
	}

	public void updateEntity(){
		if(worldObj.isRemote)return;
		boolean flag = false;

		if(!hasEnergy){
			hasEnergy = getCharge() >= MachineData.MAssembly.use;
		}

		if(progres > 0){
			removeCharge(MachineData.MAssembly.use*speed/1000);
		}
		if (hasEnergy && hasrecipe && (getStackInSlot(9) == null || OreDictionary.itemMatches(getStackInSlot(9), getStackInSlot(10), true))){
			this.progres += speed;
			if (this.progres >= 1000)
			{
				this.progres = 0;
				craft();
				flag = true;
				hasEnergy = false;
			}
		}else{
			this.progres = 0;
		}
		if (flag){
			this.sendNetworkUpdate();		
		}
		if(!hasrecipe){
			if(Assembly_Recipes.matches(this)){
				ItemStack a = Assembly_Recipes.getCraftingResult(this);
				setInventorySlotContents(10, a);
				hasrecipe = true;
			}else if(this.getStackInSlot(10) != null){
				setInventorySlotContents(10, null);
			}
		}
	}


	private void craft() {
		if(Assembly_Recipes.matches(this)){
			ItemStack itemstack = Assembly_Recipes.getCraftingResult(this);

			if (this.inventory[9] == null)
			{
				this.inventory[9] = itemstack.copy();
			}
			else if (this.inventory[9].isItemEqual(itemstack))
			{
				inventory[9].stackSize += itemstack.stackSize;
			}
			hasrecipe = false;
			for(int x=0;x<9;x++){
				if(this.inventory[x] != null){
					--this.inventory[x].stackSize;
					if (this.inventory[x].stackSize <= 0)
					{
						this.inventory[x] = null;
					}
				}
			}
		}
	}


	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 10 || i == 11 ? false : true;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {

		super.readFromNBT(nbtTagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
	}

	//Synchronization
	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
		super.sendGUINetworkData(container, iCrafting);
		iCrafting.sendProgressBarUpdate(container, 2, progres);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)progres = value;
	}

}

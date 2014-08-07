package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.Random;

import ultratech.api.recipes.CVD_Recipe;
import ultratech.api.recipes.Cutter_Recipe;
import ultratech.api.recipes.Laminator_Recipe;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.LogHelper;

public class CutterT1_Entity extends ConfigurableMachineWithInventory implements ISidedInventory{

	public float Progres = 0;
	public int maxProgres = 80;
	public boolean hasEnergy;

	public CutterT1_Entity() {
		super(3, "Cutter", MachineData.Cutter);
	}

	@Override
	public void updateEntity(){

		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = MachineData.Cutter.use/maxProgres;
			else extract = MachineData.Cutter.use;
			removeCharge(extract);
		}
		if(!hasEnergy && shouldWork()){
			hasEnergy = this.getCharge() >= MachineData.Cutter.use;
		}
		if(hasEnergy && Cutter_Recipe.canCraft(this)){
			Progres++;
			if(Progres >= maxProgres){
				Progres = 0;
				craft();
				changes = true;
				hasEnergy = false;
			}
		}else{
			Progres = 0;
		}
		if(changes){
			this.markDirty();
		}
	}


	protected void craft() {
		if(Cutter_Recipe.canCraft(this)){
			Cutter_Recipe recipe = Cutter_Recipe.getCraftingResult(this);
			ItemStack itemstack = recipe.getResult(0);
			ItemStack extra = recipe.getResult(1);
			
			--this.inventory[0].stackSize;
			if (this.inventory[0].stackSize <= 0){
				this.inventory[0] = null;
			}
			
			if (this.inventory[1] == null){
				this.inventory[1] = itemstack.copy();
			}else if (this.inventory[1].isItemEqual(itemstack)){
				inventory[1].stackSize += itemstack.stackSize;
			}
			
			Random r = new Random();
			if(extra != null){
				int intents = ((int)(recipe.prob/100))+1;
				for(int x = 0; x < intents;x++){
					int p = r.nextInt(100);
					if(p <= recipe.prob){
						
						if (this.inventory[2] == null){
							this.inventory[2] = extra.copy();
						}else if (this.inventory[2].isItemEqual(extra)){
							inventory[2].stackSize += extra.stackSize;
						}
					}
				}
			}
		}
	}

	//Synchronization
	
	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, (int)Progres);
		c.sendProgressBarUpdate(cont, 3, maxProgres);
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)Progres = value;
		if(id == 3)maxProgres = value;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		Progres = nbt.getFloat("progres");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("progres", Progres);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return new int[]{0,1};
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return var1 == 0;
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return var1 == 1;
	}

}

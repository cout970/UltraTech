package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.misc.ISpeeded;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.MachineWithInventory;

public class ChemicalVaporDesintegrationT1_Entity extends ConfigurableMachine implements ISidedInventory{

	public float Progres = 0;
	public int maxProgres = 80;
	public boolean hasEnergy;

	public ChemicalVaporDesintegrationT1_Entity(){
		super(3,"CVD",CostData.CVD);
	}

	@Override
	public void updateEntity(){
		if(worldObj.isRemote)return;
		boolean changes = false;
		if(Progres > 0){
			double extract;
			if(maxProgres > 0)extract = CostData.CVD.use/maxProgres;
			else extract = CostData.CVD.use;
			removeEnergy(extract);
		}
		if(!hasEnergy && shouldWork()){
			hasEnergy = this.getEnergy() >= CostData.CVD.use;
		}
		if(hasEnergy && CVD_Recipe.INSTANCE.matches(this)){
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
		if(CVD_Recipe.INSTANCE.matches(this)){
			ItemStack itemstack = CVD_Recipe.INSTANCE.getCraftingResult(this);

			if (this.inventory[2] == null)
			{
				this.inventory[2] = itemstack.copy();
			}
			else if (this.inventory[2].isItemEqual(itemstack))
			{
				inventory[2].stackSize += itemstack.stackSize;
			}
			--this.inventory[0].stackSize;
			--this.inventory[1].stackSize;
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
			if (this.inventory[1].stackSize <= 0)
			{
				this.inventory[1] = null;
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
		if(worldObj != null)worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
		return new int[]{0,1,2};
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		return var1 == 0 || var1 ==1;
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		return var1 == 2;
	}
}

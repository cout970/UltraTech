package common.cout970.UltraTech.TileEntities.electric.tiers;


import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

public class CoalGeneratorEntityT1_Entity extends ConfigurableMachine implements IUpdatedEntity{

	public float Progres = 0;
	public int maxProgres;
	public float heat = 25;
	public float maxHeat = 400;

	public CoalGeneratorEntityT1_Entity(){
		super(1,"Generator",CostData.Generator);
	}
	
	public double production(){
		double r = heat*10/1000;
		double h = (int) (heat*10/1000);
		if(r-h >= 0.5)h += 0.5d;
		return h;
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		
		if(Progres > 0){
			double extract = production();
			if(Progres - extract < 0){
				addEnergy(EnergyCosts.toEnergy(Progres));
				Progres = 0;
			}else{
				Progres -= extract*2;
				addEnergy(extract);
			}
			if(heat < maxHeat)heat+=1.2-heat/maxHeat;
		}else{
			if(heat > 25)heat-=0.1+heat/maxHeat;
		}
		if(Progres <= 0){
			if(inventory[0] != null && shouldWork()){
				int fuel = TileEntityFurnace.getItemBurnTime(inventory[0]);
				if(fuel > 0 && ((int)(getEnergy()+EnergyCosts.toEnergy(fuel)) <= maxEnergy() || (EnergyCosts.toEnergy(fuel) > maxEnergy()&& getEnergy() < maxEnergy()))){
						Progres = fuel;
						maxProgres = fuel;
						if(inventory[0] != null){
							inventory[0].stackSize--;
							if(inventory[0].stackSize <= 0){
								inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
							}
						}
						markDirty();
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
		c.sendProgressBarUpdate(cont, 4, (int) heat);
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)Progres = value;
		if(id == 3)maxProgres = value;
		if(id == 4)heat = value;
	}
	
	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		Progres = nbt.getFloat("progres");
		heat = nbt.getInteger("Heat");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("progres", Progres);
		nbt.setInteger("Heat", (int) heat);
	}

	@Override
	public void onNeigUpdate() {}
}

package common.cout970.UltraTech.TileEntities.electric.tiers;


import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.power.PowerExchange;

public class TileEntityCoalGeneratorEntityT1 extends ConfigurableMachineWithInventory implements IUpdatedEntity{

	public float Progres = 0;
	public int maxProgres;
	public float heat = 25;
	public float maxHeat = 400;
	public boolean updated;//metadata true == working

	public TileEntityCoalGeneratorEntityT1(){
		super(1,"Generator",MachineData.Generator);
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
			if(!updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
				updated = true;
			}
			double extract = production();
			if(Progres - extract*2 < 0){
				addCharge(PowerExchange.FTtoQP(Progres));
				Progres = 0;
			}else{
				Progres -= extract*2;
				addCharge(extract);
			}
			if(heat < maxHeat)heat+=1.2-heat/maxHeat;
		}else{
			if(heat > 25)heat-=0.1+heat/maxHeat;
		}
		if(Progres <= 0){
			if(inventory[0] != null && shouldWork()){
				int fuel = TileEntityFurnace.getItemBurnTime(inventory[0]);
				if(fuel > 0 && ((int)(getCharge()+PowerExchange.FTtoQP(fuel)) <= getCapacity() || (PowerExchange.FTtoQP(fuel) > getCapacity()&& getCharge() < getCapacity()))){
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
			if(Progres <= 0 && updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
				updated = false;
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

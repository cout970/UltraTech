package common.cout970.UltraTech.TileEntities.electric;


import api.cout970.UltraTech.Wpower.Machine;
import api.cout970.UltraTech.Wpower.StorageInterface;
import api.cout970.UltraTech.Wpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.misc.MachineWithInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

public class GeneratorEntity extends MachineWithInventory{

	public float Progres = 0;
	public boolean on;
	public int maxProgres;
	public float heat = 25;
	public float maxHeat = 600;

	public GeneratorEntity(){
		super(1,"Generator",CostData.Generator);
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
				if(Progres - (int)(heat*10/maxHeat) < 0){
					addEnergy(Progres*EnergyCosts.k);
					Progres = 0;
				}else{
					Progres-= (int)(heat*10/maxHeat);
					this.addEnergy(((int)(heat*10/maxHeat))*EnergyCosts.k);
				}
				if(heat < maxHeat)heat+=1.2-heat/maxHeat;
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
								inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
							}
						}
						Net_Utils.sendUpdate(this);
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
				Net_Utils.sendUpdate(this);
			}
			
		}
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
		if(worldObj != null)worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		super.readFromNBT(nbt);
		Progres = nbt.getFloat("progres");
		on = nbt.getBoolean("on");
		heat = nbt.getInteger("Heat");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setFloat("progres", Progres);
		nbt.setInteger("Heat", (int) heat);
		nbt.setBoolean("on", on);
	}
	
	
}

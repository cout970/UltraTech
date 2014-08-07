package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.blocks.models.Boiler;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.HeaterInteraction;
import common.cout970.UltraTech.misc.HeaterInteraction.Interaction;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.power.PowerExchange;

public class Heater_Entity extends ConfigurableMachineWithInventory{

	public int Progres;
	public int maxProgres;
	public float Heat = 25;
	private float maxHeat = 1000;
	private boolean change = true;
	public List<HeaterInteraction> inter;

	public Heater_Entity() {
		super(1, "Heater", MachineData.Heater);
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(Progres > 0){
			if(Heat <= maxHeat){
				Heat += PowerExchange.FTtoHeat(10);
				Progres-=10;
				if(Progres <= 0)change = true;
			}
		}
		if(Progres <= 0){
			if(getStackInSlot(0) != null && shouldWork()){
				int fuel = TileEntityFurnace.getItemBurnTime(inventory[0]);
				if(fuel > 0){
					Progres = fuel;
					maxProgres = fuel;
					if(inventory[0] != null){
						inventory[0].stackSize--;
						if(inventory[0].stackSize <= 0){
							inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
						}
					}
					change = true;
					markDirty();
				}
			}else if(getCharge() >= MachineData.Heater.use){
				Progres = 200;
				maxProgres = 200;
				removeCharge(MachineData.Heater.use);
				change = true;
			}
			if(Progres <= 0 && Heat > 25){
				Heat -= Heat/1000;
			}
		}
		if(change){
			change = false;
			if(Progres > 0){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
			}else{
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
			}	
		}
		if(inter == null)onNeigUpdate();
		if(Heat > 25){
			for(HeaterInteraction h:inter){
				Heat -= h.apply(Heat);
			}
		}
	}

	public void onNeigUpdate(){
		super.onNeigUpdate();
		List<HeaterInteraction> h = new ArrayList<HeaterInteraction>();
		if(inter == null)inter = new ArrayList<HeaterInteraction>();
		else{
			h = inter;
			inter = new ArrayList<HeaterInteraction>();
		}
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			Interaction in = HeaterInteraction.isInteresting(this,d);
			if(in != Interaction.Nothing){
				HeaterInteraction hi = new HeaterInteraction(this, d, in);
				for(HeaterInteraction i : h){
					if(i.type == Interaction.Block && i.side == d){
						hi.own = i.own;
					}
				}
				inter.add(hi);
			}
		}
		
	}

	//Synchronization

	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, (int)Progres);
		c.sendProgressBarUpdate(cont, 3, maxProgres);
		c.sendProgressBarUpdate(cont, 4, (int) Heat);
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)Progres = value;
		if(id == 3)maxProgres = value;
		if(id == 4)Heat = value;
	}

	//Save & Load

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		Heat = nbt.getFloat("Heat");
		Progres = nbt.getInteger("Progres");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setFloat("Heat", Heat);
		nbt.setInteger("Progres", Progres);
	}
}

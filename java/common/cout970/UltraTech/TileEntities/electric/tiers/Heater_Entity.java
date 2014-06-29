package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.blocks.models.Boiler;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.misc.HeaterInteraction;
import common.cout970.UltraTech.misc.HeaterInteraction.Interaction;

public class Heater_Entity extends ConfigurableMachine{

	public int Progres;
	public int maxProgres;
	public float Heat = 25;
	private float maxHeat = 1000;
	private boolean change = true;
	public List<HeaterInteraction> inter;

	public Heater_Entity() {
		super(1, "Heater", CostData.Heater);
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(Progres > 0){
			if(Heat < maxHeat){
				Heat += 0.5;
				Progres-=5;
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
			}else if(getEnergy() >= CostData.Heater.use){
				Progres = 100;
				removeEnergy(CostData.Heater.use);
				change = true;
			}else{
				if(Heat >= 25)Heat -= Heat*2/maxHeat;
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
		inter = new ArrayList<HeaterInteraction>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			Interaction in = HeaterInteraction.isInteresting(this,d);
			if(in != Interaction.Nothing){
				inter.add(new HeaterInteraction(this, d, in));
			}
		}
	}

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

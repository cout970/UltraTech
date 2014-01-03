package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.misc.Energy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class Machine extends TileEntity implements Energy{

	
	public int Energy;
	public int EnergyMax = 4000;
	public boolean update = false;

	
	/**
	 * return the exces of energy
	 */

	@Override
	public int gainEnergy(int energy2) {
		if(Energy+energy2 <= EnergyMax){
			Energy += energy2;
			return 0;
		}else{
			int l =EnergyMax - Energy;
			Energy = EnergyMax;
			return l;
		}

	}


	@Override
	public void loseEnergy(int amount) {
		if(Energy-amount >= 0){
		Energy -= amount;	
		}
	}


	@Override
	public int getEnergy() {
		return Energy;
	}
	

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		
		NBTTagList tagList = nbtTagCompound.getTagList("Energy_UT");
		NBTTagCompound tagCompound = (NBTTagCompound) tagList.tagAt(0);
		Energy = tagCompound.getInteger("Energy");
		NBTTagCompound tagCompound2 = (NBTTagCompound) tagList.tagAt(1);
		EnergyMax = tagCompound2.getInteger("EnergyMax");

	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		
		NBTTagList tagList = new NBTTagList();
		NBTTagCompound tagCompound = new NBTTagCompound();
		tagCompound.setInteger("Energy", this.Energy);
		tagList.appendTag(tagCompound);
		NBTTagCompound tagCompound2 = new NBTTagCompound();
		tagCompound2.setInteger("EnergyMax", this.EnergyMax);
		tagList.appendTag(tagCompound2);
		nbtTagCompound.setTag("Energy_UT", tagList);
		
	}


}

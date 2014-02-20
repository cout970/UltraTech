package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.misc.Energy;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Machine extends TileEntity implements Energy{

	
	public int Energy;
	public int EnergyMax = 20000;
	public boolean update = false;
	public MachineTipe tipe = MachineTipe.Nothing;
	public List<Machine> Machines;

	@Override
	public void updateEntity(){
		if(Machines == null)updateMachine(worldObj, xCoord, yCoord, zCoord);
		if(tipe == MachineTipe.Output){
			this.emptyMachine(worldObj, xCoord, yCoord, zCoord);
		}else if(tipe == MachineTipe.Input){
			this.fillMachine(worldObj, xCoord, yCoord, zCoord);
		}
	}
	
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

	/**
	 * this method pass the energy from machine a to machine b
	 * @param a
	 * @param b
	 */
	public static void passEnergy(Machine a, Machine b){
		if(a == null || b == null)return;
		if(a.getEnergy() > 0){
			int space = b.EnergyMax-b.getEnergy();
			if(space > 0){
				if(a.getEnergy() > 500 && space > 500){
					a.loseEnergy(500);
					b.gainEnergy(500);
				}else if(a.getEnergy() >= space){
					a.loseEnergy(space);
					b.gainEnergy(space);
				}else{
					b.gainEnergy(a.getEnergy());
					a.loseEnergy(a.getEnergy());
				}
			}
		}
	}
	/**
	 * this method empty a machine passing energy to all next machines 
	 * @param a
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 */
	public void emptyMachine(World w,int x,int y,int z){

		if(Machines == null)this.updateMachine(w, x, y, z);
		boolean flag;
		for(Machine b:Machines){
			flag = b instanceof IDSentity;
			if(b.tipe != MachineTipe.Output || flag)
			if(this instanceof IDSentity && flag){
				if(this.EnergyMax-this.getEnergy()< 1000){
					passEnergy(this, b);
				}
			}else passEnergy(this, b);
		}
	}
	
	/**
	 * this update machines list
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 */
	public void updateMachine(World w,int x,int y,int z){
		Machines = new ArrayList<Machine>();
		TileEntity[] t = new TileEntity[6];
		t[0] = w.getBlockTileEntity(x, y-1, z);
		t[1] = w.getBlockTileEntity(x, y+1, z);
		t[2] = w.getBlockTileEntity(x, y, z+1);
		t[3] = w.getBlockTileEntity(x+1, y, z);
		t[4] = w.getBlockTileEntity(x, y, z-1);
		t[5] = w.getBlockTileEntity(x-1, y, z);
		for(TileEntity h : t){
			if(h != null && h instanceof Machine){
				Machines.add((Machine) h);
			}
		}
	}
	
	/**
	 * this method fill a machine using energy from all next machines 
	 * @param a
	 * @param w
	 * @param x
	 * @param y
	 * @param z
	 */
	public void fillMachine(World w,int x,int y,int z){
		if(Machines == null)this.updateMachine(w, x, y, z);
		for(Machine b:Machines){
			if(b.tipe != MachineTipe.Input)
			passEnergy(b, this);
		}
	}
	
	public static enum MachineTipe{
		Output,Input,Nothing
	}
}

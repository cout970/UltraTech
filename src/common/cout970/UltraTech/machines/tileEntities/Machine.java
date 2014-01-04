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
	public int EnergyMax = 4000;
	public boolean update = false;
	public MachineTipe tipe = MachineTipe.Nothing;

	@Override
	public void updateEntity(){
		if(tipe == MachineTipe.Output){
			Machine.emptyMachine(this, worldObj, xCoord, yCoord, zCoord);
		}else if(tipe == MachineTipe.Input){
			Machine.fillMachine(this, worldObj, xCoord, yCoord, zCoord);
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
				if(a.getEnergy() > 64 && space > 64){
					a.loseEnergy(64);
					b.gainEnergy(64);
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
	public static void emptyMachine(Machine a, World w,int x,int y,int z){

		if(a == null)return;
		TileEntity[] t = new TileEntity[6];
		t[0] = w.getBlockTileEntity(x, y-1, z);
		t[1] = w.getBlockTileEntity(x, y+1, z);
		t[2] = w.getBlockTileEntity(x, y, z+1);
		t[3] = w.getBlockTileEntity(x+1, y, z);
		t[4] = w.getBlockTileEntity(x, y, z-1);
		t[5] = w.getBlockTileEntity(x-1, y, z);
		List<Machine> l = new ArrayList<Machine>();
		for(TileEntity h : t){
			if(h != null && h instanceof Machine){
				l.add((Machine) h);
			}
		}
		for(Machine b:l){
			if(b.tipe != MachineTipe.Output || b instanceof IDSentity)
			passEnergy(a, b);
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
	public static void fillMachine(Machine a, World w,int x,int y,int z){
		if(a == null)return;
		TileEntity[] t = new TileEntity[6];
		t[0] = w.getBlockTileEntity(x, y-1, z);
		t[1] = w.getBlockTileEntity(x, y+1, z);
		t[2] = w.getBlockTileEntity(x, y, z+1);
		t[3] = w.getBlockTileEntity(x+1, y, z);
		t[4] = w.getBlockTileEntity(x, y, z-1);
		t[5] = w.getBlockTileEntity(x-1, y, z);
		List<Machine> l = new ArrayList<Machine>();
		for(TileEntity h : t){
			if(h != null && h instanceof Machine){
				l.add((Machine) h);
			}
		}
		for(Machine b:l){
			if(b.tipe != MachineTipe.Input)
			passEnergy(b, a);
		}
	}
	
	public static enum MachineTipe{
		Output,Input,Nothing
	}
}

package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.containers.ControllerContainer;
import common.cout970.UltraTech.multiblocks.TileReactorPart;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;

public class ReactorControllerEntity extends TileReactorPart{

	public boolean update = false;
	public int meta;
	public boolean on;
	public boolean useHeat = false;
	public int maxheat = 1000;
	public int minheat = 900;
	
	public void updateEntity(){
		if(!Structure && meta == 1){
			meta = 0;
		}else if(Structure && meta == 0){
			meta = 1;
		}
		if(Reactor != null && !worldObj.isRemote && useHeat){
			if(Reactor.heat > maxheat){
				on = false;
				Reactor.control = null;
			}else if(Reactor.heat < minheat  && !on){
				on = true;
				Reactor.control = null;
			}
		}
	}
	
	@Override
	public void onNeighChange() {
		super.onNeighChange();
		if(!useHeat){
			on = !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
			if(Reactor != null)Reactor.control = null;
		}
	}
	
	//Save and Load

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		on = nbtTagCompound.getBoolean("on");
		useHeat = nbtTagCompound.getBoolean("heat");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("on", on);
		nbtTagCompound.setBoolean("heat", useHeat);
	}
	
	//Synchronization

	public void sendGUINetworkData(ControllerContainer controllerContainer,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(controllerContainer, 0, on ? 1:0);
		iCrafting.sendProgressBarUpdate(controllerContainer, 1, useHeat ? 1:0);
	}

	public void getGUINetworkData(int i, int j) {
		if(i == 0)on = j == 1;
		if(i == 1)useHeat = j == 1;
	}

}

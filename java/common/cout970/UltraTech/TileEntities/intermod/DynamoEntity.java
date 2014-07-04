package common.cout970.UltraTech.TileEntities.intermod;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.MeVpower.CableType;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.misc.IUpdatedEntity;

public class DynamoEntity extends SyncTile implements IPowerConductor, IEnergyHandler, IUpdatedEntity{

	public ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage storage;//RF
	protected StorageInterface inter;//Mev
	public static final int RF = 20;//RF => MeV
	public boolean on = false;
	public IEnergyHandler recep = null;

	public DynamoEntity(){
		super();
		storage = new EnergyStorage((int) (CostData.Dynamo.cap*RF));
		inter = new StorageInterface(this,CostData.Dynamo.cap,2);
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(inter.getCharge() >= 4 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF*4){
			inter.removeCharge(4d);
			storage.receiveEnergy(RF*4, false);
		}else if(inter.getCharge() >= 1 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= RF){
			inter.removeCharge(1d);
			storage.receiveEnergy(RF, false);
		}else if(inter.getCharge() >= 1d/RF && storage.getMaxEnergyStored()-storage.getEnergyStored() >= 1){
			inter.removeCharge(1d/RF);
			storage.receiveEnergy(1, false);
		}
		if(recep != null){
			int send = Math.min(400, storage.getEnergyStored());
			int b = recep.receiveEnergy(facing.getOpposite(), send, false);
			
			if(b > 0){
				int e = this.extractEnergy(facing, b, false);
			}
		}
	}

	public boolean isWorking() {
		return on;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return from == facing;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {

		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {

		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {

		return storage.getMaxEnergyStored();
	}
	
	//Save and Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		inter.readFromNBT(nbt);
		facing = ForgeDirection.getOrientation(nbt.getInteger("Direction"));
		on = nbt.getBoolean("On");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		inter.writeToNBT(nbt);
		nbt.setInteger("Direction", facing.ordinal());
		nbt.setBoolean("On", isWorking());
	}

	public void switchOrientation() {
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(UT_Utils.getRelative(this, d) instanceof IEnergyHandler){
				facing = d;
				return;
			}
		}	
	}

	@Override
	public PowerInterface getPower() {
		return inter;
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		super.sendGUINetworkData(cont, c);
		c.sendProgressBarUpdate(cont, 2, storage.getEnergyStored());
	}

	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
		if(id == 2)storage.setEnergyStored(value);
	}

	@Override
	public void onNeigUpdate() {
		TileEntity a = UT_Utils.getRelative(this, facing);
		if(a instanceof IEnergyHandler)recep = (IEnergyHandler) a;
		switchOrientation();
	}
}

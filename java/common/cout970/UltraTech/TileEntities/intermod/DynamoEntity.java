package common.cout970.UltraTech.TileEntities.intermod;

import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.StorageInterface;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.misc.PowerExchange;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.common.Mod.Instance;

public class DynamoEntity extends SyncTile implements IPowerConductor, IEnergyHandler, IUpdatedEntity{

	public ForgeDirection facing = ForgeDirection.UP;
	protected EnergyStorage storage;//RF
	protected StorageInterface inter;//Mev
	public static PowerExchange pe = new PowerExchange();
	public boolean on = false;
	public IEnergyHandler recep = null;
	public boolean redstone = false;

	public DynamoEntity(){
		super();
		storage = new EnergyStorage((int) (pe.MeVtoRF(MachineData.Dynamo.cap)));
		inter = new StorageInterface(this,MachineData.Dynamo.cap,2);
	}

	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		boolean work = false;
		if(shouldWork()){
			if(inter.getCharge() >= 4 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= pe.MeVtoRF(4)){
				inter.removeCharge(4d);
				storage.receiveEnergy(pe.MeVtoRF(4), false);
				work = true;
			}else if(inter.getCharge() >= 1 && storage.getMaxEnergyStored()-storage.getEnergyStored() >= pe.MeVtoRF(1)){
				inter.removeCharge(1d);
				storage.receiveEnergy(pe.MeVtoRF(1), false);
				work = true;
			}
		}
		if(worldObj.getTotalWorldTime()% 20 == 1){
			if(!on && work){
				on = true;
				sendNetworkUpdate();
			}
			if(on && !work){
				on = false;
				sendNetworkUpdate();
			}
		}
		if(recep != null){
			int send = Math.min(400, storage.getEnergyStored());
			int b = recep.receiveEnergy(facing.getOpposite(), send, false);
			if(b > 0){
				int e = this.extractEnergy(facing, b, false);
			}
		}
	}

	private boolean shouldWork() {
		return !redstone;
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
			TileEntity a = UT_Utils.getRelative(this, d);
			if(a instanceof IEnergyHandler && !(a instanceof IPowerConductor)){
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
		redstone = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		TileEntity a = UT_Utils.getRelative(this, facing);
		if(a instanceof IEnergyHandler && !(a instanceof IPowerConductor))recep = (IEnergyHandler) a;
		switchOrientation();
	}
}

package common.cout970.UltraTech.util.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.PowerNetwork;
import ultratech.api.power.StorageInterface;
import ultratech.api.power.StorageInterface.PowerIO;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPower;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.CableInterfaceBlock;
import cofh.api.energy.IEnergyHandler;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.network.SyncTile;

public class Machine extends SyncTile implements IPowerConductor,IPower,IEnergyHandler{

	public StorageInterface cond;
	
	public Machine(double capacity,int tier,PowerIO t){
		super();
		cond = new StorageInterface(this, new CableInterfaceBlock(this), capacity, tier);
		cond.configIO = t;
	}
	
	public Machine(double capacity,int tier,PowerIO t, ICable c){
		super();
		cond = new StorageInterface(this, c, capacity, tier);
		cond.configIO = t;
	}
	
	public Machine(double capacity,int tier){
		super();
		cond = new StorageInterface(this, new CableInterfaceBlock(this), capacity, tier);
		cond.configIO = PowerIO.Nothing;
	}
	
	public Machine(MachineData a){
		this(a.cap,a.tier,a.type);
	}

	public Machine(MachineData a, ICable c) {
		this(a.cap,a.tier,a.type,c);
	}

	@Override
	public PowerInterface getPower() {
		return cond;
	}

	public void updateEntity(){
		super.updateEntity();
		cond.MachineUpdate();
		if(worldObj.getTotalWorldTime() % 20 == 3){
			sendNetworkUpdate();
		}
	}
	
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		cond.readFromNBT(nbt);
	}
	
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		cond.writeToNBT(nbt);
	}

	
	//methods for old energy system
	@Override
	public double addCharge(double amount) {
		return cond.addCharge(amount);
	}

	@Override
	public void removeCharge(double amount) {
		cond.removeCharge(amount);
	}

	@Override
	public double getCharge() {
		return cond.getCharge();
	}

	@Override
	public double getCapacity() {
		return cond.getCapacity();
	}

	@Override
	public double getFlow() {
		return cond.getFlow();
	}

	public double maxFlow() {
		return getFlow();
	}

	public PowerNetwork getNetwork(){
		return cond.getNetwork();
	}

	//util
	
	public boolean spaceForCharge(double charge){
		if(getCapacity()-getCharge() >= charge)return true;
		return false;
	}
	
	//redstone flux

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return this.getPower().getCable().isOpenSide(from);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(simulate){
			double cap = getCapacity()-getCharge();
			return (int) Math.min(maxReceive, PowerExchange.QPtoRF(cap));
		}else{
			double old = getCharge();
			addCharge(PowerExchange.RFtoQP(maxReceive));
			double spent = getCharge()-old;
			return PowerExchange.QPtoRF(spent);
		}	
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return PowerExchange.QPtoRF(getCharge());
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return PowerExchange.QPtoRF(getCapacity());
	}
}

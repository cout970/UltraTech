package api.cout970.UltraTech.MeVpower;

import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.blocks.models.Dynamo;
import common.cout970.UltraTech.lib.CostData;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;

public class Machine extends SyncTile implements IPowerConductor,IEnergy,IEnergyHandler{

	public StorageInterface cond;
	
	public Machine(double capacity,int tier,MachineTipe t){
		super();
		cond = new StorageInterface(this, capacity, tier);
		cond.tipe = t;
	}
	
	public Machine(double capacity,int tier){
		super();
		cond = new StorageInterface(this, capacity, tier);
		cond.tipe = MachineTipe.Nothing;
	}
	
	public Machine(double capacity,int tier,MachineTipe t,boolean a){
		super();
		cond = new StorageInterface(this, capacity, tier){
			public CableType getConnectionType(ForgeDirection side){
				return ((Machine) getParent()).getConection(side);
			}
			public boolean isConnectableSide(ForgeDirection side ,CableType conection){
				return ((Machine) getParent()).isConnectableSide(side, conection);
			}
		};
		cond.tipe = t;
	}
	
	public Machine(CostData a){
		this(a.cap,a.tier,a.type);
	}
	
	public Machine(CostData a,boolean b){
		this(a.cap,a.tier,a.type,true);
	}

	//only for special connection
	public boolean isConnectableSide(ForgeDirection side, CableType conection) {
		return true;
	}
	//only for special connection
	public CableType getConection(ForgeDirection side) {
		return CableType.BLOCK;
	}

	@Override
	public PowerInterface getPower() {
		return cond;
	}

	public void updateEntity(){
		super.updateEntity();
		cond.MachineUpdate();
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
	public double addEnergy(double amount) {
		return cond.addCharge(amount);
	}

	@Override
	public void removeEnergy(double amount) {
		cond.removeCharge(amount);
	}

	@Override
	public double getEnergy() {
		return cond.getCharge();
	}

	@Override
	public double maxEnergy() {
		return cond.maxCharge();
	}

	@Override
	public double maxFlow() {
		return cond.getFlow();
	}

	public PowerNetwork getNetwork(){
		return cond.getNetwork();
	}

	//redstone flux

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return this.isConnectableSide(from, CableType.BLOCK);
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		if(simulate){
			double cap = maxEnergy()-getEnergy();
			return (int) Math.min(maxReceive, cap*DynamoEntity.RF);
		}else{
			double old = getEnergy();
			addEnergy(maxReceive/DynamoEntity.RF);
			double spent = getEnergy()-old;
			return (int) spent*DynamoEntity.RF;
		}	
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return (int) (getEnergy()*DynamoEntity.RF);
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return (int) (maxEnergy()*DynamoEntity.RF);
	}
	
	
}

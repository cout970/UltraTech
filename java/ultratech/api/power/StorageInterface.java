package ultratech.api.power;

import ultratech.api.power.StorageInterface.PowerIO;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.prefab.CableInterfaceBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Cout970
 *
 */
public class StorageInterface extends PowerInterface{

	private double Capacity = 0.0D;					//max energy accepted
	private double Charge = 0.0D;					//big storage
	public PowerTier tier;			                //machine level (only for max transfer rate)
	public PowerIO configIO = PowerIO.Nothing;      //Input/Output of machine 

	/**
	 * @param p TileEntity
	 * @param capacity 
	 * @param tier see MachineTier for more
	 */
	public StorageInterface(TileEntity p, ICable c, double capacity, int tier){
		super(p,c);
		Capacity = capacity;
		this.tier = PowerTier.getTier(tier);
	}

	/**
	 * called every tick
	 * moves the power
	 * only need use super.updateentity() if the machine add energy to the network
	 */
	public void MachineUpdate(){
		if(getParent().getWorldObj().isRemote)return;
		super.MachineUpdate();
		if(configIO == StorageInterface.PowerIO.Output || configIO == StorageInterface.PowerIO.Storage){
			this.emptyMachine();
		}else if(configIO == StorageInterface.PowerIO.Input){
			this.fillMachine();
		}
	}

	public void emptyMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.configIO != PowerIO.Output){
				if(b.configIO == PowerIO.Storage && this.configIO == PowerIO.Storage)continue;
				PowerUtils.MoveCharge(((IPowerConductor)this.getParent()), ((IPowerConductor)b.getParent()));
			}
		}
	}

	public void fillMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.configIO == StorageInterface.PowerIO.Output || b.configIO == StorageInterface.PowerIO.Storage)
				PowerUtils.MoveCharge(((IPowerConductor)b.getParent()), ((IPowerConductor)this.getParent()));
		}
	}

	/**
	 * @param amount the amount positive of energy added
	 * @return excess of charge
	 */
	@Override
	public double addCharge(double amount) {
		Charge += amount;
		if(Charge > getCapacity()){
			double aux = getCapacity() - Charge;
			Charge = getCapacity();
			return aux;
		}if(Charge < 0)Charge = 0;
		return 0;
	}

	/**
	 * @param amount the amount positive of energy removed
	 */
	@Override
	public void removeCharge(double amount) {
		if(Charge-amount >= 0){
			Charge -= amount;	
		}else{
			Charge = 0;
		}
		if(Charge < 0)Charge = 0;
	}

	public void setCharge(double d) {
		Charge = d;
	}
	/**
	 * @return the machine charge
	 */
	@Override
	public double getCharge() {return Charge;}

	/**
	 * @return the machine capacity
	 */
	@Override
	public double getCapacity() {return Capacity;}

	/**
	 * max amount of charge accepted per tick
	 */
	@Override
	public double getFlow() {return tier.getFlow();}

	public void writeToNBT(NBTTagCompound nbt){
		nbt.setDouble("Charge", Charge);
		nbt.setDouble("Capacity", Capacity);
		if(tier != null)nbt.setInteger("Tier", PowerTier.getPosition(tier));
		if(configIO != null)nbt.setInteger("IO", configIO.ordinal());
	}

	public void readFromNBT(NBTTagCompound nbt){
		Charge = nbt.getDouble("Charge");
		Capacity = nbt.getDouble("Capacity");
		tier = PowerTier.getTier(nbt.getInteger("Tier"));
		configIO = PowerIO.values()[nbt.getInteger("IO")];
	}

	public static enum PowerIO{
		Output,	//only empty the machine, used for generators
		Input,	//get energy from the all type of machines, unusual use
		Nothing,//for simple machines that not generate or provide energy for others machines
		Storage;//for storage, accept energy from generators and export to the machines
	}

	public void setChargeDeci(int value) {
		int c = (int) getCharge();
		Charge = (c+((double) value/10));
	}

	
}

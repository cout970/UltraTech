package api.cout970.UltraTech.MeVpower;

import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * 
 * @author Cout970
 *
 */
public class StorageInterface extends PowerInterface{

	private double Capacity = 0.0D;					//max energy acepted
	private double Charge = 0.0D;					//big storage
	public PowerTier tier;			                //machine level
	public MachineTipe tipe = MachineTipe.Nothing;  //tipe of machine 

	/**
	 * 
	 * @param p TileEntity
	 * @param capacity 
	 * @param tier see MachineTier for more
	 */
	public StorageInterface(TileEntity p,double capacity,int tier){
		super(p);
		Capacity = capacity;
		this.tier = PowerTier.getTier(tier);
	}

	/**
	 * called every tick
	 * moves the power
	 */
	public void MachineUpdate(){
		if(getParent().getWorldObj().isRemote)return;
		super.MachineUpdate();
		if(tipe == StorageInterface.MachineTipe.Output || tipe == StorageInterface.MachineTipe.Storage){
			this.emptyMachine();
		}else if(tipe == StorageInterface.MachineTipe.Input){
			this.fillMachine();
		}
	}

	public void emptyMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.tipe != StorageInterface.MachineTipe.Output)
				PowerUtils.MoveCharge(((IPowerConductor)this.getParent()), ((IPowerConductor)b.getParent()));
		}
	}

	public void fillMachine(){
		if(getNetwork() == null)return;
		for(StorageInterface b: getNetwork().getMachines()){
			if(b.tipe == StorageInterface.MachineTipe.Output || b.tipe == StorageInterface.MachineTipe.Storage)
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
		if(Charge > maxCharge()){
			double aux = maxCharge() - Charge;
			Charge = maxCharge();
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

	public void setCharge(int value) {
		Charge = value;
	}
	/**
	 * @return the machine charge
	 */
	@Override
	public double getCharge() {return Charge;}

	@Override
	public double maxCharge() {return Capacity;}

	/**
	 * max amount of charge accepted per tick
	 */
	@Override
	public double getFlow() {return tier.getFlow();}

	public void writeToNBT(NBTTagCompound nbt){
		nbt.setDouble("Charge", Charge);
		nbt.setDouble("Capacity", Capacity);
		if(tier != null)nbt.setInteger("Tier", PowerTier.getPosition(tier));
	}

	public void readFromNBT(NBTTagCompound nbt){
		Charge = nbt.getDouble("Charge");
		Capacity = nbt.getDouble("Capacity");
		tier = PowerTier.getTier(nbt.getInteger("Tier"));
	}

	public static enum MachineTipe{
		Output,Input,Nothing,Storage
	}

	public void setChargeDeci(int value) {
		int c = (int) getCharge();
		Charge = (c+((double) value/10));
	}

	
}

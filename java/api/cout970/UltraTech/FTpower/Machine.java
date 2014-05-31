package api.cout970.UltraTech.FTpower;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;

public class Machine extends SyncTile implements IPowerConductor,IEnergy{

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
			public ConnType getConnectionType(ForgeDirection side){
				return ((Machine) getParent()).getConection(side);
			}
			public boolean isConnectableSide(ForgeDirection side ,ConnType conection){
				return ((Machine) getParent()).isConnectableSide(side, conection);
			}
		};
		cond.tipe = t;
	}
	
	//only for special connection
	public boolean isConnectableSide(ForgeDirection side, ConnType conection) {
		return true;
	}
	//only for special connection
	public ConnType getConection(ForgeDirection side) {
		return ConnType.ALL_CABLES;
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
		double c = cond.addCharge(amount);
		Sync();
		return c;
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
}

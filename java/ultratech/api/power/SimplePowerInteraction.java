package ultratech.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
/**
 * 
 * @author Cout970
 *
 */
public class SimplePowerInteraction extends TileEntity implements IPowerConductor{

	public StorageInterface cond;//if want to make cables use simple cable
	
	public SimplePowerInteraction(double cap,int tier){
		super();
		cond = new StorageInterface(this, cap, tier);
	}
	
	/**
	 * the basic interaction with energy
	 */
	@Override
	public PowerInterface getPower() {
		return cond;
	}

	/**
	 * only need this if want to export or import energy to the network
	 * simple machines not need this 
	 */
	public void updateEntity(){
		cond.MachineUpdate();
	}
	
	/**
	 * save eenrgy
	 */
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		cond.readFromNBT(nbt);
	}
	/**
	 * load energy
	 */
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		cond.writeToNBT(nbt);
	}
}

package ultratech.api.power;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Basic class for cables and thinks that not keep charge
 * @author Cout970
 *
 */
public class PowerInterface implements IPower{
	
    private TileEntity Parent; //machine
    private PowerNetwork net; //conections
    
    public PowerInterface(TileEntity p){
    	Parent = p;
    }

    public TileEntity getParent(){
    	return Parent;
    }

    public PowerNetwork getNetwork() {
    	return net;
    }
    
    public void setNetwork(PowerNetwork n) {
    	net = n;
    }
    
	public void MachineUpdate(){
		if(net == null){
			NetworkManagerRegistry.iterate(this);
		}
	}

    public void onNetworkUpdate() {
    	net.onNetworkUpdate();
    }

	@Override
	public double addCharge(double amount) {
		return 0;
	}

	@Override
	public void removeCharge(double amount) {		
	}

	@Override
	public double getCharge() {
		return 0;
	}

	@Override
	public double getCapacity() {
		return 0;
	}

	@Override
	public double getFlow() {
		return 0;
	}
	
	public CableType getConnectionType(ForgeDirection side){
		return CableType.BLOCK;
	}

}

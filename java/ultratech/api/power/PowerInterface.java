package ultratech.api.power;

import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPower;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Basic class for cables and thinks that not keep charge
 * @author Cout970
 *
 */
public class PowerInterface implements IPower{
	
    private TileEntity Parent; //owner of the interface
    private PowerNetwork net; //machines connected
    private ICable cond; //the interface for interaction with cables
    
    public PowerInterface(TileEntity p,ICable c){
    	Parent = p;
    	cond = c;
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
			if(!net.interfaces.contains(this)){
				net.interfaces.add(this);
			}
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

	public ICable getCable() {
		return cond;
	}
	
	public void setCable(ICable c) {
		cond = c;
	}

}

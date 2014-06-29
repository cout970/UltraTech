package api.cout970.UltraTech.MeVpower;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * basic class for cables and thinks that not keep charge
 * @author Cout970
 *
 */
public class PowerInterface implements IPower{
	
    private TileEntity Parent; //machine
    private PowerNetwork net; //machines conected
    
    public PowerInterface(TileEntity p){
    	Parent = p;
    }

    public TileEntity getParent(){
    	return Parent;
    }
    
	public boolean isConnectableSide(ForgeDirection side ,CableType conection){
		return true;
	}

    public PowerNetwork getNetwork() {
    	return net;
    }
    
    public void setNetwork(PowerNetwork n) {
    	net = n;
    }
    
	public void MachineUpdate(){
		if(net == null){
			iterate();
		}
	}

    public void iterate(){
    	boolean hasNetwork = false;
    	for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
    		TileEntity e = PowerUtils.getRelative(Parent, dir);
    		if(e instanceof IPowerConductor){
    			PowerInterface p = ((IPowerConductor) e).getPower();
    			if(p.isConnectableSide(dir.getOpposite(), getConnectionType(dir))){
    				if(p.getNetwork() != null){
    					if(!hasNetwork){
    						setNetwork(p.getNetwork());
    						hasNetwork = true;
    					}else{
    						net.mergeWith(p.getNetwork());
    					}
    				}
    			}
    		}
    	}
    	if(!hasNetwork){
    		setNetwork(PowerNetwork.create(this));
    	}
    	net.refresh();
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
	public double maxCharge() {
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

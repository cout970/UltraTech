package ultratech.api.power;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.util.LogHelper;

import net.minecraft.tileentity.TileEntity;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.multipart.MultipartReference;
import ultratech.api.util.UT_Utils;
/**
 * 
 * @author Cout970
 *
 */
public class PowerNetwork {

	public List<PowerInterface> interfaces = new ArrayList<PowerInterface>();
	private List<PowerPath> rutes = new ArrayList<PowerPath>();

	private PowerNetwork(){}
	
	public static PowerNetwork create(PowerInterface base){
		PowerNetwork p = new PowerNetwork();
		p.interfaces.add(base);
		return p;
	}

	public List<PowerInterface> getConductors(){
		return interfaces;
	}

	public List<StorageInterface> getMachines(){
		List<StorageInterface> s = new ArrayList<StorageInterface>();
		for(PowerInterface p : interfaces)if(p instanceof StorageInterface)s.add((StorageInterface) p);
		return s;
	}

	public void refresh(){
		IPowerConductor base = null;
		for(PowerInterface e : interfaces){
			for(ICable t : NetworkManagerRegistry.getConnections(e.getParent())){
				base = t.getConductor();
			}
		}
		try{
			if(base != null){
				interfaces.clear();
				List<PowerInterface> l = NetworkManagerRegistry.search(base, null).getFinding();
				interfaces.addAll(l);
			}
		}catch(Exception e){e.printStackTrace();};

		for(PowerInterface e : interfaces){
			e.setNetwork(this);
			e.onNetworkUpdate();
		}		
	}

	public void onNetworkUpdate(){
		rutes.clear();
	}

	public void mergeWith(PowerNetwork net){
		for(PowerInterface te : interfaces){
			te.setNetwork(net);
		}
		for(PowerInterface e : interfaces){
			e.onNetworkUpdate();
		}
	}
	public boolean canPowerGoToEnd(IPowerConductor from, IPowerConductor to) {
		for(PowerPath p:rutes){
			if(p.contains(from,to))return true;
		}
		if(NetworkManagerRegistry.search(from, to).canGoToTheEnd()){
			rutes.add(new PowerPath(from,to));
			return true;
		}else return false;
	}

	public void excludeAndRecalculate(IPowerConductor p) {
		try{
			NetworkManagerRegistry.excludeAndRecalculate(p);
		}catch(Exception e){}

	}

}

package common.cout970.UltraTech.energy.api;

import java.util.ArrayList;
import java.util.List;


public class EnergyNetwork {

	private List<ElectricConductor> components = new ArrayList<ElectricConductor>();
	private List<ElectricConductor> excluded = new ArrayList<ElectricConductor>();

	
	private EnergyNetwork(){}

	public static EnergyNetwork create(ElectricConductor base){
		EnergyNetwork net = new EnergyNetwork();
		net.components.add(base);
		return net;
	}
	
	public List<ElectricConductor> getComponents(){
		return components;
	}
	
	public void refresh(){
		
		ElectricConductor base = null;
		for(ElectricConductor e : components){
			if(e.worldObj.getBlockTileEntity(e.xCoord, e.yCoord, e.zCoord) != null){
				if(!excluded.contains(e)){
					base = e;
				}
			}
		}
		
		if(base != null){
			List<ElectricConductor> things = new ArrayList<ElectricConductor>();
			things.addAll(new EnergyPathfinder(base, null).getComponents());
			things.removeAll(excluded);
			for(ElectricConductor c : excluded){
				c.setNetwork(null);
			}
			components = things;
		}
		
		for(ElectricConductor e : components){
			e.setNetwork(this);
			e.onNetworkUpdate();
		}
	}

	public void mergeWith(EnergyNetwork net){
		for(ElectricConductor te : components){
			te.setNetwork(net);
		}
		for(ElectricConductor e : components){
			e.onNetworkUpdate();
		}
	}
	
	public void exclude(ElectricConductor te){
		excluded.add(te);
		try{
			components.remove(te);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void excludeAndRecalculate(ElectricConductor te) {
		exclude(te);
		refresh();
	}

	public List<Machine> getMachines() {
		List<Machine> a = new ArrayList<Machine>();
		for(ElectricConductor i : components){
			if(i instanceof Machine){
				a.add((Machine) i);
			}
		}
		return a;
	}
}

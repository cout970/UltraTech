package ultratech.api.power.prefab;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.PowerNetwork;
import ultratech.api.power.PowerUtils;
import ultratech.api.power.interfaces.ICable;
import ultratech.api.power.interfaces.INetworkManager;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.util.UT_Utils;

public class DefaultNetworkManager implements INetworkManager{

	public List<ICable> visited = new ArrayList<ICable>();
	public IPowerConductor end;
	public IPowerConductor base;
	

	public void search(IPowerConductor base, IPowerConductor end) {
		this.end = end;
		this.base = base;
		visited.add(base.getPower().getCable());
		list(base.getPower().getCable());
	}
	
	public DefaultNetworkManager() {}

	public void list(ICable t) {
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			if(t.isOpenSide(dir)){
				TileEntity tile = PowerUtils.getRelative(t, dir);
				if(tile != null){
					List<ICable> cab = this.getConnections(tile);
					for(ICable c : cab){
						if(c.isOpenSide(dir.getOpposite())){
							if(performCableConnection(t,dir,c)){
								if(!visited.contains(c)){
									visited.add(c);
									this.list(c);
								}
							}
						}
					}
				}
			}
		}
	}

	private boolean performCableConnection(ICable a, ForgeDirection dir, ICable b) {
		return a.shouldConnectWithThis(b, dir) && b.shouldConnectWithThis(a, dir.getOpposite());
	}

	public List<PowerInterface> getFinding() {
		List<PowerInterface> l = new ArrayList<PowerInterface>();
		for(ICable c : visited){
			l.add(c.getPower());
		}
		return l;
	}
	
	public boolean canGoToTheEnd() {
		if(end == null)return false;
		return visited.contains(end.getPower().getCable());
	}

	public IPowerConductor getEnd() {
		return end;
	}

	public IPowerConductor getBase() {
		return base;
	}

	public void clear() {
		visited.clear();
		base = null;
		end = null;
	}

	public void excludeAndRecalculate(IPowerConductor p) {
		//new method in test to refresh the network
		List<PowerInterface> members = p.getPower().getNetwork().getConductors();
		for(PowerInterface i : members)i.setNetwork(null);
//		try{
//			p.getPower().getNetwork().interfaces.remove(p);
//		}catch(Exception e){}
//		for(TileEntity t : UT_Utils.getTiles(p.getPower().getParent())){
//			if(t instanceof IPowerConductor){
//				((IPowerConductor) t).getPower().getNetwork().refresh();
//			}
//		}
	}

	public void iterate(PowerInterface cond) {
		boolean hasNetwork = false;
    	for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
    		TileEntity e = PowerUtils.getRelative(cond.getParent(), dir);
    		if(e instanceof IPowerConductor){
    			PowerInterface p = ((IPowerConductor) e).getPower();
    			if(performCableConnection(p.getCable(), dir, cond.getCable())){
    				if(p.getNetwork() != null){
    					if(!hasNetwork){
    						cond.setNetwork(p.getNetwork());
    						hasNetwork = true;
    					}else{
    						cond.getNetwork().mergeWith(p.getNetwork());
    					}
    				}
    			}
    		}
    	}
    	if(!hasNetwork){
    		cond.setNetwork(PowerNetwork.create(cond));
    	}
    	cond.getNetwork().refresh();
	}

	@Override
	public List<ICable> getConnections(TileEntity t) {
		List<ICable> c = new ArrayList<ICable>();
		if(t instanceof IPowerConductor){
			c.add(((IPowerConductor) t).getPower().getCable());
		}
		return c;
	}
}

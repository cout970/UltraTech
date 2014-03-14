package common.cout970.UltraTech.energy.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;


public class EnergyPathfinder {

	private List<ElectricConductor> visited = new ArrayList<ElectricConductor>();
	private List<ElectricConductor> electric = new ArrayList<ElectricConductor>();
	
	private ElectricConductor to;
	
	public EnergyPathfinder(ElectricConductor from, ElectricConductor to) {
		visited.add(from);
		electric.add(from);
		list(from);
		this.to = to;
	}
	
	private void list(ElectricConductor from){
		for(ForgeDirection dir : from.getConnectableSides()){
			TileEntity tile = EnergyUtils.getRelative(from, dir);
			if(tile != null){
				if(tile instanceof ElectricConductor){
					ElectricConductor e = (ElectricConductor) tile;
					if(Arrays.asList(e.getConnectableSides()).contains(dir.getOpposite())){
						if(!visited.contains(e)){
							if(!electric.contains(e)){
								electric.add(e);
								visited.add(e);
								list(e);
							}
						}
					}
				}
			}
		}
	}
	
	public boolean canEnergyGoToEnd(){
		if(to == null)return false;
		return electric.contains(to);
	}
	
	public int getFlow(){
		if(!canEnergyGoToEnd())return 0;
		int base = to.tier.getFlow();
		for(ElectricConductor e : electric){
			base = Math.min(base, e.tier.getFlow());
		}
		return base;
	}
	
	public List<ElectricConductor> getComponents(){
		return electric;
	}
	
}

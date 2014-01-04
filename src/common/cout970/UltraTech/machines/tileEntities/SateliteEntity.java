package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.core.UltraTech;

public class SateliteEntity extends Machine{
	

	public SateliteEntity(){
		super();
		this.EnergyMax = 5000;
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			List<SolarPanelEntity> s = getSolarPanels();
			for(SolarPanelEntity p : s){
				if(p.getEnergy() > 1 && p.getEnergy() < 64){
					this.gainEnergy(1);
					p.loseEnergy(1);
				}else if(p.getEnergy() > 64){
					this.gainEnergy(64);
					p.loseEnergy(64);
				}
			}
		}
		
	}

	public List<SolarPanelEntity> getSolarPanels(){
		List<SolarPanelEntity> s = new ArrayList<SolarPanelEntity>();
		for(int x= -5;x<5;x++){
			for(int z= -5;z<5;z++){
				if(this.worldObj.getBlockId(xCoord+x, yCoord, zCoord+z)== UltraTech.SolarPanel.blockID){
					s.add((SolarPanelEntity) worldObj.getBlockTileEntity(xCoord+x, yCoord, zCoord+z));
				}
			}
		}
		return s;
	}
}

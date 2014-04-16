package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
public class ClimateEntity extends Machine{

	public ClimateEntity(){
		tier = MachineTier.Tier3;
		this.setMaxEnergy(EnergyCosts.ClimateEstationCost);
	}
	
	public void updateEntity(){}
	
	public void setRain(){
		if(!worldObj.isRaining())worldObj.toggleRain();
	}
	
	public void setThunder(){
        worldObj.getWorldInfo().setThundering(true);
		worldObj.thunderingStrength = 1f;
		worldObj.prevThunderingStrength = 1f;
		setRain();
	}
	
	public void setSun(){
		worldObj.getWorldInfo().setRaining(false);
		worldObj.getWorldInfo().setRainTime(90000);
		worldObj.getWorldInfo().setThundering(false);
	}
	
	public void restoneUpdate(boolean on){

	}

	public void setClimate(int i){
		if(worldObj.isRemote)return;
		if(getEnergy() >= EnergyCosts.ClimateEstationCost){
		if(i==0)setSun();
		if(i==1)setRain();
		if(i==2)setThunder();
		removeEnergy(EnergyCosts.ClimateEstationCost);
		}
	}
}

package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Wpower.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.CostData;

public class ClimateEntity extends Machine{
	
	public ClimateEntity() {
		super(CostData.Climate_Station);
	}

	public void updateEntity(){}
	
	public void setRain(){
		if(!worldObj.isRaining())worldObj.getWorldInfo().setRaining(true);
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
		if(getEnergy() >= CostData.Climate_Station.use){
		if(i==0)setSun();
		if(i==1)setRain();
		if(i==2)setThunder();
		removeEnergy(CostData.Climate_Station.use);
		}
	}
}

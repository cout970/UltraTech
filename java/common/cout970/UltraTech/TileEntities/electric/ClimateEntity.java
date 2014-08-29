package common.cout970.UltraTech.TileEntities.electric;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.power.Machine;

public class ClimateEntity extends Machine{
	
	public ClimateEntity() {
		super(MachineData.Climate_Station);
	}

	public void updateEntity(){
		super.updateEntity();
	}
	
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
		if(getCharge() >= MachineData.Climate_Station.use){
		if(i==0)setSun();
		if(i==1)setRain();
		if(i==2)setThunder();
		removeCharge(MachineData.Climate_Station.use);
		}
	}
}

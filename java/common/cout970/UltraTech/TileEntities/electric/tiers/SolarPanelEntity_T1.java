package common.cout970.UltraTech.TileEntities.electric.tiers;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.util.power.cables.CableInterfaceSolarPanel;


public class SolarPanelEntity_T1 extends Machine{

	public SolarPanelEntity_T1(){
		super(MachineData.Solar_Panel);
		this.cond.setCable(new CableInterfaceSolarPanel(this));
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote) return;
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering()))){
				addCharge(getProduction());
			}
		}
	}
	
	public double getProduction(){
		return MachineData.Solar_Panel.use;
	}
	
}
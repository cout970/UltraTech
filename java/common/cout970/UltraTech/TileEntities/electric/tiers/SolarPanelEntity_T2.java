package common.cout970.UltraTech.TileEntities.electric.tiers;

import net.minecraftforge.common.util.ForgeDirection;
import ultratech.api.power.CableType;
import ultratech.api.power.IPowerConductor;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.power.Machine;


public class SolarPanelEntity_T2 extends SolarPanelEntity_T1 implements IPowerConductor{

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote) return;
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering()))){
				cond.addCharge(MachineData.Solar_Panel.use*8);
			}
		}
	}
}
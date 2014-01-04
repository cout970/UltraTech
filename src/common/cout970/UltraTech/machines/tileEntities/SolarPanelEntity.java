package common.cout970.UltraTech.machines.tileEntities;


public class SolarPanelEntity extends Machine{

	private float power;

	@Override
	public void updateEntity(){
		if(worldObj.provider.hasNoSky)return;
		if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering())) && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(!this.worldObj.isRemote){			
				power += 0.1f*(yCoord/50f);
				while(power > 1){
					this.gainEnergy(1);
					power--;
				}
			}
		}
	}
}
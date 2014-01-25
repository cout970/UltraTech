package common.cout970.UltraTech.machines.tileEntities;


public class SolarPanelEntity extends Machine{

	private float power;

	public SolarPanelEntity(){
		super();
		this.tipe = MachineTipe.Output;
		this.EnergyMax = 1000;
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.provider.hasNoSky)return;
		if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering())) && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(!this.worldObj.isRemote){			
				power = (yCoord/50f);
				this.gainEnergy((int)(power));
			}
		}
	}
}
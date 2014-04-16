package common.cout970.UltraTech.TileEntities.Tier2;

import net.minecraftforge.common.ForgeDirection;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;


public class SolarPanelEntity extends Machine{

	public SolarPanelEntity(){
		super();
		this.tipe = MachineTipe.Output;
		this.tier = MachineTier.Tier2;
		setMaxEnergy(1000);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering())) && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){	
				addEnergy(EnergyCosts.SolarPanelProduct);
			}
		}
	}
	public ForgeDirection[] getConnectableSides(){
		ForgeDirection[] sides = new ForgeDirection[5];
		sides[0] = ForgeDirection.DOWN;
		sides[1] = ForgeDirection.NORTH;
		sides[2] = ForgeDirection.SOUTH;
		sides[3] = ForgeDirection.EAST;
		sides[4] = ForgeDirection.WEST;
		return sides;
	}
	
}
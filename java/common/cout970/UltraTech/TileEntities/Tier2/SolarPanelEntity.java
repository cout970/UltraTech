package common.cout970.UltraTech.TileEntities.Tier2;

import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.PowerInterface;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.lib.EnergyCosts;


public class SolarPanelEntity extends Machine{

	public SolarPanelEntity(){
		super(200,2,MachineTipe.Output);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote) return;
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering()))){
				cond.addCharge(EnergyCosts.SolarPanelProduct);
			}
		}
//		System.out.println();
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
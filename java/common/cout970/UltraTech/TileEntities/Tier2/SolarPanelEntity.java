package common.cout970.UltraTech.TileEntities.Tier2;

import api.cout970.UltraTech.FTpower.ConnType;
import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.Machine;
import api.cout970.UltraTech.FTpower.PowerInterface;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.lib.EnergyCosts;


public class SolarPanelEntity extends Machine implements IPowerConductor{

	public SolarPanelEntity(){
		super(200, 2,MachineTipe.Output,true);
	}
	
	public boolean isConnectableSide(ForgeDirection side, ConnType conection) {
		if(side == ForgeDirection.UP)return false;
		if(side == ForgeDirection.DOWN)return true;
		return ConnType.isCompatible(ConnType.ONLY_SMALL, conection);
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
	}
}
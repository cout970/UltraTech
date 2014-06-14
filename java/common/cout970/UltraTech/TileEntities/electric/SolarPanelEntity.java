package common.cout970.UltraTech.TileEntities.electric;

import api.cout970.UltraTech.Vpower.CableType;
import api.cout970.UltraTech.Vpower.IPowerConductor;
import api.cout970.UltraTech.Vpower.Machine;
import api.cout970.UltraTech.Vpower.PowerInterface;
import api.cout970.UltraTech.Vpower.StorageInterface;
import api.cout970.UltraTech.Vpower.StorageInterface.MachineTipe;
import api.cout970.UltraTech.network.SyncTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;


public class SolarPanelEntity extends Machine implements IPowerConductor{

	public SolarPanelEntity(){
		super(CostData.Solar_Panel,true);
	}
	
	public boolean isConnectableSide(ForgeDirection side, CableType conection) {
		if(side == ForgeDirection.UP)return false;
		if(side == ForgeDirection.DOWN)return true;
		return CableType.isCompatible(CableType.RIBBON_BOTTOM, conection);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(this.worldObj.isRemote) return;
		if(worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering()))){
				cond.addCharge(CostData.Solar_Panel.use);
			}
		}
	}
}
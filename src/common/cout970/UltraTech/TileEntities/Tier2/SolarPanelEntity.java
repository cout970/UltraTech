package common.cout970.UltraTech.TileEntities.Tier2;

import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.ForgeDirection;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class SolarPanelEntity extends Machine{

	public SolarPanelEntity(){
		super();
		this.tipe = MachineTipe.Output;
		setMaxEnergy(1000);
	}
	
	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!this.worldObj.isRemote){
			if(worldObj.provider.hasNoSky)return;
			if(worldObj.isDaytime() && !(worldObj.getWorldChunkManager().getBiomeGenAt(xCoord, zCoord).getIntRainfall() > 0 && (worldObj.isRaining() || worldObj.isThundering())) && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)){	
				addEnergy(yCoord/GraficCost.SolarPanelDivisor_Height + 1);
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
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
            bb = AxisAlignedBB.getAABBPool().getAABB(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
        
        return bb;
    }
	
}
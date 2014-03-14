package common.cout970.UltraTech.machines.tileEntities;

import net.minecraft.util.AxisAlignedBB;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.machines.renders.holograms.Map;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HologramEmiterEntity extends Machine{

	public boolean update = false;
	public Map map;

	public HologramEmiterEntity(){
		super();
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
            bb = AxisAlignedBB.getAABBPool().getAABB(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 2+5, zCoord + 2);
        
        return bb;
    }

}

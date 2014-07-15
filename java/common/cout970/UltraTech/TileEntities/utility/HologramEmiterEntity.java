package common.cout970.UltraTech.TileEntities.utility;

import net.minecraft.util.AxisAlignedBB;
import common.cout970.UltraTech.client.renders.Map;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class HologramEmiterEntity extends SyncTile{

	public boolean update = false;
	public Map map;

	public HologramEmiterEntity(){
		super();
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = INFINITE_EXTENT_AABB;
        
        return bb;
    }

}

package ultratech.api.power;

import ultratech.api.power.interfaces.IPowerConductor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
/**
 * 
 * @author Cout970
 *
 */
public abstract class PowerConductorBlock  extends BlockContainer{

	public PowerConductorBlock(Material m) {
		super(m);
	}

	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		super.onBlockPreDestroy(w, x, y, z, meta);
		if(w.isRemote)return;
		TileEntity te = w.getTileEntity(x, y, z);
		if(te instanceof IPowerConductor){
			IPowerConductor p = (IPowerConductor) te;
			if(p.getPower().getNetwork() != null){
				p.getPower().getNetwork().excludeAndRecalculate(p);
			}
		}
	}
	
	public void onBlockAdded(World w, int x, int y, int z) {
		super.onBlockAdded(w, x, y, z);
		if(w.isRemote)return;
		TileEntity t = w.getTileEntity(x, y, z);
		if(!(t instanceof IPowerConductor))return;
		IPowerConductor te = (IPowerConductor) t;
		NetworkManagerRegistry.iterate(te.getPower());
	}
}

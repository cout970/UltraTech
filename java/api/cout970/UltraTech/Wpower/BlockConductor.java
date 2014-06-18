package api.cout970.UltraTech.Wpower;

import java.util.Arrays;

import buildcraft.api.power.IPowerReceptor;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
/**
 * 
 * @author Cout970
 *
 */
public abstract class BlockConductor extends BlockContainer{

	public BlockConductor(Material m) {
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
		if(!(w.getTileEntity(x, y, z) instanceof IPowerConductor))return;
		IPowerConductor te = (IPowerConductor) w.getTileEntity(x, y, z);
		te.getPower().iterate();
	}
	
}

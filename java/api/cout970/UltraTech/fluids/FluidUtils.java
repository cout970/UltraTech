package api.cout970.UltraTech.fluids;


import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

public class FluidUtils {

	public static void onBlockAdded(World w, int x, int y, int z) {
		if(w.isRemote)return;
		if(!(w.getTileEntity(x, y, z) instanceof IFluidTransport))return;
		IFluidTransport te = (IFluidTransport) w.getTileEntity(x, y, z);
		boolean hasNetwork = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = getRelative(te.getTileEntity(), dir);
			if(e instanceof IFluidTransport){
					if(((IFluidTransport) e).getNetwork() != null){
						if(!hasNetwork){
							te.setNetwork(((IFluidTransport) e).getNetwork());
							te.getNetwork().getPipes().add(te);
							hasNetwork = true;
						}else{
							te.getNetwork().mergeWith(((IFluidTransport) e).getNetwork());
						}		
					}

			}
		}
		if(!hasNetwork){
			te.setNetwork(FluidNetwork.create(te));
		}
		te.getNetwork().refresh();
	}
	
	public static void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		if(w.isRemote)return;
		TileEntity te = w.getTileEntity(x, y, z);
		if(te instanceof IFluidTransport){
			if(((IFluidTransport)te).getNetwork() != null){
				((IFluidTransport)te).getNetwork().excludeAndRecalculate((IFluidTransport)te);
			}
		}
	}
	
	private static boolean isConectable(TileEntity a) {
		if(a instanceof IFluidTransport || a instanceof IFluidHandler){
			return true;
		}
		return false;
	}

	public static boolean CanPassFluid(TileEntity a,TileEntity b) {
		
		if(!(isConectable(b) && isConectable(a)))return false;
		return true;
	}

	public static TileEntity getRelative(TileEntity from, ForgeDirection dir) {
		return from.getWorldObj().getTileEntity(from.xCoord+dir.offsetX, from.yCoord+dir.offsetY, from.zCoord+dir.offsetZ);
	}
}

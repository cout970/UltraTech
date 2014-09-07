package common.cout970.UltraTech.util.fluids;


import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import ultratech.api.power.multipart.MultipartReference;
import ultratech.api.util.UT_Utils;

import common.cout970.UltraTech.multipart.MultipartUtil;

public class FluidUtils {

	public static void onBlockAdded(World w, int x, int y, int z) {
		if(w.isRemote)return;
		if(!(w.getTileEntity(x, y, z) instanceof IFluidTransport))return;
		IFluidTransport te = (IFluidTransport) w.getTileEntity(x, y, z);
		boolean hasNetwork = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = UT_Utils.getRelative(te.getTileEntity(), dir);
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
			te.setNetwork(FluidNetwork.create(te,w.getTileEntity(x, y, z)));
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

	public static List<TankConection> getTankConections(TileEntity tile) {
		List<TankConection> t = new ArrayList<TankConection>();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity y = UT_Utils.getRelative(tile, d);
			if(y instanceof IFluidHandler){
				t.add(new TankConection((IFluidHandler) y, d.getOpposite()));
			}
		}
		return t;
	}

	public static boolean isPipe(TileEntity g) {
		if(MultipartReference.isMicroPartActived)return MultipartUtil.isMultiPartPipe(g);
		return g instanceof IFluidTransport;
	}

	public static IFluidTransport getFluidTransport(TileEntity e) {
		if(MultipartReference.isMicroPartActived)return MultipartUtil.getFluidTransport(e);
		if(e instanceof IFluidTransport)return (IFluidTransport) e;
		return null;
	}
}

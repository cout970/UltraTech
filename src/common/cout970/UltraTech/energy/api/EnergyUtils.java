package common.cout970.UltraTech.energy.api;

import java.util.Arrays;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class EnergyUtils {

	public static void onBlockAdded(World w, int x, int y, int z) {
		if(w.isRemote)return;
		if(!(w.getBlockTileEntity(x, y, z) instanceof ElectricConductor))return;
		ElectricConductor te = (ElectricConductor) w.getBlockTileEntity(x, y, z);
		boolean hasNetwork = false;
		for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
			TileEntity e = EnergyUtils.getRelative(te, dir);
			if(e instanceof ElectricConductor){
				if(Arrays.asList(((ElectricConductor) e).getConnectableSides()).contains(dir.getOpposite())){
					if(((ElectricConductor) e).getNetwork() != null){
						if(!hasNetwork){
							te.setNetwork(((ElectricConductor) e).getNetwork());
							te.getNetwork().getComponents().add(te);
							hasNetwork = true;
						}else{
							te.getNetwork().mergeWith(((ElectricConductor) e).getNetwork());
						}		
					}
				}
			}
		}
		if(!hasNetwork){
			te.setNetwork(EnergyNetwork.create(te));
		}
		te.getNetwork().refresh();
	}
	
	public static boolean canConnectTo(TileEntity a, TileEntity b){
		if(!(isElectric(a) && isElectric(b)))
			return false;
		
		ForgeDirection csA = getCommonSide(b, a);
		if(csA == null)
			return false;
		ForgeDirection csB = csA.getOpposite();
		
		boolean canA = false;
		boolean canB = false;
		
		if(a instanceof ElectricConductor)
			if(Arrays.asList(((ElectricConductor)a).getConnectableSides()).contains(csA))
				canA = true;
		
		if(b instanceof ElectricConductor)
			if(Arrays.asList(((ElectricConductor)b).getConnectableSides()).contains(csB))
				canB = true;
		
		if(!(canA && canB))
			return false;	
		return true;
	}
	
	public static ForgeDirection getCommonSide(TileEntity d, TileEntity o){
		if(d.xCoord < o.xCoord){
			return ForgeDirection.WEST;
		}else if(d.xCoord > o.xCoord){
			return ForgeDirection.EAST;
		}else if(d.yCoord < o.yCoord){
			return ForgeDirection.DOWN;
		}else if(d.yCoord > o.yCoord){
			return ForgeDirection.UP;
		}else if(d.zCoord < o.zCoord){
			return ForgeDirection.NORTH;
		}else if(d.zCoord > o.zCoord){
			return ForgeDirection.SOUTH;
		}
		return null;
	}

	public static TileEntity getRelative(TileEntity from, ForgeDirection d){
		return from.worldObj.getBlockTileEntity(from.xCoord + d.offsetX, from.yCoord + d.offsetY, from.zCoord + d.offsetZ);
	}

	public static void onBlockPreDestroy(World w, int x, int y, int z, int meta) {
		if(w.isRemote)return;
		TileEntity te = w.getBlockTileEntity(x, y, z);
		if(te instanceof ElectricConductor){
			if(((ElectricConductor)te).getNetwork() != null){
				((ElectricConductor)te).getNetwork().excludeAndRecalculate((ElectricConductor)te);
			}
		}
	}
	
	public static boolean isElectric(TileEntity te){
			if(te instanceof ElectricConductor)
				return true;
		return false;
	}
}

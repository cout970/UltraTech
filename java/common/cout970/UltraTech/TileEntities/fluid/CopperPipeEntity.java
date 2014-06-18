package common.cout970.UltraTech.TileEntities.fluid;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;
import api.cout970.UltraTech.fluids.IFluidTransport;
import api.cout970.UltraTech.fluids.Pipe;
import api.cout970.UltraTech.fluids.TankConection;

public class CopperPipeEntity extends Pipe{

	public List<TankConection> connections = new ArrayList<TankConection>();
	public List<ForgeDirection> pipes = new ArrayList<ForgeDirection>();

	public void updateEntity(){
		pipes.clear();
		connections.clear();
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity t = UT_Utils.getRelative(this, d);
			if(t instanceof IFluidTransport){
				pipes.add(d);
			}else if(t instanceof IFluidHandler){
				pipes.add(d);
				connections.add(new TankConection((IFluidHandler) t, d.getOpposite()));
			}
		}
	}
}

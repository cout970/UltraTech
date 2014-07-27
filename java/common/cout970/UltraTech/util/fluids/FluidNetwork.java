package common.cout970.UltraTech.util.fluids;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.misc.IUpdatedEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class FluidNetwork{

	private List<IFluidTransport> pipes = new ArrayList<IFluidTransport>();
	private List<IFluidTransport> pipesExcluded = new ArrayList<IFluidTransport>();
	private List<IFluidHandler> tanks = new ArrayList<IFluidHandler>();
	public TileEntity tile;
	public Fluid fluid;
	public FluidNetWorkManager manager = new FluidNetWorkManager(this);
	
	private FluidNetwork(){}

	public static FluidNetwork create(IFluidTransport base, TileEntity tile){
		FluidNetwork net = new FluidNetwork();
		net.pipes.add(base);
		net.tile = tile;
		return net;
	}	
	
	private TileEntity getTile() {
		return tile;
	}

	public List<IFluidTransport> getPipes(){
		return pipes;
	}
	
	public List<IFluidHandler> getTanks(){
		return tanks;
	}
	
	public void refresh(){
		IFluidTransport base = null;
		for(IFluidTransport e : pipes){
			TileEntity t = e.getTileEntity();
			if(t.getWorldObj().getTileEntity(t.xCoord, t.yCoord, t.zCoord) != null){
				if(!pipesExcluded.contains(e)){
					base = e;
				}
			}
		}
		
		if(base != null){
			List<IFluidTransport> things = new ArrayList<IFluidTransport>();
			FluidPathfinder found = new FluidPathfinder(base, null);
			things.addAll(found.getPipes());
			things.removeAll(pipesExcluded);
			pipes = things;
			tanks = found.getTanks();
		}
		
		for(IFluidTransport e : pipes){
			e.setNetwork(this);
			e.onNetworkUpdate();
		}
	}

	public void mergeWith(FluidNetwork net){
		for(IFluidTransport te : pipes){
			te.setNetwork(net);
		}
		for(IFluidTransport e : pipes){
			e.onNetworkUpdate();
		}
	}
	
	public void exclude(IFluidTransport te){
		pipesExcluded.add(te);
		try{
			pipes.remove(te);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void excludeAndRecalculate(IFluidTransport te) {
		exclude(te);
		refresh();
	}

	public int getFluidAmount() {
		int amount = 0;
		for(IFluidTransport t : pipes){
			amount += t.getTank().getFluidAmount();
		}
		return amount;
	}

	public Fluid getFluid() {
		if(fluid == null){
			if(getFluidAmount() == 0)return null;
			for(IFluidTransport t : pipes){
				if(t.getTank().getFluidAmount() > 0){
					fluid = t.getTank().getFluid().getFluid();
					break;
				}
			}
		}
		return fluid;
	}

	public int getCapacity() {
		int cap = 0;
		for(IFluidTransport t : pipes){
			cap += t.getTank().getCapacity();
		}
		return cap;
	}
}

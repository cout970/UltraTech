package common.cout970.UltraTech.fluid.api;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class Pipe extends TileEntity implements IFluidTransport{

	private FluidNetwork net;
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(net == null){
			if(!(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) instanceof IFluidTransport))return;
			IFluidTransport te = (IFluidTransport) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
			boolean hasNetwork = false;
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
				TileEntity e = FluidUtils.getRelative(te.getTileEntity(), dir);
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
	}


	@Override
	public TileEntity getTileEntity() {
		return this;
	}

	@Override
	public void setNetwork(FluidNetwork fluidNetwork) {
		net = fluidNetwork;
	}

	@Override
	public void onNetworkUpdate() {}

	@Override
	public FluidNetwork getNetwork() {
		return net;
	}

}

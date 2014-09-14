package common.cout970.UltraTech.multipart;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidTank;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.fluids.FluidNetwork;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.fluids.TankUT;

public abstract class MultiPartPipe extends MultiPartUT implements IFluidTransport{

	private FluidNetwork net;
	public TankUT buffer;
	
	public MultiPartPipe(Item a) {
		super(a);
	}
	
	@Override
	public void update() {
		if(world().isRemote)return;
		if(net == null){
			boolean hasNetwork = false;
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
				TileEntity e = UT_Utils.getRelative(tile(), dir);
				if(FluidUtils.isPipe(e)){
					IFluidTransport it = FluidUtils.getFluidTransport(e);
					if(it != null){
						if(this.canConectOnSide(dir) && it.canConectOnSide(dir.getOpposite())){
							if(it.getNetwork() != null){
								if(!hasNetwork){
									setNetwork(it.getNetwork());
									getNetwork().addPipe(this);
									hasNetwork = true;
								}else{
									getNetwork().mergeWith(it.getNetwork());
								}		
							}
						}
					}
				}
			}
			if(!hasNetwork){
				setNetwork(FluidNetwork.create(this,tile()));
			}
			if(net != null && !net.getPipes().contains(this))net.addPipe(this);
			getNetwork().refresh();
		}
	}

	public void save(NBTTagCompound p_145839_1_)
    {
		super.save(p_145839_1_);
        ((TankUT) getTank()).writeToNBT(p_145839_1_, "net");
    }

    public void load(NBTTagCompound p_145841_1_)
    {
    	super.load(p_145841_1_);
    	((TankUT) getTank()).readFromNBT(p_145841_1_, "net");
		if(net != null && net.fluid == null){
			if(getTank().getFluid() != null)net.fluid = getTank().getFluid().getFluid();
		}
    }

	@Override
	public TileEntity getTileEntity() {
		return tile();
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

	@Override
	public IFluidTank getTank() {
		if(buffer == null)buffer = new TankUT(100, tile());
		return buffer;
	}
}

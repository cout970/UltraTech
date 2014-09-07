package common.cout970.UltraTech.util.fluids;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidTank;

public interface IFluidTransport {

	TileEntity getTileEntity();

	void setNetwork(FluidNetwork fluidNetwork);

	void onNetworkUpdate();

	FluidNetwork getNetwork();
	
	IFluidTank getTank();
	
	boolean canConectOnSide(ForgeDirection d);

}

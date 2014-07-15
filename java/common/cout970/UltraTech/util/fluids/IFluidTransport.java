package common.cout970.UltraTech.util.fluids;

import net.minecraft.tileentity.TileEntity;

public interface IFluidTransport {

	TileEntity getTileEntity();

	void setNetwork(FluidNetwork fluidNetwork);

	void onNetworkUpdate();

	FluidNetwork getNetwork();

}

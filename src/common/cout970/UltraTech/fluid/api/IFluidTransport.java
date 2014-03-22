package common.cout970.UltraTech.fluid.api;

import net.minecraft.tileentity.TileEntity;

public interface IFluidTransport {

	TileEntity getTileEntity();

	void setNetwork(FluidNetwork fluidNetwork);

	void onNetworkUpdate();

	FluidNetwork getNetwork();

}

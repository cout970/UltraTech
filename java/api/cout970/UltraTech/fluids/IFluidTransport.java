package api.cout970.UltraTech.fluids;

import net.minecraft.tileentity.TileEntity;

public interface IFluidTransport {

	TileEntity getTileEntity();

	void setNetwork(FluidNetwork fluidNetwork);

	void onNetworkUpdate();

	FluidNetwork getNetwork();

}

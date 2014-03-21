package common.cout970.UltraTech.TileEntities.Tier2;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import ic2.api.energy.tile.IEnergySource;
import common.cout970.UltraTech.energy.api.Machine;

public class EnergyTransformer extends Machine implements IEnergySource{


	@Override
	public void drawEnergy(double arg0) {
		
	}

	@Override
	public double getOfferedEnergy() {
		return 1024;
	}

	@Override
	public boolean emitsEnergyTo(TileEntity receiver,
			ForgeDirection direction) {
		return true;
	}



}

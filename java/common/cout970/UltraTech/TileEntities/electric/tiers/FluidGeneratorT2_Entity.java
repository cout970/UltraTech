package common.cout970.UltraTech.TileEntities.electric.tiers;

import buildcraft.api.fuels.IronEngineFuel;
import buildcraft.api.fuels.IronEngineFuel.Fuel;

public class FluidGeneratorT2_Entity extends FluidGeneratorT1_Entity{

	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(progres <= 0){
			if(getTank().getFluidAmount() >= 10){
				Fuel f = IronEngineFuel.getFuelForFluid(getTank().getFluid().getFluid());
				if(f != null){
					double prod = pe.FTtoMev(f.powerPerCycle)*(f.totalBurningTime/100);
					if(spaceForCharge(prod) || getCapacity() < prod){
						progres = f.totalBurningTime/100;
						production = pe.FTtoMev(f.powerPerCycle);
						getTank().drain(10, true);
					}
				}

			}
			if(progres <= 0 && updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
				updated = false;
			}
		}
		if(progres > 0){
			if(!updated){
				worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
				updated = true;
			}
			if(progres >= 2){
				progres -= 2;
				addCharge(production);
			}else{
				addCharge(production*progres);
				progres = 0;
			}
		}
	}
}

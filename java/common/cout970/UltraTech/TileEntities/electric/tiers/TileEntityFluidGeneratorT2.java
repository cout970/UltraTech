package common.cout970.UltraTech.TileEntities.electric.tiers;

import common.cout970.UltraTech.util.power.PowerExchange;

import buildcraft.api.fuels.IronEngineFuel;
import buildcraft.api.fuels.IronEngineFuel.Fuel;

public class TileEntityFluidGeneratorT2 extends TileEntityFluidGeneratorT1{

	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(progres <= 0){
			int amount = Math.min(getTank().getFluidAmount(), 10);
			if(amount > 0){
				Fuel f = IronEngineFuel.getFuelForFluid(getTank().getFluid().getFluid());
				if(f != null){
					double ft = (f.powerPerCycle)*(f.totalBurningTime/1000)*amount;
					double prod = PowerExchange.FTtoQP((float) ft);
					if(spaceForCharge(prod) || getCapacity() < prod){
						progres = (f.totalBurningTime/1000)*amount;
						production = PowerExchange.FTtoQP(f.powerPerCycle);
						getTank().drain(amount, true);
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
			if(progres >= 4){
				progres -= 4;
				addCharge(production);
			}else{
				addCharge(production*progres);
				progres = 0;
			}
		}
	}
}

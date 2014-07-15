package common.cout970.UltraTech.TileEntities.electric;

import ultratech.api.power.IPowerConductor;
import ultratech.api.power.IStorageItem;
import ultratech.api.power.PowerInterface;

import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.MachineWithInventory;

public class ChargeStationEntity extends MachineWithInventory implements IPowerConductor{

	public ChargeStationEntity(){
		super(6,"Charge station",MachineData.Charge_Station);
	}
	
	public void updateEntity(){
		super.updateEntity();
		for(int u = 0; u < 3;u++){//charge
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IStorageItem){
					IStorageItem b = ((IStorageItem)inventory[u].getItem());
					int space = b.getMaxPower()-b.getPower(inventory[u]);
					int flow = (int) Math.min(MachineData.Charge_Station.use, space);
					int drain = (int) Math.min(flow, getCharge());
					if(drain >= 1){
						b.addPower(inventory[u], drain);
						this.removeCharge(drain);
					}
				}
			}
		}

		for(int u = 3; u < 6;u++){//discharge
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IStorageItem){
					IStorageItem b = ((IStorageItem)inventory[u].getItem());
					double space = getCapacity() - getCharge();
					int flow = (int) Math.min(MachineData.Charge_Station.use,space);
					int fill = Math.min(flow, b.getPower(inventory[u]));
					if(fill >= 1){
						addCharge(fill);
						b.removePower(inventory[u],fill);
					}
				}
			}
		}
	}

	@Override
	public PowerInterface getPower() {
		return cond ;
	}

}

package common.cout970.UltraTech.TileEntities.electric;

import net.minecraft.item.ItemStack;
import cofh.api.energy.IEnergyContainerItem;
import ultratech.api.power.PowerInterface;
import ultratech.api.power.interfaces.IPowerConductor;
import ultratech.api.power.interfaces.IStorageItem;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.MachineWithInventory;
import common.cout970.UltraTech.util.power.PowerExchange;

public class TileEntityChargeStation extends MachineWithInventory implements IPowerConductor{

	public TileEntityChargeStation(){
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
				}else if(inventory[u].getItem() instanceof IEnergyContainerItem){
					ItemStack s = inventory[u];
					IEnergyContainerItem b = ((IEnergyContainerItem)s.getItem());
					int space = (int) PowerExchange.RFtoQP(b.getMaxEnergyStored(s)-b.getEnergyStored(s));
					int flow = (int) Math.min(MachineData.Charge_Station.use, space);
					int drain = (int) Math.min(flow, getCharge());
					if(drain >= 1){
						b.receiveEnergy(s, PowerExchange.QPtoRF(drain), false);
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
				}else if(inventory[u].getItem() instanceof IEnergyContainerItem){
					IEnergyContainerItem b = ((IEnergyContainerItem)inventory[u].getItem());
					double space = getCapacity() - getCharge();
					int flow = (int) Math.min(MachineData.Charge_Station.use, space);
					int fill = (int) Math.min(flow, PowerExchange.RFtoQP(b.getEnergyStored(inventory[u])));
					if(fill >= 1){
						addCharge(fill);
						b.extractEnergy(inventory[u],PowerExchange.QPtoRF(fill),false);
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

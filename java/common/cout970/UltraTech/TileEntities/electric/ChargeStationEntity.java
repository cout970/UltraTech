package common.cout970.UltraTech.TileEntities.electric;

import common.cout970.UltraTech.lib.CostData;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.misc.MachineWithInventory;
import api.cout970.UltraTech.MeVpower.IPowerConductor;
import api.cout970.UltraTech.MeVpower.IStorageItem;
import api.cout970.UltraTech.MeVpower.PowerInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface;
import api.cout970.UltraTech.MeVpower.StorageInterface.MachineTipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ChargeStationEntity extends MachineWithInventory implements IPowerConductor{

	public ChargeStationEntity(){
		super(6,"Charge station",CostData.Charge_Station);
	}
	
	public void updateEntity(){
		super.updateEntity();
		for(int u = 0; u < 3;u++){//charge
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IStorageItem){
					IStorageItem b = ((IStorageItem)inventory[u].getItem());
					int space = b.getMaxPower()-b.getPower(inventory[u]);
					int flow = (int) Math.min(CostData.Charge_Station.use, space);
					int drain = (int) Math.min(flow, getEnergy());
					if(drain >= 1){
						b.addPower(inventory[u], drain);
						this.removeEnergy(drain);
					}
				}
			}
		}

		for(int u = 3; u < 6;u++){//discharge
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IStorageItem){
					IStorageItem b = ((IStorageItem)inventory[u].getItem());
					double space = maxEnergy() - getEnergy();
					int flow = (int) Math.min(CostData.Charge_Station.use,space);
					int fill = Math.min(flow, b.getPower(inventory[u]));
					if(fill >= 1){
						addEnergy(fill);
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

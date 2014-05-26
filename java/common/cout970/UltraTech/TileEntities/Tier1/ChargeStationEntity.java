package common.cout970.UltraTech.TileEntities.Tier1;

import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.misc.MachineWithInventory;
import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.IStorageItem;
import api.cout970.UltraTech.FTpower.PowerInterface;
import api.cout970.UltraTech.FTpower.StorageInterface;
import api.cout970.UltraTech.FTpower.StorageInterface.MachineTipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ChargeStationEntity extends MachineWithInventory implements IPowerConductor{

	public ChargeStationEntity(){
		super(6,"Charge station",5000,1,MachineTipe.Storage);
	}
	
	public void updateEntity(){
		super.updateEntity();
		for(int u = 0; u < 3;u++){//charge
			if(inventory[u] != null){
				if(inventory[u].getItem() instanceof IStorageItem){
					IStorageItem b = ((IStorageItem)inventory[u].getItem());
					double space = b.getMaxPower()-b.getPower(inventory[u]);
					double flow = Math.min(EnergyCosts.ChargeStationFlow, space);
					double drain = Math.min(flow, getEnergy());
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
					double flow = Math.min(EnergyCosts.ChargeStationFlow,space);
					double fill = Math.min(flow, b.getPower(inventory[u]));
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

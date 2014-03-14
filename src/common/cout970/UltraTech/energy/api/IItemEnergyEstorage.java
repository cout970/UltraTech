package common.cout970.UltraTech.energy.api;

import net.minecraft.item.ItemStack;

public interface IItemEnergyEstorage {

	int addEnergy(ItemStack i,int amount);

	void removeEnergy(ItemStack i,int amount);

	int getEnergy(ItemStack i);
	
	int getMaxEnergy();
}

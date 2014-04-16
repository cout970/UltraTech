package common.cout970.UltraTech.energy.api;

import net.minecraft.item.ItemStack;

public interface IItemEnergyEstorage {

	float addEnergy(ItemStack i,float chargestationflow);

	void removeEnergy(ItemStack i,float chargestationflow);

	float getEnergy(ItemStack i);
	
	float getMaxEnergy();
}

package common.cout970.UltraTech.misc;

import net.minecraft.item.ItemStack;

public interface IItemEnergyEstorage {

	int gainEnergy(ItemStack i,int amount);

	void loseEnergy(ItemStack i,int amount);

	int getEnergy(ItemStack i);
	
	int getMaxEnergy();
}

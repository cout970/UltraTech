package api.cout970.UltraTech.Vpower;

import net.minecraft.item.ItemStack;

public interface IStorageItem {

	double getPower(ItemStack it);

	void removePower(ItemStack it, double energy);

	double addPower(ItemStack stack, double energy);

	double getMaxPower();

}

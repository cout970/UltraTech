package api.cout970.UltraTech.Wpower;

import net.minecraft.item.ItemStack;

public interface IStorageItem {

	int getPower(ItemStack it);

	void removePower(ItemStack it, int energy);

	int addPower(ItemStack stack, int energy);

	int getMaxPower();

}

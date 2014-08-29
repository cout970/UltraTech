package ultratech.api.power.interfaces;

import net.minecraft.item.ItemStack;
/**
 * 
 * @author Cout970
 *
 */
public interface IStorageItem {

	int getPower(ItemStack it);

	void removePower(ItemStack it, int energy);

	int addPower(ItemStack stack, int energy);

	int getMaxPower();

}

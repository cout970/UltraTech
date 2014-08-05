package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IRecipeHandler {
	
	public boolean matches(IInventory inv);
	public ItemStack getResult();
	public ItemStack getInput(int slot);
}

package common.cout970.UltraTech.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IRecipeHandler {
	
	public void addRecipe(IRecipeHandler r);
	public boolean matches(IInventory inv);
	public ItemStack getCraftingResult(IInventory inv);
	public ItemStack getResult(ItemStack itemstack);
}

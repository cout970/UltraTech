package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ultratech.api.util.UT_Utils;

public class Laminator_Recipe implements IRecipeHandler{
	
	final ItemStack input;
	final ItemStack output;
	
	public Laminator_Recipe(ItemStack output,ItemStack input){
		this.input = input;
		this.output = output;
	}

	public boolean matches(IInventory inv) {
		if(inv == null)return false;
		if(inv.getStackInSlot(0) != null){
			if(UT_Utils.areEcuals(getInput(0), inv.getStackInSlot(0),true)){
				return true;
			}
		}
		return false;
	}
	
	public ItemStack getResult() {
		return output;
	}

	public ItemStack getInput(int slot) {
		return input;
	}
	
	public static ItemStack getCraftingResult(IInventory inv) {
		if(inv == null)return null;
		for(Laminator_Recipe a :RecipeRegistry.laminator){
			if(inv.getStackInSlot(0) != null){
				if(UT_Utils.areEcuals(a.getInput(0), inv.getStackInSlot(0),true)){
					return a.getResult();
				}
			}
		}
		return null;
	}
	
	public static boolean canCraft(IInventory lm){
		if(lm == null)return false;
		ItemStack result = getCraftingResult(lm);
		if(result == null)return false;
		if(lm.getStackInSlot(1) == null)return true;
		if(OreDictionary.itemMatches(lm.getStackInSlot(1), result,true) && lm.getInventoryStackLimit() >= lm.getStackInSlot(1).stackSize + result.stackSize)
			return true;
		return false;
	}

	public static boolean isIngredient(ItemStack itemstack) {
		if(itemstack == null)return false;
		for(Laminator_Recipe a : RecipeRegistry.laminator){
			if(UT_Utils.areEcuals(a.getInput(0), itemstack, true))
				return true;
		}
		return false;
	}
}

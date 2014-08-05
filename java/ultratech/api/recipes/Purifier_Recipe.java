package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT1_Entity;
import common.cout970.UltraTech.util.UT_Utils;

public class Purifier_Recipe implements IRecipeHandler{
	
	final ItemStack input;
	final ItemStack output;
	
	public Purifier_Recipe(ItemStack output,ItemStack input){
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
		for(Purifier_Recipe a :RecipeRegistry.purifier){
			if(inv.getStackInSlot(0) != null){
				if(UT_Utils.areEcuals(a.getInput(0), inv.getStackInSlot(0),true)){
					return a.getResult();
				}
			}
		}
		return null;
	}
	
	public static boolean canCraft(PurifierT1_Entity lm){
		if(lm == null)return false;
		ItemStack result = getCraftingResult(lm);
		if(result == null)return false;
		if(lm.getStackInSlot(1) == null)	
			return true;
		if(OreDictionary.itemMatches(lm.getStackInSlot(1), result,true) && lm.getInventoryStackLimit() >= lm.getStackInSlot(1).stackSize + result.stackSize)
			return true;
		return true;
	}

	public static boolean isIngredient(ItemStack itemstack) {
		if(itemstack == null)return false;
		for(Purifier_Recipe a : RecipeRegistry.purifier){
			if(UT_Utils.areEcuals(a.getInput(0), itemstack, true))
				return true;
		}
		return false;
	}
}

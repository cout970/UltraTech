package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import common.cout970.UltraTech.lib.UT_Utils;

public class Laminator_Recipe implements IRecipeHandler{
	
	public static List<Laminator_Recipe> recipes = new ArrayList<Laminator_Recipe>();
	public static Laminator_Recipe INSTANCE = new Laminator_Recipe(null, null);
	private final ItemStack input;
	private final ItemStack output;
	
	public Laminator_Recipe(ItemStack input,ItemStack output){
		this.input = input;
		this.output = output;
	}
	
	public static void addRecipe(Laminator_Recipe r){
		INSTANCE.addRecipe((IRecipeHandler)r);
	}

	public boolean matches(IInventory inv) {
		if(inv == null)return false;
		for(Laminator_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null){
				if(UT_Utils.areEcuals(a.getInput(), inv.getStackInSlot(0),true)){
					if(inv.getStackInSlot(1) == null)	
					return true;
					if(OreDictionary.itemMatches(inv.getStackInSlot(1), a.getOutput(),true) && inv.getInventoryStackLimit() >= inv.getStackInSlot(1).stackSize + a.getOutput().stackSize)
					return true;
				}
			}
		}
		return false;
	}


	public ItemStack getCraftingResult(IInventory inv) {
		if(inv == null)return null;
		for(Laminator_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null){
				if(UT_Utils.areEcuals(a.getInput(), inv.getStackInSlot(0),true)){
					return a.getOutput();
				}
			}
		}
		return null;
	}


	public ItemStack getOutput() {
		return output;
	}

	public ItemStack getInput() {
		return input;
	}

	public void addRecipe(IRecipeHandler b){
		Laminator_Recipe a = (Laminator_Recipe) b;
		if(a.getInput().stackSize == 0)a.input.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		
		if(!recipes.contains(a))
		recipes.add(a);
	}

	public ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(Laminator_Recipe a:recipes){
			if(UT_Utils.areEcuals(a.getInput(), itemstack, true))
				return a.getOutput();
		}
		return null;
	}
}

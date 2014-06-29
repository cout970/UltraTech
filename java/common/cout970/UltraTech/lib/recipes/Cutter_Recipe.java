package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import common.cout970.UltraTech.lib.UT_Utils;

public class Cutter_Recipe implements IRecipeHandler{
	
	public static final Cutter_Recipe INSTANCE = new Cutter_Recipe(null, null);
	public static List<Cutter_Recipe> recipes = new ArrayList<Cutter_Recipe>();
	protected final ItemStack input;
	protected final ItemStack output;
	
	public Cutter_Recipe(ItemStack input,ItemStack output){
		this.input = input;
		this.output = output;
	}
	
	public boolean matches(IInventory cuterEntity){
		if(cuterEntity == null)return false;
		for(Cutter_Recipe a:recipes){
			if(cuterEntity.getStackInSlot(0) != null){
				if(UT_Utils.areEcuals(a.getInput(), cuterEntity.getStackInSlot(0),true)){
					if(cuterEntity.getStackInSlot(1)==null){
						return true;
					}else{
						if(OreDictionary.itemMatches(cuterEntity.getStackInSlot(1), a.getOutput(), true))
							if(cuterEntity.getStackInSlot(1).stackSize + a.getOutput().stackSize <= cuterEntity.getInventoryStackLimit()){
								return true;
							}
					}
				}
			}
		}
		return false;
	}
	
	public static void addRecipe(Cutter_Recipe r){
		INSTANCE.addRecipe((IRecipeHandler)r);
	}
	
	public ItemStack getCraftingResult(IInventory cuterEntity){
		if(cuterEntity != null)if(cuterEntity.getStackInSlot(0) != null)
			for(Cutter_Recipe a:recipes){
			if(UT_Utils.areEcuals(a.getInput(), cuterEntity.getStackInSlot(0),true))
			return a.getOutput();
		}
		return null;
	}
	
	public ItemStack getInput() {
		return input;
	}

	public ItemStack getOutput() {
		return output;
	}
	
	public void addRecipe(IRecipeHandler b){
		Cutter_Recipe a = (Cutter_Recipe) b;
		if(a.getInput().stackSize == 0)a.input.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		if(!recipes.contains(a))
			recipes.add(a);
	}

	public ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(Cutter_Recipe a:recipes){
			if(UT_Utils.areEcuals(itemstack,a.getInput(),true))
				return a.getOutput();
		}
		return null;
	}
}

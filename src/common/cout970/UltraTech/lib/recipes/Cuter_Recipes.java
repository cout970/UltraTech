package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.machines.tileEntities.CutterEntity;

public class Cuter_Recipes{
	
	public static List<Cuter_Recipes> recipes = new ArrayList<Cuter_Recipes>();
	protected final ItemStack input;
	protected final ItemStack output;
	
	public Cuter_Recipes(ItemStack input,ItemStack output){
		this.input = input;
		this.output = output;
	}
	
	public static boolean matches(CutterEntity cuterEntity){
		if(cuterEntity == null)return false;
		for(Cuter_Recipes a:recipes){
			if(cuterEntity.getStackInSlot(0) != null)
			if(a.getInput().itemID == cuterEntity.getStackInSlot(0).itemID)if(cuterEntity.getStackInSlot(1)==null)return true;
			else if(cuterEntity.getStackInSlot(1).itemID == a.getOutput().itemID && cuterEntity.getStackInSlot(1).stackSize + a.getInput().stackSize <= cuterEntity.getInventoryStackLimit())return true;
		}
		return false;
	}
	
	public static ItemStack getCraftingResult(CutterEntity cuterEntity){
		if(cuterEntity != null)if(cuterEntity.getStackInSlot(0) != null)
			for(Cuter_Recipes a:recipes){
			if(a.getInput().itemID == cuterEntity.getStackInSlot(0).itemID)
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
	
	public static void addRecipe(Cuter_Recipes a){
		if(a.getInput().stackSize == 0)a.input.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		if(!recipes.contains(a))
			recipes.add(a);
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(Cuter_Recipes a:recipes){
			if(a.getInput().itemID == itemstack.itemID)
				return a.getOutput();
		}
		return null;
	}

}

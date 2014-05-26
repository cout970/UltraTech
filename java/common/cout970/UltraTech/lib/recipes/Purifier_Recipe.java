package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.Tier2.PurifierEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class Purifier_Recipe{
	
	public static List<Purifier_Recipe> recipes = new ArrayList<Purifier_Recipe>();
	
	protected final ItemStack input;
	protected final ItemStack output;
	
	
	public Purifier_Recipe(ItemStack input,ItemStack output){
		this.input = input;
		this.output = output;
	}
	
	public static boolean matches(PurifierEntity inv){
		if(inv == null)return false;
		for(Purifier_Recipe a:recipes){
			if(inv.getStackInSlot(0) != null)
			if(UT_Utils.areEcuals(a.getInput(), inv.getStackInSlot(0),true)){
				if(inv.getStackInSlot(1)==null)return true;
				else if(OreDictionary.itemMatches(inv.getStackInSlot(1), a.getOutput(), true))
					if(inv.getStackInSlot(1).stackSize + a.getInput().stackSize <= inv.getInventoryStackLimit())return true;
			}
		}
		return false;
	}
	
	public static ItemStack getCraftingResult(PurifierEntity inv){
		if(inv != null)if(inv.getStackInSlot(0) != null)
			for(Purifier_Recipe a:recipes){
			if(UT_Utils.areEcuals(a.getInput(), inv.getStackInSlot(0),true))
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
	
	public static void addRecipe(Purifier_Recipe a){
		if(a.getInput().stackSize == 0)a.input.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		if(!recipes.contains(a))
		recipes.add(a);
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(Purifier_Recipe a:recipes){
			if(UT_Utils.areEcuals(a.getInput(), itemstack,true))
				return a.getOutput();
		}
		return null;
	}
	
}

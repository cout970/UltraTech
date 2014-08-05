package ultratech.api.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.util.UT_Utils;

public class CVD_Recipe implements IRecipeHandler{

	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack output;
	
	public CVD_Recipe(ItemStack output,ItemStack input1,ItemStack input2){
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
	}

	public boolean matches(IInventory inv) {
		if(inv == null)return false;
		if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null){
			if(UT_Utils.areEcuals(getInput(0), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(getInput(1), inv.getStackInSlot(1), true)){
				return true;
			}else if(UT_Utils.areEcuals(getInput(1), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(getInput(0), inv.getStackInSlot(1), true)){
				return true;
			}
		}
		return false;
	}


	public static ItemStack getCraftingResult(IInventory inv) {
		if(inv == null)return null;
		for(CVD_Recipe a : RecipeRegistry.cvd){
			if(a.matches(inv)){
				return a.getResult();	
			}
		}
		return null;
	}

	@Override
	public ItemStack getResult() {
		return output;
	}

	@Override
	public ItemStack getInput(int slot) {
		if(slot == 0)return input1;
		if(slot == 1)return input2;
		return null;
	}

	public static boolean isIngredient(ItemStack i) {
		for(CVD_Recipe a : RecipeRegistry.cvd){
			if(UT_Utils.areEcuals(a.getInput(0), i,true) || UT_Utils.areEcuals(a.getInput(1), i, true)){
				return true;
			}
		}
		return false;
	}

	public static boolean canCraft(ChemicalVaporDesintegrationT1_Entity cvd){
		if(cvd == null)return false;
		ItemStack result = getCraftingResult(cvd);
		if(result == null)return false;
		if(cvd.getStackInSlot(2) == null || (OreDictionary.itemMatches(cvd.getStackInSlot(2),result,true) && (cvd.getStackInSlot(2).stackSize+result.stackSize) <= cvd.getInventoryStackLimit()))return true;
		return true;
	}
}

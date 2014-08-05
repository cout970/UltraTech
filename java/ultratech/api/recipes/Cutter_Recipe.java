package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;
import common.cout970.UltraTech.util.UT_Utils;

public class Cutter_Recipe implements IRecipeHandler{
	
	public final ItemStack input;
	public final ItemStack output;
	public final ItemStack output2;
	public int prob;
	
	public Cutter_Recipe(ItemStack input,ItemStack output,ItemStack output2,int prob){
		this.input = input;
		this.output = output;
		this.output2 = output2;
		this.prob = prob;
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
	
	public static Cutter_Recipe getCraftingResult(IInventory inv) {
		if(inv == null)return null;
		for(Cutter_Recipe a : RecipeRegistry.cutter){
			if(a.matches(inv)){
				return a;	
			}
		}
		return null;
	}
	
	public static boolean isIngredient(ItemStack i) {
		for(Cutter_Recipe a : RecipeRegistry.cutter){
			if(UT_Utils.areEcuals(a.getInput(0), i,true)){
				return true;
			}
		}
		return false;
	}

	@Override
	public ItemStack getResult() {
		return output;
	}
	
	public ItemStack getResult(int slot) {
		if(slot == 1)return output2;
		return output;
	}

	@Override
	public ItemStack getInput(int slot) {
		return input;
	}
	
	public static boolean canCraft(CutterT1_Entity cvd){
		if(cvd == null)return false;
		Cutter_Recipe a = getCraftingResult(cvd);
		if(a == null)return false;
		ItemStack[] result = {a.getResult(0),a.getResult(1)};
		boolean valid = true;
		if(cvd.getStackInSlot(1) != null){
			if(!(OreDictionary.itemMatches(cvd.getStackInSlot(1),result[0],true))){
				valid = false;
			}else{
				if(cvd.getStackInSlot(1).stackSize+result[0].stackSize > cvd.getInventoryStackLimit()){
					valid = false;
				}
			}
		}
		if(cvd.getStackInSlot(2) != null && result[1] != null){
			if(!(OreDictionary.itemMatches(cvd.getStackInSlot(2),result[1],true))){
				valid = false;
			}else{
				if(cvd.getStackInSlot(2).stackSize+result[1].stackSize > cvd.getInventoryStackLimit()){
					valid = false;
				}
			}
		}
		return valid;
	}
}

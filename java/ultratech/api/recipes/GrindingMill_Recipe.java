package ultratech.api.recipes;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ultratech.api.util.UT_Utils;

public class GrindingMill_Recipe implements IRecipeHandler{
	
	public final ItemStack input;
	public final ItemStack output;
	public final ItemStack output2;
	public final ItemStack output3;
	public int prob2;
	public int prob3;
	
	public GrindingMill_Recipe(ItemStack input,ItemStack output,ItemStack output2,int prob2,ItemStack output3,int prob3){
		this.input = input;
		this.output = output;
		this.output2 = output2;
		this.output3 = output3;
		this.prob2 = prob2;
		this.prob3 = prob3;
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
	
	public static GrindingMill_Recipe getCraftingResult(IInventory inv) {
		if(inv == null)return null;
		for(GrindingMill_Recipe a : RecipeRegistry.grinder){
			if(a.matches(inv)){
				return a;	
			}
		}
		return null;
	}
	
	public static boolean isIngredient(ItemStack i) {
		for(GrindingMill_Recipe a : RecipeRegistry.grinder){
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
		if(slot == 2)return output3;
		if(slot == 1)return output2;
		return output;
	}

	@Override
	public ItemStack getInput(int slot) {
		return input;
	}
	
	public static boolean canCraft(IInventory cvd){
		if(cvd == null)return false;
		GrindingMill_Recipe a = getCraftingResult(cvd);
		if(a == null)return false;
		ItemStack[] result = {a.getResult(0),a.getResult(1),a.getResult(2)};
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
		if(cvd.getStackInSlot(3) != null && result[2] != null){
			if(!(OreDictionary.itemMatches(cvd.getStackInSlot(3),result[2],true))){
				valid = false;
			}else{
				if(cvd.getStackInSlot(3).stackSize+result[2].stackSize > cvd.getInventoryStackLimit()){
					valid = false;
				}
			}
		}
		return valid;
	}
}
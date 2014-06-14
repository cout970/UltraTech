package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.electric.CVD_Entity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CVD_Recipe{

	public static List<CVD_Recipe> recipes = new ArrayList<CVD_Recipe>();
	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack output;
	
	public CVD_Recipe(ItemStack input1,ItemStack input2,ItemStack output){
		this.input1 = input1;
		this.input2 = input2;
		this.output = output;
	}

	public static boolean matches(CVD_Entity inv) {
		if(inv == null)return false;
		for(CVD_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null){
				if(UT_Utils.areEcuals(a.getImput1(), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(a.getImput2(), inv.getStackInSlot(1), true)){
					if(inv.getStackInSlot(2) == null)	
					return true;
					if(OreDictionary.itemMatches(inv.getStackInSlot(2), a.getOutput(),true) && inv.getInventoryStackLimit() >= inv.getStackInSlot(2).stackSize + a.getOutput().stackSize)
					return true;
				}
			}
		}
		return false;
	}


	public static ItemStack getCraftingResult(CVD_Entity inv) {
		if(inv == null)return null;
		for(CVD_Recipe a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null){
				if(UT_Utils.areEcuals(a.getImput1(), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(a.getImput2(), inv.getStackInSlot(1), true)){
					return a.getOutput();
				}
			}
		}
		return null;
	}


	public ItemStack getOutput() {
		return output;
	}

	public ItemStack getImput2() {
		return input2;
	}

	public ItemStack getImput1() {
		return input1;
	}

	public static void addRecipe(CVD_Recipe a){
		if(a.getImput1().stackSize == 0)a.input1.stackSize = 1;
		if(a.getImput2().stackSize == 0)a.input2.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		
		if(!recipes.contains(a))
		recipes.add(a);
		
		CVD_Recipe b = new CVD_Recipe(a.getImput2(), a.getImput1(), a.getOutput());
		boolean flag = true;
		for(CVD_Recipe c :recipes){
			if(isEqual(c,b))flag = false;
		}
		if(flag)recipes.add(b);
	}

	private static boolean isEqual(CVD_Recipe c, CVD_Recipe b) {
		if(UT_Utils.areEcuals(c.getImput1(), b.getImput1(),true)){
			if(UT_Utils.areEcuals(c.getImput2(), b.getImput2(),true)){
				if(UT_Utils.areEcuals(c.getOutput(), b.getOutput(),true))
					return true;
			}
		}
		return false;
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(CVD_Recipe a:recipes){
			if(UT_Utils.areEcuals(a.getImput1(), itemstack, true) || UT_Utils.areEcuals(a.getImput2(), itemstack, true))
				return a.getOutput();
		}
		return null;
	}

}

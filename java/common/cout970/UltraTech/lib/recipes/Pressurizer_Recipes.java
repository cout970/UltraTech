package common.cout970.UltraTech.lib.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import common.cout970.UltraTech.TileEntities.electric.PresuricerEntity;
import common.cout970.UltraTech.lib.UT_Utils;

public class Pressurizer_Recipes {

	public static List<Pressurizer_Recipes> recipes = new ArrayList<Pressurizer_Recipes>();
	private final ItemStack input1;
	private final ItemStack input2;
	private final ItemStack input3;
	private final ItemStack output;
	
	public Pressurizer_Recipes(ItemStack input1,ItemStack input2,ItemStack input3,ItemStack output){
		this.input1 = input1;
		this.input2 = input2;
		this.input3 = input3;
		this.output = output;
	}

	public static boolean matches(PresuricerEntity inv) {
		if(inv == null)return false;
		for(Pressurizer_Recipes a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null && inv.getStackInSlot(2) != null){
				if(UT_Utils.areEcuals(a.getImput1(), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(a.getImput2(), inv.getStackInSlot(1), true) && UT_Utils.areEcuals(a.getImput3(), inv.getStackInSlot(2), true)){
					
					if(inv.getStackInSlot(3) == null)return true;
					if(UT_Utils.areEcuals(inv.getStackInSlot(3), a.getOutput(),true) && inv.getInventoryStackLimit() >= inv.getStackInSlot(3).stackSize + a.getOutput().stackSize)
					return true;
				}
			}
		}
		return false;
	}


	public static ItemStack getCraftingResult(PresuricerEntity inv) {
		if(inv == null)return null;
		for(Pressurizer_Recipes a :recipes){
			if(inv.getStackInSlot(0) != null && inv.getStackInSlot(1) != null && inv.getStackInSlot(2) != null){
				if(UT_Utils.areEcuals(a.getImput1(), inv.getStackInSlot(0),true) && UT_Utils.areEcuals(a.getImput2(), inv.getStackInSlot(1), true) && UT_Utils.areEcuals(a.getImput3(), inv.getStackInSlot(2), true)){
					return a.getOutput();
				}
			}
		}
		return null;
	}


	public ItemStack getOutput() {
		return output;
	}
	
	public ItemStack getImput3() {
		return input3;
	}

	public ItemStack getImput2() {
		return input2;
	}

	public ItemStack getImput1() {
		return input1;
	}

	public static void addRecipe(Pressurizer_Recipes a){
		if(a.getImput1().stackSize == 0)a.input1.stackSize = 1;
		if(a.getImput2().stackSize == 0)a.input2.stackSize = 1;
		if(a.getImput3().stackSize == 0)a.input3.stackSize = 1;
		if(a.getOutput().stackSize == 0)a.output.stackSize = 1;
		
		if(!recipes.contains(a))
		recipes.add(a);
	}

	public static ItemStack getResult(ItemStack itemstack) {
		if(itemstack == null)return null;
		for(Pressurizer_Recipes a:recipes){
			if(UT_Utils.areEcuals(a.getImput1(), itemstack,true) || UT_Utils.areEcuals(a.getImput2(), itemstack,true) || UT_Utils.areEcuals(a.getImput3(), itemstack,true))
				return a.getOutput();
		}
		return null;
	}
}

package ultratech.api.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class RecipeRegistry{

	public static List<CVD_Recipe> cvd = new ArrayList<CVD_Recipe>();
	public static List<Laminator_Recipe> laminator = new ArrayList<Laminator_Recipe>();
	public static List<Purifier_Recipe> purifier = new ArrayList<Purifier_Recipe>();
	public static List<Cutter_Recipe> cutter = new ArrayList<Cutter_Recipe>();
	public static List<Fermenter_Recipe> fermenter = new ArrayList<Fermenter_Recipe>();
	public static Map<String,String> boiler = new HashMap<String,String>();
	public static List<Refinery_Recipe> refinery = new ArrayList<Refinery_Recipe>();
	public static List<Chemical_Recipe> chemical = new ArrayList<Chemical_Recipe>();
	public static List<GrindingMill_Recipe> grinder = new ArrayList<GrindingMill_Recipe>();

	
	public static boolean addRecipeCVD(ItemStack output, ItemStack in1, ItemStack in2){
		if(output == null || in1 == null || in2 == null)return false;
		CVD_Recipe a = new CVD_Recipe(output, in1, in2);
		if(a.getInput(0).stackSize == 0)a.getInput(0).stackSize = 1;
		if(a.getInput(1).stackSize == 0)a.getInput(1).stackSize = 1;
		if(a.getResult().stackSize == 0)a.getResult().stackSize = 1;
		cvd.add(new CVD_Recipe(output, in1, in2));
		return true;
	}

	public static boolean addRecipeLaminator(ItemStack output, ItemStack input){
		if(output == null || input == null)return false;
		Laminator_Recipe a = new Laminator_Recipe(output, input);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult().stackSize == 0)a.output.stackSize = 1;
		if(!laminator.contains(a)){
			laminator.add(a);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipePurifier(ItemStack output, ItemStack input){
		if(output == null || input == null)return false;
		Purifier_Recipe a = new Purifier_Recipe(output, input);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult().stackSize == 0)a.output.stackSize = 1;
		if(!purifier.contains(a)){
			purifier.add(a);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipeCutter(ItemStack input, ItemStack output){
		if(output == null || input == null)return false;
		Cutter_Recipe a = new Cutter_Recipe(input, output, null, 100);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult().stackSize == 0)a.output.stackSize = 1;
		if(!RecipeRegistry.cutter.contains(a)){
			RecipeRegistry.cutter.add(a);
			return true;
		}
		return false;
	}

	public static boolean addRecipeCutter(ItemStack input, ItemStack output, ItemStack output2, int prob){
		if(output == null || input == null)return false;
		Cutter_Recipe a = new Cutter_Recipe(input, output, output2, prob);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult(0).stackSize == 0)a.getResult(0).stackSize = 1;
		if(a.getResult(1) != null && a.getResult(1).stackSize == 0)a.getResult(1).stackSize = 1;
		if(!RecipeRegistry.cutter.contains(a)){
			RecipeRegistry.cutter.add(a);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipeGrinderMill(ItemStack input, ItemStack output, ItemStack output2, int prob2, ItemStack output3, int prob3){
		if(output == null || input == null)return false;
		GrindingMill_Recipe a = new GrindingMill_Recipe(input, output, output2, prob2, output3, prob3);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult(0).stackSize == 0)a.getResult(0).stackSize = 1;
		if(a.getResult(1) != null && a.getResult(1).stackSize == 0)a.getResult(1).stackSize = 1;
		if(a.getResult(2) != null && a.getResult(2).stackSize == 0)a.getResult(2).stackSize = 1;
		if(!RecipeRegistry.grinder.contains(a)){
			RecipeRegistry.grinder.add(a);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipeFermenter(ItemStack input, int amount){
		if(input != null && amount > 0){
			Fermenter_Recipe a = new Fermenter_Recipe(input, amount);
			fermenter.add(a);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipeBoiler(FluidStack input, FluidStack output){
		if(input == null || output == null)return false;
		if(!boiler.containsKey(input.getFluid().getName())){
			boiler.put(input.getFluid().getName(), output.getFluid().getName());
			return true;
		}
		return false;
	}

	public static boolean addRecipeBoiler(String input, String output) {
		if(!boiler.containsKey(input)){
			boiler.put(input, output);
			return true;
		}
		return false;
	}

	public static boolean addRecipeRefinery(FluidStack input,Fluid out1,float amount1,Fluid out2,float amount2,Fluid out3,float amount3){
		if(input == null || out1 == null || out2 == null || out3 == null)return false;
		Refinery_Recipe d = new Refinery_Recipe(input,out1,amount1,out2,amount2,out3,amount3);
		if(!refinery.contains(d)){
			refinery.add(d);
			return true;
		}
		return false;
	}
	
	public static boolean addRecipeChemical(FluidStack f,ItemStack i1, ItemStack i2, ItemStack i3){
		if(f != null && i1 != null && i2 != null && i3 != null){
			Chemical_Recipe a = new Chemical_Recipe(f, i1, i2, i3);
			if(a.out_1 != null && a.out_1.stackSize == 0)a.out_1.stackSize = 1;
			if(a.out_2 != null && a.out_2.stackSize == 0)a.out_2.stackSize = 1;
			if(a.out_3 != null && a.out_3.stackSize == 0)a.out_3.stackSize = 1;
			if(!chemical.contains(a)){
				chemical.add(a);
				return true;
			}
		}
		return false;
	}

}

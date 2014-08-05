package ultratech.api.recipes;

import static common.cout970.UltraTech.managers.BlockManager.Ores;
import static common.cout970.UltraTech.managers.BlockManager.stoneblockblack;
import static common.cout970.UltraTech.managers.BlockManager.stoneblockwhite;
import static common.cout970.UltraTech.managers.ItemManager.ItemName;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

public class RecipeRegistry{

	public static List<CVD_Recipe> cvd = new ArrayList<CVD_Recipe>();
	public static List<Laminator_Recipe> laminator = new ArrayList<Laminator_Recipe>();
	public static List<Purifier_Recipe> purifier = new ArrayList<Purifier_Recipe>();
	public static List<Cutter_Recipe> cutter = new ArrayList<Cutter_Recipe>();
	
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
		if(output == null && input == null)return false;
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
		if(output == null && input == null && output2 == null && prob > 0)return false;
		Cutter_Recipe a = new Cutter_Recipe(input, output, output2, prob);
		if(a.getInput(0).stackSize == 0)a.input.stackSize = 1;
		if(a.getResult(0).stackSize == 0)a.getResult(0).stackSize = 1;
		if(a.getResult(1).stackSize == 0)a.getResult(1).stackSize = 1;
		if(!RecipeRegistry.cutter.contains(a)){
			RecipeRegistry.cutter.add(a);
			return true;
		}
		return false;
	}
	
	public static void initAssembly(){
		//assembly laminator
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',ItemName.get("Coil"),'r', new ItemStack(ItemManager.ItemName.get("Plate"),1,4),'i',new ItemStack(ItemName.get("Plate"),1,6)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Battery"),1), new Object[]{"hah","hah","hah",'h',new ItemStack(ItemName.get("Plate"),1,0),'a',new ItemStack(ItemName.get("Plate"),1,5)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(SolarPanel_T1,1), new Object[]{"hhh","aaa","bbb",'h',new ItemStack(ItemName.get("Plate"),1,10),'a',new ItemStack(ItemName.get("Plate"),1,5),'b', new ItemStack(ItemManager.ItemName.get("Plate"),1,4)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("AdvCircuit"),1), new Object[]{"hph","hah","hph",'h',new ItemStack(MicroRegistry.PlaneCable,1),'a',new ItemStack(ItemName.get("Plate"),1,10),'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,8)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Fan"),1), new Object[]{"opo","ppp","opo",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,9)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Coil"),2), new Object[]{"rrr","ppp","rrr",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,6),'r',new ItemStack(ItemManager.ItemName.get("Plate"),1,11)}));
		//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HeatCoil"),1), new Object[]{"pp ","pp ","   ",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,5)}));
	}

	public static void initCutter(){
		//cuter laminator
		addRecipeCutter(new ItemStack(Items.reeds), new ItemStack(Items.sugar,2));
		addRecipeCutter(new ItemStack(Blocks.wool), new ItemStack(Items.string,4));
		addRecipeCutter(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sand,1));
		addRecipeCutter(new ItemStack(Items.bone), new ItemStack(Items.dye,6,15));
		addRecipeCutter(new ItemStack(Items.diamond), new ItemStack(ItemName.get("UnorganicPlate"),8,0),new ItemStack(ItemName.get("Dust"),1,8),20);
		addRecipeCutter(new ItemStack(Items.cookie), new ItemStack(ItemName.get("RawMeal"), 1));
		addRecipeCutter(new ItemStack(Items.rotten_flesh), new ItemStack(ItemName.get("RawMeal"), 2));
		addRecipeCutter(new ItemStack(Items.porkchop), new ItemStack(ItemName.get("RawMeal"), 2));
		addRecipeCutter(new ItemStack(Items.beef), new ItemStack(ItemName.get("RawMeal"), 2));
		addRecipeCutter(new ItemStack(Items.potato), new ItemStack(ItemName.get("RawMeal"), 1));
		addRecipeCutter(new ItemStack(Items.chicken), new ItemStack(ItemName.get("RawMeal"), 1));
		addRecipeCutter(new ItemStack(Items.spider_eye), new ItemStack(ItemName.get("RawMeal"), 1));
		addRecipeCutter(new ItemStack(Items.fish), new ItemStack(ItemName.get("RawMeal"), 1));
		addRecipeCutter(new ItemStack(Items.carrot), new ItemStack(ItemName.get("RawMeal"), 2));
		addRecipeCutter(new ItemStack(ItemName.get("RawSilicon")),new ItemStack(ItemName.get("UnorganicPlate"),1,2));
		for(int r = 1;r <6;r++)//ores to chunk
			addRecipeCutter(new ItemStack(Ores,1,r),new ItemStack(ItemName.get("Chunk"),3,r-1));
		addRecipeCutter(new ItemStack(Ores,1,0),new ItemStack(ItemName.get("Chunk"),1,7));
		addRecipeCutter(new ItemStack(Blocks.iron_ore,1),new ItemStack(ItemName.get("Chunk"),3,5));
		addRecipeCutter(new ItemStack(Blocks.gold_ore,1),new ItemStack(ItemName.get("Chunk"),3,6));
		addRecipeCutter(new ItemStack(ItemName.get("MetalPlate"),1,4),new ItemStack(ItemName.get("SilverCable"),3));
		addRecipeCutter(new ItemStack(Blocks.coal_ore,1),new ItemStack(Items.coal,3));
		addRecipeCutter(new ItemStack(Blocks.lapis_ore,1),new ItemStack(Items.dye,3,4));
		addRecipeCutter(new ItemStack(Blocks.redstone_ore,1),new ItemStack(Items.redstone,8));
		addRecipeCutter(new ItemStack(Blocks.emerald_ore,1),new ItemStack(Items.emerald,3));
		addRecipeCutter(new ItemStack(Blocks.quartz_ore,1),new ItemStack(Items.quartz,3));
		addRecipeCutter(new ItemStack(Blocks.diamond_ore,1),new ItemStack(Items.diamond,3));
		addRecipeCutter(new ItemStack(Blocks.obsidian,1),new ItemStack(ItemName.get("Dust"),1,9));
	}

	public static void initCVD(){
		//cvd laminator
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,1), 	new ItemStack(Items.coal), 					new ItemStack(Items.coal));//grafeno
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,1), 	new ItemStack(Items.coal,1,1), 				new ItemStack(Items.coal,1,1));//grafeno
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),1,3), 	new ItemStack(Items.iron_ingot), 			new ItemStack(Items.redstone));//restone plate
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),1,4), 	new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Radionite"),1));//radionite plate
		addRecipeCVD(new ItemStack(ItemName.get("Dust"),1,8), 				new ItemStack(ItemName.get("GrafenoPlate")),new ItemStack(ItemName.get("GrafenoPlate")));//diamond dust
		addRecipeCVD(new ItemStack(stoneblockwhite,1,0),					new ItemStack(ItemName.get("Dust"),1,0), 	new ItemStack(stoneblockblack,1,0));//white stone
		addRecipeCVD(new ItemStack(ItemName.get("Rubber_bulcanized"),1,0), 	new ItemStack(ItemName.get("Sulfur"),1,0), 	new ItemStack(ItemName.get("Rubber"),1,0));//rubber
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,5),		new ItemStack(Blocks.obsidian,1,0), 		new ItemStack(Blocks.obsidian,1,0));//obsidian plate
		addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,5),		new ItemStack(ItemName.get("Dust"),1,9),	new ItemStack(ItemName.get("Dust"),1,9));//obsidian plate
	}

	public static void initLaminator() {
	
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,6), new ItemStack(ItemName.get("Dust"),1,7));//gold plate
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,5), new ItemStack(ItemName.get("Dust"),1,6));//iron plate
		for(int r = 0;r < 5;r++)//dust to plates
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,r), new ItemStack(ItemName.get("Dust"),1,r));
		for(int r = 0;r < 5;r++)//ingot to plates
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,r), new ItemStack(ItemName.get("Ingot"),1,r));
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,6), new ItemStack(Items.gold_ingot));
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,5), new ItemStack(Items.iron_ingot));
		addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,7), new ItemStack(ItemName.get("Dust"),1,5));
	}

	public static void initPurifier(){
		//purifier laminator
		addRecipePurifier(new ItemStack(ItemName.get("RawSilicon"),1), new ItemStack(Blocks.sand));
		addRecipePurifier(new ItemStack(Items.flint,1), new ItemStack(Blocks.gravel));
		addRecipePurifier(new ItemStack(Blocks.sand,4), new ItemStack(Blocks.sandstone));
		addRecipePurifier(new ItemStack(stoneblockblack,1,0), new ItemStack(Blocks.stone));
		addRecipePurifier(new ItemStack(stoneblockblack,1,0), new ItemStack(Blocks.cobblestone));
		addRecipePurifier(new ItemStack(stoneblockwhite,1,0), new ItemStack(Items.quartz));
		for(int r = 0;r <5;r++)//chunk to dust
		addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,r), new ItemStack(ItemName.get("Chunk"),1,r));
		addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,6), new ItemStack(ItemName.get("Chunk"),1,5));
		addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,7), new ItemStack(ItemName.get("Chunk"),1,6));
		addRecipePurifier(new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Chunk"),1,7));
	}

	public static void initRecipes(){
		
		RecipeRegistry.initCVD();//
		RecipeRegistry.initPurifier();//
		RecipeRegistry.initCutter();
		RecipeRegistry.initAssembly();
		RecipeRegistry.initLaminator();//

		//fermenter laminator
		Fermenter_Recipes.recipes.put(Items.sugar, 75);
		Fermenter_Recipes.recipes.put(Items.apple, 25);
		Fermenter_Recipes.recipes.put(Items.wheat, 100);

		//liquid laminator
		Boiler_Recipes.recipes.put("juice", "gas_ethanol");
		Boiler_Recipes.recipes.put("oil", "gas_oil");
		Boiler_Recipes.recipes.put("water", "steam");

		Cooling_Recipes.recipes.add(new Cooling_Recipes(FluidRegistry.getFluidStack("gas_oil", 100), FluidRegistry.getFluidStack("plastic", 40), FluidRegistry.getFluidStack("fuel", 60), FluidRegistry.getFluidStack("gasoline", 10)));
		Cooling_Recipes.recipes.add(new Cooling_Recipes(FluidRegistry.getFluidStack("gas_ethanol", 100), FluidRegistry.getFluidStack("bioethanol", 80), FluidRegistry.getFluidStack("water", 9), FluidRegistry.getFluidStack("water", 1)));

		Chemical_Recipe.INSTANCE.addRecipe(new Chemical_Recipe(FluidRegistry.getFluidStack("plastic", 1000), new ItemStack(ItemName.get("Sulfur")), new ItemStack(ItemName.get("Rubber")), new ItemStack(ItemName.get("Plastic"))));

	}

}

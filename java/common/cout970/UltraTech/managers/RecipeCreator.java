package common.cout970.UltraTech.managers;

import static common.cout970.UltraTech.managers.BlockManager.Ores;
import static common.cout970.UltraTech.managers.BlockManager.stoneblockblack;
import static common.cout970.UltraTech.managers.BlockManager.stoneblockwhite;
import static common.cout970.UltraTech.managers.ItemManager.ItemName;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import ultratech.api.recipes.Chemical_Recipe;
import ultratech.api.recipes.Refinery_Recipe;
import ultratech.api.recipes.Fermenter_Recipe;
import ultratech.api.recipes.RecipeRegistry;

public class RecipeCreator {

	public static void initRecipes(){
		
		RecipeCreator.initCVD();//
		RecipeCreator.initPurifier();//
		RecipeCreator.initCutter();
		RecipeCreator.initAssembly();
		RecipeCreator.initLaminator();//
	
		//fermenter 
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.sugar), 36);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.apple), 36);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.wheat), 72);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.carrot), 72);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.potato), 72);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.melon), 36);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Items.spider_eye), 108);
		RecipeRegistry.addRecipeFermenter(new ItemStack(Blocks.pumpkin), 90);
		
		//liquid 
		RecipeRegistry.addRecipeBoiler("juice", "gas_ethanol");
		RecipeRegistry.addRecipeBoiler("oil", "gas_oil");
		RecipeRegistry.addRecipeBoiler("water", "steam");
	
		RecipeRegistry.addRecipeRefinery(FluidRegistry.getFluidStack("gas_oil", 100), FluidRegistry.getFluid("plastic"), 2, FluidRegistry.getFluid("fuel"), 8, FluidRegistry.getFluid("gasoline"), 1);
		RecipeRegistry.addRecipeRefinery(FluidRegistry.getFluidStack("gas_ethanol", 100), FluidRegistry.getFluid("bioethanol"), 2, FluidRegistry.getFluid("water"), 4f, FluidRegistry.getFluid("water"), 4f);
	
		RecipeRegistry.addRecipeChemical(FluidRegistry.getFluidStack("plastic", 1000), new ItemStack(ItemName.get("Sulfur"),1), new ItemStack(ItemName.get("Rubber"),2), new ItemStack(ItemName.get("Plastic"),1));
	
	}

	public static void initPurifier(){
		//purifier laminator
		RecipeRegistry.addRecipePurifier(new ItemStack(ItemName.get("RawSilicon"),1), new ItemStack(Blocks.quartz_block));
		RecipeRegistry.addRecipePurifier(new ItemStack(Items.flint,1), new ItemStack(Blocks.gravel));
		RecipeRegistry.addRecipePurifier(new ItemStack(Blocks.sand,4), new ItemStack(Blocks.sandstone));
		RecipeRegistry.addRecipePurifier(new ItemStack(stoneblockblack,1,0), new ItemStack(Blocks.stone));
		RecipeRegistry.addRecipePurifier(new ItemStack(stoneblockblack,1,0), new ItemStack(Blocks.cobblestone));
		RecipeRegistry.addRecipePurifier(new ItemStack(stoneblockwhite,1,0), new ItemStack(Items.quartz));
		for(int r = 0;r <5;r++)//chunk to dust
		RecipeRegistry.addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,r), new ItemStack(ItemName.get("Chunk"),1,r));
		RecipeRegistry.addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,6), new ItemStack(ItemName.get("Chunk"),1,5));
		RecipeRegistry.addRecipePurifier(new ItemStack(ItemName.get("Dust"),1,7), new ItemStack(ItemName.get("Chunk"),1,6));
		RecipeRegistry.addRecipePurifier(new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Chunk"),1,7));
	}

	public static void initLaminator() {
	
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,6), new ItemStack(ItemName.get("Dust"),1,7));//gold plate
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,5), new ItemStack(ItemName.get("Dust"),1,6));//iron plate
		for(int r = 0;r < 5;r++)//dust to plates
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,r), new ItemStack(ItemName.get("Dust"),1,r));
		for(int r = 0;r < 5;r++)//ingot to plates
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,r), new ItemStack(ItemName.get("Ingot"),1,r));
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,6), new ItemStack(Items.gold_ingot));
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,5), new ItemStack(Items.iron_ingot));
		RecipeRegistry.addRecipeLaminator(new ItemStack(ItemName.get("MetalPlate"),1,7), new ItemStack(ItemName.get("Dust"),1,5));
	}

	public static void initCVD(){
		//cvd laminator
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,1), 	new ItemStack(Items.coal), 					new ItemStack(Items.coal));//grafeno
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,1), 	new ItemStack(Items.coal,1,1), 				new ItemStack(Items.coal,1,1));//grafeno
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),1,3), 	new ItemStack(Items.iron_ingot), 			new ItemStack(Items.redstone));//restone plate
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),1,4), 	new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Radionite"),1));//radionite plate
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("Dust"),1,8), 				new ItemStack(ItemName.get("GrafenoPlate")),new ItemStack(ItemName.get("GrafenoPlate")));//diamond dust
		RecipeRegistry.addRecipeCVD(new ItemStack(stoneblockwhite,1,0),					new ItemStack(ItemName.get("Dust"),1,0), 	new ItemStack(stoneblockblack,1,0));//white stone
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,5),		new ItemStack(Blocks.obsidian,1,0), 		new ItemStack(Blocks.obsidian,1,0));//obsidian plate
		RecipeRegistry.addRecipeCVD(new ItemStack(ItemName.get("UnorganicPlate"),2,5),		new ItemStack(ItemName.get("Dust"),1,9),	new ItemStack(ItemName.get("Dust"),1,9));//obsidian plate
	}

	public static void initCutter(){
		//cuter
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.reeds), new ItemStack(Items.sugar,2));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.wool), new ItemStack(Items.string,4));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sand,1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.bone), new ItemStack(Items.dye,6,15));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.diamond), new ItemStack(ItemName.get("UnorganicPlate"),8,0),new ItemStack(ItemName.get("Dust"),1,8),20);
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.cookie), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.rotten_flesh), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.porkchop), new ItemStack(ItemName.get("RawMeal"), 2));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.beef), new ItemStack(ItemName.get("RawMeal"), 2));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.potato), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.chicken), new ItemStack(ItemName.get("RawMeal"), 2));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.spider_eye), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.fish), new ItemStack(ItemName.get("RawMeal"), 2));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.carrot), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.wheat), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.apple), new ItemStack(ItemName.get("RawMeal"), 1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Items.baked_potato), new ItemStack(ItemName.get("RawMeal"), 2));
		RecipeRegistry.addRecipeCutter(new ItemStack(ItemName.get("RawSilicon")),new ItemStack(ItemName.get("UnorganicPlate"),4,2));
		for(int r = 1;r <6;r++)//ores to chunk
			RecipeRegistry.addRecipeCutter(new ItemStack(Ores,1,r),new ItemStack(ItemName.get("Chunk"),3,r-1));
		RecipeRegistry.addRecipeCutter(new ItemStack(Ores,1,0),new ItemStack(ItemName.get("Chunk"),1,7));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.iron_ore,1),new ItemStack(ItemName.get("Chunk"),3,5));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.gold_ore,1),new ItemStack(ItemName.get("Chunk"),3,6));
		RecipeRegistry.addRecipeCutter(new ItemStack(ItemName.get("MetalPlate"),1,4),new ItemStack(ItemName.get("SilverCable"),3));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.coal_ore,1),new ItemStack(Items.coal,3));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.lapis_ore,1),new ItemStack(Items.dye,3,4));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.redstone_ore,1),new ItemStack(Items.redstone,8));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.emerald_ore,1),new ItemStack(Items.emerald,3));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.quartz_ore,1),new ItemStack(Items.quartz,3));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.diamond_ore,1),new ItemStack(Items.diamond,3));
		RecipeRegistry.addRecipeCutter(new ItemStack(Blocks.obsidian,1),new ItemStack(ItemName.get("Dust"),1,9));
		RecipeRegistry.addRecipeCutter(new ItemStack(ItemName.get("UnorganicPlate"),1,2),new ItemStack(ItemName.get("OpticCable"),2));
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

}

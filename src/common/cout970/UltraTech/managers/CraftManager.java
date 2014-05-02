package common.cout970.UltraTech.managers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import common.cout970.UltraTech.TileEntities.Tier2.FluidGenerator;
import common.cout970.UltraTech.blocks.DecoBlocks;
import common.cout970.UltraTech.lib.recipes.Assembly_Recipes;
import common.cout970.UltraTech.lib.recipes.Boiler_Recipes;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Cooling_Recipes;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.lib.recipes.Fermenter_Recipes;
import common.cout970.UltraTech.lib.recipes.Pressurizer_Recipes;
import common.cout970.UltraTech.lib.recipes.Purifier_Recipe;
import cpw.mods.fml.common.registry.GameRegistry;
import static common.cout970.UltraTech.managers.BlockManager.*;
import static common.cout970.UltraTech.managers.ItemManager.*;

public class CraftManager {

	public static void registerCraft(){

		//crafting recipes
		//blocks
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,0),new Object[]{"aaa","a a","aaa",'a',"ingotAluminum"}));//chasis mk1
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,1),new Object[]{"aaa","a a","aaa",'a',"plateGrafeno"}));//chasis mk2
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,2),new Object[]{"aaa","a a","aaa",'a',"plateDiamond"}));//chasis mk3
		
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SolarPanel,4),new Object[]{"rrr",'r',ItemName.get("SolarPanel")}));//solarpanel
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(WindMill,1),new Object[]{"omf","opo","oco",'m',ItemName.get("Motor"),'f',ItemName.get("Fan"),'p',"plateGrafeno",'c',new ItemStack(Chasis,1,1)}));//windmill
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Engine,1),new Object[]{"pcp","aaa","pcp",'a',"plateAlloy_UT",'p',"plateGrafeno",'c',ItemName.get("Coil")}));//engine
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Boiler,1),new Object[]{"ctc","cac","ccc",'a',ItemName.get("HeatCoil"),'c',"plateCopper",'t',CopperPipe}));//boiler
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,0),new Object[]{"ctc","tat","ctc",'a',new ItemStack(Chasis,1,0),'c',"plateTin",'t',"plateRedstone"}));//storage mk1
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,1),new Object[]{"ctc","lal","ctc",'a',new ItemStack(Chasis,1,1),'c',"plateTin",'t',"plateAlloy_UT",'l',"plateLead"}));//storage mk2
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,2),new Object[]{"ctc","lal","ctc",'a',new ItemStack(Chasis,1,2),'c',"plateAlloy_UT",'t',"plateGold",'l',"plateSilver"}));//storage mk3
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,0),new Object[]{"oco","oho","oeo",'c',Block.workbench,'h',new ItemStack(Chasis,1,0),'e',Block.chest}));//crafter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,1),new Object[]{"oco","oho","oco",'c',"ingotCopper",'h',new ItemStack(Chasis,1,0),'o',"ingotAluminum"}));//generator
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,2),new Object[]{"aaa","oho","iii",'a',"ingotAluminum",'h',new ItemStack(Chasis,1,0),'o',"ingotTin",'i',Item.ingotIron}));//CVD
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,3),new Object[]{"cwc","oho","cwc",'c',"ingotCopper",'h',new ItemStack(Chasis,1,0),'o',"ingotTin",'w',Block.cloth}));//painter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,4),new Object[]{"lll","lhl","lll",'l',"ingotLead",'h',new ItemStack(Chasis,1,0)}));//charge station
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,5),new Object[]{"ctc","tht","ctc",'c',"ingotCopper",'t',"ingotTin",'h',new ItemStack(Chasis,1,0)}));//fermenter
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,0),new Object[]{"iii","lml","lsl",'m',new ItemStack(Chasis,1,1),'i',"plateIron",'l',"plateAluminum",'s',"plateSilver"}));//furnace
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,1),new Object[]{"iii","lml","sss",'m',new ItemStack(Chasis,1,1),'i',"plateGrafeno",'l',"plateGold",'s',"plateIron"}));//purifier
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,2),new Object[]{"ixi","xmx","ixi",'m',new ItemStack(Chasis,1,1),'i',"plateGrafeno",'x',"dustDiamond"}));//cuter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,3),new Object[]{"xxx","xmx","iii",'m',new ItemStack(Chasis,1,1),'i',Block.pistonBase,'x',"plateIron"}));//pressurizer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,4),new Object[]{"oso","lml","oco",'o',"plateAluminum",'m',new ItemStack(Chasis,1,1),'l',Item.bucketEmpty,'s',"plateSilicon",'c',ItemName.get("Circuit")}));//fluid firebox(fluid generator<)

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,0),new Object[]{"dpd","gmg","scs",'m',new ItemStack(Chasis,1,2),'d',"plateDiamond",'g',"plateGold",'s',"plateSilver",'c',ItemName.get("AdvCircuit"),'p',Item.pickaxeDiamond}));//miner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,1),new Object[]{"oio","csc","omo",'m',new ItemStack(Chasis,1,2),'i',"plateSilicon",'s',"plateSilver",'c',ItemName.get("Circuit")}));//hologram emiter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,2),new Object[]{"rrr","ggg","cmc",'m',new ItemStack(Chasis,1,2),'r',"plateRedstone",'g',"plateGold",'c',ItemName.get("Circuit")}));//adv crafter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,3),new Object[]{"zxz","xmx","zxz",'m',new ItemStack(Chasis,1,2),'z',"plateSilver",'x',ItemName.get("AdvCircuit")}));//climate station
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,4),new Object[]{"aaa","gmg","aaa",'m',new ItemStack(Chasis,1,2),'a',"plateAlloy_UT",'g',ItemName.get("AdvCircuit")}));//tesseract

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AluminumPipe,8),new Object[]{"oao","aga","oao",'a',"plateAluminum",'g',Block.thinGlass}));//aluminum pipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CopperPipe,8),new Object[]{"oao","aga","oao",'a',"plateCopper",'g',Block.thinGlass}));//copper pipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(LeadPipe,8),new Object[]{"oao","aga","oao",'a',"plateLead",'g',Block.thinGlass}));//lead pipe

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CovedGlass,8),new Object[]{"oo","oo",'o',"plateSilicon"}));//coved glass
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,0),new Object[]{"lll","lml","lll",'l',"plateLead",'m',new ItemStack(Chasis,1,2)}));//reactor
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,4,1),new Object[]{"olo","lml","olo",'l',"plateLead",'m',new ItemStack(Chasis,1,1)}));//reactor wall
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,2),new Object[]{"olo","lml","olo",'o',"plateLead",'l',Block.thinGlass}));//reactor tank
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,3),new Object[]{"olo","lml","olo",'l',"plateLead",'m',ItemName.get("Circuit")}));//reactor controller
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,4),new Object[]{"olo","lml","olo",'l',"plateLead",'m',new ItemStack(Chasis,1,1),'o',ItemName.get("Circuit")}));//reactor water provider
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,5),new Object[]{"ooo","lml","ooo",'o',"plateLead",'m',new ItemStack(Chasis,1,1),'l',ItemName.get("Fan")}));//reactor steam turbine
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Refinery,2,0),new Object[]{"ii","ii",'i',"plateCopper"}));//refinery block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Refinery,2,1),new Object[]{"xix","ili","xix",'i',"plateCopper",'l',"plateIron"}));//refinery input
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Refinery,2,2),new Object[]{"xix","ili","xix",'i',"plateCopper",'l',"plateLead"}));//refinery output
		
		//items
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("UpgradeBase"),4),new Object[]{"iii","ici","iii",'i',"plateIron",'c',ItemName.get("Circuit")}));//upgrade base
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("SpeedUpgrade"),2),new Object[]{"iii","ici","iii",'i',"plateSilver",'c',ItemName.get("UpgradeBase")}));//speed upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("MiningUpgrade"),2),new Object[]{"iii","ici","iii",'i',"plateGold",'c',ItemName.get("UpgradeBase")}));//mining upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RangeUpgrade"),2),new Object[]{"iii","ici","iii",'i',"plateRedstone",'c',ItemName.get("UpgradeBase")}));//range upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("AutoEjectUpgrade"),1),new Object[]{"iii","ici","iii",'i',"plateTin",'c',ItemName.get("UpgradeBase")}));//auto eject upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("FortuneUpgrade"),1),new Object[]{"iii","ici","iii",'i',"plateDiamond",'c',ItemName.get("UpgradeBase")}));//fortune upgrade

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Linker"),1),new Object[]{"iio","iio","oox",'i',"plateIron",'x',"plateRedstone"}));//Linker
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RadioniteCell"),1),new Object[]{"ioi","ioi","ioi",'i',"plateIron",'o',"plateRadionite"}));//Radionite Cell
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.Misc,1,0),new Object[]{"iii","iii","iii",'i',ItemName.get("Radionite")}));//Radionite block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Circuit"),1),new Object[]{"igi","iri","igi",'g',"plateGrafeno",'r',"plateRedstone",'i',Item.redstone}));//Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DecoBlocks.stoneblock,4,1),new Object[]{"ii","ii",'i',new ItemStack(DecoBlocks.stoneblock,4,0)}));//Deco block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DecoBlocks.stoneblock,4,3),new Object[]{"ii","ii",'i',new ItemStack(DecoBlocks.stoneblock,4,2)}));//Deco block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("GrafenoPlate"),1),new Object[]{"iii","iii","iii",'i',"plateGrafeno"}));//plate grafeno
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tank,1),new Object[]{"isi","sxs","isi",'i',"plateCopper",'s',"plateSilicon"}));//fluid tank
		
		Smelting();//furnace recipes
		CVD();
		Purifier();
		Cutter();
		Assembly();

		//presurizer
		Pressurizer_Recipes.addRecipe(new Pressurizer_Recipes(new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(DiamondGlass,4)));
		Pressurizer_Recipes.addRecipe(new Pressurizer_Recipes(new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemName.get("GrafenoPlate"),1)));

		//fermenter recipes
		Fermenter_Recipes.recipes.put(Item.sugar, 75);
		Fermenter_Recipes.recipes.put(Item.appleRed, 25);
		Fermenter_Recipes.recipes.put(Item.wheat, 100);
		
		//liquid recipes
		Boiler_Recipes.recipes.put("juice", "gas_ethanol");
		Boiler_Recipes.recipes.put("oil", "gas_oil");
		Boiler_Recipes.recipes.put("water", "steam");
		
		Cooling_Recipes.recipes.add(new Cooling_Recipes(new FluidStack(FluidRegistry.getFluid("gas_ethanol"),100), new FluidStack(FluidRegistry.getFluid("bioethanol"),2), null, null));
		if(FluidRegistry.getFluid("fuel") != null)Cooling_Recipes.recipes.add(new Cooling_Recipes(new FluidStack(FluidRegistry.getFluid("gas_oil"),100), null, new FluidStack(FluidRegistry.getFluid("fuel"),10), new FluidStack(FluidRegistry.getFluid("gasoline"),1)));
		
		FluidGenerator.fuels.put("fuel", 185);
		FluidGenerator.fuels.put("gasoline", 600);
		FluidGenerator.fuels.put("bioethanol", 75);
	}

	public static void Smelting(){
		for(int r = 0;r < 6;r++)//dust to ingots
			FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, r, new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, 6, new ItemStack(Item.ingotIron), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, 7, new ItemStack(Item.ingotGold), 0.5f);

		for(int r = 1;r < 6;r++)//ores to ingots
			FurnaceRecipes.smelting().addSmelting(Ores.blockID, r, new ItemStack(ItemName.get("Ingot"),1,r-1), 0.5f);

		for(int r = 0;r < 6;r++)//plate to ingot
			FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, r, new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, 6, new ItemStack(Item.ingotIron), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, 7, new ItemStack(Item.ingotGold), 0.5f);
		//other
		GameRegistry.addSmelting(ItemName.get("RawMeal").itemID, new ItemStack(ItemName.get("ProcesedFood")), 0.35f);
	}

	public static void CVD(){
		//cvd recipes
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal), new ItemStack(Item.coal),new ItemStack(ItemName.get("Plate"),2,9)));//grafeno
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal,1,1), new ItemStack(Item.coal,1,1),new ItemStack(ItemName.get("Plate"),2,9)));//grafeno
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.redstone),new ItemStack(ItemName.get("Plate"),1,11)));//restone plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,4), new ItemStack(Item.dyePowder,1,4),new ItemStack(ItemName.get("Plate"),1,5)));//alloy
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Radionite"),1),new ItemStack(ItemName.get("Plate"),1,12)));//radionite plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("GrafenoPlate")), new ItemStack(ItemName.get("GrafenoPlate")),new ItemStack(ItemName.get("Dust"),1,8)));//diamond dust
//		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(DecoBlocks.stoneblock,1,0), new ItemStack(Item.dyePowder,1,15),new ItemStack(DecoBlocks.stoneblock,1,2)));/*--Remove--*/
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,7), new ItemStack(ItemName.get("Dust"),1,7),new ItemStack(ItemName.get("Plate"),2,7)));//gold plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,6), new ItemStack(ItemName.get("Dust"),1,6),new ItemStack(ItemName.get("Plate"),2,6)));//iron plate
		for(int r = 0;r < 6;r++)//dust to plates
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,r), new ItemStack(ItemName.get("Dust"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));
		for(int r = 0;r < 6;r++)//ingot to plates
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,r), new ItemStack(ItemName.get("Ingot"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotGold), new ItemStack(Item.ingotGold),new ItemStack(ItemName.get("Plate"),2,7)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron),new ItemStack(ItemName.get("Plate"),2,6)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,0), new ItemStack(DecoBlocks.stoneblock,1,0),new ItemStack(DecoBlocks.stoneblock,1,2)));
	}
	public static void Purifier(){
		//purifier recipes
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sand), new ItemStack(ItemName.get("RawSilicon"),1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.gravel), new ItemStack(Item.flint,1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand,4)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Ores,1,0), new ItemStack(ItemName.get("Radionite"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.stone), new ItemStack(DecoBlocks.stoneblock,1,0)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.cobblestone), new ItemStack(DecoBlocks.stoneblock,1,0)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Item.netherQuartz), new ItemStack(DecoBlocks.stoneblock,1,2)));
		for(int r = 0;r <5;r++)//chunk to dust
			Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,r),new ItemStack(ItemName.get("Dust"),1,r)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,5),new ItemStack(ItemName.get("Dust"),1,6)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,6),new ItemStack(ItemName.get("Dust"),1,7)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,7),new ItemStack(ItemName.get("Radionite"),1)));
	}
	public static void Cutter(){
		//cuter recipes
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.reed), new ItemStack(Item.sugar,2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.cloth), new ItemStack(Item.silk,4)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.cobblestone), new ItemStack(Block.sand,1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.bone), new ItemStack(Item.dyePowder,6,15)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.diamond), new ItemStack(ItemName.get("Plate"),8,8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.cookie), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.rottenFlesh), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.porkRaw), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.beefRaw), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.potato), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.chickenRaw), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.spiderEye), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.fishRaw), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.carrot), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.bone), new ItemStack(Item.bone,3,15)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("RawSilicon")),new ItemStack(ItemName.get("Plate"),1,10)));
		for(int r = 1;r <6;r++)//ores to chunk
			Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Ores,1,r),new ItemStack(ItemName.get("Chunk"),3,r-1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Ores,1,0),new ItemStack(ItemName.get("Chunk"),1,7)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreIron,1),new ItemStack(ItemName.get("Chunk"),3,5)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreGold,1),new ItemStack(ItemName.get("Chunk"),3,6)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),1,4),new ItemStack(Cable,4)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreCoal,1),new ItemStack(Item.coal,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreLapis,1),new ItemStack(Item.dyePowder,3,4)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreRedstone,1),new ItemStack(Item.redstone,8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreEmerald,1),new ItemStack(Item.emerald,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreNetherQuartz,1),new ItemStack(Item.netherQuartz,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Block.oreDiamond,1),new ItemStack(Item.diamond,3)));
	}
	public static void Assembly(){
		//assembly recipes
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',ItemName.get("Coil"),'r', new ItemStack(ItemManager.ItemName.get("Plate"),1,4),'i',new ItemStack(ItemName.get("Plate"),1,6)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HidrogenBattery"),1), new Object[]{"hah","hah","hah",'h',new ItemStack(ItemName.get("Plate"),1,0),'a',new ItemStack(ItemName.get("Plate"),1,5)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(SolarPanel,1), new Object[]{"hhh","aaa","bbb",'h',new ItemStack(ItemName.get("Plate"),1,10),'a',new ItemStack(ItemName.get("Plate"),1,5),'b', new ItemStack(ItemManager.ItemName.get("Plate"),1,4)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("AdvCircuit"),1), new Object[]{"hph","hah","hph",'h',new ItemStack(Cable,1),'a',new ItemStack(ItemName.get("Plate"),1,10),'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,8)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Fan"),1), new Object[]{"opo","ppp","opo",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,9)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Coil"),2), new Object[]{"rrr","ppp","rrr",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,6),'r',new ItemStack(ItemManager.ItemName.get("Plate"),1,11)}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HeatCoil"),1), new Object[]{"pp ","pp ","   ",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,5)}));
	}

}

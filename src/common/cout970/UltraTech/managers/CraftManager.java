package common.cout970.UltraTech.managers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import common.cout970.UltraTech.blocks.DecoBlocks;
import common.cout970.UltraTech.lib.recipes.Assembly_Recipes;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.lib.recipes.Purifier_Recipe;
import cpw.mods.fml.common.registry.GameRegistry;
import static common.cout970.UltraTech.managers.BlockManager.*;
import static common.cout970.UltraTech.managers.ItemManager.*;

public class CraftManager {

	public static void registerCraft(){

		//crafting recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,0),new Object[]{"aaa","a a","aaa",'a',"ingotAluminum"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,1),new Object[]{"aaa","a a","aaa",'a',"plateGrafeno"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Chasis,1,2),new Object[]{"aaa","a a","aaa",'a',"plateDiamond"}));
		
		//old recipes
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CVDmachine,1),new Object[]{"rsr","ses","rsr",'r',ItemName.get("CarbonFiber"),'s',Item.ingotIron,'e',MachineChasis}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(UTfurnace,1),new Object[]{"rcr","ded","rcr",'r',ItemName.get("CarbonFiber"),'e',MachineChasis,'d',Item.ingotIron,'c',"plateIron"}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Purifier,1),new Object[]{"rdr","ded","rdr",'r',ItemName.get("CarbonFiber"),'e',MachineChasis,'d',ItemName.get("Filter")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("EnergyTransmiter"),1),new Object[]{"rrr","rer","rrr",'r',Block.stone,'s',Item.redstone,'e',ItemName.get("LapisPearl")}));//revisar
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MachineChasis,1),new Object[]{"aaa","a a","aaa",'a',ItemName.get("CarbonFiber")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("CarbonFiber"),8),new Object[]{"a",'a',MachineChasis}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ReactorWall,4),new Object[]{"aaa","aba","aaa",'a',ItemName.get("CarbonFiber"),'b',new ItemStack(ItemName.get("Plate"),1,5)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor, 1), new Object[]{"apa","pcp","apa",'p',new ItemStack(ItemName.get("Plate"),1,5),'c',AdvMachineChasis,'a',ReactorTank}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ReactorTank, 4), new Object[]{"aga","gpg","aga",'p',new ItemStack(ItemName.get("Plate"),1,5),'a',ItemName.get("CarbonFiber"),'g',new ItemStack(ItemName.get("Plate"),1,10)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Circuit"),1),new Object[]{"rsr","rdr","rsr",'r',ItemName.get("RedstoneCable"),'s',new ItemStack(ItemName.get("Plate"),1,9),'d',new ItemStack(ItemName.get("Plate"),1,10)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Generator,1),new Object[]{"rrr","rcr","rer",'c',MachineChasis,'r',Item.ingotIron,'e',ItemName.get("EnergyTransmiter")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DiamondGlass,16),new Object[]{"rr","rr",'r',new ItemStack(ItemName.get("Plate"),1,9)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CovedGlass,8),new Object[]{"rr","rr",'r',new ItemStack(ItemName.get("Plate"),1,10)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(IDS,1),new Object[]{"rtr","tet","rtr",'r',ItemName.get("CarbonFiber"),'t',Block.blockLapis,'e',ItemName.get("EnergyTransmiter")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Cuter,1),new Object[]{"rdr","ded","rcr",'r',ItemName.get("CarbonFiber"),'d',new ItemStack(ItemName.get("Dust"),1,8),'e',MachineChasis,'c',Item.ingotIron}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Miner,1),new Object[]{"rfr","dcd","rfr",'r',new ItemStack(ItemName.get("Plate"),1,9),'d',Item.pickaxeDiamond,'c',MachineChasis,'f',ItemName.get("AdvCircuit")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("AdvCircuit"),1),new Object[]{"rfr","rcr","rfr",'r',ItemName.get("GrafenoCable"),'f',new ItemStack(ItemName.get("Plate"),1,8),'c',new ItemStack(ItemName.get("Plate"),1,10)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("UpgradeBase"),2),new Object[]{"rrr","rir","rrr",'r',new ItemStack(ItemName.get("Plate"),1,6),'i',ItemName.get("Circuit")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("SpeedUpgrade"),2),new Object[]{"rhr","cic","rhr",'r',new ItemStack(ItemName.get("Plate"),1,7),'i',ItemName.get("UpgradeBase"),'c',ItemName.get("Circuit"),'h',ItemName.get("HeatCoil")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("MiningUpgrade"),1),new Object[]{"rgr","gig","rgr",'r',new ItemStack(ItemName.get("Plate"),1,8),'i',ItemName.get("UpgradeBase"),'g',new ItemStack(ItemName.get("Plate"),1,7)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RangeUpgrade"),2),new Object[]{"rrr","rir","rrr",'r',new ItemStack(ItemName.get("Plate"),1,10),'i',ItemName.get("UpgradeBase")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("AutoEjectUpgrade"),1),new Object[]{"rrr","cic","rrr",'r',new ItemStack(ItemName.get("Plate"),1,11),'i',ItemName.get("UpgradeBase"),'c',ItemName.get("Circuit")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("FortuneUpgrade"),1),new Object[]{"rrr","rir","rrr",'r',Item.diamond,'i',ItemName.get("UpgradeBase")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AdvMachineChasis,1),new Object[]{"rrr","r r","rrr",'r',new ItemStack(ItemName.get("Plate"),1,8)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(RadioniteBlock,1),new Object[]{"rrr","rrr","rrr",'r',ItemName.get("Radionite")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("LapisPearl"),1),new Object[]{" r ","rar"," r ",'r',new ItemStack(ItemName.get("Ingot"),1,5),'a',Item.redstone}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("LapisPearl"),1),new Object[]{"iri","rar","iri",'r',Item.ingotIron,'a',Item.redstone,'i',new ItemStack(Item.dyePowder,1,4)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Linker"),1),new Object[]{"ppa","ppa","aar",'r',new ItemStack(ItemName.get("Plate"),1,11),'p',new ItemStack(ItemName.get("Plate"),1,6)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(GrafenoBlock,9), new Object[]{"dd","dd",'d',new ItemStack(ItemName.get("Plate"),1,9)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Radionite"),9), new Object[]{"d",'d',RadioniteBlock}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RadioniteCell"),1), new Object[]{"idi","idi","idi",'d',new ItemStack(ItemName.get("Plate"),1,12),'i',new ItemStack(ItemName.get("Plate"),1,6)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("SolarPanel"),3), new Object[]{"iii","ddd","rrr",'d',new ItemStack(ItemName.get("Plate"),1,10),'r',new ItemStack(ItemName.get("Plate"),1,9),'i',Block.glass}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SolarPanel,4), new Object[]{"iii","ddd",'d',ItemName.get("CarbonFiber"),'i',ItemName.get("SolarPanel")}));	
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Coil"),1), new Object[]{"ici","cmc","ici",'i',new ItemStack(ItemName.get("Plate"),1,9),'m',new ItemStack(ItemName.get("Plate"),1,6),'c',ItemName.get("RedstoneCable")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Sender,2), new Object[]{"fcf","cmc","fcf",'f',ItemName.get("CarbonFiber"),'m',ItemName.get("LapisPearl"),'c',new ItemStack(ItemName.get("Plate"),1,11)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reciver,2),new Object[]{"fpf","pmp","fpf",'f',ItemName.get("CarbonFiber"),'m',ItemName.get("LapisPearl"),'p',new ItemStack(ItemName.get("Plate"),1,6)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("HeatCoil"),1), new Object[]{"ii","ii",'i',ItemName.get("AlloyCable")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Plate"),4,9), new Object[]{"iii","iii","iii",'i',GrafenoBlock}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("GrafenoPlate"),1), new Object[]{"iii","iii","iii",'i',new ItemStack(ItemName.get("Plate"),1,9)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("CarbonPlate"),4), new Object[]{"ii","ii",'i',Item.coal}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("CarbonPlate"),4), new Object[]{"ii","ii",'i',new ItemStack(Item.coal,1,1)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Filter"),1), new Object[]{"ii","ii",'i',new ItemStack(ItemName.get("Plate"),1,6)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(SteamTurbine,1), new Object[]{"fff","bmb","fff",'f',ItemName.get("CarbonFiber"),'m',ReactorWall,'b',ItemName.get("Fan")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Fan"),1), new Object[]{"fjf","jfj","fjf",'f',ItemName.get("CarbonFiber")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(WaterBlock,1), new Object[]{"fbf","jmj","fbf",'f',ItemName.get("CarbonFiber"),'m',ReactorWall,'b',Item.bucketEmpty,'j',ItemName.get("Circuit")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DecoBlocks.stoneblock,4,1), new Object[]{"ff","ff",'f',new ItemStack(DecoBlocks.stoneblock,1,0)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DecoBlocks.stoneblock,4,3), new Object[]{"ff","ff",'f',new ItemStack(DecoBlocks.stoneblock,1,2)}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EnergyColector,1), new Object[]{"fff","cmc","fpf",'f',ItemName.get("CarbonFiber"),'m',MachineChasis,'p',ItemName.get("EnergyTransmiter"),'c',ItemName.get("OpticFiber")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(WindMill,1), new Object[]{"fp "," p "," m ",'f',ItemName.get("Fan"),'m',MachineChasis,'p',ItemName.get("CarbonFiber")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ChargeStation,1), new Object[]{" f ","fmf"," f ",'f',new ItemStack(ItemName.get("Plate"),1,6),'m',MachineChasis}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MolecularAssembly, 1), new Object[]{"bcb","omo","bcb",'m',AdvMachineChasis,'c',ItemName.get("AdvCircuit"),'o',ItemName.get("OpticFiber"),'b',ItemName.get("Circuit")}));
//		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Printer3D, 1), new Object[]{"abc","pmp",'m',MachineChasis,'p',Item.paper,'a',new ItemStack(Item.dyePowder,1,1),'b',new ItemStack(Item.dyePowder,1,2),'c',new ItemStack(Item.dyePowder,1,4)}));
//
		//furnace recipes

		//dust-to-ingots
		for(int r = 0;r < 6;r++)
			FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, r, new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, 6, new ItemStack(Item.ingotIron), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Dust").itemID, 7, new ItemStack(Item.ingotGold), 0.5f);

		//plates-to-ingots
		for(int r = 0;r < 6;r++)
			FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, r, new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, 6, new ItemStack(Item.ingotIron), 0.5f);
		FurnaceRecipes.smelting().addSmelting(ItemName.get("Plate").itemID, 7, new ItemStack(Item.ingotGold), 0.5f);
		//simple
		GameRegistry.addSmelting(ItemName.get("RawMeal").itemID, new ItemStack(ItemName.get("ProcesedFood")), 0.35f);
		GameRegistry.addSmelting(ItemName.get("CarbonPlate").itemID, new ItemStack(ItemName.get("CarbonFiber")), 0.35f);

		//cvd recipes
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal), new ItemStack(Item.coal,1,0),new ItemStack(ItemName.get("Plate"),2,9)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal), new ItemStack(Item.coal,1,1),new ItemStack(ItemName.get("Plate"),2,9)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.redstone),new ItemStack(ItemName.get("Plate"),1,11)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,3), new ItemStack(Item.dyePowder,1,4),new ItemStack(ItemName.get("Plate"),1,5)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(BlockManager.Misc,1,0), new ItemStack(ItemName.get("Ingot"),1,3),new ItemStack(ItemName.get("Plate"),1,12)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("GrafenoPlate")), new ItemStack(ItemName.get("GrafenoPlate")),new ItemStack(ItemName.get("Dust"),1,8)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(DecoBlocks.stoneblock,1,0), new ItemStack(Item.dyePowder,1,15),new ItemStack(DecoBlocks.stoneblock,1,2)));
		
		//duplication
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,7), new ItemStack(ItemName.get("Dust"),1,7),new ItemStack(ItemName.get("Plate"),2,7)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,6), new ItemStack(ItemName.get("Dust"),1,6),new ItemStack(ItemName.get("Plate"),2,6)));
		for(int r = 0;r < 6;r++)
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,r), new ItemStack(ItemName.get("Dust"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));

		for(int r = 0;r < 6;r++)
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,r), new ItemStack(ItemName.get("Ingot"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));

		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotGold), new ItemStack(Item.ingotGold),new ItemStack(ItemName.get("Plate"),2,7)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron),new ItemStack(ItemName.get("Plate"),2,6)));

//
//		//purifier recipes
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sand), new ItemStack(ItemName.get("RawSilicon"),1)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreGold), new ItemStack(ItemName.get("Dust"),3,7)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreIron), new ItemStack(ItemName.get("Dust"),3,6)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.gravel), new ItemStack(Item.flint,1)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand,4)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Ores,1,0), new ItemStack(ItemName.get("Radionite"),2)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.obsidian), new ItemStack(ItemName.get("Dust"),1,6)));//ilogic
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.stone), new ItemStack(DecoBlocks.stoneblock,1,0)));
//		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.cobblestone), new ItemStack(DecoBlocks.stoneblock,1,0)));
//
		//cuter recipes
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.diamond), new ItemStack(ItemName.get("Plate"),8,8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.ingotIron), new ItemStack(ItemName.get("Filter"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),8,5), new ItemStack(ItemName.get("AlloyCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),1,9), new ItemStack(ItemName.get("GrafenoCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),1,10), new ItemStack(ItemName.get("OpticFiber"), 1)));
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
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),1,11), new ItemStack(ItemName.get("RedstoneCable"),3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("RawSilicon")),new ItemStack(ItemName.get("Plate"),1,10)));
//
//		//assembly recipes
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',ItemName.get("Coil"),'r',ItemName.get("RedstoneCable"),'i',new ItemStack(ItemName.get("Plate"),1,6)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HidrogenBattery"),1), new Object[]{"hhh","aia","hhh",'h',new ItemStack(ItemName.get("Plate"),1,6),'a',new ItemStack(ItemName.get("Plate"),1,5),'i',ItemName.get("Filter")}));

	}
}

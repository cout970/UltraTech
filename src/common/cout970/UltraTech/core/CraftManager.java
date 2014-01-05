package common.cout970.UltraTech.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import common.cout970.UltraTech.lib.recipes.Assembly_Recipes;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.lib.recipes.Purifier_Recipe;

import cpw.mods.fml.common.registry.GameRegistry;

public class CraftManager {

public static void registerCraft(){
		
		//vanilla recipes
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.CVDmachine,1),new Object[]{"rsr","ses","rsr",'r',UltraTech.ItemName.get("CarbonFiber"),'s',Item.ingotIron,'e',UltraTech.MachineChasis});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.UTfurnace,1),new Object[]{"rcr","ded","rcr",'r',UltraTech.ItemName.get("CarbonFiber"),'e',UltraTech.MachineChasis,'d',Item.ingotIron,'c',UltraTech.ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Purifier,1),new Object[]{"rdr","ded","rdr",'r',UltraTech.ItemName.get("CarbonFiber"),'e',UltraTech.MachineChasis,'d',UltraTech.ItemName.get("Filter")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("EnergyTransmiter"),1),new Object[]{"rrr","rer","rrr",'r',Block.stone,'s',Item.redstone,'e',UltraTech.ItemName.get("LapisPearl")});//revisar
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.MachineChasis,1),new Object[]{"aaa","a a","aaa",'a',UltraTech.ItemName.get("CarbonFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("CarbonFiber"),8),new Object[]{"a",'a',UltraTech.MachineChasis});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ReactorWall,2),new Object[]{"aaa","aba","aaa",'a',UltraTech.ItemName.get("CarbonFiber"),'b',UltraTech.ItemName.get("AlloyPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Reactor, 1), new Object[]{"apa","pcp","apa",'p',UltraTech.ItemName.get("AlloyPlate"),'c',UltraTech.AdvMachineChasis,'a',UltraTech.ReactorTank});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ReactorTank, 2), new Object[]{"aga","gpg","aga",'p',UltraTech.ItemName.get("AlloyPlate"),'a',UltraTech.ItemName.get("CarbonFiber"),'g',UltraTech.ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Circuit"),1),new Object[]{"rrr","sds","rrr",'r',UltraTech.ItemName.get("RedstoneCable"),'s',UltraTech.ItemName.get("Grafeno"),'d',UltraTech.ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Generator,1),new Object[]{"rrr","rcr","rer",'c',UltraTech.MachineChasis,'r',Item.ingotIron,'e',UltraTech.ItemName.get("EnergyTransmiter")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.DiamondGlass,16),new Object[]{"rr","rr",'r',UltraTech.ItemName.get("DiamondPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.CovedGlass,8),new Object[]{"rr","rr",'r',UltraTech.ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.IDS,1),new Object[]{"rtr","tet","rtr",'r',UltraTech.ItemName.get("CarbonFiber"),'t',Block.blockLapis,'e',UltraTech.ItemName.get("EnergyTransmiter")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Cuter,1),new Object[]{"rdr","ded","rcr",'r',UltraTech.ItemName.get("CarbonFiber"),'d',UltraTech.ItemName.get("DiamondDust"),'e',UltraTech.MachineChasis,'c',Item.ingotIron});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Miner,1),new Object[]{"rfr","dcd","rfr",'r',UltraTech.ItemName.get("DiamondPlate"),'d',Item.pickaxeDiamond,'c',UltraTech.MachineChasis,'f',UltraTech.ItemName.get("AdvCircuit")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("AdvCircuit"),1),new Object[]{"rfr","rcr","rfr",'r',UltraTech.ItemName.get("GrafenoCable"),'f',UltraTech.ItemName.get("DiamondPlate"),'c',UltraTech.ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("UpgradeBase"),2),new Object[]{"rrr","rir","rrr",'r',UltraTech.ItemName.get("IronPlate"),'i',UltraTech.ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("SpeedUpgrade"),1),new Object[]{"rhr","cic","rhr",'r',UltraTech.ItemName.get("GoldPlate"),'i',UltraTech.ItemName.get("UpgradeBase"),'c',UltraTech.ItemName.get("Circuit"),'h',UltraTech.ItemName.get("HeatCoil")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("MiningUpgrade"),1),new Object[]{"rgr","gig","rgr",'r',UltraTech.ItemName.get("DiamondPlate"),'i',UltraTech.ItemName.get("UpgradeBase"),'g',UltraTech.ItemName.get("GoldPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("RangeUpgrade"),2),new Object[]{"rrr","rir","rrr",'r',UltraTech.ItemName.get("SiliconPlate"),'i',UltraTech.ItemName.get("UpgradeBase")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("AutoEjectUpgrade"),1),new Object[]{"rrr","cic","rrr",'r',UltraTech.ItemName.get("RedstonePlate"),'i',UltraTech.ItemName.get("UpgradeBase"),'c',UltraTech.ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("FortuneUpgrade"),1),new Object[]{"rrr","rir","rrr",'r',Item.diamond,'i',UltraTech.ItemName.get("UpgradeBase")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.AdvMachineChasis,1),new Object[]{"rrr","r r","rrr",'r',UltraTech.ItemName.get("DiamondPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.RadioniteBlock,1),new Object[]{"rrr","rrr","rrr",'r',UltraTech.ItemName.get("Radionite")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("LapisPearl"),1),new Object[]{" r ","rar"," r ",'r',UltraTech.ItemName.get("Alloyingot"),'a',Item.redstone});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("LapisPearl"),1),new Object[]{"iri","rar","iri",'r',Item.ingotIron,'a',Item.redstone,'i',new ItemStack(Item.dyePowder,1,4)});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Linker"),1),new Object[]{"ppa","ppa","aar",'r',UltraTech.ItemName.get("RedstonePlate"),'p',UltraTech.ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.GrafenoBlock,9), new Object[]{"dd","dd",'d',UltraTech.ItemName.get("Grafeno")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Radionite"),9), new Object[]{"d",'d',UltraTech.RadioniteBlock});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("RadioniteCell"),1), new Object[]{"idi","idi","idi",'d',UltraTech.ItemName.get("RadionitePlate"),'i',UltraTech.ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("SolarPanel"),3), new Object[]{"iii","ddd","rrr",'d',UltraTech.ItemName.get("SiliconPlate"),'r',UltraTech.ItemName.get("Grafeno"),'i',Block.glass});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.SolarPanel,4), new Object[]{"iii","ddd","rrr",'d',UltraTech.ItemName.get("CarbonFiber"),'i',UltraTech.ItemName.get("SolarPanel")});	
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Coil"),1), new Object[]{"ici","cmc","ici",'i',UltraTech.ItemName.get("Grafeno"),'m',UltraTech.ItemName.get("IronPlate"),'c',UltraTech.ItemName.get("RedstoneCable")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Sender,2), new Object[]{"fcf","cmc","fcf",'f',UltraTech.ItemName.get("CarbonFiber"),'m',UltraTech.ItemName.get("LapisPearl"),'c',UltraTech.ItemName.get("RedstonePlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.Reciver,2),new Object[]{"fpf","pmp","fpf",'f',UltraTech.ItemName.get("CarbonFiber"),'m',UltraTech.ItemName.get("LapisPearl"),'p',UltraTech.ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("HeatCoil"),1), new Object[]{"ii ","ii ","   ",'i',UltraTech.ItemName.get("AlloyCable")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Grafeno"),4), new Object[]{"iii","iii","iii",'i',UltraTech.GrafenoBlock});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("GrafenoPlate"),1), new Object[]{"iii","iii","iii",'i',UltraTech.ItemName.get("Grafeno")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("CarbonPlate"),2), new Object[]{"ii","ii",'i',Item.coal});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("CarbonPlate"),2), new Object[]{"ii","ii",'i',new ItemStack(Item.coal,1,1)});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Filter"),1), new Object[]{"ii","ii",'i',UltraTech.ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.SteamTurbine,1), new Object[]{"fff","bmb","fff",'f',UltraTech.ItemName.get("CarbonFiber"),'m',UltraTech.MachineChasis,'b',UltraTech.ItemName.get("Fan")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.ItemName.get("Fan"),1), new Object[]{"fjf","jfj","fjf",'f',UltraTech.ItemName.get("CarbonFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.WaterBlock,1), new Object[]{"fbf","jmj","fbf",'f',UltraTech.ItemName.get("CarbonFiber"),'m',UltraTech.MachineChasis,'b',Item.bucketEmpty,'j',UltraTech.ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.StoneBlockBricks,4), new Object[]{"ff","ff",'f',UltraTech.StoneBlock});
		GameRegistry.addShapedRecipe(new ItemStack(UltraTech.EnergyColector,1), new Object[]{"fff","cmc","fpf",'f',UltraTech.ItemName.get("CarbonFiber"),'m',UltraTech.MachineChasis,'p',UltraTech.ItemName.get("EnergyTransmiter"),'c',UltraTech.ItemName.get("OpticFiber")});

		//furnace recipes
		GameRegistry.addSmelting(UltraTech.ItemName.get("GoldPlate").itemID, new ItemStack(Item.ingotGold), 0.35f);
		GameRegistry.addSmelting(UltraTech.ItemName.get("IronPlate").itemID, new ItemStack(Item.ingotIron), 0.35f);
		GameRegistry.addSmelting(UltraTech.ItemName.get("RawMeal").itemID, new ItemStack(UltraTech.ProcesedFood), 0.35f);
		GameRegistry.addSmelting(UltraTech.ItemName.get("AlloyPlate").itemID, new ItemStack(UltraTech.ItemName.get("Alloyingot")), 0.35f);
		GameRegistry.addSmelting(UltraTech.ItemName.get("CarbonPlate").itemID, new ItemStack(UltraTech.ItemName.get("CarbonFiber")), 0.35f);
		
		//cvd recipes
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal), new ItemStack(Item.coal),new ItemStack(UltraTech.ItemName.get("Grafeno"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.redstone),new ItemStack(UltraTech.ItemName.get("RedstonePlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.dyePowder,1,4),new ItemStack(UltraTech.ItemName.get("AlloyPlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(UltraTech.ItemName.get("GoldDust")), new ItemStack(UltraTech.ItemName.get("GoldDust")),new ItemStack(UltraTech.ItemName.get("GoldPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(UltraTech.ItemName.get("IronDust")), new ItemStack(UltraTech.ItemName.get("IronDust")),new ItemStack(UltraTech.ItemName.get("IronPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(UltraTech.RadioniteBlock), new ItemStack(Item.ingotIron),new ItemStack(UltraTech.ItemName.get("RadionitePlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotGold), new ItemStack(Item.ingotGold),new ItemStack(UltraTech.ItemName.get("GoldPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron),new ItemStack(UltraTech.ItemName.get("IronPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(UltraTech.ItemName.get("CarbonFiber")), new ItemStack(UltraTech.ItemName.get("Grafeno")),new ItemStack(UltraTech.ItemName.get("DiamondDust"),1)));

		
		//purifier recipes
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sand), new ItemStack(UltraTech.ItemName.get("RawSilicon"),1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreGold), new ItemStack(UltraTech.ItemName.get("GoldDust"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreIron), new ItemStack(UltraTech.ItemName.get("IronDust"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.gravel), new ItemStack(Item.flint,1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand,4)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(UltraTech.RadioniteOre), new ItemStack(UltraTech.ItemName.get("Radionite"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.obsidian), new ItemStack(UltraTech.ItemName.get("IronDust"),1)));//ilogic
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.stone), new ItemStack(UltraTech.StoneBlock,1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.cobblestone), new ItemStack(UltraTech.StoneBlock,1)));
		
		//cuter recipes
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.diamond), new ItemStack(UltraTech.ItemName.get("DiamondPlate"), 8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.ingotIron), new ItemStack(UltraTech.ItemName.get("Filter"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(UltraTech.ItemName.get("AlloyPlate")), new ItemStack(UltraTech.ItemName.get("AlloyCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(UltraTech.ItemName.get("Grafeno")), new ItemStack(UltraTech.ItemName.get("GrafenoCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(UltraTech.ItemName.get("SiliconPlate")), new ItemStack(UltraTech.ItemName.get("OpticFiber"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.cookie), new ItemStack(UltraTech.ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.rottenFlesh), new ItemStack(UltraTech.ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.porkRaw), new ItemStack(UltraTech.ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.beefRaw), new ItemStack(UltraTech.ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.potato), new ItemStack(UltraTech.ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.chickenRaw), new ItemStack(UltraTech.ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.spiderEye), new ItemStack(UltraTech.ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.fishRaw), new ItemStack(UltraTech.ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.carrot), new ItemStack(UltraTech.ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.bone), new ItemStack(Item.bone,3,15)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(UltraTech.ItemName.get("RedstonePlate")), new ItemStack(UltraTech.ItemName.get("RedstoneCable"),3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(UltraTech.ItemName.get("RawSilicon")), new ItemStack(UltraTech.ItemName.get("SiliconPlate"),1)));

		//assembly recipes
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(UltraTech.ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',UltraTech.ItemName.get("Coil"),'r',UltraTech.ItemName.get("RedstoneCable"),'i',UltraTech.ItemName.get("IronPlate")}));
	}
}

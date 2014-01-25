package common.cout970.UltraTech.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import common.cout970.UltraTech.blocks.DecoBlocks;
import common.cout970.UltraTech.lib.recipes.Assembly_Recipes;
import common.cout970.UltraTech.lib.recipes.CVD_Recipe;
import common.cout970.UltraTech.lib.recipes.Cuter_Recipes;
import common.cout970.UltraTech.lib.recipes.Purifier_Recipe;
import cpw.mods.fml.common.registry.GameRegistry;
import static common.cout970.UltraTech.blocks.BlockManager.*;
import static common.cout970.UltraTech.core.UltraTech.*;

public class CraftManager {

public static void registerCraft(){
		
		//vanilla recipes
		GameRegistry.addShapedRecipe(new ItemStack(CVDmachine,1),new Object[]{"rsr","ses","rsr",'r',ItemName.get("CarbonFiber"),'s',Item.ingotIron,'e',MachineChasis});
		GameRegistry.addShapedRecipe(new ItemStack(UTfurnace,1),new Object[]{"rcr","ded","rcr",'r',ItemName.get("CarbonFiber"),'e',MachineChasis,'d',Item.ingotIron,'c',ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(Purifier,1),new Object[]{"rdr","ded","rdr",'r',ItemName.get("CarbonFiber"),'e',MachineChasis,'d',ItemName.get("Filter")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("EnergyTransmiter"),1),new Object[]{"rrr","rer","rrr",'r',Block.stone,'s',Item.redstone,'e',ItemName.get("LapisPearl")});//revisar
		GameRegistry.addShapedRecipe(new ItemStack(MachineChasis,1),new Object[]{"aaa","a a","aaa",'a',ItemName.get("CarbonFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("CarbonFiber"),8),new Object[]{"a",'a',MachineChasis});
		GameRegistry.addShapedRecipe(new ItemStack(ReactorWall,4),new Object[]{"aaa","aba","aaa",'a',ItemName.get("CarbonFiber"),'b',ItemName.get("AlloyPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(Reactor, 1), new Object[]{"apa","pcp","apa",'p',ItemName.get("AlloyPlate"),'c',AdvMachineChasis,'a',ReactorTank});
		GameRegistry.addShapedRecipe(new ItemStack(ReactorTank, 4), new Object[]{"aga","gpg","aga",'p',ItemName.get("AlloyPlate"),'a',ItemName.get("CarbonFiber"),'g',ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Circuit"),1),new Object[]{"rrr","sds","rrr",'r',ItemName.get("RedstoneCable"),'s',ItemName.get("Grafeno"),'d',ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(Generator,1),new Object[]{"rrr","rcr","rer",'c',MachineChasis,'r',Item.ingotIron,'e',ItemName.get("EnergyTransmiter")});
		GameRegistry.addShapedRecipe(new ItemStack(DiamondGlass,16),new Object[]{"rr","rr",'r',ItemName.get("DiamondPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(CovedGlass,8),new Object[]{"rr","rr",'r',ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(IDS,1),new Object[]{"rtr","tet","rtr",'r',ItemName.get("CarbonFiber"),'t',Block.blockLapis,'e',ItemName.get("EnergyTransmiter")});
		GameRegistry.addShapedRecipe(new ItemStack(Cuter,1),new Object[]{"rdr","ded","rcr",'r',ItemName.get("CarbonFiber"),'d',ItemName.get("DiamondDust"),'e',MachineChasis,'c',Item.ingotIron});
		GameRegistry.addShapedRecipe(new ItemStack(Miner,1),new Object[]{"rfr","dcd","rfr",'r',ItemName.get("DiamondPlate"),'d',Item.pickaxeDiamond,'c',MachineChasis,'f',ItemName.get("AdvCircuit")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("AdvCircuit"),1),new Object[]{"rfr","rcr","rfr",'r',ItemName.get("GrafenoCable"),'f',ItemName.get("DiamondPlate"),'c',ItemName.get("SiliconPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("UpgradeBase"),2),new Object[]{"rrr","rir","rrr",'r',ItemName.get("IronPlate"),'i',ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("SpeedUpgrade"),2),new Object[]{"rhr","cic","rhr",'r',ItemName.get("GoldPlate"),'i',ItemName.get("UpgradeBase"),'c',ItemName.get("Circuit"),'h',ItemName.get("HeatCoil")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("MiningUpgrade"),1),new Object[]{"rgr","gig","rgr",'r',ItemName.get("DiamondPlate"),'i',ItemName.get("UpgradeBase"),'g',ItemName.get("GoldPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("RangeUpgrade"),2),new Object[]{"rrr","rir","rrr",'r',ItemName.get("SiliconPlate"),'i',ItemName.get("UpgradeBase")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("AutoEjectUpgrade"),1),new Object[]{"rrr","cic","rrr",'r',ItemName.get("RedstonePlate"),'i',ItemName.get("UpgradeBase"),'c',ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("FortuneUpgrade"),1),new Object[]{"rrr","rir","rrr",'r',Item.diamond,'i',ItemName.get("UpgradeBase")});
		GameRegistry.addShapedRecipe(new ItemStack(AdvMachineChasis,1),new Object[]{"rrr","r r","rrr",'r',ItemName.get("DiamondPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(RadioniteBlock,1),new Object[]{"rrr","rrr","rrr",'r',ItemName.get("Radionite")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("LapisPearl"),1),new Object[]{" r ","rar"," r ",'r',ItemName.get("Alloyingot"),'a',Item.redstone});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("LapisPearl"),1),new Object[]{"iri","rar","iri",'r',Item.ingotIron,'a',Item.redstone,'i',new ItemStack(Item.dyePowder,1,4)});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Linker"),1),new Object[]{"ppa","ppa","aar",'r',ItemName.get("RedstonePlate"),'p',ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(GrafenoBlock,9), new Object[]{"dd","dd",'d',ItemName.get("Grafeno")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Radionite"),9), new Object[]{"d",'d',RadioniteBlock});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("RadioniteCell"),1), new Object[]{"idi","idi","idi",'d',ItemName.get("RadionitePlate"),'i',ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("SolarPanel"),3), new Object[]{"iii","ddd","rrr",'d',ItemName.get("SiliconPlate"),'r',ItemName.get("Grafeno"),'i',Block.glass});
		GameRegistry.addShapedRecipe(new ItemStack(SolarPanel,4), new Object[]{"iii","ddd",'d',ItemName.get("CarbonFiber"),'i',ItemName.get("SolarPanel")});	
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Coil"),1), new Object[]{"ici","cmc","ici",'i',ItemName.get("Grafeno"),'m',ItemName.get("IronPlate"),'c',ItemName.get("RedstoneCable")});
		GameRegistry.addShapedRecipe(new ItemStack(Sender,2), new Object[]{"fcf","cmc","fcf",'f',ItemName.get("CarbonFiber"),'m',ItemName.get("LapisPearl"),'c',ItemName.get("RedstonePlate")});
		GameRegistry.addShapedRecipe(new ItemStack(Reciver,2),new Object[]{"fpf","pmp","fpf",'f',ItemName.get("CarbonFiber"),'m',ItemName.get("LapisPearl"),'p',ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("HeatCoil"),1), new Object[]{"ii ","ii ","   ",'i',ItemName.get("AlloyCable")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Grafeno"),4), new Object[]{"iii","iii","iii",'i',GrafenoBlock});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("GrafenoPlate"),1), new Object[]{"iii","iii","iii",'i',ItemName.get("Grafeno")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("CarbonPlate"),4), new Object[]{"ii","ii",'i',Item.coal});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("CarbonPlate"),4), new Object[]{"ii","ii",'i',new ItemStack(Item.coal,1,1)});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Filter"),1), new Object[]{"ii","ii",'i',ItemName.get("IronPlate")});
		GameRegistry.addShapedRecipe(new ItemStack(SteamTurbine,1), new Object[]{"fff","bmb","fff",'f',ItemName.get("CarbonFiber"),'m',ReactorWall,'b',ItemName.get("Fan")});
		GameRegistry.addShapedRecipe(new ItemStack(ItemName.get("Fan"),1), new Object[]{"fjf","jfj","fjf",'f',ItemName.get("CarbonFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(WaterBlock,1), new Object[]{"fbf","jmj","fbf",'f',ItemName.get("CarbonFiber"),'m',ReactorWall,'b',Item.bucketEmpty,'j',ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(DecoBlocks.stoneblock,4,1), new Object[]{"ff","ff",'f',new ItemStack(DecoBlocks.stoneblock,1,0)});
		GameRegistry.addShapedRecipe(new ItemStack(DecoBlocks.stoneblock,4,3), new Object[]{"ff","ff",'f',new ItemStack(DecoBlocks.stoneblock,1,2)});
		GameRegistry.addShapedRecipe(new ItemStack(EnergyColector,1), new Object[]{"fff","cmc","fpf",'f',ItemName.get("CarbonFiber"),'m',MachineChasis,'p',ItemName.get("EnergyTransmiter"),'c',ItemName.get("OpticFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(WindMill,1), new Object[]{"fp "," p "," m ",'f',ItemName.get("Fan"),'m',MachineChasis,'p',ItemName.get("CarbonFiber")});
		GameRegistry.addShapedRecipe(new ItemStack(ChargeStation,1), new Object[]{" f ","fmf"," f ",'f',ItemName.get("IronPlate"),'m',MachineChasis});
		GameRegistry.addShapedRecipe(new ItemStack(MolecularAssembly, 1), new Object[]{"bcb","omo","bcb",'m',AdvMachineChasis,'c',ItemName.get("AdvCircuit"),'o',ItemName.get("OpticFiber"),'b',ItemName.get("Circuit")});
		GameRegistry.addShapedRecipe(new ItemStack(Printer3D, 1), new Object[]{"abc","pmp",'m',MachineChasis,'p',Item.paper,'a',new ItemStack(Item.dyePowder,1,1),'b',new ItemStack(Item.dyePowder,1,2),'c',new ItemStack(Item.dyePowder,1,4)});

		//furnace recipes
		GameRegistry.addSmelting(ItemName.get("GoldDust").itemID, new ItemStack(Item.ingotGold), 0.35f);
		GameRegistry.addSmelting(ItemName.get("IronDust").itemID, new ItemStack(Item.ingotIron), 0.35f);
		GameRegistry.addSmelting(ItemName.get("GoldPlate").itemID, new ItemStack(Item.ingotGold), 0.35f);
		GameRegistry.addSmelting(ItemName.get("IronPlate").itemID, new ItemStack(Item.ingotIron), 0.35f);
		GameRegistry.addSmelting(ItemName.get("RawMeal").itemID, new ItemStack(ProcesedFood), 0.35f);
		GameRegistry.addSmelting(ItemName.get("AlloyPlate").itemID, new ItemStack(ItemName.get("Alloyingot")), 0.35f);
		GameRegistry.addSmelting(ItemName.get("CarbonPlate").itemID, new ItemStack(ItemName.get("CarbonFiber")), 0.35f);
		
		//cvd recipes
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.coal), new ItemStack(Item.coal),new ItemStack(ItemName.get("Grafeno"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.redstone),new ItemStack(ItemName.get("RedstonePlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.dyePowder,1,4),new ItemStack(ItemName.get("AlloyPlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("GoldDust")), new ItemStack(ItemName.get("GoldDust")),new ItemStack(ItemName.get("GoldPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("IronDust")), new ItemStack(ItemName.get("IronDust")),new ItemStack(ItemName.get("IronPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(RadioniteBlock), new ItemStack(Item.ingotIron),new ItemStack(ItemName.get("RadionitePlate"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotGold), new ItemStack(Item.ingotGold),new ItemStack(ItemName.get("GoldPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotIron),new ItemStack(ItemName.get("IronPlate"),2)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("CarbonFiber")), new ItemStack(ItemName.get("Grafeno")),new ItemStack(ItemName.get("DiamondDust"),1)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(DecoBlocks.stoneblock,1,0), new ItemStack(Item.dyePowder,1,15),new ItemStack(DecoBlocks.stoneblock,1,2)));

		
		//purifier recipes
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sand), new ItemStack(ItemName.get("RawSilicon"),1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreGold), new ItemStack(ItemName.get("GoldDust"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.oreIron), new ItemStack(ItemName.get("IronDust"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.gravel), new ItemStack(Item.flint,1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.sandStone), new ItemStack(Block.sand,4)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(RadioniteOre), new ItemStack(ItemName.get("Radionite"),2)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.obsidian), new ItemStack(ItemName.get("IronDust"),1)));//ilogic
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.stone), new ItemStack(DecoBlocks.stoneblock,1,0)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Block.cobblestone), new ItemStack(DecoBlocks.stoneblock,1,0)));
		
		//cuter recipes
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.diamond), new ItemStack(ItemName.get("DiamondPlate"), 8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Item.ingotIron), new ItemStack(ItemName.get("Filter"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("AlloyPlate")), new ItemStack(ItemName.get("AlloyCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Grafeno")), new ItemStack(ItemName.get("GrafenoCable"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("SiliconPlate")), new ItemStack(ItemName.get("OpticFiber"), 1)));
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
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("RedstonePlate")), new ItemStack(ItemName.get("RedstoneCable"),3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("RawSilicon")), new ItemStack(ItemName.get("SiliconPlate"),1)));

		//assembly recipes
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',ItemName.get("Coil"),'r',ItemName.get("RedstoneCable"),'i',ItemName.get("IronPlate")}));
		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HidrogenBattery"),1), new Object[]{"hhh","aia","hhh",'h',ItemName.get("IronPlate"),'a',ItemName.get("AlloyPlate"),'i',ItemName.get("Filter")}));
		
		//oredict
		OreDictionary.registerOre("dustIron", ItemName.get("IronDust"));
		OreDictionary.registerOre("dustGold", ItemName.get("GoldDust"));
	}
}

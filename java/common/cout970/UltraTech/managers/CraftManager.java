package common.cout970.UltraTech.managers;

import api.cout970.UltraTech.microparts.MicroRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import common.cout970.UltraTech.TileEntities.electric.FluidGenerator;
import common.cout970.UltraTech.items.MetalPlate;
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
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(WindMill,1),new Object[]{"omf","opo","oco",'m',ItemName.get("Motor"),'f',ItemName.get("Fan"),'p',"plateGrafeno",'c',new ItemStack(Chasis,1,1)}));//windmill
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Engine,1),new Object[]{"pcp","aaa","pcp",'a',"plateAlloy_UT",'p',"plateGrafeno",'c',ItemName.get("Coil")}));//engine
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Boiler,1),new Object[]{"ctc","cac","ccc",'a',ItemName.get("HeatCoil"),'c',"plateCopper",'t',CopperPipe}));//boiler
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,0),new Object[]{"ccc","aaa","ccc",'a',ItemName.get("ElectricCell"),'c',"plateGrafeno"}));//storage mk1
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,1),new Object[]{"cpc","ama","cpc",'m',new ItemStack(Chasis,1,1),'c',"plateGrafeno",'p',"plateSilver",'a',ItemName.get("MediumElectricCell")}));//storage mk2
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Storage,1,2),new Object[]{"cpc","ama","cpc",'m',new ItemStack(Chasis,1,2),'c',"plateGrafeno",'p',"plateSilver",'a',ItemName.get("BigElectricCell")}));//storage mk3
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,0),new Object[]{"oco","oho","oeo",'c',Blocks.crafting_table,'h',new ItemStack(Chasis,1,0),'e',Blocks.chest}));//crafter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,1),new Object[]{"oco","oho","oco",'c',"ingotCopper",'h',new ItemStack(Chasis,1,0),'o',"ingotAluminum"}));//generator
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,2),new Object[]{"aaa","oho","iii",'a',"ingotAluminum",'h',new ItemStack(Chasis,1,0),'o',"ingotTin",'i',Items.iron_ingot}));//CVD
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,3),new Object[]{"cwc","oho","cwc",'c',"ingotCopper",'h',new ItemStack(Chasis,1,0),'o',"ingotTin",'w',Blocks.wool}));//painter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,4),new Object[]{"lll","lhl","lll",'l',"ingotLead",'h',new ItemStack(Chasis,1,0)}));//charge station
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier1,1,5),new Object[]{"ctc","tht","ctc",'c',"ingotCopper",'t',"ingotTin",'h',new ItemStack(Chasis,1,0)}));//fermenter
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,0),new Object[]{"iii","lml","lsl",'m',new ItemStack(Chasis,1,1),'i',"plateIron",'l',"plateAluminum",'s',"plateSilver"}));//furnace
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,1),new Object[]{"iii","lml","sss",'m',new ItemStack(Chasis,1,1),'i',"plateGrafeno",'l',"plateGold",'s',"plateIron"}));//purifier
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,2),new Object[]{"ixi","xmx","ixi",'m',new ItemStack(Chasis,1,1),'i',"plateGrafeno",'x',"dustDiamond"}));//cuter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,3),new Object[]{"xxx","xmx","iii",'m',new ItemStack(Chasis,1,1),'i',Blocks.piston,'x',"plateIron"}));//pressurizer
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier2,1,4),new Object[]{"oso","lml","oco",'o',"plateAluminum",'m',new ItemStack(Chasis,1,1),'l',Items.bucket,'s',"plateSilicon",'c',ItemName.get("Circuit")}));//fluid firebox(fluid generator<)

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,0),new Object[]{"dpd","gmg","scs",'m',new ItemStack(Chasis,1,2),'d',"plateDiamond",'g',"plateGold",'s',"plateSilver",'c',ItemName.get("AdvCircuit"),'p',Items.diamond_pickaxe}));//miner
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,1),new Object[]{"oio","csc","omo",'m',new ItemStack(Chasis,1,2),'i',"plateSilicon",'s',"plateSilver",'c',ItemName.get("Circuit")}));//hologram emiter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,2),new Object[]{"rrr","ggg","cmc",'m',new ItemStack(Chasis,1,2),'r',"plateRedstone",'g',"plateGold",'c',ItemName.get("Circuit")}));//adv crafter
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,3),new Object[]{"zxz","xmx","zxz",'m',new ItemStack(Chasis,1,2),'z',"plateSilver",'x',ItemName.get("AdvCircuit")}));//climate station
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tier3,1,4),new Object[]{"aaa","gmg","aaa",'m',new ItemStack(Chasis,1,2),'a',"plateAlloy_UT",'g',ItemName.get("AdvCircuit")}));//tesseract

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(AluminumPipe,8),new Object[]{"oao","aga","oao",'a',"plateAluminum",'g',Blocks.glass_pane}));//aluminum pipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CopperPipe,8),new Object[]{"oao","aga","oao",'a',"plateCopper",'g',Blocks.glass_pane}));//copper pipe
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(LeadPipe,8),new Object[]{"oao","aga","oao",'a',"plateLead",'g',Blocks.glass_pane}));//lead pipe

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CovedGlass,8),new Object[]{"oo","oo",'o',"plateSilicon"}));//coved glass
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,0),new Object[]{"lll","lml","lll",'l',"plateLead",'m',new ItemStack(Chasis,1,2)}));//reactor
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,4,1),new Object[]{"olo","lml","olo",'l',"plateLead",'m',new ItemStack(Chasis,1,1)}));//reactor wall
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,2),new Object[]{"olo","lml","olo",'o',"plateLead",'l',Blocks.glass_pane}));//reactor tank
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,3),new Object[]{"olo","lml","olo",'l',"plateLead",'m',ItemName.get("Circuit"),'o',"plateGrafeno"}));//reactor controller
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,4),new Object[]{"clo","lml","olc",'l',"plateLead",'m',new ItemStack(Chasis,1,1),'o',ItemName.get("Circuit"),'c',"plateGrafeno"}));//reactor water provider
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Reactor,1,5),new Object[]{"ooo","lml","ooo",'o',"plateLead",'m',new ItemStack(Chasis,1,1),'l',CopperPipe}));//reactor steam extractor
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Destilery,2,0),new Object[]{"ii","ii",'i',"plateCopper"}));//refinery block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Destilery,2,1),new Object[]{"xix","ili","xix",'i',"plateCopper",'l',"plateIron"}));//refinery input
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Destilery,2,2),new Object[]{"xix","ili","xix",'i',"plateCopper",'l',"plateLead"}));//refinery output
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Turbine,1),new Object[]{"xrx","iri","xrx",'x',"plateGrafeno",'i',Blocks.glass,'r',ItemName.get("Fan")}));//turbine
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(DiamondGlass,6),new Object[]{"xx","xx",'x',"plateDiamond"}));//diamond glass
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Pump,1),new Object[]{"xpx","ptp","mpt",'p',"plateIron",'t',AluminumPipe,'m',ItemName.get("Motor")}));//pump
		
		//items
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MicroRegistry.PlaneCable,3), new Object[]{"ttt","sss","ttt",'s',ItemName.get("SilverCable"),'t',Items.string}));//plane cable
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MicroRegistry.BigCable,3),new Object[]{"ttt","tst","ttt",'s',"plateSilver",'t',Items.string}));//Big cable
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("UpgradeBase"),4),new Object[]{"iii","ici","iii",'i',"plateIron",'c',ItemName.get("Circuit")}));//upgrade base
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("SpeedUpgrade"),1),new Object[]{"iii","ici","iii",'i',"plateSilver",'c',ItemName.get("UpgradeBase")}));//speed upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("MiningUpgrade"),2),new Object[]{"iii","ici","iii",'i',"plateGold",'c',ItemName.get("UpgradeBase")}));//mining upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RangeUpgrade"),3),new Object[]{"iii","ici","iii",'i',"plateRedstone",'c',ItemName.get("UpgradeBase")}));//range upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("AutoEjectUpgrade"),1),new Object[]{"iii","ici","iii",'i',"plateTin",'c',ItemName.get("UpgradeBase")}));//auto eject upgrade
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("FortuneUpgrade"),1),new Object[]{"iii","ici","iii",'i',"plateDiamond",'c',ItemName.get("UpgradeBase")}));//fortune upgrade

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Linker"),1),new Object[]{"iio","iio","oox",'i',"plateIron",'x',"plateRedstone"}));//Linker
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("RadioniteCell"),1),new Object[]{"ioi","ioi","ioi",'i',"plateIron",'o',"plateRadionite"}));//Radionite Cell
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.Misc,1,0),new Object[]{"iii","iii","iii",'i',ItemName.get("Radionite")}));//Radionite block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Circuit"),1),new Object[]{"igi","iri","igi",'g',"plateGrafeno",'r',"plateRedstone",'i',Items.redstone}));//Circuit
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.stoneblock,4,1),new Object[]{"ii","ii",'i',new ItemStack(BlockManager.stoneblock,4,0)}));//Deco block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockManager.stoneblock,4,3),new Object[]{"ii","ii",'i',new ItemStack(BlockManager.stoneblock,4,2)}));//Deco block
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("GrafenoPlate"),1),new Object[]{"iii","iii","iii",'i',"plateGrafeno"}));//plate grafeno
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("LasserSword"),1),new Object[]{"c","c","i",'i',"plateIron",'c',"plateAlloy_UT"}));//sword
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Tank,1),new Object[]{"isi","sxs","isi",'i',"plateCopper",'s',"plateSilicon"}));//fluid tank
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(MultiTank,4),new Object[]{"pgp","ggg","pgp",'p',"plateAluminum",'g',"blockGlass"}));//MultiTank
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("SulfuricAcid"),1),new Object[]{"ws",'w',Items.potionitem,'s',"dustSulfur"}));//sulfuric acid
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("ElectricCell"),1),new Object[]{"cal",'c',"plateCopper",'l',"plateLead",'a',ItemName.get("SulfuricAcid")}));//electric cell
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("MediumElectricCell"),1),new Object[]{"ccc","ccc","ccc",'c',ItemName.get("ElectricCell")}));//medium electric cell
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("BigElectricCell"),1),new Object[]{"ccc","ccc","ccc",'c',ItemName.get("MediumElectricCell")}));//big electric cell
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.gunpowder,3),new Object[]{"cs",'c',Items.coal,'s',"dustSulfur"}));//gunpowder
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemName.get("Battery"),1),new Object[]{"ese","clc","glg",'s',"plateSilver",'l',ItemName.get("ElectricCell"),'c',"plateCopper",'g',"plateGrafeno"}));//battery
		
		Smelting();//furnace recipes
//		CVD();
		Purifier();
		Cutter();
		Assembly();

		//presurizer
//		Pressurizer_Recipes.addRecipe(new Pressurizer_Recipes(new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(ItemManager.ItemName.get("Plate"),1,8), new ItemStack(DiamondGlass,4)));
//		Pressurizer_Recipes.addRecipe(new Pressurizer_Recipes(new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemManager.ItemName.get("Plate"),1,9), new ItemStack(ItemName.get("GrafenoPlate"),1)));

		//fermenter recipes
		Fermenter_Recipes.recipes.put(Items.sugar, 75);
		Fermenter_Recipes.recipes.put(Items.apple, 25);
		Fermenter_Recipes.recipes.put(Items.wheat, 100);
		
		//liquid recipes
		Boiler_Recipes.recipes.put("juice", "gas_ethanol");
		Boiler_Recipes.recipes.put("oil", "gas_oil");
		Boiler_Recipes.recipes.put("water", "steam");
		
		Cooling_Recipes.recipes.add(new Cooling_Recipes(new FluidStack(FluidRegistry.getFluid("gas_ethanol"),100), new FluidStack(FluidRegistry.getFluid("bioethanol"),2), null, null));
		if(FluidRegistry.getFluid("fuel") != null)Cooling_Recipes.recipes.add(new Cooling_Recipes(new FluidStack(FluidRegistry.getFluid("gas_oil"),100), null, new FluidStack(FluidRegistry.getFluid("fuel"),10), new FluidStack(FluidRegistry.getFluid("gasoline"),1)));
		
		if(FluidRegistry.isFluidRegistered("fuel"))FluidGenerator.fuels.put("fuel", 185);
		FluidGenerator.fuels.put("gasoline", 600);
		FluidGenerator.fuels.put("bioethanol", 75);
	}

	public static void Smelting(){
		
		for(int r = 0;r < 6;r++)//dust to ingots
			GameRegistry.addSmelting(new ItemStack(ItemName.get("Dust"),1, r), new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ItemName.get("Dust"),1, 6), new ItemStack(Items.iron_ingot,1), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ItemName.get("Dust"),1, 7), new ItemStack(Items.gold_ingot,1), 0.5f);

		for(int r = 1;r < 6;r++)//ores to ingots
			GameRegistry.addSmelting(new ItemStack(Ores,1,r),new ItemStack(ItemName.get("Ingot"),1,r-1), 0.5f);

		for(int r=0;r<5;r++)GameRegistry.addSmelting(new ItemStack(ItemName.get("MetalPlate"),1,r), new ItemStack(ItemName.get("Ingot"),1,r), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ItemName.get("MetalPlate"),1,5), new ItemStack(Items.iron_ingot), 0.5f);
		GameRegistry.addSmelting(new ItemStack(ItemName.get("MetalPlate"),1,6), new ItemStack(Items.gold_ingot), 0.5f);
		
		//other
		GameRegistry.addSmelting(ItemName.get("RawMeal"), new ItemStack(ItemName.get("ProcesedFood")), 0.35f);
	}

	
	public static void CVD(){
		//cvd recipes
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Items.coal), new ItemStack(Items.coal),new ItemStack(ItemName.get("Plate"),2,9)));//grafeno
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Items.coal,1,1), new ItemStack(Items.coal,1,1),new ItemStack(ItemName.get("Plate"),2,9)));//grafeno
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Items.iron_ingot), new ItemStack(Items.redstone),new ItemStack(ItemName.get("Plate"),1,11)));//restone plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,4), new ItemStack(Items.dye,1,4),new ItemStack(ItemName.get("Plate"),1,5)));//alloy
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Radionite"),1), new ItemStack(ItemName.get("Radionite"),1),new ItemStack(ItemName.get("Plate"),1,12)));//radionite plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("GrafenoPlate")), new ItemStack(ItemName.get("GrafenoPlate")),new ItemStack(ItemName.get("Dust"),1,8)));//diamond dust
//		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(DecoBlocks.stoneblock,1,0), new ItemStack(Item.dyePowder,1,15),new ItemStack(DecoBlocks.stoneblock,1,2)));/*--Remove--*/
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,7), new ItemStack(ItemName.get("Dust"),1,7),new ItemStack(ItemName.get("Plate"),2,7)));//gold plate
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,6), new ItemStack(ItemName.get("Dust"),1,6),new ItemStack(ItemName.get("Plate"),2,6)));//iron plate
		for(int r = 0;r < 6;r++)//dust to plates
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,r), new ItemStack(ItemName.get("Dust"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));
		for(int r = 0;r < 6;r++)//ingot to plates
			CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Ingot"),1,r), new ItemStack(ItemName.get("Ingot"),1,r),new ItemStack(ItemName.get("Plate"),2,r)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Items.gold_ingot), new ItemStack(Items.gold_ingot),new ItemStack(ItemName.get("Plate"),2,7)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(Items.iron_ingot), new ItemStack(Items.iron_ingot),new ItemStack(ItemName.get("Plate"),2,6)));
		CVD_Recipe.addRecipe(new CVD_Recipe(new ItemStack(ItemName.get("Dust"),1,0), new ItemStack(stoneblock,1,0),new ItemStack(stoneblock,1,2)));
	}
	
	public static void Purifier(){
		//purifier recipes
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Blocks.sand), new ItemStack(ItemName.get("RawSilicon"),1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Blocks.gravel), new ItemStack(Items.flint,1)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Blocks.sandstone), new ItemStack(Blocks.sand,4)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Blocks.stone), new ItemStack(stoneblock,1,0)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Blocks.cobblestone), new ItemStack(stoneblock,1,0)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(Items.quartz), new ItemStack(stoneblock,1,2)));
		for(int r = 0;r <5;r++)//chunk to dust
			Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,r),new ItemStack(ItemName.get("Dust"),1,r)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,5),new ItemStack(ItemName.get("Dust"),1,6)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,6),new ItemStack(ItemName.get("Dust"),1,7)));
		Purifier_Recipe.addRecipe(new Purifier_Recipe(new ItemStack(ItemName.get("Chunk"),1,7),new ItemStack(ItemName.get("Radionite"),1)));
	}
	public static void Cutter(){
		//cuter recipes
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.reeds), new ItemStack(Items.sugar,2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.wool), new ItemStack(Items.string,4)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.sand,1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.bone), new ItemStack(Items.dye,6,15)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.diamond), new ItemStack(ItemName.get("UnorganicPlate"),8,0)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.cookie), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.rotten_flesh), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.porkchop), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.beef), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.potato), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.chicken), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.spider_eye), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.fish), new ItemStack(ItemName.get("RawMeal"), 1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.carrot), new ItemStack(ItemName.get("RawMeal"), 2)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Items.bone), new ItemStack(Items.bone,3,15)));
//		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("RawSilicon")),new ItemStack(ItemName.get("Plate"),1,10)));
		for(int r = 1;r <6;r++)//ores to chunk
			Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Ores,1,r),new ItemStack(ItemName.get("Chunk"),3,r-1)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Ores,1,0),new ItemStack(ItemName.get("Chunk"),1,7)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.iron_ore,1),new ItemStack(ItemName.get("Chunk"),3,5)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.gold_ore,1),new ItemStack(ItemName.get("Chunk"),3,6)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(ItemName.get("Plate"),1,4),new ItemStack(ItemName.get("SilverCable"),3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.coal_ore,1),new ItemStack(Items.coal,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.lapis_ore,1),new ItemStack(Items.dye,3,4)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.redstone_ore,1),new ItemStack(Items.redstone,8)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.emerald_ore,1),new ItemStack(Items.emerald,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.quartz_ore,1),new ItemStack(Items.quartz,3)));
		Cuter_Recipes.addRecipe(new Cuter_Recipes(new ItemStack(Blocks.diamond_ore,1),new ItemStack(Items.diamond,3)));
	}
	public static void Assembly(){
		//assembly recipes
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Motor"),1), new Object[]{"rc ","iii","rc ",'c',ItemName.get("Coil"),'r', new ItemStack(ItemManager.ItemName.get("Plate"),1,4),'i',new ItemStack(ItemName.get("Plate"),1,6)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Battery"),1), new Object[]{"hah","hah","hah",'h',new ItemStack(ItemName.get("Plate"),1,0),'a',new ItemStack(ItemName.get("Plate"),1,5)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(SolarPanel,1), new Object[]{"hhh","aaa","bbb",'h',new ItemStack(ItemName.get("Plate"),1,10),'a',new ItemStack(ItemName.get("Plate"),1,5),'b', new ItemStack(ItemManager.ItemName.get("Plate"),1,4)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("AdvCircuit"),1), new Object[]{"hph","hah","hph",'h',new ItemStack(MicroRegistry.PlaneCable,1),'a',new ItemStack(ItemName.get("Plate"),1,10),'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,8)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Fan"),1), new Object[]{"opo","ppp","opo",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,9)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("Coil"),2), new Object[]{"rrr","ppp","rrr",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,6),'r',new ItemStack(ItemManager.ItemName.get("Plate"),1,11)}));
//		Assembly_Recipes.addRecipe(new Assembly_Recipes(new ItemStack(ItemName.get("HeatCoil"),1), new Object[]{"pp ","pp ","   ",'p',new ItemStack(ItemManager.ItemName.get("Plate"),1,5)}));
	}

}

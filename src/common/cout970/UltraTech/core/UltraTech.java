package common.cout970.UltraTech.core;


//minecraft
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import common.cout970.UltraTech.blocks.BlockManager;
//my
import common.cout970.UltraTech.handlers.GuiHandler;
import common.cout970.UltraTech.handlers.PacketHandler;
import common.cout970.UltraTech.handlers.WorldGen;
import common.cout970.UltraTech.items.*;
import common.cout970.UltraTech.lib.Reference;
import common.cout970.UltraTech.machines.blocks.*;
import common.cout970.UltraTech.machines.tileEntities.CVDentity;
import common.cout970.UltraTech.machines.tileEntities.CuterEntity;
import common.cout970.UltraTech.machines.tileEntities.EnergyIOentity;
import common.cout970.UltraTech.machines.tileEntities.GeneratorEntity;
import common.cout970.UltraTech.machines.tileEntities.IDSentity;
import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import common.cout970.UltraTech.machines.tileEntities.MolecularAssemblyEntity;
import common.cout970.UltraTech.machines.tileEntities.PCentity;
import common.cout970.UltraTech.machines.tileEntities.PurifierEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorWallEntity;
import common.cout970.UltraTech.machines.tileEntities.ReciverEntity;
import common.cout970.UltraTech.machines.tileEntities.SateliteEntity;
import common.cout970.UltraTech.machines.tileEntities.SteamTurbineEntity;
import common.cout970.UltraTech.machines.tileEntities.UTfurnaceEntity;
import common.cout970.UltraTech.machines.tileEntities.WaterBlockEntity;
import common.cout970.UltraTech.misc.ID;
import common.cout970.UltraTech.proxy.CommonProxy;

//forge
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;



@Mod(modid = "UltraTech", name = "UltraTech",version = "0.5.1")

@NetworkMod(clientSideRequired=true, serverSideRequired=true, channels={"UltraTech","UltraTech2"}, packetHandler = PacketHandler.class)

public class UltraTech {

	//Mod instances
	@Instance("UltraTech")
	public static UltraTech instance;
	
	//Proxy
	@SidedProxy(clientSide="common.cout970.UltraTech.proxy.ClientProxy",serverSide="common.cout970.UltraTech.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	//creative tabs
	public static final CreativeTabs techTab = new CreativeTabs("UltraTech"){
		
		public ItemStack getIconItemStack() {
			return new ItemStack(ItemName.get("LapisPearl"), 1, 0);
		}
		
		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
	    {
	        return "UltraTech";
	    }
		
		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech";
		}
	};
	
public static final CreativeTabs DecoTab = new CreativeTabs("UltraTech"){
		
		public ItemStack getIconItemStack() {
			return new ItemStack(GrafenoBlock, 1, 0);
		}
		
		@SideOnly(Side.CLIENT)
		@Override
		public String getTranslatedTabLabel()
	    {
	        return "UltraTech Decoration";
	    }
		
		@SideOnly(Side.CLIENT)
		@Override
		public String getTabLabel()
		{
			return "UltraTech Decoration";
		}
	};
	
	public static List<UT_Item> items = new ArrayList<UT_Item>();
	public static Map<String,UT_Item> ItemName = new HashMap<String,UT_Item>();
	public static Map<UT_Item,String> LangNames = new HashMap<UT_Item,String>();
	Map<String,ID> itemsID = new HashMap<String,ID>();
	//code name
	String[] item ={"Circuit","Grafeno","SiliconPlate","DiamondPlate","GoldPlate","IronPlate","Trinitramida","RedstonePlate","AlloyPlate","RadionitePlate","GrafenoPlate",
			"RedstoneCable","EnergyTransmiter","HeatCoil","RawSilicon","LasserSword","Radionite","RawMeal","IronDust","GoldDust","LapisPearl","Filter","SolarPanel",
			"GrafenoCable","OpticFiber","AlloyCable","Coil","UpgradeBase","CarbonPlate","CarbonFiber","AdvCircuit","DiamondDust","Motor","Alloyingot","Fan"};
	//game name
	String[] lang ={"Circuit","Grafeno","Silicon Plate","Diamond Plate","Gold Plate","Iron Plate","Trinitramida","Redstone Plate","Alloy Plate","Radionite Plate","Grafeno Plate",
			"Redstone Cable","Energy Transmiter","Heat Coil","Raw Silicon","Lasser Sword","Radionite","Raw Meal","Iron Dust","Gold Dust","Lapis Pearl","Filter","Solar Panel",
			"Grafeno Cable","Optic Fiber","Alloy Cable","Coil","Upgrade Base","Carbon Plate","Carbon Fiber","Advanced Circuit","Diamond Dust","Motor","Alloy ingot","Fan",
			"Speed Upgrade","Mining Upgrade","Range Upgrade","AutoEject Upgrade","Linker","Radionite Cell"};
	
	//Blocks
	public static Block CVDmachine;
	public static  Block UTfurnace;
	public static  Block IDS;
	public static  Block Satelite;
	public static  Block Cuter;
	public static  Block Purifier;
	public static  Block PresureChamber;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;
	public static  Block SateliteBox;
	public static  Block Generator;
	public static  Block MachineChasis;
	public static  Block Miner;
	public static  Block GrafenoBlock;
	public static  Block AdvMachineChasis;
	public static  Block Reactor;
	public static  Block ReactorWall;
	public static  Block EnergyIO;
	public static  Block Reciver;
	public static  Block RadioniteBlock;
	public static  Block ReactorTank;
	public static Fluid Steam;
	public static  Block SteamTurbine;
	public static  Block WaterBlock;
	public static Block RadioniteOre;
	public static Block MolecularAssembly;
	public static  Item ProcesedFood;

	
	
	
	
	//init
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){

		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		//blocks
		Reference.CVDmachine = config.getBlock("CVDmachine", 2049).getInt();
		Reference.UTfurnace = config.getBlock("UTfurnace", 2051).getInt();
		Reference.IDS = config.getBlock("IDS", 2052).getInt();
		Reference.Satelite = config.getBlock("Satelite", 2053).getInt();
		Reference.Cuter = config.getBlock("Cuter", 2054).getInt();
		Reference.Purifier = config.getBlock("Purifier", 2055).getInt();
		Reference.PresureChamber = config.getBlock("PresureChamber", 2056).getInt();
		Reference.DiamondGlass = config.getBlock("DiamondGlass", 2057).getInt();
		Reference.CovedGlass = config.getBlock("CovedGlass", 2058).getInt();
		Reference.SateliteBox = config.getBlock("SateliteBox", 2059).getInt();
		Reference.Generator = config.getBlock("Generator", 2060).getInt();
		Reference.MachineChasis = config.getBlock("MachineChasis", 2061).getInt();
		Reference.Miner = config.getBlock("Miner", 2062).getInt();
		Reference.GrafenoBlock = config.getBlock("GrafenoBlock", 2063).getInt();
		Reference.AdvMachineChasis = config.getBlock("AdvMachineChasis", 2064).getInt();
		Reference.Reactor = config.getBlock("Reactor", 2065).getInt();
		Reference.ReactorWall = config.getBlock("ReactorWall", 2066).getInt();
		Reference.EnergyIO = config.getBlock("EnergyIO", 2067).getInt();
		Reference.Reciver = config.getBlock("Reciver", 2068).getInt();
		Reference.RadioniteBlock = config.getBlock("RadioniteBlock", 2069).getInt();
		Reference.ReactorTank = config.getBlock("ReactorTank", 2070).getInt();
		Reference.Steam = config.getBlock("Steam", 2071).getInt();
		Reference.SteamTurbine = config.getBlock("SteamTurbine",2072).getInt();
		Reference.WaterBlock = config.getBlock("WaterBlock",2073).getInt();
		Reference.RadioniteOre = config.getBlock("RadioniteOre",2074).getInt();
		Reference.MolecularAssembly = config.getBlock("MolecularAssembly", 2075).getInt();
		Reference.Deco = config.getBlock("Deco", 2076).getInt();
		//items

		Reference.ProcesedFood = config.getItem("ProcesedFood", 5020).getInt();
		initIDs(config);
		
		config.save();
	}
	
	//items
		public void initIDs(Configuration c){
			int def = 5021;
			for(String i : item){
			itemsID.put(i, new ID(c.getItem(i, def).getInt()));
			def++;
			}
			itemsID.put("SpeedUpgrade", new ID(c.getItem("SpeedUpgrade", def).getInt()));def++;
			itemsID.put("MiningUpgrade", new ID(c.getItem("MiningUpgrade", def).getInt()));def++;
			itemsID.put("RangeUpgrade", new ID(c.getItem("RangeUpgrade", def).getInt()));def++;
			itemsID.put("AutoEjectUpgrade", new ID(c.getItem("AutoEjectUpgrade", def).getInt()));def++;
			itemsID.put("Linker", new ID(c.getItem("Linker", def).getInt()));def++;
			itemsID.put("RadioniteCell", new ID(c.getItem("RadioniteCell", def).getInt()));def++;

		}
	
	private void registerBlocks(){
		CVDmachine = new CVDmachine(Reference.CVDmachine,Material.iron);
		UTfurnace = new UTfurnace(Reference.UTfurnace,Material.iron);
		IDS = new InterdimensionalStorageBlock(Reference.IDS,Material.iron);
		Satelite = new Satelite(Reference.Satelite,Material.iron);
		Cuter = new PrecisionCuter(Reference.Cuter,Material.iron);
		Purifier = new Purifier(Reference.Purifier,Material.iron);
		PresureChamber = new PresureChamber(Reference.PresureChamber,Material.iron);	
		DiamondGlass = new DiamondGlass(Reference.DiamondGlass,Material.glass,false);	
		CovedGlass = new CovedGlass(Reference.CovedGlass,Material.glass,false);
		SateliteBox = new SateliteBox(Reference.SateliteBox,Material.iron);	
		Generator = new Generator(Reference.Generator,Material.iron);
		MachineChasis = new MachineChasis(Reference.MachineChasis,Material.iron);
		Miner = new Miner(Reference.Miner,Material.iron);
		GrafenoBlock = new GrafenoBlock(Reference.GrafenoBlock,Material.iron);
		AdvMachineChasis = new AdvancedMachineChasis(Reference.AdvMachineChasis,Material.iron);
		Reactor = new Reactor(Reference.Reactor,Material.iron);
		ReactorWall = new ReactorWall(Reference.ReactorWall,Material.iron);
		EnergyIO  = new EnergyIO(Reference.EnergyIO,Material.iron);
		Reciver = new Reciver(Reference.Reciver,Material.iron);
		RadioniteBlock = new RadioniteBlock(Reference.RadioniteBlock,Material.iron);
		ReactorTank = new ReactorTank(Reference.ReactorTank,Material.iron);
		Steam = new Steam("steam");
		SteamTurbine = new SteamTurbine(Reference.SteamTurbine,Material.iron);
		WaterBlock = new WaterBlock(Reference.WaterBlock, Material.iron);
		RadioniteOre = new RadioniteOre(Reference.RadioniteOre, Material.iron);
		MolecularAssembly = new MolecularAssembly(Reference.MolecularAssembly, Material.iron);
		
		FluidRegistry.registerFluid(Steam);
		
	}
	
	private void registerItems(){
		
		ProcesedFood = new ProcesedFood(Reference.ProcesedFood);
		GameRegistry.registerItem(ProcesedFood, ProcesedFood.getUnlocalizedName()+"_UT");
		LanguageRegistry.addName(ProcesedFood, "Procesed Food");

		for(String i : item){
			items.add(new UT_Item(itemsID.get(i).id,i));
		}
		
		items.add(new SpeedUpgrade(itemsID.get("SpeedUpgrade").id,"SpeedUpgrade"));
		items.add(new MiningUpgrade(itemsID.get("MiningUpgrade").id,"MiningUpgrade"));
		items.add(new RangeUpgrade(itemsID.get("RangeUpgrade").id,"RangeUpgrade"));
		items.add(new AutoEjectUpgrade(itemsID.get("AutoEjectUpgrade").id,"AutoEjectUpgrade"));
		items.add(new Linker(itemsID.get("Linker").id,"Linker"));
		items.add(new RadioniteCell(itemsID.get("RadioniteCell").id,"RadioniteCell"));

		int d = 0;
		for(UT_Item i : items){
			ItemName.put(i.getName(), i);
			LangNames.put(i, lang[d]);
			d++;
		}
		
		for(UT_Item i : items){
			GameRegistry.registerItem(i, i.getName()+"_UT");
			LanguageRegistry.addName(i, LangNames.get(i));
		}
	}

	

	@EventHandler
	public void load(FMLInitializationEvent event){
		
		
		registerBlocks();
		registerItems();
		BlockManager.initBlocks();
		
		proxy.registerRenders();

		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());

		//CVD
		GameRegistry.registerTileEntity(CVDentity.class, "cvd_UT");
		GameRegistry.registerBlock(CVDmachine, "cvd");
		LanguageRegistry.addName(CVDmachine, "CVD Machine");
		//UTfurnace
		GameRegistry.registerTileEntity(UTfurnaceEntity.class, "utf_UT");
		GameRegistry.registerBlock(UTfurnace, "utf");
		LanguageRegistry.addName(UTfurnace, "Ultra Tech Furnace");
		//IDS
		GameRegistry.registerTileEntity(IDSentity.class, "ids_UT");
		GameRegistry.registerBlock(IDS, "ids");
		LanguageRegistry.addName(IDS, "Interdimensional Storage");
		//satelite
		GameRegistry.registerTileEntity(SateliteEntity.class, "sat_UT");
		GameRegistry.registerBlock(Satelite, "sat");
		LanguageRegistry.addName(Satelite, "Satelite solar");
		GameRegistry.registerBlock(SateliteBox, "SateliteBox");
		//cuter
		GameRegistry.registerTileEntity(CuterEntity.class, "cuter_UT");
		GameRegistry.registerBlock(Cuter, "cuter");
		LanguageRegistry.addName(Cuter, "Precision Cuter");
		//purifier
		GameRegistry.registerTileEntity(PurifierEntity.class, "purifier_UT");
		GameRegistry.registerBlock(Purifier, "purifier");
		LanguageRegistry.addName(Purifier, "Purifier");
		//cuter
		GameRegistry.registerTileEntity(PCentity.class, "PC_UT");
		GameRegistry.registerBlock(PresureChamber, "PC");
		LanguageRegistry.addName(PresureChamber, "Presure Chamber");
		//Generator
		GameRegistry.registerTileEntity(GeneratorEntity.class, "Generator_UT");
		GameRegistry.registerBlock(Generator, "Generator");
		LanguageRegistry.addName(Generator, "Coal Generator");
		//Miner
		GameRegistry.registerTileEntity(MinerEntity.class, "Miner_UT");
		GameRegistry.registerBlock(Miner, "Miner");
		LanguageRegistry.addName(Miner, "Miner");
		//Reactor
		GameRegistry.registerTileEntity(ReactorEntity.class, "Reactor_UT");
		GameRegistry.registerBlock(Reactor, "Reactor");
		LanguageRegistry.addName(Reactor, "Reactor");
		//reactorwall
		GameRegistry.registerTileEntity(ReactorWallEntity.class, "ReactorWall_UT");
		GameRegistry.registerBlock(ReactorWall, "ReactorWall");
		LanguageRegistry.addName(ReactorWall, "ReactorWall");
		//EnergyIO
		GameRegistry.registerTileEntity(EnergyIOentity.class, "EnergyIO_UT");
		GameRegistry.registerBlock(EnergyIO, "EnergyIO");
		LanguageRegistry.addName(EnergyIO, "EnergyIO");
		//Reciver
		GameRegistry.registerTileEntity(ReciverEntity.class, "Reciver_UT");
		GameRegistry.registerBlock(Reciver, "Reciver");
		LanguageRegistry.addName(Reciver, "Reciver");
		//ReactorTank
		GameRegistry.registerTileEntity(ReactorTankEntity.class, "ReactorTank_UT");
		GameRegistry.registerBlock(ReactorTank, "ReactorTank");
		LanguageRegistry.addName(ReactorTank, "Reactor Tank");
		//SteamTurbine
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "SteamTurbine_UT");
		GameRegistry.registerBlock(SteamTurbine, "SteamTurbine");
		LanguageRegistry.addName(SteamTurbine, "Steam Turbine");
		//WaterBlock
		GameRegistry.registerTileEntity(WaterBlockEntity.class, "WaterBlock_UT");
		GameRegistry.registerBlock(WaterBlock, "WaterBlock");
		LanguageRegistry.addName(WaterBlock, "Water Block");
		//MolecularAssembly
		GameRegistry.registerTileEntity(MolecularAssemblyEntity.class, "MolecularAssembly_UT");
		GameRegistry.registerBlock(MolecularAssembly, "MolecularAssembly");
		LanguageRegistry.addName(MolecularAssembly, "Molecular Assembly");
		
		//DiamondGlass
		GameRegistry.registerBlock(DiamondGlass, "Diamond Glass");
		LanguageRegistry.addName(DiamondGlass, "Diamond Glass");
		//CovedGlass
		GameRegistry.registerBlock(CovedGlass, "Coved Glass");
		LanguageRegistry.addName(CovedGlass, "Coved Glass");
		//MachineChasis
		GameRegistry.registerBlock(MachineChasis, "MachineChasis");
		LanguageRegistry.addName(MachineChasis, "Machine Chasis");
		//GrafenoBlock
		GameRegistry.registerBlock(GrafenoBlock, "GrafenoBlock");
		LanguageRegistry.addName(GrafenoBlock, "Grafeno Block");
		//AdvMachineChasis
		GameRegistry.registerBlock(AdvMachineChasis, "AdvMachineChasis");
		LanguageRegistry.addName(AdvMachineChasis, "Advanced MachineChasis");
		//RadioniteBlock
		GameRegistry.registerBlock(RadioniteBlock, "RadioniteBlock");
		LanguageRegistry.addName(RadioniteBlock, "Radionite Block");
		//RadioniteOre
		GameRegistry.registerBlock(RadioniteOre, "RadioniteOre");
		LanguageRegistry.addName(RadioniteOre, "Radionite Ore");


		
		CraftManager.registerCraft();
		GameRegistry.registerWorldGenerator(new WorldGen());
	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event){

	}
	


}

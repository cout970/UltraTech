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
import common.cout970.UltraTech.machines.tileEntities.ChargeStationEntity;
import common.cout970.UltraTech.machines.tileEntities.CuterEntity;
import common.cout970.UltraTech.machines.tileEntities.EnergyColectorEntity;
import common.cout970.UltraTech.machines.tileEntities.GeneratorEntity;
import common.cout970.UltraTech.machines.tileEntities.IDSentity;
import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import common.cout970.UltraTech.machines.tileEntities.MolecularAssemblyEntity;
import common.cout970.UltraTech.machines.tileEntities.PCentity;
import common.cout970.UltraTech.machines.tileEntities.Printer3DEntity;
import common.cout970.UltraTech.machines.tileEntities.PurifierEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorTankEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorWallEntity;
import common.cout970.UltraTech.machines.tileEntities.ReciverEntity;
import common.cout970.UltraTech.machines.tileEntities.SenderEntity;
import common.cout970.UltraTech.machines.tileEntities.SolarPanelEntity;
import common.cout970.UltraTech.machines.tileEntities.SteamTurbineEntity;
import common.cout970.UltraTech.machines.tileEntities.UTfurnaceEntity;
import common.cout970.UltraTech.machines.tileEntities.WaterBlockEntity;
import common.cout970.UltraTech.machines.tileEntities.WindMillEntity;
import common.cout970.UltraTech.machines.tileEntities.hitBoxEntity;
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



@Mod(modid = "UltraTech", name = "UltraTech",version = "0.7")

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
			return new ItemStack(IDS, 1, 0);
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
			return new ItemStack(BlockManager.d.get(1), 1, 4);
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
			"RedstoneCable","EnergyTransmiter","HeatCoil","RawSilicon","Radionite","RawMeal","IronDust","GoldDust","LapisPearl","Filter","SolarPanel",
			"GrafenoCable","OpticFiber","AlloyCable","Coil","UpgradeBase","CarbonPlate","CarbonFiber","AdvCircuit","DiamondDust","Motor","Alloyingot","Fan"};
	//game name
	String[] lang ={"Circuit","Grafeno","Silicon Plate","Diamond Plate","Gold Plate","Iron Plate","Trinitramida","Redstone Plate","Alloy Plate","Radionite Plate","Grafeno Plate",
			"Redstone Cable","Energy Transmiter","Heat Coil","Raw Silicon","Radionite","Raw Meal","Iron Dust","Gold Dust","Lapis Pearl","Filter","Solar Panel",
			"Grafeno Cable","Optic Fiber","Alloy Cable","Coil","Upgrade Base","Carbon Plate","Carbon Fiber","Advanced Circuit","Diamond Dust","Nano Engine","Alloy ingot","Fan",
			
			"Speed Upgrade","Mining Upgrade","Range Upgrade","AutoEject Upgrade","Linker","Radionite Cell","Hidrogen Battery","Lasser Sword","Fortune Upgrade"};

	

	//Blocks
	public static Block CVDmachine;
	public static  Block UTfurnace;
	public static  Block IDS;
	public static  Block EnergyColector;
	public static  Block Cuter;
	public static  Block Purifier;
	public static  Block PresureChamber;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;
	public static  Block hitBox;
	public static  Block Generator;
	public static  Block MachineChasis;
	public static  Block Miner;
	public static  Block GrafenoBlock;
	public static  Block AdvMachineChasis;
	public static  Block Reactor;
	public static  Block ReactorWall;
	public static  Block Sender;
	public static  Block Reciver;
	public static  Block RadioniteBlock;
	public static  Block ReactorTank;
	public static Fluid Steam;
	public static  Block SteamTurbine;
	public static  Block WaterBlock;
	public static Block RadioniteOre;
	public static Block MolecularAssembly;
	public static  Item ProcesedFood;
	public static Block StoneBlock;
	public static Block StoneBlockBricks;
	public static Block ChargeStation;
	public static Block SolarPanel;
	public static Block WindMill;
	public static Block Printer3D;




	//init
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){


		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();

		//blocks
		Reference.CVDmachine = config.getBlock("CVDmachine", 2049).getInt();
		Reference.UTfurnace = config.getBlock("UTfurnace", 2051).getInt();
		Reference.IDS = config.getBlock("IDS", 2052).getInt();
		Reference.EnergyColector = config.getBlock("EnergyColector", 2053).getInt();
		Reference.Cuter = config.getBlock("Cuter", 2054).getInt();
		Reference.Purifier = config.getBlock("Purifier", 2055).getInt();
		Reference.PresureChamber = config.getBlock("PresureChamber", 2056).getInt();
		Reference.DiamondGlass = config.getBlock("DiamondGlass", 2057).getInt();
		Reference.CovedGlass = config.getBlock("CovedGlass", 2058).getInt();
		Reference.hitBox = config.getBlock("SateliteBox", 2059).getInt();
		Reference.Generator = config.getBlock("Generator", 2060).getInt();
		Reference.MachineChasis = config.getBlock("MachineChasis", 2061).getInt();
		Reference.Miner = config.getBlock("Miner", 2062).getInt();
		Reference.GrafenoBlock = config.getBlock("GrafenoBlock", 2063).getInt();
		Reference.AdvMachineChasis = config.getBlock("AdvMachineChasis", 2064).getInt();
		Reference.Reactor = config.getBlock("Reactor", 2065).getInt();
		Reference.ReactorWall = config.getBlock("ReactorWall", 2066).getInt();
		Reference.Sender = config.getBlock("Sender", 2067).getInt();
		Reference.Reciver = config.getBlock("Reciver", 2068).getInt();
		Reference.RadioniteBlock = config.getBlock("RadioniteBlock", 2069).getInt();
		Reference.ReactorTank = config.getBlock("ReactorTank", 2070).getInt();
		Reference.Steam = config.getBlock("Steam", 2071).getInt();
		Reference.SteamTurbine = config.getBlock("SteamTurbine",2072).getInt();
		Reference.WaterBlock = config.getBlock("WaterBlock",2073).getInt();
		Reference.RadioniteOre = config.getBlock("RadioniteOre",2074).getInt();
		Reference.MolecularAssembly = config.getBlock("MolecularAssembly", 2075).getInt();
		Reference.ChargeStation = config.getBlock("ChargeStation", 2076).getInt();
		Reference.SolarPanel = config.getBlock("SolarPanel", 2077).getInt();
		Reference.WindMill = config.getBlock("WindMill", 2078).getInt();
		Reference.Printer3D = config.getBlock("Printer3D", 2079).getInt();
		
		//deco
		Reference.StoneBlock = config.getBlock("StoneBlock", 2098).getInt();
		Reference.StoneBlockBricks = config.getBlock("StoneBlockBricks", 2099).getInt();
		Reference.Deco = config.getBlock("Deco", 2100).getInt();
		Reference.Deco2 = config.getBlock("Deco2", 2101).getInt();
		Reference.Deco3 = config.getBlock("Deco3", 2102).getInt();
		Reference.Deco4 = config.getBlock("Deco4", 2103).getInt();
		Reference.Deco5 = config.getBlock("Deco5", 2104).getInt();
		Reference.Deco6 = config.getBlock("Deco6", 2105).getInt();
		Reference.Deco7 = config.getBlock("Deco7", 2106).getInt();


		//items

		Reference.ProcesedFood = config.getItem("ProcesedFood", 5020).getInt();
		initIDs(config);
		if(config.hasChanged())config.save();
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
		itemsID.put("HidrogenBattery", new ID(c.getItem("HidrogenBattery", def).getInt()));def++;
		itemsID.put("LasserSword", new ID(c.getItem("LasserSword", def).getInt()));def++;
		itemsID.put("FortuneUpgrade", new ID(c.getItem("FortuneUpgrade", def).getInt()));def++;

	}

	private void registerBlocks(){
		CVDmachine = new CVDmachine(Reference.CVDmachine,Material.iron);
		UTfurnace = new UTfurnace(Reference.UTfurnace,Material.iron);
		IDS = new InterdimensionalStorageBlock(Reference.IDS,Material.iron);
		EnergyColector = new EnergyColector(Reference.EnergyColector,Material.iron);
		Cuter = new PrecisionCuter(Reference.Cuter,Material.iron);
		Purifier = new Purifier(Reference.Purifier,Material.iron);
		PresureChamber = new PresureChamber(Reference.PresureChamber,Material.iron);	
		DiamondGlass = new DiamondGlass(Reference.DiamondGlass,Material.glass,false);	
		CovedGlass = new CovedGlass(Reference.CovedGlass,Material.glass,false);
		hitBox = new hitBox(Reference.hitBox,Material.iron);	
		Generator = new Generator(Reference.Generator,Material.iron);
		MachineChasis = new MachineChasis(Reference.MachineChasis,Material.iron);
		Miner = new Miner(Reference.Miner,Material.iron);
		GrafenoBlock = new GrafenoBlock(Reference.GrafenoBlock,Material.iron);
		AdvMachineChasis = new AdvancedMachineChasis(Reference.AdvMachineChasis,Material.iron);
		Reactor = new Reactor(Reference.Reactor,Material.iron);
		ReactorWall = new ReactorWall(Reference.ReactorWall,Material.iron);
		Sender  = new Sender(Reference.Sender,Material.iron);
		Reciver = new Reciver(Reference.Reciver,Material.iron);
		RadioniteBlock = new RadioniteBlock(Reference.RadioniteBlock,Material.iron);
		ReactorTank = new ReactorTank(Reference.ReactorTank,Material.iron);
		Steam = new Steam("steam");
		SteamTurbine = new SteamTurbine(Reference.SteamTurbine,Material.iron);
		WaterBlock = new WaterBlock(Reference.WaterBlock, Material.iron);
		RadioniteOre = new RadioniteOre(Reference.RadioniteOre, Material.iron);
		MolecularAssembly = new MolecularAssembly(Reference.MolecularAssembly, Material.iron);
		StoneBlock = new common.cout970.UltraTech.blocks.StoneBlock(Reference.StoneBlock,Material.rock);
		StoneBlockBricks = new common.cout970.UltraTech.blocks.StoneBlockBricks(Reference.StoneBlockBricks,Material.rock);
		ChargeStation = new ChargeStation(Reference.ChargeStation, Material.iron);
		SolarPanel = new SolarPanel(Reference.SolarPanel, Material.iron);
		WindMill = new WindMill(Reference.WindMill, Material.iron);
		Printer3D = new Printer3D(Reference.Printer3D, Material.iron);
		if(!FluidRegistry.isFluidRegistered("steam"))FluidRegistry.registerFluid(Steam);

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
		items.add(new HidrogenBattery(itemsID.get("HidrogenBattery").id,"HidrogenBattery"));
		items.add(new LasserSword(itemsID.get("LasserSword").id,"LasserSword"));
		items.add(new FortuneUpgrade(itemsID.get("FortuneUpgrade").id,"FortuneUpgrade"));

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
		GameRegistry.registerTileEntity(EnergyColectorEntity.class, "Colector_UT");
		GameRegistry.registerBlock(EnergyColector, "EnergyColector");
		LanguageRegistry.addName(EnergyColector, "Energy Colector");
		//hitBox
		GameRegistry.registerTileEntity(hitBoxEntity.class, "hitBox_UT");
		GameRegistry.registerBlock(hitBox, "hitBox");
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
		//Sender
		GameRegistry.registerTileEntity(SenderEntity.class, "Sender_UT");
		GameRegistry.registerBlock(Sender, "Sender");
		LanguageRegistry.addName(Sender, "Sender");
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
		//ChargeStation
		GameRegistry.registerTileEntity(ChargeStationEntity.class, "ChargeStation_UT");
		GameRegistry.registerBlock(ChargeStation, "ChargeStation");
		LanguageRegistry.addName(ChargeStation, "Charge Station");
		//SolarPanel
		GameRegistry.registerTileEntity(SolarPanelEntity.class, "SolarPanel_UT");
		GameRegistry.registerBlock(SolarPanel, "SolarPanel");
		LanguageRegistry.addName(SolarPanel, "Solar Panel");
		//WindMill
		GameRegistry.registerTileEntity(WindMillEntity.class, "WindMill_UT");
		GameRegistry.registerBlock(WindMill, "WindMill");
		LanguageRegistry.addName(WindMill, "Wind Mill");
		//Printer3D
		GameRegistry.registerTileEntity(Printer3DEntity.class, "Printer3D_UT");
		GameRegistry.registerBlock(Printer3D, "Printer3D");
		LanguageRegistry.addName(Printer3D, "3D Printer");
				
		
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
		//StoneBlock
		GameRegistry.registerBlock(StoneBlock, "StoneBlock");
		LanguageRegistry.addName(StoneBlock, "Stone Block");
		//StoneBlockBricks
		GameRegistry.registerBlock(StoneBlockBricks, "StoneBlockBricks");
		LanguageRegistry.addName(StoneBlockBricks, "Stone Block Bricks");

		CraftManager.registerCraft();
		GameRegistry.registerWorldGenerator(new WorldGen());
	}



	@EventHandler
	public void postInit(FMLPostInitializationEvent event){

	}



}

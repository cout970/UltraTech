package common.cout970.UltraTech.managers;

import java.util.HashMap;
import java.util.Map;

import common.cout970.UltraTech.blocks.Chasis;
import common.cout970.UltraTech.blocks.OreBlock;
import common.cout970.UltraTech.itemBlock.UT_ItemBlock;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockOre;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockReactor;
import common.cout970.UltraTech.machines.blocks.*;
import common.cout970.UltraTech.machines.tileEntities.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockManager {

	//new blocks
	public static Map<String,Integer> ids = new HashMap<String,Integer>(); 
	public static  Block Chasis;
	public static  Block Ores;
	public static  Block Reactor;
	//Blocks old
	public static  Block CVDmachine;
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
	public static  Block Miner;
	public static  Block GrafenoBlock;
	public static  Block ReactorWall;
	public static  Block Sender;
	public static  Block Reciver;
	public static  Block RadioniteBlock;
	public static  Block MolecularAssembly;
	public static  Block ChargeStation;
	public static  Block SolarPanel;
	public static  Block WindMill;
	public static  Block Printer3D;
	public static  Block Engine;
	public static  Block HologramEmiter;
	public static  Block Crafter;

	
	//fluid
	public static Fluid Steam;
	


	public static void InitBlocks(){
		
		Chasis = new Chasis(ids.get("Chasis"), Material.iron);
		CVDmachine = new CVDmachine(ids.get("CVDmachine"),Material.iron);
		UTfurnace = new UTfurnace(ids.get("UTfurnace"),Material.iron);
		IDS = new InterdimensionalStorageBlock(ids.get("IDS"),Material.iron);
		EnergyColector = new EnergyColector(ids.get("EnergyCollector"),Material.iron);
		Cuter = new PrecisionCuter(ids.get("Cutter"),Material.iron);
		Purifier = new Purifier(ids.get("Purifier"),Material.iron);
		PresureChamber = new Presuricer(ids.get("PresureChamber"),Material.iron);	
		DiamondGlass = new DiamondGlass(ids.get("DiamondGlass"),Material.glass,false);	
		CovedGlass = new CovedGlass(ids.get("CovedGlass"),Material.glass,false);
		hitBox = new hitBox(ids.get("HitBox"),Material.iron);	
		Generator = new Generator(ids.get("Generator"),Material.iron);
		Miner = new Miner(ids.get("Miner"),Material.iron);
		GrafenoBlock = new GrafenoBlock(ids.get("GrafenoBlock"),Material.iron);
//		Reactor = new Reactor(ids.get("Reactor"),Material.iron);
		Reactor = new ReactorMultiblock(ids.get("Reactor"),Material.iron);
//		ReactorWall = new ReactorWall(ids.get("ReactorWall"),Material.iron);
		Sender  = new Sender(ids.get("Sender"),Material.iron);
		Reciver = new Reciver(ids.get("Reciver"),Material.iron);
		RadioniteBlock = new RadioniteBlock(ids.get("RadioniteBlock"),Material.iron);
//		ReactorTank = new ReactorTank(ids.get("ReactorTank"),Material.iron);
		
//		SteamTurbine = new SteamTurbine(ids.get("SteamTurbine"),Material.iron);
//		WaterBlock = new WaterBlock(ids.get("WaterBlock"), Material.iron);
		MolecularAssembly = new MolecularAssembly(ids.get("MolecularAssembly"), Material.iron);
		ChargeStation = new ChargeStation(ids.get("ChargeStation"), Material.iron);
		SolarPanel = new SolarPanel(ids.get("SolarPanel"), Material.iron);
		WindMill = new WindMill(ids.get("WindMill"), Material.iron);
		Printer3D = new Printer3D(ids.get("Printer3D"), Material.iron);
//		ReactorController = new ReactorController(ids.get("ReactorController"),Material.iron);
		Engine = new Engine(ids.get("Engine"),Material.iron);
		Ores = new OreBlock(ids.get("Ores"), Material.rock);
		HologramEmiter = new HologramEmiter(ids.get("Hologram"), Material.rock);
		Crafter = new Crafter(ids.get("Crafter"),Material.iron);
		
		//fluid
		Steam = new Steam("steam");
		if(!FluidRegistry.isFluidRegistered("steam"))FluidRegistry.registerFluid(Steam);
	}


	public static void RegisterBlocks(){
		
		//itemblocks
		GameRegistry.registerBlock(Ores, UT_ItemBlockOre.class ,"Ores_UT");
		GameRegistry.registerBlock(Chasis, UT_ItemBlock.class ,"Chasis_UT");
		LanguageRegistry.addName(new ItemStack(Chasis,1,0), "Machine Chasis MK1");
		LanguageRegistry.addName(new ItemStack(Chasis,1,1), "Machine Chasis MK2");
		LanguageRegistry.addName(new ItemStack(Chasis,1,2), "Machine Chasis MK3");
		
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
		GameRegistry.registerTileEntity(PresuricerEntity.class, "PC_UT");
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
		GameRegistry.registerBlock(Reactor, UT_ItemBlockReactor.class, "ReactorMultiblock");
		LanguageRegistry.addName(new ItemStack(Reactor,1,0), "Reactor Core");
		LanguageRegistry.addName(new ItemStack(Reactor,1,1), "Reactor Wall");
		LanguageRegistry.addName(new ItemStack(Reactor,1,2), "Reactor Tank");
		LanguageRegistry.addName(new ItemStack(Reactor,1,3), "Reactor Controller");
		LanguageRegistry.addName(new ItemStack(Reactor,1,4), "Water Block");
		LanguageRegistry.addName(new ItemStack(Reactor,1,5), "Steam Turbine");
		//reactorwall
		GameRegistry.registerTileEntity(ReactorWallEntity.class, "ReactorWall_UT");
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
		//SteamTurbine
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "SteamTurbine_UT");
		//WaterBlock
		GameRegistry.registerTileEntity(WaterBlockEntity.class, "WaterBlock_UT");
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
		//ReactorController	
		GameRegistry.registerTileEntity(ReactorControllerEntity.class, "ReactorController_UT");
		//Engine	
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerBlock(Engine, "Engine");
		LanguageRegistry.addName(Engine, "Electric Engine");
		//HologramEmiter	
		GameRegistry.registerTileEntity(HologramEmiterEntity.class, "HologramEmiter_UT");
		GameRegistry.registerBlock(HologramEmiter, "HologramEmiter");
		LanguageRegistry.addName(HologramEmiter, "Hologram Emiter");
		//Crafter	
		GameRegistry.registerTileEntity(CrafterEntity.class, "Crafter_UT");
		GameRegistry.registerBlock(Crafter, "Crafter");
		LanguageRegistry.addName(Crafter, "Crafter");

		
		//DiamondGlass
		GameRegistry.registerBlock(DiamondGlass, "Diamond Glass");
		LanguageRegistry.addName(DiamondGlass, "Diamond Glass");
		//CovedGlass
		GameRegistry.registerBlock(CovedGlass, "Coved Glass");
		LanguageRegistry.addName(CovedGlass, "Coved Glass");
		//GrafenoBlock
		GameRegistry.registerBlock(GrafenoBlock, "GrafenoBlock");
		LanguageRegistry.addName(GrafenoBlock, "Grafeno Block");
		//RadioniteBlock
		GameRegistry.registerBlock(RadioniteBlock, "RadioniteBlock");
		LanguageRegistry.addName(RadioniteBlock, "Radionite Block");

	}

}

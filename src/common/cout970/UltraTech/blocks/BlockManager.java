package common.cout970.UltraTech.blocks;

import common.cout970.UltraTech.lib.Reference;
import common.cout970.UltraTech.machines.blocks.*;
import common.cout970.UltraTech.machines.tileEntities.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockManager {

	//Blocks
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
	public static  Block SteamTurbine;
	public static  Block WaterBlock;
	public static  Block RadioniteOre;
	public static  Block MolecularAssembly;
	public static  Block ChargeStation;
	public static  Block SolarPanel;
	public static  Block WindMill;
	public static  Block Printer3D;
	public static  Block ReactorController;
	public static  Block Engine;

	//fluid
	public static Fluid Steam;



	public static void InitBlocks(){
		CVDmachine = new CVDmachine(Reference.CVDmachine,Material.iron);
		UTfurnace = new UTfurnace(Reference.UTfurnace,Material.iron);
		IDS = new InterdimensionalStorageBlock(Reference.IDS,Material.iron);
		EnergyColector = new EnergyColector(Reference.EnergyColector,Material.iron);
		Cuter = new PrecisionCuter(Reference.Cuter,Material.iron);
		Purifier = new Purifier(Reference.Purifier,Material.iron);
		PresureChamber = new Presuricer(Reference.PresureChamber,Material.iron);	
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
		ChargeStation = new ChargeStation(Reference.ChargeStation, Material.iron);
		SolarPanel = new SolarPanel(Reference.SolarPanel, Material.iron);
		WindMill = new WindMill(Reference.WindMill, Material.iron);
		Printer3D = new Printer3D(Reference.Printer3D, Material.iron);
		ReactorController = new ReactorController(Reference.ReactorController,Material.iron);
		Engine = new Engine(Reference.Engine,Material.iron);


		//fluid
		if(!FluidRegistry.isFluidRegistered("steam"))FluidRegistry.registerFluid(Steam);
	}


	public static void RegisterBlocks(){
		
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
		//ReactorController	
		GameRegistry.registerTileEntity(ReactorControllerEntity.class, "ReactorController_UT");
		GameRegistry.registerBlock(ReactorController, "ReactorController");
		LanguageRegistry.addName(ReactorController, "Reactor Controller");
		//Engine	
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerBlock(Engine, "Engine");
		LanguageRegistry.addName(Engine, "Engine[WIP] not work");

		
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

	}
}

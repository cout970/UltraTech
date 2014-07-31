package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultratech.api.power.multipart.MultipartReference;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.LavaGeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.WindMillEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalPlant_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FluidGeneratorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FluidGeneratorT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FurnaceT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FurnaceT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.SolarPanelEntity_T1;
import common.cout970.UltraTech.TileEntities.electric.tiers.SolarPanelEntity_T2;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Chamber_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Control_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Core_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_IO_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Redstone_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Tank_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Wall_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Water_Entity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.Painter3DEntity;
import common.cout970.UltraTech.TileEntities.utility.hitBoxEntity;
import common.cout970.UltraTech.blocks.AlienBlock;
import common.cout970.UltraTech.blocks.ChasisBlock;
import common.cout970.UltraTech.blocks.CovedGlass;
import common.cout970.UltraTech.blocks.Deco_Block;
import common.cout970.UltraTech.blocks.DiamondGlass;
import common.cout970.UltraTech.blocks.MiscBlock;
import common.cout970.UltraTech.blocks.OreBlock;
import common.cout970.UltraTech.blocks.StoneBlockBlack;
import common.cout970.UltraTech.blocks.StoneBlockWhite;
import common.cout970.UltraTech.blocks.common.ChargeStation;
import common.cout970.UltraTech.blocks.common.ChemicalPlant;
import common.cout970.UltraTech.blocks.common.CrafterBlock;
import common.cout970.UltraTech.blocks.common.FermenterBlock;
import common.cout970.UltraTech.blocks.common.Heater;
import common.cout970.UltraTech.blocks.common.LavaGenerator;
import common.cout970.UltraTech.blocks.common.MultiTank;
import common.cout970.UltraTech.blocks.common.Painter3D;
import common.cout970.UltraTech.blocks.common.RefineryBlock;
import common.cout970.UltraTech.blocks.common.Tier3Block;
import common.cout970.UltraTech.blocks.models.Boiler;
import common.cout970.UltraTech.blocks.models.CopperPipe;
import common.cout970.UltraTech.blocks.models.Dynamo;
import common.cout970.UltraTech.blocks.models.Engine;
import common.cout970.UltraTech.blocks.models.FluidTank;
import common.cout970.UltraTech.blocks.models.PumpBlock;
import common.cout970.UltraTech.blocks.models.SteamTurbine;
import common.cout970.UltraTech.blocks.models.TesseractT3;
import common.cout970.UltraTech.blocks.models.WindMill;
import common.cout970.UltraTech.blocks.reactor.Reactor_Chamber_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Control_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Core_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_IO_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Redstone_Control_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Tank_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Wall_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Water_Block;
import common.cout970.UltraTech.blocks.tiers.ChemicalVaporDesintegrationT1;
import common.cout970.UltraTech.blocks.tiers.ChemicalVaporDesintegrationT2;
import common.cout970.UltraTech.blocks.tiers.CoalGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.CoalGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.CutterT1;
import common.cout970.UltraTech.blocks.tiers.CutterT2;
import common.cout970.UltraTech.blocks.tiers.FluidGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.FluidGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.FurnaceT1;
import common.cout970.UltraTech.blocks.tiers.FurnaceT2;
import common.cout970.UltraTech.blocks.tiers.LaminatorT1;
import common.cout970.UltraTech.blocks.tiers.LaminatorT2;
import common.cout970.UltraTech.blocks.tiers.PurifierT1;
import common.cout970.UltraTech.blocks.tiers.PurifierT2;
import common.cout970.UltraTech.blocks.tiers.SolarPanelT1;
import common.cout970.UltraTech.blocks.tiers.SolarPanelT2;
import common.cout970.UltraTech.blocks.tiers.StorageBlock;
import common.cout970.UltraTech.itemBlock.ItemBlock_Chasis;
import common.cout970.UltraTech.itemBlock.ItemBlock_Deco;
import common.cout970.UltraTech.itemBlock.ItemBlock_Misc;
import common.cout970.UltraTech.itemBlock.ItemBlock_Ores;
import common.cout970.UltraTech.itemBlock.ItemBlock_Refinery;
import common.cout970.UltraTech.itemBlock.ItemBlock_Stone;
import common.cout970.UltraTech.itemBlock.ItemBlock_Storage;
import common.cout970.UltraTech.itemBlock.ItemBlock_Tier3;
import common.cout970.UltraTech.microparts.CableBlock;
import common.cout970.UltraTech.microparts.Cable_Entity;
import common.cout970.UltraTech.multiblocks.refinery.RefineryBase;
import common.cout970.UltraTech.multiblocks.refinery.RefineryCore;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockManager {

	//blocks	
	public static List<Deco_Block> deco = new ArrayList<Deco_Block>();

	public static  Block stoneblockblack;
	public static  Block stoneblockwhite;
	public static  Block Chasis;
	public static  Block Ores;
	
	public static  Block Reactor_Core;
	public static  Block Reactor_Wall;
	public static  Block Reactor_Control;
	public static  Block Reactor_IO;
	public static  Block Reactor_Tank;
	public static  Block Reactor_Water;
	public static  Block Reactor_Chamber;
	public static  Block Reactor_Redstone;
	
	public static  Block SolarPanel_T1;
	public static  Block SolarPanel_T2;
	public static  Block WindMill;
	public static  Block Engine;
	public static  Block Tier3;
	public static  Block Misc;
	public static  Block Storage;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;
	public static  Block CopperPipe;
	public static  Block Boiler;
	public static  Block Tank;
	public static  Block Turbine;
	public static  Block Refinery;
	public static  Block Dynamo;
	public static  Block MultiTank;
	public static  Block Pump;
	public static  Block AlienBlock;
	public static  Block LavaGenerator;
	public static  Block ChemicalPlant;
	public static  Block Crafter;
	public static  Block Painter;
	public static  Block Fermenter;
	public static  Block Heater;
	public static  Block ChargeStation;
	public static  Block CVD_T1;
	public static  Block CVD_T2;
	public static  Block Laminator_T1;
	public static  Block Laminator_T2;
	public static  Block Purifier_T1;
	public static  Block Purifier_T2;
	public static  Block Cutter_T1;
	public static  Block Cutter_T2;
	public static  Block Furnace_T1;
	public static  Block Furnace_T2;
	public static  Block Generator_T1;
	public static  Block Generator_T2;
	public static  Block Tesseract;
	public static  Block FluidGenerator_T1;
	public static  Block FluidGenerator_T2;

	public static  Block CableBlock;


	public static void InitBlocks(){
		Material m = Material.iron;
		
		Chasis = new ChasisBlock(Material.iron);
		CVD_T1 = new ChemicalVaporDesintegrationT1(m);
		Laminator_T1 = new LaminatorT1(m);
		Purifier_T1 = new PurifierT1(m);
		Cutter_T1 = new CutterT1(m);
		Furnace_T1 = new FurnaceT1(m);
		Generator_T1 = new CoalGeneratorT1(m);
		CVD_T2 = new ChemicalVaporDesintegrationT2(m);
		Laminator_T2 = new LaminatorT2(m);
		Purifier_T2 = new PurifierT2(m);
		Cutter_T2 = new CutterT2(m);
		Furnace_T2 = new FurnaceT2(m);
		Generator_T2 = new CoalGeneratorT2(m);
		Crafter = new CrafterBlock(m);
		Painter = new Painter3D(m);
		Fermenter = new FermenterBlock(m);
		Heater = new Heater(m);
		ChargeStation = new ChargeStation(m);
		LavaGenerator = new LavaGenerator(m);
		ChemicalPlant = new ChemicalPlant(m);
		Tesseract = new TesseractT3(m);
		Tier3 = new Tier3Block(m);
		
		Reactor_Core = new Reactor_Core_Block(m);
		Reactor_Wall = new Reactor_Wall_Block(m);
		Reactor_Control = new Reactor_Control_Block(m);
		Reactor_IO = new Reactor_IO_Block(m);
		Reactor_Tank = new Reactor_Tank_Block(m);
		Reactor_Water = new Reactor_Water_Block(m);
		Reactor_Chamber = new Reactor_Chamber_Block(m);
		Reactor_Redstone = new Reactor_Redstone_Control_Block(m);
		
		Refinery = new RefineryBlock(m);
		Misc = new MiscBlock(m);
		SolarPanel_T1 = new SolarPanelT1(m);
		SolarPanel_T2 = new SolarPanelT2(m);
		WindMill = new WindMill(m);
		Engine = new Engine(Material.piston);
		Storage = new StorageBlock(Material.iron);
		CopperPipe = new CopperPipe(Material.iron);
		Boiler = new Boiler(Material.iron);
		Tank = new FluidTank(Material.iron);
		Turbine = new SteamTurbine(Material.iron);
		Dynamo = new Dynamo(Material.iron);
		MultiTank = new MultiTank(Material.iron);
		Pump = new PumpBlock(Material.iron);
		FluidGenerator_T1 = new FluidGeneratorT1(m);
		FluidGenerator_T2 = new FluidGeneratorT2(m);
		if(!MultipartReference.isMicroPartActived)CableBlock = new CableBlock(Material.iron);
		
		//decotab
		Ores = new OreBlock(Material.rock);
		AlienBlock = new AlienBlock(Material.iron);
		DiamondGlass = new DiamondGlass(Material.glass,false);	
		CovedGlass = new CovedGlass(Material.glass,false);
		
		//deco

		deco.add(new Deco_Block(1,true));
		deco.add(new Deco_Block(2,true));
		deco.add(new Deco_Block(3,true));
		deco.add(new Deco_Block(4,true));
		deco.add(new Deco_Block(5,true));
		deco.add(new Deco_Block(6,true));
		deco.add(new Deco_Block(7,true));
		deco.add(new Deco_Block(8,true));
		//white
		deco.add(new Deco_Block(1,false));
		deco.add(new Deco_Block(2,false));
		deco.add(new Deco_Block(3,false));
		deco.add(new Deco_Block(4,false));
		deco.add(new Deco_Block(5,false));
		deco.add(new Deco_Block(6,false));
		deco.add(new Deco_Block(7,false));
		deco.add(new Deco_Block(8,false));

		stoneblockblack = new StoneBlockBlack(Material.rock);
		stoneblockwhite = new StoneBlockWhite(Material.rock);
	}


	public static void RegisterBlocks(){

		for(Deco_Block b : deco)
			GameRegistry.registerBlock(b, ItemBlock_Deco.class, "Deco_"+b.number+"_"+(b.black ? "w" : "b"));

		//Blocks & Itemblocks
		GameRegistry.registerBlock(Chasis, ItemBlock_Chasis.class ,"Chasis_UT");
		GameRegistry.registerBlock(Ores, ItemBlock_Ores.class ,"Ores_UT");
		GameRegistry.registerBlock(Tier3, ItemBlock_Tier3.class ,"Tier3_UT");//will be removed
		GameRegistry.registerBlock(Storage, ItemBlock_Storage.class ,"Storage_UT");
		GameRegistry.registerBlock(Misc, ItemBlock_Misc.class, "Misc_UT");//will be removed
		GameRegistry.registerBlock(Refinery, ItemBlock_Refinery.class, "Refinery_UT");
		GameRegistry.registerBlock(stoneblockblack, ItemBlock_Stone.class, "StoneBlockBlack");
		GameRegistry.registerBlock(stoneblockwhite, ItemBlock_Stone.class, "StoneBlockWhite");

		GameRegistry.registerBlock(SolarPanel_T1, "SolarPanel_T1");
		GameRegistry.registerBlock(SolarPanel_T2, "SolarPanel2");
		GameRegistry.registerBlock(WindMill, "WindMill");
		GameRegistry.registerBlock(Engine, "Engine");
		GameRegistry.registerBlock(DiamondGlass, "Diamond_Glass");
		GameRegistry.registerBlock(CovedGlass, "Coved_Glass");
		GameRegistry.registerBlock(CopperPipe, "CopperPipe");
		GameRegistry.registerBlock(Boiler, "Boiler");
		GameRegistry.registerBlock(Tank, "FluidTank");
		GameRegistry.registerBlock(Turbine, "SteamTurbine");
		GameRegistry.registerBlock(Dynamo, "Dynamo");
		GameRegistry.registerBlock(MultiTank, "MultiTank");
		GameRegistry.registerBlock(Pump, "Pump");
		GameRegistry.registerBlock(AlienBlock, "AlienBlock");
		GameRegistry.registerBlock(LavaGenerator, "Lava Generator");
		GameRegistry.registerBlock(ChemicalPlant, "ChemicalPlant");
		GameRegistry.registerBlock(Heater, "Heater");
		GameRegistry.registerBlock(ChargeStation, "Charge Station");
		GameRegistry.registerBlock(Tesseract, "Tesseract");

		//tiers
		GameRegistry.registerBlock(CVD_T1, "CVD_T1");
		GameRegistry.registerBlock(Furnace_T1, "Furnace_T1");
		GameRegistry.registerBlock(Cutter_T1, "Cutter_T1");
		GameRegistry.registerBlock(Purifier_T1, "Purifier_T1");
		GameRegistry.registerBlock(Laminator_T1, "Laminator_T1");
		GameRegistry.registerBlock(Generator_T1, "Generator_T1");

		GameRegistry.registerBlock(CVD_T2, "CVD_T2");
		GameRegistry.registerBlock(Furnace_T2, "Furnace_T2");
		GameRegistry.registerBlock(Cutter_T2, "Cutter_T2");
		GameRegistry.registerBlock(Purifier_T2, "Purifier_T2");
		GameRegistry.registerBlock(Laminator_T2, "Laminator_T2");
		GameRegistry.registerBlock(Generator_T2, "Generator_T2");
		GameRegistry.registerBlock(FluidGenerator_T1, "FluidGenerator_T1");
		GameRegistry.registerBlock(FluidGenerator_T2, "FluidGenerator_T2");
		
		GameRegistry.registerBlock(Crafter, "Crafter");
		GameRegistry.registerBlock(Painter, "Painter");
		GameRegistry.registerBlock(Fermenter, "Fermenter");
		
		GameRegistry.registerBlock(Reactor_Core, 	"Core");
		GameRegistry.registerBlock(Reactor_Wall, 	"Wall");
		GameRegistry.registerBlock(Reactor_Control, "Control");
		GameRegistry.registerBlock(Reactor_IO, 		"IO");
		GameRegistry.registerBlock(Reactor_Tank, 	"Tank");
		GameRegistry.registerBlock(Reactor_Water, 	"Water");
		GameRegistry.registerBlock(Reactor_Chamber, "Chamber");
		GameRegistry.registerBlock(Reactor_Redstone, "Redstone");

		if(!MultipartReference.isMicroPartActived)GameRegistry.registerBlock(CableBlock, "UT_CableBlock");
		//TileEntities

		GameRegistry.registerTileEntity(Machine.class, "Energy_UT");
		GameRegistry.registerTileEntity(StorageTier1.class, "Storage1_UT");
		GameRegistry.registerTileEntity(StorageTier2.class, "Storage2_UT");
		GameRegistry.registerTileEntity(StorageTier3.class, "Storage3_UT");
		GameRegistry.registerTileEntity(hitBoxEntity.class, "hitBox_UT");
		GameRegistry.registerTileEntity(CoalGeneratorEntityT1_Entity.class, "generator_UT");
		GameRegistry.registerTileEntity(MinerEntity.class, "miner_UT");
		
		GameRegistry.registerTileEntity(Reactor_Chamber_Entity.class, "Reactor_Chamber_UT");
		GameRegistry.registerTileEntity(Reactor_Wall_Entity.class, "Reactor_Wall_UT");
		GameRegistry.registerTileEntity(Reactor_Tank_Entity.class, "Reactor_Tank_UT");
		GameRegistry.registerTileEntity(Reactor_IO_Entity.class, "Reactor_IO_UT");
		GameRegistry.registerTileEntity(Reactor_Water_Entity.class, "Reactor_Water_UT");
		GameRegistry.registerTileEntity(Reactor_Core_Entity.class, "Reactor_Core_UT");
		GameRegistry.registerTileEntity(Reactor_Control_Entity.class, "Reactor_Control_UT");
		GameRegistry.registerTileEntity(Reactor_Redstone_Entity.class, "Reactor_Redstone_UT");

		GameRegistry.registerTileEntity(MolecularAssemblyEntity.class, "MolecularAssembly_UT");
		GameRegistry.registerTileEntity(ChargeStationEntity.class, "ChargeStation_UT");
		GameRegistry.registerTileEntity(SolarPanelEntity_T1.class, "SolarPanel_T1_UT");
		GameRegistry.registerTileEntity(SolarPanelEntity_T2.class, "SolarPanel_T2_UT");
		GameRegistry.registerTileEntity(WindMillEntity.class, "WindMill_UT");
		GameRegistry.registerTileEntity(Painter3DEntity.class, "Printer3D_UT");
		GameRegistry.registerTileEntity(HologramEmiterEntity.class, "HologramEmiter_UT");
		GameRegistry.registerTileEntity(CrafterEntity.class, "Crafter_UT");
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerTileEntity(EnergyTransformer.class, "TransFormer_UT");
		GameRegistry.registerTileEntity(Tesseract_Entity.class, "Tesseract_UT");
		GameRegistry.registerTileEntity(FermenterEntity.class, "Fermenter_UT");
		GameRegistry.registerTileEntity(ClimateEntity.class, "Climate_UT");
		GameRegistry.registerTileEntity(CopperPipeEntity.class, "CopperPipeEntity_UT");
		GameRegistry.registerTileEntity(BoilerEntity.class, "Boiler_UT");
		GameRegistry.registerTileEntity(TankEntity.class, "Tank_UT");
		GameRegistry.registerTileEntity(FluidGeneratorT1_Entity.class, "FluidGen_UT");
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "Turbine_UT");
		GameRegistry.registerTileEntity(RefineryCore.class, "RefineryCore_UT");
		GameRegistry.registerTileEntity(RefineryBase.class, "RefBase_UT");
		GameRegistry.registerTileEntity(TileGag.class, "RefGag_UT");
		GameRegistry.registerTileEntity(DynamoEntity.class, "Dynamo_UT");
		GameRegistry.registerTileEntity(PumpEntity.class, "Pump_UT");
		GameRegistry.registerTileEntity(LavaGeneratorEntity.class, "LavaGenerator_UT");
		if(!MultipartReference.isMicroPartActived)GameRegistry.registerTileEntity(Cable_Entity.class, "Cable_UT");
		GameRegistry.registerTileEntity(ChemicalPlant_Entity.class, "ChemicalPlant_UT");
		GameRegistry.registerTileEntity(Heater_Entity.class, "Heater_UT");
		//tiers
		Class[] a = {ChemicalVaporDesintegrationT1_Entity.class,
				ChemicalVaporDesintegrationT2_Entity.class,
				CoalGeneratorT1.class,CoalGeneratorEntityT2_Entity.class,
				FurnaceT1_Entity.class,FurnaceT2_Entity.class,
				CutterT1_Entity.class,CutterT2_Entity.class,
				PurifierT1_Entity.class,PurifierT2_Entity.class,
				LaminatorT1_Entity.class,LaminatorT2_Entity.class,
				FluidGeneratorT1_Entity.class,FluidGeneratorT2_Entity.class};
		for(Class b : a)
			GameRegistry.registerTileEntity(b, b.getSimpleName()+"_UT");
		Language.AddBlockNames();
	}

}

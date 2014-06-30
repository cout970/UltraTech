package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import api.cout970.UltraTech.MeVpower.Machine;
import api.cout970.UltraTech.microparts.CableBlock;
import api.cout970.UltraTech.microparts.CableEntity;
import common.cout970.UltraTech.Tiers.blocks.ChemicalVaporDesintegrationT1;
import common.cout970.UltraTech.Tiers.blocks.ChemicalVaporDesintegrationT2;
import common.cout970.UltraTech.Tiers.blocks.CoalGeneratorT1;
import common.cout970.UltraTech.Tiers.blocks.CoalGeneratorT2;
import common.cout970.UltraTech.Tiers.blocks.CutterT1;
import common.cout970.UltraTech.Tiers.blocks.CutterT2;
import common.cout970.UltraTech.Tiers.blocks.FurnaceT1;
import common.cout970.UltraTech.Tiers.blocks.FurnaceT2;
import common.cout970.UltraTech.Tiers.blocks.LaminatorT1;
import common.cout970.UltraTech.Tiers.blocks.LaminatorT2;
import common.cout970.UltraTech.Tiers.blocks.PurifierT1;
import common.cout970.UltraTech.Tiers.blocks.PurifierT2;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.FluidGeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.LavaGeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.TileEntities.electric.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.TesseractEntity;
import common.cout970.UltraTech.TileEntities.electric.WindMillEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalPlant_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FurnaceT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FurnaceT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT2_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT2_Entity;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.Painter3DEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorTankEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorWallEntity;
import common.cout970.UltraTech.TileEntities.utility.SteamExtractorEntity;
import common.cout970.UltraTech.TileEntities.utility.WaterBlockEntity;
import common.cout970.UltraTech.TileEntities.utility.hitBoxEntity;
import common.cout970.UltraTech.blocks.AlienBlock;
import common.cout970.UltraTech.blocks.ChasisBlock;
import common.cout970.UltraTech.blocks.CovedGlass;
import common.cout970.UltraTech.blocks.DiamondGlass;
import common.cout970.UltraTech.blocks.MiscBlock;
import common.cout970.UltraTech.blocks.MultiTank;
import common.cout970.UltraTech.blocks.OreBlock;
import common.cout970.UltraTech.blocks.ReactorMultiblock;
import common.cout970.UltraTech.blocks.StoneBlock;
import common.cout970.UltraTech.blocks.StorageBlock;
import common.cout970.UltraTech.blocks.Tier3Block;
import common.cout970.UltraTech.blocks.UT_Block;
import common.cout970.UltraTech.blocks.common.ChemicalPlant;
import common.cout970.UltraTech.blocks.common.CrafterBlock;
import common.cout970.UltraTech.blocks.common.FermenterBlock;
import common.cout970.UltraTech.blocks.common.Heater;
import common.cout970.UltraTech.blocks.common.LavaGenerator;
import common.cout970.UltraTech.blocks.common.Painter3D;
import common.cout970.UltraTech.blocks.common.RefineryBlock;
import common.cout970.UltraTech.blocks.models.Boiler;
import common.cout970.UltraTech.blocks.models.CopperPipe;
import common.cout970.UltraTech.blocks.models.Dynamo;
import common.cout970.UltraTech.blocks.models.Engine;
import common.cout970.UltraTech.blocks.models.FluidTank;
import common.cout970.UltraTech.blocks.models.PumpBlock;
import common.cout970.UltraTech.blocks.models.SolarPanel;
import common.cout970.UltraTech.blocks.models.SteamTurbine;
import common.cout970.UltraTech.blocks.models.WindMill;
import common.cout970.UltraTech.itemBlock.UT_ItemBlock;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockDeco;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockMisc;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockOre;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockReactor;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockRefinery;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockStone;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockStorage;
import common.cout970.UltraTech.itemBlock.UT_ItemBlockTier3;
import common.cout970.UltraTech.lib.Control;
import common.cout970.UltraTech.multiblocks.refinery.RefineryBase;
import common.cout970.UltraTech.multiblocks.refinery.RefineryCore;
import common.cout970.UltraTech.multiblocks.refinery.TileGag;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockManager {

	//blocks	
	public static List<UT_Block> deco = new ArrayList<UT_Block>();

	public static  Block stoneblock;
	public static  Block Chasis;
	public static  Block Ores;
	public static  Block Reactor;
	public static  Block SolarPanel;
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
	//tiers
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

	public static  Block CableBlock;

	public static void InitBlocks(){
		Material m = Material.iron;
		
		Chasis = new ChasisBlock(Material.iron);
		Reactor = new ReactorMultiblock(Material.iron);
		Ores = new OreBlock(Material.rock);
		SolarPanel = new SolarPanel(Material.piston);
		WindMill = new WindMill(Material.piston);
		Engine = new Engine(Material.piston);
		Tier3 = new Tier3Block(Material.iron);
		Misc = new MiscBlock(Material.rock);
		Storage = new StorageBlock(Material.iron);
		DiamondGlass = new DiamondGlass(Material.glass,false);	
		CovedGlass = new CovedGlass(Material.glass,false);
		CopperPipe = new CopperPipe(Material.iron);
		Boiler = new Boiler(Material.iron);
		Tank = new FluidTank(Material.iron);
		Turbine = new SteamTurbine(Material.iron);
		Refinery = new RefineryBlock(Material.iron);
		Dynamo = new Dynamo(Material.iron);
		MultiTank = new MultiTank(Material.iron);
		Pump = new PumpBlock(Material.iron);
		AlienBlock = new AlienBlock(Material.iron);
		LavaGenerator = new LavaGenerator(Material.iron);
		ChemicalPlant = new ChemicalPlant(Material.iron);
		Painter = new Painter3D(m);
		Fermenter = new FermenterBlock(m);
		Heater = new Heater(m);
		
		if(!Control.isMicroPartActived)CableBlock = new CableBlock(Material.iron);
		//tiers
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
		//deco

		deco.add(new UT_Block("deco",true));
		deco.add(new UT_Block("deco2",true));
		deco.add(new UT_Block("deco3",true));
		deco.add(new UT_Block("deco4",true));
		deco.add(new UT_Block("deco5",true));
		deco.add(new UT_Block("deco6",true));
		deco.add(new UT_Block("deco7",true));
		deco.add(new UT_Block("deco8",true));
		//white
		deco.add(new UT_Block("deco",false));
		deco.add(new UT_Block("deco2",false));
		deco.add(new UT_Block("deco3",false));
		deco.add(new UT_Block("deco4",false));
		deco.add(new UT_Block("deco5",false));
		deco.add(new UT_Block("deco6",false));
		deco.add(new UT_Block("deco7",false));
		deco.add(new UT_Block("deco8",false));

		stoneblock = new StoneBlock(Material.rock);
	}


	public static void RegisterBlocks(){

		for(UT_Block b : deco)
			GameRegistry.registerBlock(b, UT_ItemBlockDeco.class, b.texture+"_"+(b.black ? "w" : "b"));

		//Blocks & Itemblocks
		GameRegistry.registerBlock(Chasis, UT_ItemBlock.class ,"Chasis_UT");
		GameRegistry.registerBlock(Ores, UT_ItemBlockOre.class ,"Ores_UT");
		GameRegistry.registerBlock(Tier3, UT_ItemBlockTier3.class ,"Tier3_UT");
		GameRegistry.registerBlock(Storage, UT_ItemBlockStorage.class ,"Storage_UT");
		GameRegistry.registerBlock(Reactor, UT_ItemBlockReactor.class, "ReactorMultiblock");
		GameRegistry.registerBlock(Misc, UT_ItemBlockMisc.class, "Misc_UT");
		GameRegistry.registerBlock(Refinery, UT_ItemBlockRefinery.class, "Refinery_UT");
		GameRegistry.registerBlock(stoneblock, UT_ItemBlockStone.class, "StoneBlock");

		GameRegistry.registerBlock(SolarPanel, "SolarPanel");
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
		
		GameRegistry.registerBlock(Crafter, "Crafter");
		GameRegistry.registerBlock(Painter, "Painter");
		GameRegistry.registerBlock(Fermenter, "Fermenter");

		if(!Control.isMicroPartActived)GameRegistry.registerBlock(CableBlock, "UT_CableBlock");
		//TileEntities

		GameRegistry.registerTileEntity(Machine.class, "Energy_UT");
		GameRegistry.registerTileEntity(StorageTier1.class, "Storage1_UT");
		GameRegistry.registerTileEntity(StorageTier2.class, "Storage2_UT");
		GameRegistry.registerTileEntity(StorageTier3.class, "Storage3_UT");
		GameRegistry.registerTileEntity(hitBoxEntity.class, "hitBox_UT");
		GameRegistry.registerTileEntity(CoalGeneratorEntityT1_Entity.class, "generator_UT");
		GameRegistry.registerTileEntity(MinerEntity.class, "miner_UT");
		GameRegistry.registerTileEntity(ReactorWallEntity.class, "reactorwall_UT");
		GameRegistry.registerTileEntity(ReactorTankEntity.class, "ReactorTank_UT");
		GameRegistry.registerTileEntity(SteamExtractorEntity.class, "SteamTurbine_UT");
		GameRegistry.registerTileEntity(WaterBlockEntity.class, "WaterBlock_UT");
		GameRegistry.registerTileEntity(MolecularAssemblyEntity.class, "MolecularAssembly_UT");
		GameRegistry.registerTileEntity(ChargeStationEntity.class, "ChargeStation_UT");
		GameRegistry.registerTileEntity(SolarPanelEntity.class, "SolarPanel_UT");
		GameRegistry.registerTileEntity(WindMillEntity.class, "WindMill_UT");
		GameRegistry.registerTileEntity(Painter3DEntity.class, "Printer3D_UT");
		GameRegistry.registerTileEntity(ReactorControllerEntity.class, "ReactorController_UT");
		GameRegistry.registerTileEntity(HologramEmiterEntity.class, "HologramEmiter_UT");
		GameRegistry.registerTileEntity(CrafterEntity.class, "Crafter_UT");
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerTileEntity(ReactorEntity.class, "Reactor_UT");
		GameRegistry.registerTileEntity(EnergyTransformer.class, "TransFormer_UT");
		GameRegistry.registerTileEntity(TesseractEntity.class, "Tesseract_UT");
		GameRegistry.registerTileEntity(FermenterEntity.class, "Fermenter_UT");
		GameRegistry.registerTileEntity(ClimateEntity.class, "Climate_UT");
		GameRegistry.registerTileEntity(CopperPipeEntity.class, "CopperPipeEntity_UT");
		GameRegistry.registerTileEntity(BoilerEntity.class, "Boiler_UT");
		GameRegistry.registerTileEntity(TankEntity.class, "Tank_UT");
		GameRegistry.registerTileEntity(FluidGeneratorEntity.class, "FluidGen_UT");
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "Turbine_UT");
		GameRegistry.registerTileEntity(RefineryCore.class, "RefineryCore_UT");
		GameRegistry.registerTileEntity(RefineryBase.class, "RefBase_UT");
		GameRegistry.registerTileEntity(TileGag.class, "RefGag_UT");
		GameRegistry.registerTileEntity(DynamoEntity.class, "Dynamo_UT");
		GameRegistry.registerTileEntity(PumpEntity.class, "Pump_UT");
		GameRegistry.registerTileEntity(LavaGeneratorEntity.class, "LavaGenerator_UT");
		if(!Control.isMicroPartActived)GameRegistry.registerTileEntity(CableEntity.class, "CableEntity_UT");
		GameRegistry.registerTileEntity(ChemicalPlant_Entity.class, "ChemicalPlant_UT");
		GameRegistry.registerTileEntity(Heater_Entity.class, "Heater_UT");
		//tiers
		Class[] a = {ChemicalVaporDesintegrationT1_Entity.class,ChemicalVaporDesintegrationT2_Entity.class,CoalGeneratorT1.class,CoalGeneratorEntityT2_Entity.class,FurnaceT1_Entity.class,FurnaceT2_Entity.class,CutterT1_Entity.class,CutterT2_Entity.class,PurifierT1_Entity.class,PurifierT2_Entity.class,LaminatorT1_Entity.class,LaminatorT2_Entity.class};
		for(Class b : a)
			GameRegistry.registerTileEntity(b, b.getSimpleName()+"_UT");
		Language.AddBlockNames();
	}

}

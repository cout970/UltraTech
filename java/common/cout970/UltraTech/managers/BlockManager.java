package common.cout970.UltraTech.managers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ultratech.api.power.multipart.MultipartReference;
import common.cout970.UltraTech.TileEntities.electric.TileEntityChargeStation;
import common.cout970.UltraTech.TileEntities.electric.TileEntityClimateStation;
import common.cout970.UltraTech.TileEntities.electric.TileEntityFermenter;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMiner;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMolecularAssembly;
import common.cout970.UltraTech.TileEntities.electric.TileEntityPump;
import common.cout970.UltraTech.TileEntities.electric.TileEntitySteamTurbine;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier1;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier2;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier3;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier4;
import common.cout970.UltraTech.TileEntities.electric.TileEntityWindMill;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalPlantT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalPlantT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalVaporDesintegrationT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalVaporDesintegrationT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCoalGeneratorEntityT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCoalGeneratorEntityT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCutterT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCutterT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFluidGeneratorT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFluidGeneratorT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFurnaceT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFurnaceT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityGrindingMillT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityGrindingMillT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityHeater;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityLaminatorT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityLaminatorT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityThermoelectricGeneratorT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityThermoelectricGeneratorT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityPurifierT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityPurifierT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntitySolarPanelT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntitySolarPanelT2;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;
import common.cout970.UltraTech.TileEntities.fluid.TileEntityBoiler;
import common.cout970.UltraTech.TileEntities.fluid.TileEntityFluidTank;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityDynamo;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityEngine;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityKineticGenerator;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityTransformer;
import common.cout970.UltraTech.TileEntities.logistics.TileEntityInfiniteSupply;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Chamber_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Control_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Core_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_IO_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Redstone_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Tank_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Wall_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Water_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Core_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Entity_Base;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_IO_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Structure_Entity;
import common.cout970.UltraTech.TileEntities.utility.TileEntityCrafter;
import common.cout970.UltraTech.TileEntities.utility.TileEntityHologramEmiter;
import common.cout970.UltraTech.TileEntities.utility.TileEntityLaserTurret;
import common.cout970.UltraTech.TileEntities.utility.TileEntityPainter3D;
import common.cout970.UltraTech.TileEntities.utility.TileEntityHitBox;
import common.cout970.UltraTech.blocks.common.BlockChargeStation;
import common.cout970.UltraTech.blocks.common.BlockCrafter;
import common.cout970.UltraTech.blocks.common.BlockFermenter;
import common.cout970.UltraTech.blocks.common.BlockHeater;
import common.cout970.UltraTech.blocks.common.BlockInfiniteSupply;
import common.cout970.UltraTech.blocks.common.BlockMultiTank;
import common.cout970.UltraTech.blocks.common.BlockPainter3D;
import common.cout970.UltraTech.blocks.common.BlocksTier3;
import common.cout970.UltraTech.blocks.decoration.BlockClayStone;
import common.cout970.UltraTech.blocks.decoration.BlockNormalStone;
import common.cout970.UltraTech.blocks.decoration.BlockObalti;
import common.cout970.UltraTech.blocks.decoration.BlockChasis;
import common.cout970.UltraTech.blocks.decoration.BlockCovedGlass;
import common.cout970.UltraTech.blocks.decoration.BlockDeco;
import common.cout970.UltraTech.blocks.decoration.BlockDiamondGlass;
import common.cout970.UltraTech.blocks.decoration.BlocksMisc;
import common.cout970.UltraTech.blocks.decoration.BlocksOres;
import common.cout970.UltraTech.blocks.decoration.BlockBlackStone;
import common.cout970.UltraTech.blocks.decoration.BlockWhiteStone;
import common.cout970.UltraTech.blocks.models.BlockBoiler;
import common.cout970.UltraTech.blocks.models.BlockDynamo;
import common.cout970.UltraTech.blocks.models.BlockEngine;
import common.cout970.UltraTech.blocks.models.BlockFluidTank;
import common.cout970.UltraTech.blocks.models.BlockKineticGenerator;
import common.cout970.UltraTech.blocks.models.BlockLaserTurret;
import common.cout970.UltraTech.blocks.models.BlockPump;
import common.cout970.UltraTech.blocks.models.BlockSteamTurbine;
import common.cout970.UltraTech.blocks.models.BlockEnergyTesseract;
import common.cout970.UltraTech.blocks.models.BlockTransformer;
import common.cout970.UltraTech.blocks.models.BlockWindMill;
import common.cout970.UltraTech.blocks.reactor.Reactor_Chamber_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Control_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Core_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_IO_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Redstone_Control_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Tank_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Wall_Block;
import common.cout970.UltraTech.blocks.reactor.Reactor_Water_Block;
import common.cout970.UltraTech.blocks.refinery.Refinery_Base_Block;
import common.cout970.UltraTech.blocks.refinery.Refinery_Core_Block;
import common.cout970.UltraTech.blocks.refinery.Refinery_IO_Block;
import common.cout970.UltraTech.blocks.refinery.Refinery_Structural_Block;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalPlantT1;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalPlantT2;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalVaporDesintegrationT1;
import common.cout970.UltraTech.blocks.tiers.BlockChemicalVaporDesintegrationT2;
import common.cout970.UltraTech.blocks.tiers.BlockCoalGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.BlockCoalGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.BlockCutterT1;
import common.cout970.UltraTech.blocks.tiers.BlockCutterT2;
import common.cout970.UltraTech.blocks.tiers.BlockFluidGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.BlockFluidGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.BlockFurnaceT1;
import common.cout970.UltraTech.blocks.tiers.BlockFurnaceT2;
import common.cout970.UltraTech.blocks.tiers.BlockGrindingMillT1;
import common.cout970.UltraTech.blocks.tiers.BlockGrindingMillT2;
import common.cout970.UltraTech.blocks.tiers.BlockLaminatorT1;
import common.cout970.UltraTech.blocks.tiers.BlockLaminatorT2;
import common.cout970.UltraTech.blocks.tiers.BlockThermoelectricGeneratorT1;
import common.cout970.UltraTech.blocks.tiers.BlockThermoelectricGeneratorT2;
import common.cout970.UltraTech.blocks.tiers.BlockPurifierT1;
import common.cout970.UltraTech.blocks.tiers.BlockPurifierT2;
import common.cout970.UltraTech.blocks.tiers.BlockSolarPanelT1;
import common.cout970.UltraTech.blocks.tiers.BlockSolarPanelT2;
import common.cout970.UltraTech.blocks.tiers.BlocksBattery;
import common.cout970.UltraTech.itemBlock.ItemBlock_Chasis;
import common.cout970.UltraTech.itemBlock.ItemBlock_Deco;
import common.cout970.UltraTech.itemBlock.ItemBlock_Misc;
import common.cout970.UltraTech.itemBlock.ItemBlock_Ores;
import common.cout970.UltraTech.itemBlock.ItemBlock_Stone;
import common.cout970.UltraTech.itemBlock.ItemBlock_Storage;
import common.cout970.UltraTech.itemBlock.ItemBlock_Tier3;
import common.cout970.UltraTech.multipart.remplace.BlockCable;
import common.cout970.UltraTech.multipart.remplace.TileEntityCable;
import common.cout970.UltraTech.multipart.remplace.BlockCopperPipe;
import common.cout970.UltraTech.multipart.remplace.TileEntityCopperPipe;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockManager {

	//blocks	
	public static List<BlockDeco> deco = new ArrayList<BlockDeco>();

	public static  Block stoneblockblack;
	public static  Block stoneblockwhite;
	public static  Block stoneblocknormal;
	public static  Block stoneblockclay;
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
	
	public static  Block Refinery_Base;
	public static  Block Refinery_Core;
	public static  Block Refinery_IO;
	public static  Block Refinery_Structure;
	
	public static  Block SolarPanel_T1;
	public static  Block SolarPanel_T2;
	public static  Block WindMill;
	public static  Block Engine;
	public static  Block Tier3;
	public static  Block Misc;
	public static  Block Storage;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;
	public static  Block Boiler;
	public static  Block Tank;
	public static  Block Turbine;
	public static  Block Dynamo;
	public static  Block MultiTank;
	public static  Block Pump;
	public static  Block obaltiBlock;
	public static  Block ChemicalPlant_T1;
	public static  Block ChemicalPlant_T2;
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
	public static  Block ThermoelectricGenerator_T1;
	public static  Block ThermoelectricGenerator_T2;
	public static  Block KineticGenerator;
	public static  Block Transformer;
	public static  Block GrindingMill_T1;
	public static  Block GrindingMill_T2; 
	
	public static  Block InfiniteSupply; 
	public static  Block LaserTurret; 

	//with out fmp
	public static  Block CableBlock;
	public static  Block CopperPipe;

	public static void InitBlocks(){
		Material m = Material.iron;
		
		Chasis = new BlockChasis(m);
		//tier 1
		Generator_T1 = new BlockCoalGeneratorT1(m);
		CVD_T1 = new BlockChemicalVaporDesintegrationT1(m);
		Laminator_T1 = new BlockLaminatorT1(m);
		Purifier_T1 = new BlockPurifierT1(m);
		Cutter_T1 = new BlockCutterT1(m);
		Furnace_T1 = new BlockFurnaceT1(m);
		ChemicalPlant_T1 = new BlockChemicalPlantT1(m);
		ThermoelectricGenerator_T1 = new BlockThermoelectricGeneratorT1(m);
		FluidGenerator_T1 = new BlockFluidGeneratorT1(m);
		Crafter = new BlockCrafter(m);
		Painter = new BlockPainter3D(m);
		Fermenter = new BlockFermenter(m);
		ChargeStation = new BlockChargeStation(m);
		//tier 2
		Generator_T2 = new BlockCoalGeneratorT2(m);
		CVD_T2 = new BlockChemicalVaporDesintegrationT2(m);
		Laminator_T2 = new BlockLaminatorT2(m);
		Purifier_T2 = new BlockPurifierT2(m);
		Cutter_T2 = new BlockCutterT2(m);
		Furnace_T2 = new BlockFurnaceT2(m);
		ChemicalPlant_T2 = new BlockChemicalPlantT2(m);
		ThermoelectricGenerator_T2 = new BlockThermoelectricGeneratorT2(m);
		FluidGenerator_T2 = new BlockFluidGeneratorT2(m);
		
		Reactor_Core = new Reactor_Core_Block(m);
		Reactor_Wall = new Reactor_Wall_Block(m);
		Reactor_Control = new Reactor_Control_Block(m);
		Reactor_IO = new Reactor_IO_Block(m);
		Reactor_Tank = new Reactor_Tank_Block(m);
		Reactor_Water = new Reactor_Water_Block(m);
		Reactor_Chamber = new Reactor_Chamber_Block(m);
		Reactor_Redstone = new Reactor_Redstone_Control_Block(m);
		
		Refinery_Base = new Refinery_Base_Block(m);
		Refinery_Core = new Refinery_Core_Block(m);
		Refinery_IO = new Refinery_IO_Block(m);
		Refinery_Structure = new Refinery_Structural_Block(m);
		
		Storage = new BlocksBattery(m);
		Heater = new BlockHeater(m);
		Tesseract = new BlockEnergyTesseract(m);
		Tier3 = new BlocksTier3(m);
		Misc = new BlocksMisc(m);
		SolarPanel_T1 = new BlockSolarPanelT1(m);
		SolarPanel_T2 = new BlockSolarPanelT2(m);
		WindMill = new BlockWindMill(m);
		Engine = new BlockEngine(Material.piston);
		KineticGenerator = new BlockKineticGenerator(m);
		Transformer = new BlockTransformer(m);
		LaserTurret = new BlockLaserTurret();
		
		Boiler = new BlockBoiler(m);
		Tank = new BlockFluidTank(m);
		Turbine = new BlockSteamTurbine(m);
		Dynamo = new BlockDynamo(m);
		MultiTank = new BlockMultiTank(m);
		Pump = new BlockPump(m);
		InfiniteSupply = new BlockInfiniteSupply();
		GrindingMill_T1 = new BlockGrindingMillT1(m);
		GrindingMill_T2 = new BlockGrindingMillT2(m);
		
		
		if(!MultipartReference.isMicroPartActived){
			CableBlock = new BlockCable(Material.iron);
			CopperPipe = new BlockCopperPipe(m);
		}
		
		//decotab
		Ores = new BlocksOres(Material.rock);
		obaltiBlock = new BlockObalti(Material.iron);
		DiamondGlass = new BlockDiamondGlass(Material.glass,false);	
		CovedGlass = new BlockCovedGlass(Material.glass,false);
		
		//deco

		deco.add(new BlockDeco(1,true));
		deco.add(new BlockDeco(2,true));
		deco.add(new BlockDeco(3,true));
		deco.add(new BlockDeco(4,true));
		deco.add(new BlockDeco(5,true));
		deco.add(new BlockDeco(6,true));
		deco.add(new BlockDeco(7,true));
		deco.add(new BlockDeco(8,true));
		//white
		deco.add(new BlockDeco(1,false));
		deco.add(new BlockDeco(2,false));
		deco.add(new BlockDeco(3,false));
		deco.add(new BlockDeco(4,false));
		deco.add(new BlockDeco(5,false));
		deco.add(new BlockDeco(6,false));
		deco.add(new BlockDeco(7,false));
		deco.add(new BlockDeco(8,false));

		stoneblockblack = new BlockBlackStone(Material.rock);
		stoneblockwhite = new BlockWhiteStone(Material.rock);
		stoneblocknormal = new BlockNormalStone(Material.rock);
		stoneblockclay = new BlockClayStone(Material.rock);
	}


	public static void RegisterBlocks(){
		
		GameRegistry.registerBlock(stoneblockblack, ItemBlock_Stone.class, "StoneBlockBlack");
		GameRegistry.registerBlock(stoneblockwhite, ItemBlock_Stone.class, "StoneBlockWhite");
		GameRegistry.registerBlock(stoneblocknormal, ItemBlock_Stone.class, "StoneBlockNormal");
		GameRegistry.registerBlock(stoneblockclay, ItemBlock_Stone.class, "StoneBlockClay");
		for(BlockDeco b : deco)
			GameRegistry.registerBlock(b, ItemBlock_Deco.class, "Deco_"+b.number+"_"+(b.black ? "w" : "b"));

		//Blocks & Itemblocks		
		
		GameRegistry.registerBlock(Ores, ItemBlock_Ores.class ,"Ores_UT");
		GameRegistry.registerBlock(Chasis, ItemBlock_Chasis.class ,"Chasis_UT");
		//tier 1
		GameRegistry.registerBlock(Generator_T1, "GeneratorT1");
		GameRegistry.registerBlock(CVD_T1, "CVD_T1");
		GameRegistry.registerBlock(Furnace_T1, "Furnace_T1");
		GameRegistry.registerBlock(Cutter_T1, "Cutter_T1");
		GameRegistry.registerBlock(Purifier_T1, "Purifier_T1");
		GameRegistry.registerBlock(Laminator_T1, "Laminator_T1");
		GameRegistry.registerBlock(ChemicalPlant_T1, "ChemicalPlantT1");
		GameRegistry.registerBlock(ThermoelectricGenerator_T1, "Lava Generator T1");
		GameRegistry.registerBlock(FluidGenerator_T1, "FluidGenerator_T1");
		GameRegistry.registerBlock(Crafter, "Crafter");
		GameRegistry.registerBlock(Painter, "Painter");
		GameRegistry.registerBlock(Fermenter, "Fermenter");
		GameRegistry.registerBlock(ChargeStation, "Charge Station");
		//tier 2
		GameRegistry.registerBlock(Generator_T2, "Generator_T2");
		GameRegistry.registerBlock(CVD_T2, "CVD_T2");
		GameRegistry.registerBlock(Furnace_T2, "Furnace_T2");
		GameRegistry.registerBlock(Cutter_T2, "Cutter_T2");
		GameRegistry.registerBlock(Purifier_T2, "Purifier_T2");
		GameRegistry.registerBlock(Laminator_T2, "Laminator_T2");
		GameRegistry.registerBlock(ChemicalPlant_T2, "ChemicalPlantT2");
		GameRegistry.registerBlock(ThermoelectricGenerator_T2, "Lava Generator T2");
		GameRegistry.registerBlock(FluidGenerator_T2, "FluidGenerator_T2");
		GameRegistry.registerBlock(Heater, "Heater");
		GameRegistry.registerBlock(Storage, ItemBlock_Storage.class ,"Storage_UT");
		GameRegistry.registerBlock(Reactor_Core, 	"Core");
		GameRegistry.registerBlock(Reactor_Wall, 	"Wall");
		GameRegistry.registerBlock(Reactor_Control, "Control");
		GameRegistry.registerBlock(Reactor_IO, 		"IO");
		GameRegistry.registerBlock(Reactor_Tank, 	"Tank");
		GameRegistry.registerBlock(Reactor_Water, 	"Water");
		GameRegistry.registerBlock(Reactor_Chamber, "Chamber");
		GameRegistry.registerBlock(Reactor_Redstone, "Redstone");
		GameRegistry.registerBlock(Tier3, ItemBlock_Tier3.class ,"Tier3_UT");//will be removed
		GameRegistry.registerBlock(Tesseract, "Tesseract");
		GameRegistry.registerBlock(SolarPanel_T1, "SolarPanel_T1");
		GameRegistry.registerBlock(SolarPanel_T2, "SolarPanel2");
		GameRegistry.registerBlock(WindMill, "WindMill");
		GameRegistry.registerBlock(Engine, "Engine");
		GameRegistry.registerBlock(KineticGenerator, "KineticGenerator");
		GameRegistry.registerBlock(Boiler, "Boiler");
		GameRegistry.registerBlock(Tank, "FluidTank");
		GameRegistry.registerBlock(Pump, "Pump");
		GameRegistry.registerBlock(Turbine, "SteamTurbine");
		GameRegistry.registerBlock(Dynamo, "Dynamo");
		GameRegistry.registerBlock(Transformer, "Transformer");
		GameRegistry.registerBlock(LaserTurret, "LaserTurret");
		GameRegistry.registerBlock(GrindingMill_T1, "GrindingMill_T1");
		GameRegistry.registerBlock(GrindingMill_T2, "GrindingMill_T2");
		
		GameRegistry.registerBlock(Misc, ItemBlock_Misc.class, "Misc_UT");//will be removed
		
		GameRegistry.registerBlock(Refinery_Base, "Refinery_Base");
		GameRegistry.registerBlock(Refinery_Core, "Refinery_Core");
		GameRegistry.registerBlock(Refinery_IO, "Refinery_IO");
		GameRegistry.registerBlock(Refinery_Structure, "Refinery_Structure");
		
		GameRegistry.registerBlock(DiamondGlass, "Diamond_Glass");
		GameRegistry.registerBlock(CovedGlass, "Coved_Glass");
		GameRegistry.registerBlock(MultiTank, "MultiTank");
		GameRegistry.registerBlock(obaltiBlock, "AlienBlock");
		GameRegistry.registerBlock(InfiniteSupply, "InfiniteSupply");
		
		if(!MultipartReference.isMicroPartActived){
			GameRegistry.registerBlock(CopperPipe, "CopperPipe");
			GameRegistry.registerBlock(CableBlock, "UT_CableBlock");
		}
		//TileEntities

		GameRegistry.registerTileEntity(Machine.class, "Energy_UT");
		GameRegistry.registerTileEntity(TileEntityBatteryTier1.class, "Storage1_UT");
		GameRegistry.registerTileEntity(TileEntityBatteryTier2.class, "Storage2_UT");
		GameRegistry.registerTileEntity(TileEntityBatteryTier3.class, "Storage3_UT");
		GameRegistry.registerTileEntity(TileEntityBatteryTier4.class, "Storage4_UT");
		GameRegistry.registerTileEntity(TileEntityHitBox.class, "hitBox_UT");
		GameRegistry.registerTileEntity(TileEntityCoalGeneratorEntityT1.class, "generator_UT");
		GameRegistry.registerTileEntity(TileEntityMiner.class, "miner_UT");
		
		GameRegistry.registerTileEntity(Reactor_Chamber_Entity.class, "Reactor_Chamber_UT");
		GameRegistry.registerTileEntity(Reactor_Wall_Entity.class, "Reactor_Wall_UT");
		GameRegistry.registerTileEntity(Reactor_Tank_Entity.class, "Reactor_Tank_UT");
		GameRegistry.registerTileEntity(Reactor_IO_Entity.class, "Reactor_IO_UT");
		GameRegistry.registerTileEntity(Reactor_Water_Entity.class, "Reactor_Water_UT");
		GameRegistry.registerTileEntity(Reactor_Core_Entity.class, "Reactor_Core_UT");
		GameRegistry.registerTileEntity(Reactor_Control_Entity.class, "Reactor_Control_UT");
		GameRegistry.registerTileEntity(Reactor_Redstone_Entity.class, "Reactor_Redstone_UT");

		GameRegistry.registerTileEntity(TileEntityMolecularAssembly.class, "MolecularAssembly_UT");
		GameRegistry.registerTileEntity(TileEntityChargeStation.class, "ChargeStation_UT");
		GameRegistry.registerTileEntity(TileEntitySolarPanelT1.class, "SolarPanel_T1_UT");
		GameRegistry.registerTileEntity(TileEntitySolarPanelT2.class, "SolarPanel_T2_UT");
		GameRegistry.registerTileEntity(TileEntityWindMill.class, "WindMill_UT");
		GameRegistry.registerTileEntity(TileEntityPainter3D.class, "Printer3D_UT");
		GameRegistry.registerTileEntity(TileEntityHologramEmiter.class, "HologramEmiter_UT");
		GameRegistry.registerTileEntity(TileEntityCrafter.class, "Crafter_UT");
		GameRegistry.registerTileEntity(TileEntityEngine.class, "Engine_UT");
		GameRegistry.registerTileEntity(TileEntityTesseract.class, "Tesseract_UT");
		GameRegistry.registerTileEntity(TileEntityFermenter.class, "Fermenter_UT");
		GameRegistry.registerTileEntity(TileEntityClimateStation.class, "Climate_UT");
		GameRegistry.registerTileEntity(TileEntityBoiler.class, "Boiler_UT");
		GameRegistry.registerTileEntity(TileEntityFluidTank.class, "Tank_UT");
		GameRegistry.registerTileEntity(TileEntityFluidGeneratorT1.class, "FluidGen_UT");
		GameRegistry.registerTileEntity(TileEntitySteamTurbine.class, "Turbine_UT");
		GameRegistry.registerTileEntity(TileEntityDynamo.class, "Dynamo_UT");
		GameRegistry.registerTileEntity(TileEntityPump.class, "Pump_UT");
		GameRegistry.registerTileEntity(TileEntityThermoelectricGeneratorT1.class, "LavaGeneratorT1_UT");
		GameRegistry.registerTileEntity(TileEntityThermoelectricGeneratorT2.class, "LavaGeneratorT2_UT");
		GameRegistry.registerTileEntity(TileEntityChemicalPlantT1.class, "ChemicalPlant_T1_UT");
		GameRegistry.registerTileEntity(TileEntityChemicalPlantT2.class, "ChemicalPlant_T2_UT");
		GameRegistry.registerTileEntity(TileEntityHeater.class, "Heater_UT");
		GameRegistry.registerTileEntity(TileEntityKineticGenerator.class, "kineticGenerator_UT");
		GameRegistry.registerTileEntity(TileEntityTransformer.class, "Transformer_UT");
		GameRegistry.registerTileEntity(Refinery_Core_Entity.class, "Refinery_Core_Entity_UT");
		GameRegistry.registerTileEntity(Refinery_IO_Entity.class, "Refinery_IO_Entity_UT");
		GameRegistry.registerTileEntity(Refinery_Entity_Base.class, "Refinery_Entity_Base_UT");
		GameRegistry.registerTileEntity(Refinery_Structure_Entity.class, "Refinery_Structure_Entity_UT");
		GameRegistry.registerTileEntity(TileEntityInfiniteSupply.class, "InfiniteSupplyEntity_UT");
		GameRegistry.registerTileEntity(TileEntityLaserTurret.class, "LaserTurretEntity_UT");
		
		if(!MultipartReference.isMicroPartActived){
			GameRegistry.registerTileEntity(TileEntityCable.class, "Cable_UT");
			GameRegistry.registerTileEntity(TileEntityCopperPipe.class, "CopperPipeEntity_UT");
		}
		
		//tiers
		Class[] a = {TileEntityChemicalVaporDesintegrationT1.class,
				TileEntityChemicalVaporDesintegrationT2.class,
				BlockCoalGeneratorT1.class,TileEntityCoalGeneratorEntityT2.class,
				TileEntityFurnaceT1.class,TileEntityFurnaceT2.class,
				TileEntityCutterT1.class,TileEntityCutterT2.class,
				TileEntityPurifierT1.class,TileEntityPurifierT2.class,
				TileEntityLaminatorT1.class,TileEntityLaminatorT2.class,
				TileEntityFluidGeneratorT1.class,TileEntityFluidGeneratorT2.class,
				TileEntityGrindingMillT1.class,TileEntityGrindingMillT2.class};
		for(Class b : a)
			GameRegistry.registerTileEntity(b, b.getSimpleName()+"_UT");
		Language.AddBlockNames();
	}

}

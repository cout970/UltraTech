package common.cout970.UltraTech.managers;

import java.util.HashMap;
import java.util.Map;

import common.cout970.UltraTech.TileEntities.Tier1.*;
import common.cout970.UltraTech.TileEntities.Tier2.*;
import common.cout970.UltraTech.TileEntities.Tier3.*;
import common.cout970.UltraTech.blocks.*;
import common.cout970.UltraTech.blocks.models.WindMill;
import common.cout970.UltraTech.energy.api.ElectricConductor;
import common.cout970.UltraTech.itemBlock.*;
import common.cout970.UltraTech.machines.tileEntities.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class BlockManager {

	//blocks
	public static Map<String,Integer> ids = new HashMap<String,Integer>(); 
	
	public static  Block Chasis;
	public static  Block Ores;
	public static  Block Reactor;
	public static  Block SolarPanel;
	public static  Block WindMill;
	public static  Block Engine;
	public static  Block Tier1;
	public static  Block Tier2;
	public static  Block Tier3;
	public static  Block Misc;
	public static  Block Storage;
	public static  Block Cable;
	public static  Block DiamondGlass;
	public static  Block CovedGlass;

	
	//fluid
	public static Fluid Steam;
	


	public static void InitBlocks(){
		
		Chasis = new ChasisBlock(ids.get("Chasis"), Material.iron);
		Reactor = new ReactorMultiblock(ids.get("Reactor"),Material.iron);
		Ores = new OreBlock(ids.get("Ores"), Material.rock);
		SolarPanel = new common.cout970.UltraTech.blocks.models.SolarPanel(ids.get("SolarPanel"), Material.iron);
		WindMill = new WindMill(ids.get("WindMill"), Material.iron);
		Engine = new common.cout970.UltraTech.blocks.models.Engine(ids.get("Engine"), Material.iron);
		Tier1 = new Tier1Block(ids.get("Tier1"), Material.iron);
		Tier2 = new Tier2Block(ids.get("Tier2"), Material.iron);
		Tier3 = new Tier3Block(ids.get("Tier3"), Material.iron);
		Misc = new MiscBlock(ids.get("Misc"), Material.rock);
		Storage = new StorageBlock(ids.get("Storage"), Material.iron);
		Cable = new CableBlock(ids.get("Cable"),Material.iron);
		DiamondGlass = new DiamondGlass(ids.get("DiamondGlass"),Material.glass,false);	
		CovedGlass = new CovedGlass(ids.get("CovedGlass"),Material.glass,false);

		//fluid
		Steam = new Steam("steam");
		if(!FluidRegistry.isFluidRegistered("steam"))FluidRegistry.registerFluid(Steam);
	}


	public static void RegisterBlocks(){

		GameRegistry.registerTileEntity(ElectricConductor.class, "Energy_UT");

		//Blocks & Itemblocks
		GameRegistry.registerBlock(Chasis, UT_ItemBlock.class ,"Chasis_UT");
		GameRegistry.registerBlock(Ores, UT_ItemBlockOre.class ,"Ores_UT");
		GameRegistry.registerBlock(Tier1, UT_ItemBlockTier1.class ,"Tier1_UT");
		GameRegistry.registerBlock(Tier2, UT_ItemBlockTier2.class ,"Tier2_UT");
		GameRegistry.registerBlock(Tier3, UT_ItemBlockTier3.class ,"Tier3_UT");
		GameRegistry.registerBlock(Storage, UT_ItemBlockStorage.class ,"Storage_UT");
		GameRegistry.registerBlock(Cable, UT_ItemBlockCable.class ,"Cable_UT");
		GameRegistry.registerBlock(Reactor, UT_ItemBlockReactor.class, "ReactorMultiblock");
		GameRegistry.registerBlock(Misc, UT_ItemBlockMisc.class, "Misc_UT");
		
		GameRegistry.registerBlock(SolarPanel, "SolarPanel");
		GameRegistry.registerBlock(WindMill, "WindMill");
		GameRegistry.registerBlock(Engine, "Engine");
		GameRegistry.registerBlock(DiamondGlass, "Diamond_Glass");
		GameRegistry.registerBlock(CovedGlass, "Coved_Glass");

		//TileEntities

		GameRegistry.registerTileEntity(CVD_Entity.class, "cvd_UT");
		GameRegistry.registerTileEntity(FurnaceEntity.class, "furnace_UT");
		GameRegistry.registerTileEntity(StorageTier1.class, "Storage1_UT");
		GameRegistry.registerTileEntity(StorageTier2.class, "Storage2_UT");
		GameRegistry.registerTileEntity(StorageTier3.class, "Storage3_UT");
		GameRegistry.registerTileEntity(hitBoxEntity.class, "hitBox_UT");
		GameRegistry.registerTileEntity(CutterEntity.class, "cutter_UT");
		GameRegistry.registerTileEntity(PurifierEntity.class, "purifier_UT");
		GameRegistry.registerTileEntity(PresuricerEntity.class, "presuricer_UT");
		GameRegistry.registerTileEntity(GeneratorEntity.class, "generator_UT");
		GameRegistry.registerTileEntity(MinerEntity.class, "miner_UT");
		GameRegistry.registerTileEntity(ReactorWallEntity.class, "reactorwall_UT");
		GameRegistry.registerTileEntity(SenderEntity.class, "sender_UT");
		GameRegistry.registerTileEntity(ReciverEntity.class, "reciver_UT");
		GameRegistry.registerTileEntity(ReactorTankEntity.class, "ReactorTank_UT");
		GameRegistry.registerTileEntity(SteamTurbineEntity.class, "SteamTurbine_UT");
		GameRegistry.registerTileEntity(WaterBlockEntity.class, "WaterBlock_UT");
		GameRegistry.registerTileEntity(MolecularAssemblyEntity.class, "MolecularAssembly_UT");
		GameRegistry.registerTileEntity(ChargeStationEntity.class, "ChargeStation_UT");
		GameRegistry.registerTileEntity(SolarPanelEntity.class, "SolarPanel_UT");
		GameRegistry.registerTileEntity(WindMillEntity.class, "WindMill_UT");
		GameRegistry.registerTileEntity(Printer3DEntity.class, "Printer3D_UT");
		GameRegistry.registerTileEntity(ReactorControllerEntity.class, "ReactorController_UT");
		GameRegistry.registerTileEntity(HologramEmiterEntity.class, "HologramEmiter_UT");
		GameRegistry.registerTileEntity(CrafterEntity.class, "Crafter_UT");
		GameRegistry.registerTileEntity(EngineEntity.class, "Engine_UT");
		GameRegistry.registerTileEntity(ReactorEntity.class, "Reactor_UT");
		GameRegistry.registerTileEntity(CableEntity.class, "Cable_UT");
		GameRegistry.registerTileEntity(EnergyTransformer.class, "TransFormer_UT");
		GameRegistry.registerTileEntity(TesseractEntity.class, "Tesseract_UT");
		
		nameBlocks();
	}
	
	public static void nameBlocks(){
		//Language

		LanguageRegistry.addName(new ItemStack(Reactor,1,0), "Reactor Core");
		LanguageRegistry.addName(new ItemStack(Reactor,1,1), "Reactor Wall");
		LanguageRegistry.addName(new ItemStack(Reactor,1,2), "Reactor Tank");
		LanguageRegistry.addName(new ItemStack(Reactor,1,3), "Reactor Controller");
		LanguageRegistry.addName(new ItemStack(Reactor,1,4), "Reactor Water Provider");
		LanguageRegistry.addName(new ItemStack(Reactor,1,5), "Reactor Steam Turbine");

		LanguageRegistry.addName(new ItemStack(Ores,1,0), "Radionite Ore");
		LanguageRegistry.addName(new ItemStack(Ores,1,1), "Aluminum Ore");
		LanguageRegistry.addName(new ItemStack(Ores,1,2), "Copper Ore");
		LanguageRegistry.addName(new ItemStack(Ores,1,3), "Tin Ore");
		LanguageRegistry.addName(new ItemStack(Ores,1,4), "Lead Ore");
		LanguageRegistry.addName(new ItemStack(Ores,1,5), "Silver Ore");
		
		LanguageRegistry.addName(new ItemStack(Chasis,1,0), "Machine Chasis MK1");
		LanguageRegistry.addName(new ItemStack(Chasis,1,1), "Machine Chasis MK2");
		LanguageRegistry.addName(new ItemStack(Chasis,1,2), "Machine Chasis MK3");
		
		LanguageRegistry.addName(new ItemStack(Tier1,1,0), "Crafter");
		LanguageRegistry.addName(new ItemStack(Tier1,1,1), "Generator");
		LanguageRegistry.addName(new ItemStack(Tier1,1,2), "CVD");
		LanguageRegistry.addName(new ItemStack(Tier1,1,3), "Painter");
		LanguageRegistry.addName(new ItemStack(Tier1,1,4), "Charge Station");
		
		LanguageRegistry.addName(new ItemStack(Tier2,1,0), "Furnace");
		LanguageRegistry.addName(new ItemStack(Tier2,1,1), "Purifier");
		LanguageRegistry.addName(new ItemStack(Tier2,1,2), "Cutter");
		LanguageRegistry.addName(new ItemStack(Tier2,1,3), "Presurizer");

		LanguageRegistry.addName(new ItemStack(Tier3,1,0), "Miner");
		LanguageRegistry.addName(new ItemStack(Tier3,1,1), "Hologram Emiter");
		LanguageRegistry.addName(new ItemStack(Tier3,1,2), "Precision Crafter");
		LanguageRegistry.addName(new ItemStack(Tier3,1,3), "Climate Station");
		LanguageRegistry.addName(new ItemStack(Tier3,1,4), "Tesseract");
		
		LanguageRegistry.addName(new ItemStack(Storage,1,0), "Battery Tier1");
		LanguageRegistry.addName(new ItemStack(Storage,1,1), "Battery Tier2");
		LanguageRegistry.addName(new ItemStack(Storage,1,2), "Battery Tier3");

		LanguageRegistry.addName(new ItemStack(Misc,1,0), "Radionite Block");
		LanguageRegistry.addName(new ItemStack(Misc,1,1), "Grafeno Block");
		
		LanguageRegistry.addName(new ItemStack(Cable,1,0), "Electric Cable");

		LanguageRegistry.addName(DiamondGlass, "Diamond Glass");
		LanguageRegistry.addName(CovedGlass, "Coved Glass");
	}

}

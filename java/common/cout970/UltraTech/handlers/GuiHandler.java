package common.cout970.UltraTech.handlers;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.FluidGeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalPlant_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.CutterT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.FurnaceT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.LaminatorT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.PurifierT1_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.Painter3DEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;
import common.cout970.UltraTech.client.gui.Boiler_Gui;
import common.cout970.UltraTech.client.gui.CVD_Gui;
import common.cout970.UltraTech.client.gui.ChargeStation_Gui;
import common.cout970.UltraTech.client.gui.Chemical_Gui;
import common.cout970.UltraTech.client.gui.ClimateStation_Gui;
import common.cout970.UltraTech.client.gui.ControllerGui;
import common.cout970.UltraTech.client.gui.Crafter_Gui;
import common.cout970.UltraTech.client.gui.Cutter_Gui;
import common.cout970.UltraTech.client.gui.Dynamo_Gui;
import common.cout970.UltraTech.client.gui.EngineGui;
import common.cout970.UltraTech.client.gui.Fermenter_Gui;
import common.cout970.UltraTech.client.gui.FluidGenGui;
import common.cout970.UltraTech.client.gui.Furnace_Gui;
import common.cout970.UltraTech.client.gui.Generator_Gui;
import common.cout970.UltraTech.client.gui.Heater_Gui;
import common.cout970.UltraTech.client.gui.HologramEmiterGui;
import common.cout970.UltraTech.client.gui.Laminator_Gui;
import common.cout970.UltraTech.client.gui.MAssemblyGui;
import common.cout970.UltraTech.client.gui.MinerGui;
import common.cout970.UltraTech.client.gui.Printer3D_Gui;
import common.cout970.UltraTech.client.gui.Purifier_Gui;
import common.cout970.UltraTech.client.gui.ReactorGui;
import common.cout970.UltraTech.client.gui.Refinery_Gui;
import common.cout970.UltraTech.client.gui.Storage_Gui;
import common.cout970.UltraTech.client.gui.TesseractGui;
import common.cout970.UltraTech.containers.BoilerContainer;
import common.cout970.UltraTech.containers.CVDcontainer;
import common.cout970.UltraTech.containers.ChargeStationContainer;
import common.cout970.UltraTech.containers.Chemical_Container;
import common.cout970.UltraTech.containers.ClimateStationContainer;
import common.cout970.UltraTech.containers.ControllerContainer;
import common.cout970.UltraTech.containers.CrafterContainer;
import common.cout970.UltraTech.containers.CutterContainer;
import common.cout970.UltraTech.containers.Dynamo_Container;
import common.cout970.UltraTech.containers.EngineContainer;
import common.cout970.UltraTech.containers.FermenterContainer;
import common.cout970.UltraTech.containers.FluidGenContainer;
import common.cout970.UltraTech.containers.FurnaceContainer;
import common.cout970.UltraTech.containers.GeneratorContainer;
import common.cout970.UltraTech.containers.Heater_Container;
import common.cout970.UltraTech.containers.HologramEmiterContainer;
import common.cout970.UltraTech.containers.LaminatorContainer;
import common.cout970.UltraTech.containers.MAssemblyContainer;
import common.cout970.UltraTech.containers.MinerContainer;
import common.cout970.UltraTech.containers.Printer3DContainer;
import common.cout970.UltraTech.containers.PurifierContainer;
import common.cout970.UltraTech.containers.ReactorContainer;
import common.cout970.UltraTech.containers.RefineryContainer;
import common.cout970.UltraTech.containers.StorageContainer;
import common.cout970.UltraTech.containers.TabletContainer;
import common.cout970.UltraTech.containers.TesseractContainer;
import common.cout970.UltraTech.multiblocks.refinery.RefineryBase;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.wiki.TabletGui;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == 20)return new TabletContainer(player.inventory);
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof ChemicalVaporDesintegrationT1_Entity){
			return new CVDcontainer(player.inventory, (ChemicalVaporDesintegrationT1_Entity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof FurnaceT1_Entity){
			return new FurnaceContainer(player.inventory, (FurnaceT1_Entity) tileEntity);
		}
		//storage
		if(tileEntity instanceof StorageTier1 || tileEntity instanceof StorageTier2 || tileEntity instanceof StorageTier3){
			return new StorageContainer(player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CutterT1_Entity){
			return new CutterContainer(player.inventory, (CutterT1_Entity) tileEntity);
		}
		//purifier
		if(tileEntity instanceof PurifierT1_Entity){
			return new PurifierContainer(player.inventory, (PurifierT1_Entity) tileEntity);
		}
		//Generator
		if(tileEntity instanceof CoalGeneratorEntityT1_Entity){
			return new GeneratorContainer(player.inventory, (CoalGeneratorEntityT1_Entity) tileEntity);
		}
		//Miner
		if(tileEntity instanceof MinerEntity){
			return new MinerContainer(player.inventory, (MinerEntity) tileEntity);
		}
		//Reactor
		if(tileEntity instanceof ReactorEntity){
			return new ReactorContainer(player.inventory, (ReactorEntity) tileEntity);
		}
		//MAssembly
		if(tileEntity instanceof MolecularAssemblyEntity){
			return new MAssemblyContainer(player.inventory, (MolecularAssemblyEntity) tileEntity);
		}
		//ChargeStation
		if(tileEntity instanceof ChargeStationEntity){
			return new ChargeStationContainer(player.inventory, (ChargeStationEntity) tileEntity);
		}
		//Printer3D
		if(tileEntity instanceof Painter3DEntity){
			return new Printer3DContainer(player.inventory, (Painter3DEntity) tileEntity);
		}
		//Engine
		if(tileEntity instanceof EngineEntity){
			return new EngineContainer(player.inventory, (EngineEntity) tileEntity);
		}
		//Crafter
		if(tileEntity instanceof CrafterEntity){
			return new CrafterContainer(player.inventory, (CrafterEntity) tileEntity);
		}
		//Tesseract
		if(tileEntity instanceof Tesseract_Entity){
			return new TesseractContainer(player.inventory, (Tesseract_Entity) tileEntity);
		}
		//Fermenter
		if(tileEntity instanceof FermenterEntity){
			return new FermenterContainer(player.inventory, (FermenterEntity) tileEntity);
		}
		//HologramEmiter
		if(tileEntity instanceof HologramEmiterEntity){
			return new HologramEmiterContainer(player.inventory, (HologramEmiterEntity) tileEntity);
		}
		//Boiler
		if(tileEntity instanceof BoilerEntity){
			return new BoilerContainer(player.inventory, (BoilerEntity) tileEntity);
		}
		//Controller
		if(tileEntity instanceof ReactorControllerEntity){
			return new ControllerContainer(player.inventory, (ReactorControllerEntity) tileEntity);
		}
		//climate
		if(tileEntity instanceof ClimateEntity){
			return new ClimateStationContainer(player.inventory, (ClimateEntity) tileEntity);
		}
		//FluidGenerator
		if(tileEntity instanceof FluidGeneratorEntity){
			return new FluidGenContainer(player.inventory, tileEntity);
		}
		//Refinery
		if(tileEntity instanceof RefineryBase){
			return new RefineryContainer(player.inventory, tileEntity);
		}
		//Laminator
		if(tileEntity instanceof LaminatorT1_Entity){
			return new LaminatorContainer(player.inventory, (LaminatorT1_Entity) tileEntity);
		}
		//Heater
		if(tileEntity instanceof Heater_Entity){
			return new Heater_Container(player.inventory, (Heater_Entity) tileEntity);
		}
		//Chemical
		if(tileEntity instanceof ChemicalPlant_Entity){
			return new Chemical_Container(player.inventory, (ChemicalPlant_Entity) tileEntity);
		}
		//Dynamo
		if(tileEntity instanceof DynamoEntity){
			return new Dynamo_Container(player.inventory, (DynamoEntity) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == 20)return new TabletGui(new TabletContainer(player.inventory));
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof ChemicalVaporDesintegrationT1_Entity){
			return new CVD_Gui(new CVDcontainer(player.inventory, (ChemicalVaporDesintegrationT1_Entity) tileEntity),player.inventory, (ChemicalVaporDesintegrationT1_Entity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof FurnaceT1_Entity){
			return new Furnace_Gui(new FurnaceContainer(player.inventory, (FurnaceT1_Entity) tileEntity),player.inventory, (FurnaceT1_Entity) tileEntity);
		}
		//storage
		if(tileEntity instanceof StorageTier1 || tileEntity instanceof StorageTier2 || tileEntity instanceof StorageTier3){
			return new Storage_Gui(new StorageContainer(player.inventory, (Machine) tileEntity), player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CutterT1_Entity){
			return new Cutter_Gui(new CutterContainer(player.inventory, (CutterT1_Entity) tileEntity),player.inventory, (CutterT1_Entity) tileEntity);
		}
		//presurechamber
//		if(tileEntity instanceof PresuricerEntity){
//			return new PresuricerGui(new PresuricerContaner(player.inventory, (PresuricerEntity) tileEntity),player.inventory, (PresuricerEntity) tileEntity);
//		}
		//purifier
		if(tileEntity instanceof PurifierT1_Entity){
			return new Purifier_Gui(new PurifierContainer(player.inventory, (PurifierT1_Entity) tileEntity),player.inventory, (PurifierT1_Entity) tileEntity);
		}
		//generator
		if(tileEntity instanceof CoalGeneratorEntityT1_Entity){
			return new Generator_Gui(new GeneratorContainer(player.inventory, (CoalGeneratorEntityT1_Entity) tileEntity), player.inventory, (CoalGeneratorEntityT1_Entity) tileEntity);
		}
		//Miner
		if(tileEntity instanceof MinerEntity){
			return new MinerGui(new MinerContainer(player.inventory, (MinerEntity) tileEntity), player.inventory, (MinerEntity) tileEntity);
		}
		//Reactor
		if(tileEntity instanceof ReactorEntity){
			return new ReactorGui(new ReactorContainer(player.inventory, (ReactorEntity) tileEntity), player.inventory, (ReactorEntity) tileEntity);
		}
		//MAssembly
		if(tileEntity instanceof MolecularAssemblyEntity){
			return new MAssemblyGui(new MAssemblyContainer(player.inventory, (MolecularAssemblyEntity) tileEntity), player.inventory, (MolecularAssemblyEntity) tileEntity);
		}
		//ChargeStation
		if(tileEntity instanceof ChargeStationEntity){
			return new ChargeStation_Gui(new ChargeStationContainer(player.inventory, (ChargeStationEntity) tileEntity), player.inventory, (ChargeStationEntity) tileEntity);
		}
		//Printer3D
		if(tileEntity instanceof Painter3DEntity){
			return new Printer3D_Gui(new Printer3DContainer(player.inventory, (Painter3DEntity) tileEntity), player.inventory, (Painter3DEntity) tileEntity);
		}
		//Engine
		if(tileEntity instanceof EngineEntity){
			return new EngineGui(new EngineContainer(player.inventory, (EngineEntity) tileEntity), player.inventory, (EngineEntity) tileEntity);
		}
		//Crafter
		if(tileEntity instanceof CrafterEntity){
			return new Crafter_Gui(new CrafterContainer(player.inventory, (CrafterEntity) tileEntity), player.inventory, (CrafterEntity) tileEntity);
		}
		//Tesseract
		if(tileEntity instanceof Tesseract_Entity){
			return new TesseractGui(new TesseractContainer(player.inventory, (Tesseract_Entity) tileEntity), player.inventory, (Tesseract_Entity) tileEntity);
		}
		//Fermenter
		if(tileEntity instanceof FermenterEntity){
			return new Fermenter_Gui(new FermenterContainer(player.inventory, (FermenterEntity) tileEntity), (FermenterEntity) tileEntity);
		}
		//HologramEmiter
		if(tileEntity instanceof HologramEmiterEntity){
			return new HologramEmiterGui(new HologramEmiterContainer(player.inventory, (HologramEmiterEntity) tileEntity), (HologramEmiterEntity) tileEntity);
		}
		//Boiler
		if(tileEntity instanceof BoilerEntity){
			return new Boiler_Gui(new BoilerContainer(player.inventory, (BoilerEntity) tileEntity), (BoilerEntity) tileEntity);
		}
		//Controller
		if(tileEntity instanceof ReactorControllerEntity){
			return new ControllerGui(new ControllerContainer(player.inventory, (ReactorControllerEntity) tileEntity), player.inventory, (ReactorControllerEntity) tileEntity);
		}
		//Climate
		if(tileEntity instanceof ClimateEntity){
			return new ClimateStation_Gui(new ClimateStationContainer(player.inventory, (ClimateEntity) tileEntity), (ClimateEntity) tileEntity);
		}
		//FluidGenerator
		if(tileEntity instanceof FluidGeneratorEntity){
			return new FluidGenGui(new FluidGenContainer(player.inventory, tileEntity), (FluidGeneratorEntity) tileEntity);
		}
		//Refinery
		if(tileEntity instanceof RefineryBase){
			return new Refinery_Gui(new RefineryContainer(player.inventory, tileEntity),(RefineryBase) tileEntity);
		}
		//Laminator
		if(tileEntity instanceof LaminatorT1_Entity){
			return new Laminator_Gui(new LaminatorContainer(player.inventory, (LaminatorT1_Entity) tileEntity),(LaminatorT1_Entity) tileEntity);
		}
		//Heater
		if(tileEntity instanceof Heater_Entity){
			return new Heater_Gui(new Heater_Container(player.inventory, (Heater_Entity) tileEntity),(Heater_Entity) tileEntity);
		}
		//Chemical
		if(tileEntity instanceof ChemicalPlant_Entity){
			return new Chemical_Gui(new Chemical_Container(player.inventory, (ChemicalPlant_Entity) tileEntity),(ChemicalPlant_Entity) tileEntity);
		}
		//Dynamo
		if(tileEntity instanceof DynamoEntity){
			return new Dynamo_Gui(new Dynamo_Container(player.inventory,tileEntity),(DynamoEntity) tileEntity);
		}
		return null;
	}

}

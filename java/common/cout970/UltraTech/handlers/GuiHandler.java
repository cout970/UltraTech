package common.cout970.UltraTech.handlers;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import api.cout970.UltraTech.Vpower.Machine;

import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.TileEntities.electric.CVD_Entity;
import common.cout970.UltraTech.TileEntities.electric.ChargeStationEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.electric.CutterEntity;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.TileEntities.electric.FluidGenerator;
import common.cout970.UltraTech.TileEntities.electric.FurnaceEntity;
import common.cout970.UltraTech.TileEntities.electric.GeneratorEntity;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.TileEntities.electric.MolecularAssemblyEntity;
import common.cout970.UltraTech.TileEntities.electric.PresuricerEntity;
import common.cout970.UltraTech.TileEntities.electric.PurifierEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.TesseractEntity;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.Printer3DEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;
import common.cout970.UltraTech.containers.BoilerContainer;
import common.cout970.UltraTech.containers.CVDcontainer;
import common.cout970.UltraTech.containers.ChargeStationContainer;
import common.cout970.UltraTech.containers.ClimateStationContainer;
import common.cout970.UltraTech.containers.ControllerContainer;
import common.cout970.UltraTech.containers.CrafterContainer;
import common.cout970.UltraTech.containers.EngineContainer;
import common.cout970.UltraTech.containers.FermenterContainer;
import common.cout970.UltraTech.containers.FluidGenContainer;
import common.cout970.UltraTech.containers.GeneratorContainer;
import common.cout970.UltraTech.containers.HologramEmiterContainer;
import common.cout970.UltraTech.containers.MAssemblyContainer;
import common.cout970.UltraTech.containers.MinerContainer;
import common.cout970.UltraTech.containers.PrecisionCuterContainer;
import common.cout970.UltraTech.containers.PresuricerContaner;
import common.cout970.UltraTech.containers.Printer3DContainer;
import common.cout970.UltraTech.containers.PurifierContainer;
import common.cout970.UltraTech.containers.ReactorContainer;
import common.cout970.UltraTech.containers.StorageContainer;
import common.cout970.UltraTech.containers.TesseractContainer;
import common.cout970.UltraTech.containers.UTfurnaceContainer;
import common.cout970.UltraTech.gui.BoilerGui;
import common.cout970.UltraTech.gui.CVDgui;
import common.cout970.UltraTech.gui.ChargeStationGui;
import common.cout970.UltraTech.gui.ClimateStationGui;
import common.cout970.UltraTech.gui.ControllerGui;
import common.cout970.UltraTech.gui.CrafterGui;
import common.cout970.UltraTech.gui.CuterGui;
import common.cout970.UltraTech.gui.EngineGui;
import common.cout970.UltraTech.gui.FermenterGui;
import common.cout970.UltraTech.gui.FluidGenGui;
import common.cout970.UltraTech.gui.GeneratorGui;
import common.cout970.UltraTech.gui.HologramEmiterGui;
import common.cout970.UltraTech.gui.MAssemblyGui;
import common.cout970.UltraTech.gui.MinerGui;
import common.cout970.UltraTech.gui.PresuricerGui;
import common.cout970.UltraTech.gui.Printer3DGui;
import common.cout970.UltraTech.gui.PurifierGui;
import common.cout970.UltraTech.gui.ReactorGui;
import common.cout970.UltraTech.gui.StorageGui;
import common.cout970.UltraTech.gui.TesseractGui;
import common.cout970.UltraTech.gui.UTfurnaceGui;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof CVD_Entity){
			return new CVDcontainer(player.inventory, (CVD_Entity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof FurnaceEntity){
			return new UTfurnaceContainer(player.inventory, (FurnaceEntity) tileEntity);
		}
		//storage
		if(tileEntity instanceof StorageTier1 || tileEntity instanceof StorageTier2 || tileEntity instanceof StorageTier3){
			return new StorageContainer(player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CutterEntity){
			return new PrecisionCuterContainer(player.inventory, (CutterEntity) tileEntity);
		}
		//presurechamber
		if(tileEntity instanceof PresuricerEntity){
			return new PresuricerContaner(player.inventory, (PresuricerEntity) tileEntity);
		}
		//purifier
		if(tileEntity instanceof PurifierEntity){
			return new PurifierContainer(player.inventory, (PurifierEntity) tileEntity);
		}
		//Generator
		if(tileEntity instanceof GeneratorEntity){
			return new GeneratorContainer(player.inventory, (GeneratorEntity) tileEntity);
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
		if(tileEntity instanceof Printer3DEntity){
			return new Printer3DContainer(player.inventory, (Printer3DEntity) tileEntity);
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
		if(tileEntity instanceof TesseractEntity){
			return new TesseractContainer(player.inventory, (TesseractEntity) tileEntity);
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
		if(tileEntity instanceof FluidGenerator){
			return new FluidGenContainer(player.inventory, tileEntity);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof CVD_Entity){
			return new CVDgui(new CVDcontainer(player.inventory, (CVD_Entity) tileEntity),player.inventory, (CVD_Entity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof FurnaceEntity){
			return new UTfurnaceGui(new UTfurnaceContainer(player.inventory, (FurnaceEntity) tileEntity),player.inventory, (FurnaceEntity) tileEntity);
		}
		//storage
		if(tileEntity instanceof StorageTier1 || tileEntity instanceof StorageTier2 || tileEntity instanceof StorageTier3){
			return new StorageGui(new StorageContainer(player.inventory, (Machine) tileEntity), player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CutterEntity){
			return new CuterGui(new PrecisionCuterContainer(player.inventory, (CutterEntity) tileEntity),player.inventory, (CutterEntity) tileEntity);
		}
		//presurechamber
		if(tileEntity instanceof PresuricerEntity){
			return new PresuricerGui(new PresuricerContaner(player.inventory, (PresuricerEntity) tileEntity),player.inventory, (PresuricerEntity) tileEntity);
		}
		//purifier
		if(tileEntity instanceof PurifierEntity){
			return new PurifierGui(new PurifierContainer(player.inventory, (PurifierEntity) tileEntity),player.inventory, (PurifierEntity) tileEntity);
		}
		//generator
		if(tileEntity instanceof GeneratorEntity){
			return new GeneratorGui(new GeneratorContainer(player.inventory, (GeneratorEntity) tileEntity), player.inventory, (GeneratorEntity) tileEntity);
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
			return new ChargeStationGui(new ChargeStationContainer(player.inventory, (ChargeStationEntity) tileEntity), player.inventory, (ChargeStationEntity) tileEntity);
		}
		//Printer3D
		if(tileEntity instanceof Printer3DEntity){
			return new Printer3DGui(new Printer3DContainer(player.inventory, (Printer3DEntity) tileEntity), player.inventory, (Printer3DEntity) tileEntity);
		}
		//Engine
		if(tileEntity instanceof EngineEntity){
			return new EngineGui(new EngineContainer(player.inventory, (EngineEntity) tileEntity), player.inventory, (EngineEntity) tileEntity);
		}
		//Crafter
		if(tileEntity instanceof CrafterEntity){
			return new CrafterGui(new CrafterContainer(player.inventory, (CrafterEntity) tileEntity), player.inventory, (CrafterEntity) tileEntity);
		}
		//Tesseract
		if(tileEntity instanceof TesseractEntity){
			return new TesseractGui(new TesseractContainer(player.inventory, (TesseractEntity) tileEntity), player.inventory, (TesseractEntity) tileEntity);
		}
		//Fermenter
		if(tileEntity instanceof FermenterEntity){
			return new FermenterGui(new FermenterContainer(player.inventory, (FermenterEntity) tileEntity), (FermenterEntity) tileEntity);
		}
		//HologramEmiter
		if(tileEntity instanceof HologramEmiterEntity){
			return new HologramEmiterGui(new HologramEmiterContainer(player.inventory, (HologramEmiterEntity) tileEntity), (HologramEmiterEntity) tileEntity);
		}
		//Boiler
		if(tileEntity instanceof BoilerEntity){
			return new BoilerGui(new BoilerContainer(player.inventory, (BoilerEntity) tileEntity), (BoilerEntity) tileEntity);
		}
		//Controller
		if(tileEntity instanceof ReactorControllerEntity){
			return new ControllerGui(new ControllerContainer(player.inventory, (ReactorControllerEntity) tileEntity), player.inventory, (ReactorControllerEntity) tileEntity);
		}
		//Climate
		if(tileEntity instanceof ClimateEntity){
			return new ClimateStationGui(new ClimateStationContainer(player.inventory, (ClimateEntity) tileEntity), (ClimateEntity) tileEntity);
		}
		//FluidGenerator
		if(tileEntity instanceof FluidGenerator){
			return new FluidGenGui(new FluidGenContainer(player.inventory, tileEntity), (FluidGenerator) tileEntity);
		}
		return null;
	}

}

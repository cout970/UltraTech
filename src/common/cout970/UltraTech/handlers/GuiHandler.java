package common.cout970.UltraTech.handlers;


import common.cout970.UltraTech.TileEntities.Tier1.*;
import common.cout970.UltraTech.TileEntities.Tier2.*;
import common.cout970.UltraTech.TileEntities.Tier3.*;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.machines.containers.*;
import common.cout970.UltraTech.machines.gui.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

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
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

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
		return null;
	}

}

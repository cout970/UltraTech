package common.cout970.UltraTech.handlers;


import common.cout970.UltraTech.machines.containers.CVDcontainer;
import common.cout970.UltraTech.machines.containers.ChargeStationContainer;
import common.cout970.UltraTech.machines.containers.GeneratorContainer;
import common.cout970.UltraTech.machines.containers.IDScontainer;
import common.cout970.UltraTech.machines.containers.MAssemblyContainer;
import common.cout970.UltraTech.machines.containers.MinerContainer;
import common.cout970.UltraTech.machines.containers.PrecisionCuterContainer;
import common.cout970.UltraTech.machines.containers.PresuricerContaner;
import common.cout970.UltraTech.machines.containers.Printer3DContainer;
import common.cout970.UltraTech.machines.containers.PurifierContainer;
import common.cout970.UltraTech.machines.containers.ReactorContainer;
import common.cout970.UltraTech.machines.containers.UTfurnaceContainer;
import common.cout970.UltraTech.machines.gui.CVDgui;
import common.cout970.UltraTech.machines.gui.ChargeStationGui;
import common.cout970.UltraTech.machines.gui.CuterGui;
import common.cout970.UltraTech.machines.gui.GeneratorGui;
import common.cout970.UltraTech.machines.gui.IDSgui;
import common.cout970.UltraTech.machines.gui.MAssemblyGui;
import common.cout970.UltraTech.machines.gui.MinerGui;
import common.cout970.UltraTech.machines.gui.PresuricerGui;
import common.cout970.UltraTech.machines.gui.Printer3DGui;
import common.cout970.UltraTech.machines.gui.PurifierGui;
import common.cout970.UltraTech.machines.gui.ReactorGui;
import common.cout970.UltraTech.machines.gui.UTfurnaceGui;
import common.cout970.UltraTech.machines.tileEntities.CVDentity;
import common.cout970.UltraTech.machines.tileEntities.ChargeStationEntity;
import common.cout970.UltraTech.machines.tileEntities.CuterEntity;
import common.cout970.UltraTech.machines.tileEntities.GeneratorEntity;
import common.cout970.UltraTech.machines.tileEntities.IDSentity;
import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import common.cout970.UltraTech.machines.tileEntities.MolecularAssemblyEntity;
import common.cout970.UltraTech.machines.tileEntities.PresuricerEntity;
import common.cout970.UltraTech.machines.tileEntities.Printer3DEntity;
import common.cout970.UltraTech.machines.tileEntities.PurifierEntity;
import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;
import common.cout970.UltraTech.machines.tileEntities.UTfurnaceEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		//CVDmachine
		if(tileEntity instanceof CVDentity){
			return new CVDcontainer(player.inventory, (CVDentity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof UTfurnaceEntity){
			return new UTfurnaceContainer(player.inventory, (UTfurnaceEntity) tileEntity);
		}
		//storage
		if(tileEntity instanceof IDSentity){
			return new IDScontainer(player.inventory, (IDSentity) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CuterEntity){
			return new PrecisionCuterContainer(player.inventory, (CuterEntity) tileEntity);
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

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		//CVDmachine
		if(tileEntity instanceof CVDentity){
			return new CVDgui(new CVDcontainer(player.inventory, (CVDentity) tileEntity),player.inventory, (CVDentity) tileEntity);
		}
		//furnace
		if(tileEntity instanceof UTfurnaceEntity){
			return new UTfurnaceGui(new UTfurnaceContainer(player.inventory, (UTfurnaceEntity) tileEntity),player.inventory, (UTfurnaceEntity) tileEntity);
		}
		//storage
		if(tileEntity instanceof IDSentity){
			return new IDSgui(new IDScontainer(player.inventory, (IDSentity) tileEntity),player.inventory, (IDSentity) tileEntity);
		}
		//cuter
		if(tileEntity instanceof CuterEntity){
			return new CuterGui(new PrecisionCuterContainer(player.inventory, (CuterEntity) tileEntity),player.inventory, (CuterEntity) tileEntity);
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
		
		return null;
	}

}

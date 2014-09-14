package common.cout970.UltraTech.handlers;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import common.cout970.UltraTech.TileEntities.electric.TileEntityChargeStation;
import common.cout970.UltraTech.TileEntities.electric.TileEntityClimateStation;
import common.cout970.UltraTech.TileEntities.electric.TileEntityFermenter;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMiner;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMolecularAssembly;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier1;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier2;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier3;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier4;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalPlantT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityChemicalVaporDesintegrationT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCoalGeneratorEntityT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityCutterT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFluidGeneratorT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFurnaceT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityHeater;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityLaminatorT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityPurifierT1;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;
import common.cout970.UltraTech.TileEntities.fluid.TileEntityBoiler;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityDynamo;
import common.cout970.UltraTech.TileEntities.intermod.TileEntityEngine;
import common.cout970.UltraTech.TileEntities.logistics.TileEntityInfiniteSupply;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Core_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_IO_Entity;
import common.cout970.UltraTech.TileEntities.utility.TileEntityCrafter;
import common.cout970.UltraTech.TileEntities.utility.TileEntityHologramEmiter;
import common.cout970.UltraTech.TileEntities.utility.TileEntityPainter3D;
import common.cout970.UltraTech.client.gui.GuiBoiler;
import common.cout970.UltraTech.client.gui.GuiCVD;
import common.cout970.UltraTech.client.gui.GuiChargeStation;
import common.cout970.UltraTech.client.gui.GuiChemical;
import common.cout970.UltraTech.client.gui.GuiClimateStation;
import common.cout970.UltraTech.client.gui.GuiCoalGenerator;
import common.cout970.UltraTech.client.gui.GuiCrafter;
import common.cout970.UltraTech.client.gui.GuiCutter;
import common.cout970.UltraTech.client.gui.GuiDynamo;
import common.cout970.UltraTech.client.gui.GuiEngine;
import common.cout970.UltraTech.client.gui.GuiFermenter;
import common.cout970.UltraTech.client.gui.GuiFluidGenerator;
import common.cout970.UltraTech.client.gui.GuiFurnace;
import common.cout970.UltraTech.client.gui.GuiHeater;
import common.cout970.UltraTech.client.gui.GuiHologramEmiter;
import common.cout970.UltraTech.client.gui.GuiInfiniteSupply;
import common.cout970.UltraTech.client.gui.GuiLaminator;
import common.cout970.UltraTech.client.gui.GuiMolecularAssembly;
import common.cout970.UltraTech.client.gui.GuiMiner;
import common.cout970.UltraTech.client.gui.GuiPainter3D;
import common.cout970.UltraTech.client.gui.GuiPurifier;
import common.cout970.UltraTech.client.gui.GuiReactor;
import common.cout970.UltraTech.client.gui.GuiRefinery;
import common.cout970.UltraTech.client.gui.GuiBattery;
import common.cout970.UltraTech.client.gui.GuiTesseract;
import common.cout970.UltraTech.containers.ContainerBoiler;
import common.cout970.UltraTech.containers.ContainerCVD;
import common.cout970.UltraTech.containers.ContainerChargeStation;
import common.cout970.UltraTech.containers.ContainerChemical;
import common.cout970.UltraTech.containers.ContainerClimateStation;
import common.cout970.UltraTech.containers.ContainerCrafter;
import common.cout970.UltraTech.containers.ContainerCutter;
import common.cout970.UltraTech.containers.ContainerDynamo;
import common.cout970.UltraTech.containers.ContainerEngine;
import common.cout970.UltraTech.containers.ContainerFermenter;
import common.cout970.UltraTech.containers.ContainerFluidGenerator;
import common.cout970.UltraTech.containers.ContainerFurnace;
import common.cout970.UltraTech.containers.ContainerGenerator;
import common.cout970.UltraTech.containers.ContainerHeater;
import common.cout970.UltraTech.containers.ContainerHologramEmiter;
import common.cout970.UltraTech.containers.ContainerInfiniteSupply;
import common.cout970.UltraTech.containers.ContainerLaminator;
import common.cout970.UltraTech.containers.ContainerMolecularAssembly;
import common.cout970.UltraTech.containers.ContainerMiner;
import common.cout970.UltraTech.containers.ContainerPainter3D;
import common.cout970.UltraTech.containers.ContainerPurifier;
import common.cout970.UltraTech.containers.ContainerReactor;
import common.cout970.UltraTech.containers.ContainerRefinery;
import common.cout970.UltraTech.containers.ContainerBattery;
import common.cout970.UltraTech.containers.ContainerTablet;
import common.cout970.UltraTech.containers.ContainerTesseract;
import common.cout970.UltraTech.util.power.Machine;
import common.cout970.UltraTech.wiki.TabletGui;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == 20)return new ContainerTablet(player.inventory);
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof TileEntityChemicalVaporDesintegrationT1){
			return new ContainerCVD(player.inventory, (TileEntityChemicalVaporDesintegrationT1) tileEntity);
		}
		//furnace
		if(tileEntity instanceof TileEntityFurnaceT1){
			return new ContainerFurnace(player.inventory, (TileEntityFurnaceT1) tileEntity);
		}
		//storage
		if(tileEntity instanceof TileEntityBatteryTier1 || tileEntity instanceof TileEntityBatteryTier2 || tileEntity instanceof TileEntityBatteryTier3 || tileEntity instanceof TileEntityBatteryTier4){
			return new ContainerBattery(player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof TileEntityCutterT1){
			return new ContainerCutter(player.inventory, (TileEntityCutterT1) tileEntity);
		}
		//purifier
		if(tileEntity instanceof TileEntityPurifierT1){
			return new ContainerPurifier(player.inventory, (TileEntityPurifierT1) tileEntity);
		}
		//Generator
		if(tileEntity instanceof TileEntityCoalGeneratorEntityT1){
			return new ContainerGenerator(player.inventory, (TileEntityCoalGeneratorEntityT1) tileEntity);
		}
		//Miner
		if(tileEntity instanceof TileEntityMiner){
			return new ContainerMiner(player.inventory, (TileEntityMiner) tileEntity);
		}
		//Reactor
		if(tileEntity instanceof Reactor_Core_Entity){
			return new ContainerReactor(player.inventory, tileEntity);
		}
		//MAssembly
		if(tileEntity instanceof TileEntityMolecularAssembly){
			return new ContainerMolecularAssembly(player.inventory, (TileEntityMolecularAssembly) tileEntity);
		}
		//ChargeStation
		if(tileEntity instanceof TileEntityChargeStation){
			return new ContainerChargeStation(player.inventory, (TileEntityChargeStation) tileEntity);
		}
		//Printer3D
		if(tileEntity instanceof TileEntityPainter3D){
			return new ContainerPainter3D(player.inventory, (TileEntityPainter3D) tileEntity);
		}
		//Engine
		if(tileEntity instanceof TileEntityEngine){
			return new ContainerEngine(player.inventory, (TileEntityEngine) tileEntity);
		}
		//Crafter
		if(tileEntity instanceof TileEntityCrafter){
			return new ContainerCrafter(player.inventory, (TileEntityCrafter) tileEntity);
		}
		//Tesseract
		if(tileEntity instanceof TileEntityTesseract){
			return new ContainerTesseract(player.inventory, (TileEntityTesseract) tileEntity);
		}
		//Fermenter
		if(tileEntity instanceof TileEntityFermenter){
			return new ContainerFermenter(player.inventory, (TileEntityFermenter) tileEntity);
		}
		//HologramEmiter
		if(tileEntity instanceof TileEntityHologramEmiter){
			return new ContainerHologramEmiter(player.inventory, (TileEntityHologramEmiter) tileEntity);
		}
		//Boiler
		if(tileEntity instanceof TileEntityBoiler){
			return new ContainerBoiler(player.inventory, (TileEntityBoiler) tileEntity);
		}
		//climate
		if(tileEntity instanceof TileEntityClimateStation){
			return new ContainerClimateStation(player.inventory, (TileEntityClimateStation) tileEntity);
		}
		//FluidGenerator
		if(tileEntity instanceof TileEntityFluidGeneratorT1){
			return new ContainerFluidGenerator(player.inventory, tileEntity);
		}
		//Refinery
		if(tileEntity instanceof Refinery_IO_Entity){
			return new ContainerRefinery(player.inventory, tileEntity);
		}
		//Laminator
		if(tileEntity instanceof TileEntityLaminatorT1){
			return new ContainerLaminator(player.inventory, (TileEntityLaminatorT1) tileEntity);
		}
		//Heater
		if(tileEntity instanceof TileEntityHeater){
			return new ContainerHeater(player.inventory, (TileEntityHeater) tileEntity);
		}
		//Chemical
		if(tileEntity instanceof TileEntityChemicalPlantT1){
			return new ContainerChemical(player.inventory, (TileEntityChemicalPlantT1) tileEntity);
		}
		//Dynamo
		if(tileEntity instanceof TileEntityDynamo){
			return new ContainerDynamo(player.inventory, (TileEntityDynamo) tileEntity);
		}
		//infinite supply
		if(tileEntity instanceof TileEntityInfiniteSupply){
			return new ContainerInfiniteSupply(player.inventory, (TileEntityInfiniteSupply) tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == 20)return new TabletGui(new ContainerTablet(player.inventory));
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		//CVDmachine
		if(tileEntity instanceof TileEntityChemicalVaporDesintegrationT1){
			return new GuiCVD(new ContainerCVD(player.inventory, (TileEntityChemicalVaporDesintegrationT1) tileEntity),player.inventory, (TileEntityChemicalVaporDesintegrationT1) tileEntity);
		}
		//furnace
		if(tileEntity instanceof TileEntityFurnaceT1){
			return new GuiFurnace(new ContainerFurnace(player.inventory, (TileEntityFurnaceT1) tileEntity),player.inventory, (TileEntityFurnaceT1) tileEntity);
		}
		//storage
		if(tileEntity instanceof TileEntityBatteryTier1 || tileEntity instanceof TileEntityBatteryTier2 || tileEntity instanceof TileEntityBatteryTier3 || tileEntity instanceof TileEntityBatteryTier4){
			return new GuiBattery(new ContainerBattery(player.inventory, (Machine) tileEntity), player.inventory, (Machine) tileEntity);
		}
		//cuter
		if(tileEntity instanceof TileEntityCutterT1){
			return new GuiCutter(new ContainerCutter(player.inventory, (TileEntityCutterT1) tileEntity),player.inventory, (TileEntityCutterT1) tileEntity);
		}
		//purifier
		if(tileEntity instanceof TileEntityPurifierT1){
			return new GuiPurifier(new ContainerPurifier(player.inventory, (TileEntityPurifierT1) tileEntity),player.inventory, (TileEntityPurifierT1) tileEntity);
		}
		//generator
		if(tileEntity instanceof TileEntityCoalGeneratorEntityT1){
			return new GuiCoalGenerator(new ContainerGenerator(player.inventory, (TileEntityCoalGeneratorEntityT1) tileEntity), player.inventory, (TileEntityCoalGeneratorEntityT1) tileEntity);
		}
		//Miner
		if(tileEntity instanceof TileEntityMiner){
			return new GuiMiner(new ContainerMiner(player.inventory, (TileEntityMiner) tileEntity), player.inventory, (TileEntityMiner) tileEntity);
		}
		//Reactor
		if(tileEntity instanceof Reactor_Core_Entity){
			return new GuiReactor(new ContainerReactor(player.inventory, tileEntity), player.inventory, tileEntity);
		}
		//MAssembly
		if(tileEntity instanceof TileEntityMolecularAssembly){
			return new GuiMolecularAssembly(new ContainerMolecularAssembly(player.inventory, (TileEntityMolecularAssembly) tileEntity), player.inventory, (TileEntityMolecularAssembly) tileEntity);
		}
		//ChargeStation
		if(tileEntity instanceof TileEntityChargeStation){
			return new GuiChargeStation(new ContainerChargeStation(player.inventory, (TileEntityChargeStation) tileEntity), player.inventory, (TileEntityChargeStation) tileEntity);
		}
		//Printer3D
		if(tileEntity instanceof TileEntityPainter3D){
			return new GuiPainter3D(new ContainerPainter3D(player.inventory, (TileEntityPainter3D) tileEntity), player.inventory, (TileEntityPainter3D) tileEntity);
		}
		//Engine
		if(tileEntity instanceof TileEntityEngine){
			return new GuiEngine(new ContainerEngine(player.inventory, (TileEntityEngine) tileEntity), player.inventory, (TileEntityEngine) tileEntity);
		}
		//Crafter
		if(tileEntity instanceof TileEntityCrafter){
			return new GuiCrafter(new ContainerCrafter(player.inventory, (TileEntityCrafter) tileEntity), player.inventory, (TileEntityCrafter) tileEntity);
		}
		//Tesseract
		if(tileEntity instanceof TileEntityTesseract){
			return new GuiTesseract(new ContainerTesseract(player.inventory, (TileEntityTesseract) tileEntity), player.inventory, (TileEntityTesseract) tileEntity);
		}
		//Fermenter
		if(tileEntity instanceof TileEntityFermenter){
			return new GuiFermenter(new ContainerFermenter(player.inventory, (TileEntityFermenter) tileEntity), (TileEntityFermenter) tileEntity);
		}
		//HologramEmiter
		if(tileEntity instanceof TileEntityHologramEmiter){
			return new GuiHologramEmiter(new ContainerHologramEmiter(player.inventory, (TileEntityHologramEmiter) tileEntity), (TileEntityHologramEmiter) tileEntity);
		}
		//Boiler
		if(tileEntity instanceof TileEntityBoiler){
			return new GuiBoiler(new ContainerBoiler(player.inventory, (TileEntityBoiler) tileEntity), (TileEntityBoiler) tileEntity);
		}
		//Climate
		if(tileEntity instanceof TileEntityClimateStation){
			return new GuiClimateStation(new ContainerClimateStation(player.inventory, (TileEntityClimateStation) tileEntity), (TileEntityClimateStation) tileEntity);
		}
		//FluidGenerator
		if(tileEntity instanceof TileEntityFluidGeneratorT1){
			return new GuiFluidGenerator(new ContainerFluidGenerator(player.inventory, tileEntity), (TileEntityFluidGeneratorT1) tileEntity);
		}
		//Refinery
		if(tileEntity instanceof Refinery_IO_Entity){
			return new GuiRefinery(new ContainerRefinery(player.inventory, tileEntity),(Refinery_IO_Entity) tileEntity);
		}
		//Laminator
		if(tileEntity instanceof TileEntityLaminatorT1){
			return new GuiLaminator(new ContainerLaminator(player.inventory, (TileEntityLaminatorT1) tileEntity),(TileEntityLaminatorT1) tileEntity);
		}
		//Heater
		if(tileEntity instanceof TileEntityHeater){
			return new GuiHeater(new ContainerHeater(player.inventory, (TileEntityHeater) tileEntity),(TileEntityHeater) tileEntity);
		}
		//Chemical
		if(tileEntity instanceof TileEntityChemicalPlantT1){
			return new GuiChemical(new ContainerChemical(player.inventory, (TileEntityChemicalPlantT1) tileEntity),(TileEntityChemicalPlantT1) tileEntity);
		}
		//Dynamo
		if(tileEntity instanceof TileEntityDynamo){
			return new GuiDynamo(new ContainerDynamo(player.inventory,tileEntity),(TileEntityDynamo) tileEntity);
		}
		//infinite supply
		if(tileEntity instanceof TileEntityInfiniteSupply){
			return new GuiInfiniteSupply(new ContainerInfiniteSupply(player.inventory,tileEntity),(TileEntityInfiniteSupply) tileEntity);
		}
		return null;
	}

}

package common.cout970.UltraTech.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import common.cout970.UltraTech.TileEntities.Tier1.AluminumPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.BoilerEntity;
import common.cout970.UltraTech.TileEntities.Tier1.CableEntity;
import common.cout970.UltraTech.TileEntities.Tier1.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.LeadPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.StorageTier1;
import common.cout970.UltraTech.TileEntities.Tier2.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.Tier2.EngineEntity;
import common.cout970.UltraTech.TileEntities.Tier2.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.Tier2.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.Tier2.StorageTier2;
import common.cout970.UltraTech.TileEntities.Tier2.TankEntity;
import common.cout970.UltraTech.TileEntities.Tier2.WindMillEntity;
import common.cout970.UltraTech.TileEntities.Tier3.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.TileEntities.Tier3.StorageTier3;
import common.cout970.UltraTech.machines.renders.DecoBlocksRender;
import common.cout970.UltraTech.machines.renders.ReactorTankRender;
import common.cout970.UltraTech.machines.renders.RenderBattery;
import common.cout970.UltraTech.machines.renders.RenderBoiler;
import common.cout970.UltraTech.machines.renders.RenderCable;
import common.cout970.UltraTech.machines.renders.RenderEngine;
import common.cout970.UltraTech.machines.renders.RenderFluidPipe;
import common.cout970.UltraTech.machines.renders.RenderHologram;
import common.cout970.UltraTech.machines.renders.RenderSolarPanel;
import common.cout970.UltraTech.machines.renders.RenderTank;
import common.cout970.UltraTech.machines.renders.RenderTransformer;
import common.cout970.UltraTech.machines.renders.RenderTurbine;
import common.cout970.UltraTech.machines.renders.RenderWindMill;
import common.cout970.UltraTech.machines.renders.items.RenderBatteryItem;
import common.cout970.UltraTech.machines.renders.items.RenderBoilerItem;
import common.cout970.UltraTech.machines.renders.items.RenderEngineItem;
import common.cout970.UltraTech.machines.renders.items.RenderSolarItem;
import common.cout970.UltraTech.machines.renders.items.RenderTankItem;
import common.cout970.UltraTech.machines.renders.items.RenderTurbineItem;
import common.cout970.UltraTech.machines.renders.items.RenderWindMillItem;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.multiblocks.refinery.CoreRefinery;
import common.cout970.UltraTech.multiblocks.refinery.RenderRefinery;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int BlockRenderTipe;
	public static int renderPass;
	public static int engineRenderPass;
	public static int solarRenderPass;
	public static int windmillRenderPass;
	public static int boilerRenderPass;
	public static int tankRenderPass;
	public static int turbineRenderPass;
	public static int batteryRenderPass;

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(SolarPanelEntity.class, new RenderSolarPanel());
		ClientRegistry.bindTileEntitySpecialRenderer(ReactorTankEntity.class, new ReactorTankRender());
		ClientRegistry.bindTileEntitySpecialRenderer(WindMillEntity.class, new RenderWindMill());
		ClientRegistry.bindTileEntitySpecialRenderer(EngineEntity.class, new RenderEngine());
		ClientRegistry.bindTileEntitySpecialRenderer(HologramEmiterEntity.class, new RenderHologram());
		ClientRegistry.bindTileEntitySpecialRenderer(CableEntity.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(EnergyTransformer.class, new RenderTransformer());
		ClientRegistry.bindTileEntitySpecialRenderer(AluminumPipeEntity.class, new RenderFluidPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(CopperPipeEntity.class, new RenderFluidPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(LeadPipeEntity.class, new RenderFluidPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(BoilerEntity.class, new RenderBoiler());
		ClientRegistry.bindTileEntitySpecialRenderer(TankEntity.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(SteamTurbineEntity.class, new RenderTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(CoreRefinery.class, new RenderRefinery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier1.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier2.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier3.class, new RenderBattery());
		setCustomRenderers();
	}
	
	  public static void setCustomRenderers()
      {	
		  //deco blocks
		  BlockRenderTipe = RenderingRegistry.getNextAvailableRenderId();
		  RenderingRegistry.registerBlockHandler(new DecoBlocksRender());
		  //engine
		  engineRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.Engine.blockID, new RenderEngineItem());
		  //solar panel
		  solarRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.SolarPanel.blockID, new RenderSolarItem());
		  //windmill
		  windmillRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.WindMill.blockID, new RenderWindMillItem());
		  //windmill
		  boilerRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.Boiler.blockID, new RenderBoilerItem());
		  //tank
		  tankRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.Tank.blockID, new RenderTankItem());
		  //turbine
		  turbineRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.Turbine.blockID, new RenderTurbineItem());
		//battery
		  batteryRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(BlockManager.Storage.blockID, new RenderBatteryItem());
      }
}

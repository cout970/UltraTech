package common.cout970.UltraTech.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.StorageTier4;
import common.cout970.UltraTech.TileEntities.electric.WindMillEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.SolarPanelEntity_T1;
import common.cout970.UltraTech.TileEntities.electric.tiers.Tesseract_Entity;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.multiblocks.reactor.Reactor_Tank_Entity;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_Core_Entity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.client.renderItems.RenderBatteryItem;
import common.cout970.UltraTech.client.renderItems.RenderBoilerItem;
import common.cout970.UltraTech.client.renderItems.RenderBottle;
import common.cout970.UltraTech.client.renderItems.RenderDynamoItem;
import common.cout970.UltraTech.client.renderItems.RenderEngineItem;
import common.cout970.UltraTech.client.renderItems.RenderPipeItem;
import common.cout970.UltraTech.client.renderItems.RenderPumpItem;
import common.cout970.UltraTech.client.renderItems.RenderSolarItem;
import common.cout970.UltraTech.client.renderItems.RenderSwordItem;
import common.cout970.UltraTech.client.renderItems.RenderTankItem;
import common.cout970.UltraTech.client.renderItems.RenderTesseractItem;
import common.cout970.UltraTech.client.renderItems.RenderTurbineItem;
import common.cout970.UltraTech.client.renderItems.RenderWindMillItem;
import common.cout970.UltraTech.client.renders.DecoBlocksRender;
import common.cout970.UltraTech.client.renders.ReactorTankRender;
import common.cout970.UltraTech.client.renders.RenderBattery;
import common.cout970.UltraTech.client.renders.RenderBoiler;
import common.cout970.UltraTech.client.renders.RenderDynamo;
import common.cout970.UltraTech.client.renders.RenderEngine;
import common.cout970.UltraTech.client.renders.RenderHologram;
import common.cout970.UltraTech.client.renders.RenderPipe;
import common.cout970.UltraTech.client.renders.RenderPump;
import common.cout970.UltraTech.client.renders.RenderRefinery;
import common.cout970.UltraTech.client.renders.RenderSolarPanel;
import common.cout970.UltraTech.client.renders.RenderTank;
import common.cout970.UltraTech.client.renders.RenderTesseract;
import common.cout970.UltraTech.client.renders.RenderTurbine;
import common.cout970.UltraTech.client.renders.RenderWindMill;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.ItemManager;
import common.cout970.UltraTech.microparts.Cable_Entity;
import common.cout970.UltraTech.microparts.RenderCableWithoutMP;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int BlockRenderTipe;
	public static int renderPass;
	public static int engineRenderPass;
	public static int solarRenderPass;
	public static int windmillRenderPass;
	public static int boilerRenderPass;
	public static int turbineRenderPass;
	public static int batteryRenderPass;
	public static int dynamoRenderPass;
	public static int pumpRenderPass;
	public static int pipeRenderPass;
	public static int tesseractRenderPass;

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(SolarPanelEntity_T1.class, new RenderSolarPanel());
		ClientRegistry.bindTileEntitySpecialRenderer(Reactor_Tank_Entity.class, new ReactorTankRender());
		ClientRegistry.bindTileEntitySpecialRenderer(WindMillEntity.class, new RenderWindMill());
		ClientRegistry.bindTileEntitySpecialRenderer(EngineEntity.class, new RenderEngine());
		ClientRegistry.bindTileEntitySpecialRenderer(HologramEmiterEntity.class, new RenderHologram());
		ClientRegistry.bindTileEntitySpecialRenderer(CopperPipeEntity.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(BoilerEntity.class, new RenderBoiler());
		ClientRegistry.bindTileEntitySpecialRenderer(TankEntity.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(SteamTurbineEntity.class, new RenderTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(Refinery_Core_Entity.class, new RenderRefinery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier1.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier2.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier3.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier4.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(DynamoEntity.class, new RenderDynamo());
		ClientRegistry.bindTileEntitySpecialRenderer(PumpEntity.class, new RenderPump());
		ClientRegistry.bindTileEntitySpecialRenderer(Cable_Entity.class, new RenderCableWithoutMP());
		ClientRegistry.bindTileEntitySpecialRenderer(Tesseract_Entity.class, new RenderTesseract());
		
		setCustomRenderers();
	}
	
	  public static void setCustomRenderers()
      {	
		  //deco blocks
		  BlockRenderTipe = RenderingRegistry.getNextAvailableRenderId();
		  RenderingRegistry.registerBlockHandler(new DecoBlocksRender());
		  //engine
		  engineRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Engine), new RenderEngineItem());
		  //solar panel
		  solarRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.SolarPanel_T1), new RenderSolarItem());
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.SolarPanel_T2), new RenderSolarItem());
		  //windmill
		  windmillRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.WindMill), new RenderWindMillItem());
		  //windmill
		  boilerRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Boiler), new RenderBoilerItem());
		  //turbine
		  turbineRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Turbine), new RenderTurbineItem());
		//battery
		  batteryRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Storage), new RenderBatteryItem());
		//dynamo
		  dynamoRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Dynamo), new RenderDynamoItem());
		//pump
		  pumpRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Pump), new RenderPumpItem());
		//pipe
		  pipeRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.CopperPipe), new RenderPipeItem());
		//tesseract
		  tesseractRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Tesseract), new RenderTesseractItem());
		  
		  //Items
		  
		  //sword
		  MinecraftForgeClient.registerItemRenderer(ItemManager.ItemName.get("LaserSword"), new RenderSwordItem());
		  //bottle
		  MinecraftForgeClient.registerItemRenderer(ItemManager.ItemName.get("Bottle"), new RenderBottle());
      }
}

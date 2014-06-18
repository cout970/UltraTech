package common.cout970.UltraTech.proxy;

import api.cout970.UltraTech.microparts.RenderCablePlane;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import common.cout970.UltraTech.TileEntities.electric.BoilerEntity;
import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.TileEntities.electric.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.WindMillEntity;
import common.cout970.UltraTech.TileEntities.fluid.AluminumPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.LeadPipeEntity;
import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import common.cout970.UltraTech.TileEntities.intermod.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorTankEntity;
import common.cout970.UltraTech.machines.renders.DecoBlocksRender;
import common.cout970.UltraTech.machines.renders.ReactorTankRender;
import common.cout970.UltraTech.machines.renders.RenderBattery;
import common.cout970.UltraTech.machines.renders.RenderBoiler;
import common.cout970.UltraTech.machines.renders.RenderDynamo;
import common.cout970.UltraTech.machines.renders.RenderEngine;
import common.cout970.UltraTech.machines.renders.RenderFluidPipe;
import common.cout970.UltraTech.machines.renders.RenderHologram;
import common.cout970.UltraTech.machines.renders.RenderPipe;
import common.cout970.UltraTech.machines.renders.RenderPump;
import common.cout970.UltraTech.machines.renders.RenderSolarPanel;
import common.cout970.UltraTech.machines.renders.RenderTank;
import common.cout970.UltraTech.machines.renders.RenderTransformer;
import common.cout970.UltraTech.machines.renders.RenderTurbine;
import common.cout970.UltraTech.machines.renders.RenderWindMill;
import common.cout970.UltraTech.machines.renders.items.RenderBatteryItem;
import common.cout970.UltraTech.machines.renders.items.RenderBoilerItem;
import common.cout970.UltraTech.machines.renders.items.RenderDynamoItem;
import common.cout970.UltraTech.machines.renders.items.RenderEngineItem;
import common.cout970.UltraTech.machines.renders.items.RenderPumpItem;
import common.cout970.UltraTech.machines.renders.items.RenderSolarItem;
import common.cout970.UltraTech.machines.renders.items.RenderSwordItem;
import common.cout970.UltraTech.machines.renders.items.RenderTankItem;
import common.cout970.UltraTech.machines.renders.items.RenderTurbineItem;
import common.cout970.UltraTech.machines.renders.items.RenderWindMillItem;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.managers.ItemManager;
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
	public static int dynamoRenderPass;
	public static int pumpRenderPass;

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(SolarPanelEntity.class, new RenderSolarPanel());
		ClientRegistry.bindTileEntitySpecialRenderer(ReactorTankEntity.class, new ReactorTankRender());
		ClientRegistry.bindTileEntitySpecialRenderer(WindMillEntity.class, new RenderWindMill());
		ClientRegistry.bindTileEntitySpecialRenderer(EngineEntity.class, new RenderEngine());
		ClientRegistry.bindTileEntitySpecialRenderer(HologramEmiterEntity.class, new RenderHologram());
		ClientRegistry.bindTileEntitySpecialRenderer(EnergyTransformer.class, new RenderTransformer());
		ClientRegistry.bindTileEntitySpecialRenderer(AluminumPipeEntity.class, new RenderFluidPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(CopperPipeEntity.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(LeadPipeEntity.class, new RenderFluidPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(BoilerEntity.class, new RenderBoiler());
		ClientRegistry.bindTileEntitySpecialRenderer(TankEntity.class, new RenderTank());
		ClientRegistry.bindTileEntitySpecialRenderer(SteamTurbineEntity.class, new RenderTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(CoreRefinery.class, new RenderRefinery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier1.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier2.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(StorageTier3.class, new RenderBattery());
		ClientRegistry.bindTileEntitySpecialRenderer(DynamoEntity.class, new RenderDynamo());
		ClientRegistry.bindTileEntitySpecialRenderer(PumpEntity.class, new RenderPump());
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
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.SolarPanel), new RenderSolarItem());
		  //windmill
		  windmillRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.WindMill), new RenderWindMillItem());
		  //windmill
		  boilerRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Boiler), new RenderBoilerItem());
		  //tank
		  tankRenderPass = RenderingRegistry.getNextAvailableRenderId();
		  MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockManager.Tank), new RenderTankItem());
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
		  
		  
		  //Items
		  
		  //sword
		  MinecraftForgeClient.registerItemRenderer(ItemManager.ItemName.get("LasserSword"), new RenderSwordItem());
      }
}

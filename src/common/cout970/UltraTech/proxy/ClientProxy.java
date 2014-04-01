package common.cout970.UltraTech.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import common.cout970.UltraTech.TileEntities.Tier1.AluminumPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.BoilerEntity;
import common.cout970.UltraTech.TileEntities.Tier1.CableEntity;
import common.cout970.UltraTech.TileEntities.Tier1.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.LeadPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier2.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.Tier2.EngineEntity;
import common.cout970.UltraTech.TileEntities.Tier2.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.Tier2.WindMillEntity;
import common.cout970.UltraTech.TileEntities.Tier3.HologramEmiterEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.machines.renders.Block_UT_Render;
import common.cout970.UltraTech.machines.renders.ReactorTankRender;
import common.cout970.UltraTech.machines.renders.RenderBoiler;
import common.cout970.UltraTech.machines.renders.RenderBoilerItem;
import common.cout970.UltraTech.machines.renders.RenderCable;
import common.cout970.UltraTech.machines.renders.RenderEngine;
import common.cout970.UltraTech.machines.renders.RenderEngineItem;
import common.cout970.UltraTech.machines.renders.RenderFluidPipe;
import common.cout970.UltraTech.machines.renders.RenderHologram;
import common.cout970.UltraTech.machines.renders.RenderSolarItem;
import common.cout970.UltraTech.machines.renders.RenderSolarPanel;
import common.cout970.UltraTech.machines.renders.RenderTransformer;
import common.cout970.UltraTech.machines.renders.RenderWindMill;
import common.cout970.UltraTech.machines.renders.RenderWindMillItem;
import common.cout970.UltraTech.managers.BlockManager;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int BlockRenderTipe;
	public static int renderPass;
	public static int engineRenderPass;
	public static int solarRenderPass;
	public static int windmillRenderPass;
	public static int boilerRenderPass;

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

		setCustomRenderers();
	}
	
	  public static void setCustomRenderers()
      {	
		  //deco blocks
		  BlockRenderTipe = RenderingRegistry.getNextAvailableRenderId();
		  RenderingRegistry.registerBlockHandler(new Block_UT_Render());
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
      }
}

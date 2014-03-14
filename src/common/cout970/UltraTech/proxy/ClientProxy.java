package common.cout970.UltraTech.proxy;

import common.cout970.UltraTech.TileEntities.Tier1.CableEntity;
import common.cout970.UltraTech.TileEntities.Tier2.EnergyTransformer;
import common.cout970.UltraTech.TileEntities.Tier2.SolarPanelEntity;
import common.cout970.UltraTech.TileEntities.Tier2.WindMillEntity;
import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.machines.renders.Block_UT_Render;
import common.cout970.UltraTech.machines.renders.ReactorTankRender;
import common.cout970.UltraTech.machines.renders.RenderCable;
import common.cout970.UltraTech.machines.renders.RenderEngine;
import common.cout970.UltraTech.machines.renders.RenderHologram;
import common.cout970.UltraTech.machines.renders.RenderSolarPanel;
import common.cout970.UltraTech.machines.renders.RenderTransformer;
import common.cout970.UltraTech.machines.renders.RenderWindMill;
import common.cout970.UltraTech.machines.tileEntities.EngineEntity;
import common.cout970.UltraTech.machines.tileEntities.HologramEmiterEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	
	public static int BlockRenderTipe;
	public static int renderPass;

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(SolarPanelEntity.class, new RenderSolarPanel());
		ClientRegistry.bindTileEntitySpecialRenderer(ReactorTankEntity.class, new ReactorTankRender());
		ClientRegistry.bindTileEntitySpecialRenderer(WindMillEntity.class, new RenderWindMill());
		ClientRegistry.bindTileEntitySpecialRenderer(EngineEntity.class, new RenderEngine());
		ClientRegistry.bindTileEntitySpecialRenderer(HologramEmiterEntity.class, new RenderHologram());
		ClientRegistry.bindTileEntitySpecialRenderer(CableEntity.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(EnergyTransformer.class, new RenderTransformer());

		setCustomRenderers();
	}
	
	  public static void setCustomRenderers()
      {
              BlockRenderTipe = RenderingRegistry.getNextAvailableRenderId();
              RenderingRegistry.registerBlockHandler(new Block_UT_Render());
      }
}

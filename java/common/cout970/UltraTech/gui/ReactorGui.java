package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.ReactorEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.managers.BlockManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class ReactorGui extends GuiContainer{

	private ReactorEntity entity;
	
	public ReactorGui(Container par1Container,InventoryPlayer ip ,ReactorEntity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//TEMP VAR
		int p = (int) ((entity.heat-25)*58/1135);
		this.drawTexturedModalRect(xStart+14, yStart+12+(58-p), 209, 58-p, 6, p);

		//STEAM VAR
		if(entity.MaxSteam != 0){
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon ic = BlockManager.Steam.getStillIcon();
			int a = entity.steam*40/entity.MaxSteam;
			this.drawTexturedModelRectFromIcon(xStart+77, yStart+50-a, ic, 18, a);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+76, yStart+10, 224, 0, 20, 40);
		}
		
		//WATER VAR
		if(entity.MaxWater != 0){
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon ic = FluidRegistry.WATER.getStillIcon();
			int a = entity.water*40/entity.MaxWater;
			this.drawTexturedModelRectFromIcon(xStart+39, yStart+50-a, ic, 18, a);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+38, yStart+10, 224, 0, 20, 40);
		}
		
		if(!entity.work)fontRendererObj.drawString("off", xStart+130, yStart+74, UT_Utils.RGBtoInt(256, 0, 0));
		//water
		this.drawString(fontRendererObj, "Water", xStart+35, yStart+56, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawString(fontRendererObj, entity.water+"", xStart+35, yStart+67, UT_Utils.RGBtoInt(255, 255, 255));
		//steam
		this.drawString(fontRendererObj, "Steam", xStart+75, yStart+56, UT_Utils.RGBtoInt(255, 255, 255));
		String s = ((entity.steam <= 0) ? 0+"" : entity.steam+"");
		this.drawString(fontRendererObj, s, xStart+75, yStart+67, UT_Utils.RGBtoInt(255, 255, 255));

		if(UT_Utils.isIn(i, j, xStart+14, yStart+15, 25, 50)){
			List<String> energy = new ArrayList<String>();
			energy.add("Heat: "+((int)entity.heat)+"C");
			this.drawHoveringText(energy, i, j, fontRendererObj);
			RenderHelper.enableGUIStandardItemLighting();
		}
		
		this.drawCenteredString(fontRendererObj, "Reactor", xStart+138, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));
	}

}

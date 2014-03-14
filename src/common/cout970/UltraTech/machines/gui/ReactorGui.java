package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.ReactorEntity;
import common.cout970.UltraTech.lib.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class ReactorGui extends GuiContainer{

	private ReactorEntity tileEntity;
	
	public ReactorGui(Container par1Container,InventoryPlayer ip ,ReactorEntity entity) {
		super(par1Container);
		tileEntity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//TEMP VAR
		int p = (int) ((tileEntity.heat-25)*58/1135);
		this.drawTexturedModalRect(xStart+14, yStart+12+(58-p), 209, 58-p, 6, p);

		//STEAM VAR
		if(tileEntity.MaxSteam != 0){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/steam.png"));
			int s = tileEntity.steam*54/tileEntity.MaxSteam;
			this.drawTexturedModalRect(xStart+39, yStart+15+(54-s), 0, 0, 25, s);
		}
		
		//WATER VAR
		if(tileEntity.MaxWater != 0){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/water.png"));
			int w = tileEntity.water*54/tileEntity.MaxWater;
			this.drawTexturedModalRect(xStart+77, yStart+15+(54-w), 0, 0, 25, w);
		}
		
		if(!tileEntity.work)fontRenderer.drawString("off", xStart+45, yStart+4, RenderUtil.RGBtoInt(256, 0, 0));
		
		if(i > xStart+77 && i < xStart+77+25 && j > yStart+15 && j < yStart+15+54){
				this.drawString(fontRenderer, "Water", i, j-19, RenderUtil.RGBtoInt(255, 255, 255));
				this.drawString(fontRenderer, tileEntity.water+"/"+tileEntity.MaxWater, i, j-19+9, RenderUtil.RGBtoInt(255, 255, 255));
		}
		if(i > xStart+39 && i < xStart+39+25 && j > yStart+15 && j < yStart+15+54){
			this.drawString(fontRenderer, "Steam", i, j-19, RenderUtil.RGBtoInt(255, 255, 255));
			this.drawString(fontRenderer, ((tileEntity.steam <= 0) ? 0+"/"+tileEntity.MaxSteam : tileEntity.steam+"/"+tileEntity.MaxSteam), i, j-19+9, RenderUtil.RGBtoInt(255, 255, 255));
		}
		if(i > xStart+13 && i < xStart+14+13 && j > yStart+12 && j < yStart+12+58){
			this.drawString(fontRenderer, "Heat", i, j-19, RenderUtil.RGBtoInt(255, 255, 255));
			this.drawString(fontRenderer, ((int)tileEntity.heat)+"ºC", i, j-19+9, RenderUtil.RGBtoInt(255, 255, 255));
		}
	}

}

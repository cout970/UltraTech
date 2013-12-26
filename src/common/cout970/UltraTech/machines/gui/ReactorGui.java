package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.tileEntities.ReactorEntity;

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
		int p = (int) (tileEntity.heat*58/1000);
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
	}

}

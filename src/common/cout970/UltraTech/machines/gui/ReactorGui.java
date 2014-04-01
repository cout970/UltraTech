package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.ReactorEntity;
import common.cout970.UltraTech.lib.UT_Utils;
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
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/steam.png"));
			int a = tileEntity.steam*40/tileEntity.MaxSteam;
			this.drawTexturedModalRect(xStart+77, yStart+50-a, 0, 0, 18, a);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+76, yStart+10, 224, 0, 20, 40);
		}
		
		//WATER VAR
		if(tileEntity.MaxWater != 0){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/water.png"));
			int a = tileEntity.water*40/tileEntity.MaxWater;
			this.drawTexturedModalRect(xStart+39, yStart+50-a, 0, 0, 18, a);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+38, yStart+10, 224, 0, 20, 40);
		}
		
		if(!tileEntity.work)fontRenderer.drawString("off", xStart+130, yStart+74, UT_Utils.RGBtoInt(256, 0, 0));
		//water
		this.drawString(fontRenderer, "Water", xStart+35, yStart+56, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawString(fontRenderer, tileEntity.water+"", xStart+35, yStart+67, UT_Utils.RGBtoInt(255, 255, 255));
		//steam
		this.drawString(fontRenderer, "Steam", xStart+75, yStart+56, UT_Utils.RGBtoInt(255, 255, 255));
		String s = ((tileEntity.steam <= 0) ? 0+"" : tileEntity.steam+"");
		this.drawString(fontRenderer, s, xStart+75, yStart+67, UT_Utils.RGBtoInt(255, 255, 255));

		if(i > xStart+13 && i < xStart+14+13 && j > yStart+12 && j < yStart+12+58){
			this.drawString(fontRenderer, "Heat", i, j-19, UT_Utils.RGBtoInt(255, 255, 255));
			this.drawString(fontRenderer, ((int)tileEntity.heat)+"C", i, j-19+9, UT_Utils.RGBtoInt(255, 255, 255));
		}
	}

}

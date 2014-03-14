package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.TesseractEntity;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.RenderUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TesseractGui extends GuiContainer{

	private TesseractEntity entity;

	public TesseractGui(Container par1Container,InventoryPlayer ip ,Machine entity) {
		super(par1Container);
		this.entity = (TesseractEntity) entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/tesseract.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		int x,y;
		x = xStart+138;
		y = yStart+29;
		int v = 0;
		drawTexturedModalRect(x, y, 176, v, 19, 19);
		this.entity.To = "X-2545-Y-5566";
		drawCenteredString(fontRenderer, ""+entity.frequency, xStart+65, yStart+24, RenderUtil.RGBtoInt(255, 255, 255));
		drawCenteredString(fontRenderer, ""+entity.To, xStart+65, yStart+47, RenderUtil.RGBtoInt(255, 255, 255));
	}

    protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		int x,x1,y,y1;
		x = xStart+138;
		y = yStart+29;
		x1 = x + 19;
		y1 = y + 19;
		if(par1 > x && par1 < x1){
			if(par2 > y && par2 < y1){
				System.out.println("Clicked "+par3);
			}
		}
	}
}

package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.wiki.IPage;
import common.cout970.UltraTech.wiki.MainPage;
import common.cout970.UltraTech.wiki.TabletButtom;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TabletGui extends GuiContainer{

	public ResourceLocation texture = new ResourceLocation("ultratech:textures/gui/tablet.png");
	public IPage main = new MainPage(this);
	public IPage curr = null;
	
	public TabletGui(Container par1Container) {
		super(par1Container);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		this.mc.renderEngine.bindTexture(texture);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		if(curr == null){
			main.renderPage(this.mc.renderEngine,xStart,yStart);
		}else{
			curr.renderPage(this.mc.renderEngine,xStart,yStart);
		}
	}

	protected void mouseClicked(int x, int y, int b)
	{
		super.mouseClicked(x, y, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		if(curr == null){
			main.mouseClick(x-xStart, y-yStart);
		}else{
			curr.mouseClick(x-xStart, y-yStart);
		}
	}
}

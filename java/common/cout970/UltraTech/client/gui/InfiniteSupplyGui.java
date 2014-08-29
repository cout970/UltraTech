package common.cout970.UltraTech.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.logistics.InfiniteSupplyEntity;

public class InfiniteSupplyGui extends GuiContainer{

	public InfiniteSupplyEntity entity;

	public InfiniteSupplyGui(Container par1Container, InfiniteSupplyEntity t) {
		super(par1Container);
		entity = t;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float frame,int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/infinity.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
	}
}
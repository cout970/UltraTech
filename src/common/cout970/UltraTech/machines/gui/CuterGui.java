package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.CutterEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CuterGui extends GuiContainer{

	public CutterEntity entity;
	
	public CuterGui(Container par1Container, InventoryPlayer inventory, CutterEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/cuter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);


		//progres bar
		int i1 = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 83, yStart + 31, 176, 14, i1 +1, 16);


		//energy bar
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) entity.getEnergy()*50/entity.maxEnergy();
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}
}

package common.cout970.UltraTech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.util.UT_Utils;

public class EngineGui extends GuiContainer{

	public EngineEntity entity;
	
	public EngineGui(Container par1Container,InventoryPlayer i,EngineEntity e) {
		super(par1Container);
		entity = e;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/engine.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//energy MJ
		GL11.glColor4f(1.0F, 0.0F, 0.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/mj.png"));
		int m = (int) (entity.power.getEnergyStored()*50/entity.power.getMaxEnergyStored());
		this.drawTexturedModalRect(xStart+135, yStart+15+(50-m), 0, 0, 25, m);
		
		//energy
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getCharge()*50/entity.getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		//info
		this.drawCenteredString(fontRendererObj, "Energy "+(int)entity.getCharge(), xStart+85, yStart+25, 16777215);
		this.drawCenteredString(fontRendererObj, "BC MJ "+(int)entity.power.getEnergyStored(), xStart+87, yStart+37, 16777215);

		
		this.drawCenteredString(fontRendererObj, "FT Engine", xStart+85, yStart+6, UT_Utils.RGBtoInt(255, 255, 255));

	}

}

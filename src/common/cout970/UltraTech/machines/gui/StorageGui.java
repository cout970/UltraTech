package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.energy.api.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class StorageGui extends GuiContainer{

	private Machine entity;

	public StorageGui(Container par1Container,InventoryPlayer ip ,Machine entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/ids.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		if(entity != null){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
			int p = (int) (((double)entity.getEnergy())*50/(entity.maxEnergy()));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {

		String e = entity.getEnergy()/10+"";
		this.fontRenderer.drawString(e, this.xSize - 120+(-e.length()*5+35), 36, 4210752);
		this.fontRenderer.drawString(" / ", this.xSize - 80, 36, 4210752);
		String m = entity.maxEnergy()/10+"";
		this.fontRenderer.drawString(m, this.xSize - 67+(-m.length()*5+35), 36, 4210752);

		String f = "Interdimensional Storage";
		this.fontRenderer.drawString(f, this.xSize - 130, 8, 4210752);
	}

}

package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.tileEntities.UTfurnaceEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class UTfurnaceGui extends GuiContainer{

	private UTfurnaceEntity entity;
	
	public UTfurnaceGui(Container par1Container,InventoryPlayer ip ,UTfurnaceEntity entity) {
		super(par1Container);
		this.entity = entity;
		}


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/utfurnace.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 85, yStart + 31, 176, 14, i1 + 1, 16);

		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) entity.getEnergy()*50/entity.EnergyMax;
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);

	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.isInvNameLocalized() ? this.entity.getInvName() : "Ultra Tech Furnace";
        this.fontRenderer.drawString(s, this.xSize - 130, 6, 4210752);
	}
}

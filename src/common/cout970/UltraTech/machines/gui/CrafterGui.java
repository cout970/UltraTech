package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.tileEntities.CrafterEntity;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CrafterGui extends GuiContainer{

	public CrafterEntity entity;
	
	public CrafterGui(Container par1Container, InventoryPlayer inventory, CrafterEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/crafter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		//draw needs not haves
		GL11.glEnable(GL11.GL_BLEND);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/not.png"));
		//craft grid
		if(entity.found.containsKey(0) && entity.found.get(0))this.drawTexturedModalRect(xStart+13, yStart+16, 1, 1, 16, 16);
		if(entity.found.containsKey(1) && entity.found.get(1))this.drawTexturedModalRect(xStart+31, yStart+16, 1, 1, 16, 16);
		if(entity.found.containsKey(2) && entity.found.get(2))this.drawTexturedModalRect(xStart+49, yStart+16, 1, 1, 16, 16);
		
		if(entity.found.containsKey(3) && entity.found.get(3))this.drawTexturedModalRect(xStart+13, yStart+34, 1, 1, 16, 16);
		if(entity.found.containsKey(4) && entity.found.get(4))this.drawTexturedModalRect(xStart+31, yStart+34, 1, 1, 16, 16);
		if(entity.found.containsKey(5) && entity.found.get(5))this.drawTexturedModalRect(xStart+49, yStart+34, 1, 1, 16, 16);
		
		if(entity.found.containsKey(6) && entity.found.get(6))this.drawTexturedModalRect(xStart+13, yStart+52, 1, 1, 16, 16);
		if(entity.found.containsKey(7) && entity.found.get(7))this.drawTexturedModalRect(xStart+31, yStart+52, 1, 1, 16, 16);
		if(entity.found.containsKey(8) && entity.found.get(8))this.drawTexturedModalRect(xStart+49, yStart+52, 1, 1, 16, 16);
		//craft result
		if(entity.getStackInSlot(-1) != null && !entity.allFound(-1))
			this.drawTexturedModalRect(xStart+71, yStart+16, 1, 1, 16, 16);
		if(entity.getStackInSlot(-2) != null)
			this.drawTexturedModalRect(xStart+71, yStart+34, 1, 1, 16, 16);
		if(entity.getStackInSlot(-3) != null)
			this.drawTexturedModalRect(xStart+71, yStart+52, 1, 1, 16, 16);
		if(entity.getStackInSlot(-4) != null)
			this.drawTexturedModalRect(xStart+89, yStart+16, 1, 1, 16, 16);
		if(entity.getStackInSlot(-5) != null)
			this.drawTexturedModalRect(xStart+89, yStart+34, 1, 1, 16, 16);
		if(entity.getStackInSlot(-6) != null)
			this.drawTexturedModalRect(xStart+89, yStart+52, 1, 1, 16, 16);

		GL11.glDisable(GL11.GL_BLEND);
	}

}

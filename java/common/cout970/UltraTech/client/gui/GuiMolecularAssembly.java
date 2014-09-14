package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.TileEntityMolecularAssembly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMolecularAssembly extends GuiContainer{

	public TileEntityMolecularAssembly tile;
	
	public GuiMolecularAssembly(Container par1Container,InventoryPlayer ip ,TileEntityMolecularAssembly entity) {
		super(par1Container);
		tile = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/ma.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
		//progres bar
		int i1 = (int) this.tile.progres*24/1000;
		this.drawTexturedModalRect(xStart + 118, yStart + 33, 176, 14, i1, 16);

		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (tile.getCharge()*50/tile.getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		this.drawCenteredString(fontRendererObj, "Molecular Assembly", xStart+65, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+UT_Utils.removeDecimals(tile.getCharge())+IPower.POWER_NAME);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

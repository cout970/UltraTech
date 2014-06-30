package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalVaporDesintegrationT1_Entity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.misc.ISpeeded;


public class CVD_Gui extends GuiContainer{

	private ChemicalVaporDesintegrationT1_Entity entity;
	
	public CVD_Gui(Container par1Container,InventoryPlayer ip ,ChemicalVaporDesintegrationT1_Entity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/cvd.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres
		int i1 = (int) entity.Progres*24/entity.maxProgres;
		this.drawTexturedModalRect(xStart + 113, yStart + 32, 176, 14, i1 + 1, 16);

		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	
		if(entity instanceof ISpeeded)
			this.drawCenteredString(fontRendererObj, "Speed upgrades: "+((ISpeeded) entity).getUpgrades()+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize - 100, 6, 4210752);
       
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+(entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

	
}

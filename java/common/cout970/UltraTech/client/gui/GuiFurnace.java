package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.power.interfaces.ISpeeded;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityFurnaceT1;

public class GuiFurnace extends GuiContainer{

	private TileEntityFurnaceT1 entity;
	
	public GuiFurnace(Container par1Container,InventoryPlayer ip ,TileEntityFurnaceT1 entity) {
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
		int i1;
		if(entity.maxProgres > 0)i1 = (int) entity.Progres*24/entity.maxProgres;
		else i1 = (int) (entity.Progres*24);
		this.drawTexturedModalRect(xStart + 85, yStart + 31, 176, 14, i1 + 1, 16);

		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getPower().getCharge()*50/entity.getPower().getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		if(entity instanceof ISpeeded)
			this.drawCenteredString(fontRendererObj, "Speed upgrades: "+((ISpeeded) entity).getUpgrades()+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.getInventoryName();
        this.drawCenteredString(fontRendererObj, s, this.xSize - 110, 6, UT_Utils.RGBtoInt(255, 255, 255));
        
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+UT_Utils.removeDecimals(entity.getPower().getCharge())+IPower.POWER_NAME);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
}

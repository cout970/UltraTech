package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.power.interfaces.ISpeeded;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityLaminatorT1;

public class GuiLaminator extends GuiContainer{

	public TileEntityLaminatorT1 entity;
	
	public GuiLaminator(Container par1Container,TileEntityLaminatorT1 entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/laminator.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		//progres
		int i1;
		if(entity.maxProgres > 0)i1 = (int) entity.Progres*24/entity.maxProgres;
		else i1 = (int) (entity.Progres*24);
		this.drawTexturedModalRect(xStart + 113, yStart + 32, 176, 14, i1 + 1, 16);
		int x = 0;
		if(entity.maxProgres > 0)x = (int) entity.Progres*7/entity.maxProgres;
		else x = (int) (entity.Progres*7);
		drawTexturedModalRect(xStart + 78, yStart + 12+x, 222, 10, 32, 20);
		drawTexturedModalRect(xStart + 78, yStart + 45-x, 222, 0, 32, 20);
	
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getCharge()*50/entity.getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	
		if(entity instanceof ISpeeded)
			this.drawCenteredString(fontRendererObj, "Speed upgrades: "+((ISpeeded) entity).getUpgrades()+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.getInventoryName();
		this.drawCenteredString(fontRendererObj, s, this.xSize - 45, 6, UT_Utils.RGBtoInt(255, 255, 255));
       
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+UT_Utils.removeDecimals(entity.getCharge())+IPower.POWER_NAME);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

package common.cout970.UltraTech.machines.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.PurifierEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class PurifierGui extends GuiContainer{

	private PurifierEntity entity;
	
	public PurifierGui(Container par1Container, InventoryPlayer inventory, PurifierEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/purifier.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 85, yStart + 31, 176, 14, i1 + 1, 16);

		//energy bar
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		this.drawCenteredString(fontRenderer, "Purifier", xStart+95, yStart+6, UT_Utils.RGBtoInt(255, 255, 255));
		
		this.drawCenteredString(fontRenderer, "Speed upgrades: "+entity.speedUpgrades+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getEnergy())+"FT");
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRenderer);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
}

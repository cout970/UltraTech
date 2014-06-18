package common.cout970.UltraTech.gui;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.GeneratorEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GeneratorGui extends GuiContainer{

	private GeneratorEntity entity;
	
	public GeneratorGui(Container par1Container,InventoryPlayer ip,GeneratorEntity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/generator.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = 0;
		if(entity.maxProgres != 0)i1 = (int) entity.Progres*13/entity.maxProgres;
		if(entity.Progres > 0)i1 += 1;
		this.drawTexturedModalRect(xStart + 63, yStart + 59 - i1, 176, 12 - i1, 14, i1);

		//temp var
		int h = (int) ((entity.heat-25)*58/(entity.maxHeat-25));
		this.drawTexturedModalRect(xStart+152, yStart+14+(58-h), 191, 58-h, 6, h);

		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
		String s = this.entity.getInventoryName();
        this.fontRendererObj.drawString(s, 65, 6, 4210752);
        String h = ((int)this.entity.heat)+"C";
        this.fontRendererObj.drawString(h, 137-fontRendererObj.getStringWidth(h), 29, 4210752);
        int e = (int)(entity.heat*10/entity.maxHeat);
        if(entity.Progres <= 0)e = 0;
        String p = e+EnergyCosts.E+"/t";
        this.fontRendererObj.drawString(p, 115-fontRendererObj.getStringWidth(h)/2, 55, 4210752);
        
      //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

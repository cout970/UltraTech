package common.cout970.UltraTech.client.gui;


import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.IPower;
import ultratech.api.power.ISpeeded;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.tiers.CoalGeneratorEntityT1_Entity;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CoalGenerator_Gui extends GuiContainer{

	private CoalGeneratorEntityT1_Entity entity;
	
	public CoalGenerator_Gui(Container par1Container,InventoryPlayer ip,CoalGeneratorEntityT1_Entity entity) {
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
		int h = (int) ((entity.heat-25)*58/(1000-25));
		this.drawTexturedModalRect(xStart+152, yStart+14+(58-h), 191, 58-h, 6, h);

		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getCharge()*50/entity.getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		if(entity instanceof ISpeeded)
			this.drawCenteredString(fontRendererObj, "Speed upgrades: "+((ISpeeded) entity).getUpgrades()+"/4", xStart+90, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
		String s = this.entity.getInventoryName();
        this.drawCenteredString(fontRendererObj, s, 90, 6, UT_Utils.RGBtoInt(255, 255, 255));
        String h = ((int)this.entity.heat)+"C";
        this.fontRendererObj.drawString(h, 137-fontRendererObj.getStringWidth(h), 29, 4210752);
        double e = entity.production();
        double deci = (int) (entity.production()*100);
        e = deci/100;
        if(entity.Progres <= 0)e = 0;
        String p = e+IPower.POWER_NAME+"/t";
        this.fontRendererObj.drawString(p, 115-fontRendererObj.getStringWidth(h), 55, 4210752);
        
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

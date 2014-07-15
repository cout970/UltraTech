package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.IPower;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.util.ConfigurableMachineWithInventory;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.power.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class Heater_Gui extends MachineGuiBase{

	public Heater_Gui(Container par1Container, TileEntity t) {
		super(par1Container, t);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/heater.png"));
		xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//heat			
		Heater_Entity e = (Heater_Entity) entity;
		int h = (int) ((e.Heat*58)/1000f);
		this.drawTexturedModalRect(xStart+14, yStart+14+(58-h), 176, 58-h, 5, h);

		int i1 = 0;
		if(e.maxProgres != 0)i1 = (int) e.Progres*13/e.maxProgres;
		if(e.Progres > 0)i1 += 1;
		this.drawTexturedModalRect(xStart + 81, yStart + 72 - i1, 197, 13 - i1, 14, i1);

		
		//energy bar
		Machine tile = (Machine) entity;
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (tile.getCharge()*50/tile.getCapacity());
		this.drawTexturedModalRect(xStart+137, yStart+15+(50-p), 0, 0, 25, p);

		
		
		String s = ((int)e.Heat)+" C";
		this.fontRendererObj.drawString(s, xStart+68-fontRendererObj.getStringWidth(s), yStart+16, 4210752);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		//name
		this.drawCenteredString(fontRendererObj, "Heater", 99, 6, UT_Utils.RGBtoInt(255, 255, 255));

        if(UT_Utils.isIn(x, y, xStart+137, yStart+15, 25, 50)){
        	drawText(x,y,"Energy: "+(((Machine) entity).getCharge())+IPower.POWER_NAME);
        }
	}
}

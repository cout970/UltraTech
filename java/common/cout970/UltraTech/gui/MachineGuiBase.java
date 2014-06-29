package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import common.cout970.UltraTech.TileEntities.electric.tiers.ConfigurableMachine;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public abstract class MachineGuiBase extends GuiContainer{

	public ConfigurableMachine entity;
	public int xStart;
	public int yStart;

	public MachineGuiBase(Container par1Container, TileEntity te) {
		super(par1Container);
		entity = (ConfigurableMachine) te;
		xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
	}
	
	public void drawEnergyVar(double e, double max){
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (e*50/max);
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}
	
	public void drawControl(double e, double max){
		
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+(entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

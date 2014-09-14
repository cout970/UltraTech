package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public abstract class GuiMachineBase extends GuiContainer{

	public TileEntity entity;
	public int xStart;
	public int yStart;

	public GuiMachineBase(Container par1Container, TileEntity te) {
		super(par1Container);
		entity = (TileEntity) te;
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

	protected void drawText(int x, int y,String s) {
		List<String> energy = new ArrayList<String>();
		energy.add(s);
		this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
		RenderHelper.enableGUIStandardItemLighting();
	}


	public boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){if(my > y && my < y+h){
		return true;}}
		return false;
	}
}

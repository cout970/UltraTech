package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.TileEntityChargeStation;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.network.messages.MessageMachineMode;
import common.cout970.UltraTech.util.power.Machine;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiChargeStation extends GuiMachineBase{

	private TileEntityChargeStation tile;

	public GuiChargeStation(Container par1Container, InventoryPlayer inventory,TileEntityChargeStation tile) {
		super(par1Container, tile);
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/chargestation.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		int v = 0;
		Machine t = (Machine) tile;
		switch(t.cond.configIO){
		case Nothing: 	v=2; break;
		case Output:	v=1; break;
		case Storage:	v=0; break;
		default: v=0;}
		drawTexturedModalRect(xStart+146, yStart+12, 176, 19*v, 19, 19);
		if(t != null){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
			int p = (int) (((double)t.getCharge())*50/(t.getCapacity()));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		}
		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) ((((float)tile.getCharge())*50/tile.getCapacity()));
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);

		//NAME
		this.drawCenteredString(fontRendererObj, "Charge Station", xStart+100, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));
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
	
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		Machine t = (Machine) tile;
		xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		if(isIn(par1, par2, xStart+146, yStart+12, 19, 19)){
			int m = t.cond.configIO.ordinal();//023
			if(m == 0)m = 2;
			else if(m == 2)m = 3;
			else if(m == 3)m = 0;
			Net_Utils.INSTANCE.sendToServer(new MessageMachineMode((SyncTile) tile, m));
		}
	}

}

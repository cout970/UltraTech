package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.packets.PacketClimateStation;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class ClimateStationGui extends GuiContainer{

	public ClimateEntity entity;
	
	public ClimateStationGui(Container par1Container,TileEntity tile) {
		super(par1Container);
		entity = (ClimateEntity) tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/climate.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);	
		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int m = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+135, yStart+15+(50-m), 0, 0, 25, m);
		
		 //NAME
		this.drawCenteredString(fontRendererObj, "Climate Station", xStart+115, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));

	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+135, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
	
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		if(isIn(par1, par2, xStart+15, yStart+11, 25, 25)){
			Net_Utils.PipeLine.sendToServer(new PacketClimateStation(entity, 0));
			entity.setClimate(0);
		}
		if(isIn(par1, par2, xStart+15, yStart+45, 25, 25)){
			Net_Utils.PipeLine.sendToServer(new PacketClimateStation(entity, 1));
			entity.setClimate(1);
		}
		if(isIn(par1, par2, xStart+48, yStart+11, 25, 25)){
			Net_Utils.PipeLine.sendToServer(new PacketClimateStation(entity, 2));
			entity.setClimate(2);
		}
	}
    
    public boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
			}
		}
		return false;
	}
}

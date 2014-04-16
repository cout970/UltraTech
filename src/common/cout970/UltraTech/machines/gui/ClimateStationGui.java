package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.ClimateEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.gui.inventory.GuiContainer;
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
	}
	
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		if(isIn(par1, par2, xStart+15, yStart+11, 25, 25)){
			PacketDispatcher.sendPacketToServer(UT_Utils.getPacketToServer(entity,6,0));
			entity.setClimate(0);
		}
		if(isIn(par1, par2, xStart+15, yStart+45, 25, 25)){
			PacketDispatcher.sendPacketToServer(UT_Utils.getPacketToServer(entity,6,1));
			entity.setClimate(1);
		}
		if(isIn(par1, par2, xStart+48, yStart+11, 25, 25)){
			PacketDispatcher.sendPacketToServer(UT_Utils.getPacketToServer(entity,6,2));
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

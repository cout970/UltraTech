package common.cout970.UltraTech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;

import common.cout970.UltraTech.TileEntities.utility.TileEntityHologramEmiter;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageHologram;

public class GuiHologramEmiter extends GuiContainer{

	public TileEntityHologramEmiter entity;
	
	public GuiHologramEmiter(Container par1Container,TileEntity tile) {
		super(par1Container);
		entity = (TileEntityHologramEmiter) tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/hologram.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		this.drawCenteredString(fontRendererObj, "Hologram Emiter", xStart+85, yStart+3, UT_Utils.RGBtoInt(255, 255, 255));
		String s = "X: "+entity.mX+" Y: "+entity.mY+" Z: "+entity.mZ+" "+entity.facing;
		this.drawCenteredString(fontRendererObj, s, xStart+85, yStart+18, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		//first colum
		
		//y +-
		if(isIn(mx, my, xStart+59, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 1, entity.mY + 1/16f));
		}
		if(isIn(mx, my, xStart+59, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 1, entity.mY - 1/16f));
		}
		
		//x +-
		if(isIn(mx, my, xStart+77, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 0, entity.mX + 1/16f));
		}
		if(isIn(mx, my, xStart+77, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 0, entity.mX - 1/16f));
		}

		//z +-
		if(isIn(mx, my, xStart+95, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 2, entity.mZ + 1/16f));
		}
		if(isIn(mx, my, xStart+95, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 2, entity.mZ - 1/16f));
		}
		
		//facing
		if(isIn(mx, my, xStart+113, yStart+33, 16, 16)){
			int dir = entity.facing.ordinal()+1;
			if(dir >= 6)dir = 0;
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 3, dir));
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

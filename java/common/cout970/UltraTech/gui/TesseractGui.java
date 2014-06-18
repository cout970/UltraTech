package common.cout970.UltraTech.gui;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.Wpower.Machine;
import common.cout970.UltraTech.TileEntities.electric.TesseractEntity;
import common.cout970.UltraTech.TileEntities.electric.TesseractEntity.T_Mode;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class TesseractGui extends GuiContainer{

	private TesseractEntity entity;

	public TesseractGui(Container par1Container,InventoryPlayer ip ,Machine entity) {
		super(par1Container);
		this.entity = (TesseractEntity) entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/tesseract.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		if(entity.writing){
			drawTexturedModalRect(xStart+18, yStart+20, 0, ySize, 101, 15);
		}
		int x,y;
		x = xStart+138;
		y = yStart+29;
		int v = 0;
		v = entity.mode.ordinal()*19;
		drawTexturedModalRect(x, y, 176, v, 19, 19);
		drawCenteredString(fontRendererObj, "Freq: "+entity.frequency, xStart+65, yStart+24, UT_Utils.RGBtoInt(255, 255, 255));
		drawCenteredString(fontRendererObj, ""+((int)entity.getEnergy())+EnergyCosts.E, xStart+65, yStart+47, UT_Utils.RGBtoInt(255, 255, 255));
		
		this.drawCenteredString(fontRendererObj, "Tesseract", xStart+85, yStart+4, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	protected void keyTyped(char letra, int num)
	{
		if(entity.writing){
			if(num == 1 || num == 28){
				super.keyTyped(letra, num);
				entity.writing = false;
				return;
			}
			if(Character.isDigit(letra)){
				if(entity.frequency/100000 < 1){
				entity.frequency *= 10;
				entity.frequency += Integer.valueOf(String.valueOf(letra));
				}
			}else if(num == 14){
				entity.frequency -= entity.frequency%10;
				entity.frequency /=10;
			}
			entity.setFrequency(entity.frequency);
		}else
		super.keyTyped(letra, num);
	}

    protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		if(isIn(par1, par2, xStart+138, yStart+29, 19, 19)){
			entity.mode = T_Mode.getMode(T_Mode.change(entity.mode.ordinal()));
			entity.changeMode(T_Mode.getMode(T_Mode.change(entity.mode.ordinal())));
		}
		if(isIn(par1, par2, xStart+18, yStart+20, 100, 14)){
			entity.writing = true;
		}else{
			entity.writing = false;
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

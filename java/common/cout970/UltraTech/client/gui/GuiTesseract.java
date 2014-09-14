package common.cout970.UltraTech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;

public class GuiTesseract extends GuiContainer{

	private TileEntityTesseract entity;

	public GuiTesseract(Container par1Container,InventoryPlayer ip ,TileEntityTesseract tileEntity) {
		super(par1Container);
		this.entity =  tileEntity;
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

		drawCenteredString(fontRendererObj, "Freq: "+entity.frequency, xStart+65, yStart+24, UT_Utils.RGBtoInt(255, 255, 255));
		
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
//			entity.mode = T_Mode.getMode(T_Mode.change(entity.mode.ordinal()));
//			entity.changeMode(T_Mode.getMode(T_Mode.change(entity.mode.ordinal())));
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

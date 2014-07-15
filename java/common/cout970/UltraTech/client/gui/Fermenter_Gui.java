package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.IPower;
import common.cout970.UltraTech.TileEntities.electric.FermenterEntity;
import common.cout970.UltraTech.util.UT_Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class Fermenter_Gui extends GuiContainer{

	public FermenterEntity entity;
	
	public Fermenter_Gui(Container par1Container, FermenterEntity tile) {
		super(par1Container);
		entity = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fermenter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres
		int i1 = 0;
		if(entity.maxProgres != 0)i1 = (int) entity.progres*24/entity.maxProgres;
		this.drawTexturedModalRect(xStart + 107+25-i1, yStart + 33, 176+25-i1, 14, i1+1, 16);

		//water
		FluidStack water = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean w = water == null;
		if(!w){
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon ic = water.getFluid().getStillIcon();
			int a = water.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
			this.drawTexturedModelRectFromIcon(xStart+46, yStart+60-a, ic, 18, a);
		}
		//juice
		FluidStack juice = entity.getTankInfo(ForgeDirection.UP)[1].fluid;
		boolean ju = juice == null;
		if(!ju){
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon ic = juice.getFluid().getStillIcon();			int a = juice.amount*40/entity.getTankInfo(ForgeDirection.UP)[1].capacity;
			this.drawTexturedModelRectFromIcon(xStart+139, yStart+61-a, ic, 18, a);
		}
		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fermenter.png"));
		if(!w)this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		if(!ju)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getCharge()*50/entity.getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
		this.drawCenteredString(fontRendererObj, "Fermenter", xStart+95, yStart+6, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getCharge())+IPower.POWER_NAME);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        }
        if(UT_Utils.isIn(x, y, xStart+45, yStart+20, 20, 40)){
        	List<String> fluid1 = new ArrayList<String>();
        	if(entity.water.getFluidAmount() == 0)fluid1.add("Empty");
        	else {
        		fluid1.add("Fluid: "+entity.water.getFluid().getFluid().getName());
        		fluid1.add(""+entity.water.getFluidAmount()+"/"+entity.water.getCapacity());
        	}
        	this.drawHoveringText(fluid1, x-xStart, y-yStart, fontRendererObj);
        }
        if(UT_Utils.isIn(x, y, xStart+138, yStart+21, 20, 40)){
        	List<String> fluid1 = new ArrayList<String>();
        	if(entity.juice.getFluidAmount() == 0)fluid1.add("Empty");
        	else {
        		fluid1.add("Fluid: "+entity.juice.getFluid().getFluid().getName());
        		fluid1.add(""+entity.juice.getFluidAmount()+"/"+entity.juice.getCapacity());
        	}
        	this.drawHoveringText(fluid1, x-xStart, y-yStart, fontRendererObj);
        }
    	RenderHelper.enableGUIStandardItemLighting();
	}

}

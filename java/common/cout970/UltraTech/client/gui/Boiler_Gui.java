package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.util.UT_Utils;

public class Boiler_Gui extends GuiContainer{

	public BoilerEntity entity;
	
	public Boiler_Gui(Container par1Container, BoilerEntity t) {
		super(par1Container);
		entity = t;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/boiler.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
		//fluid 1 gas
		FluidStack output = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean l = output == null;
		
		if(!l){
			IIcon li = output.getFluid().getStillIcon();
			if(li != null){
				bindTexture(output);
				int a = output.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
				drawTexturedModelRectFromIcon(xStart+139, yStart+61-a, li, 18, a);		
			}
		}
		//fluid 2 liquid
		FluidStack input = entity.getTankInfo(ForgeDirection.UP)[1].fluid;
		boolean g = input == null;
		if(!g){
			IIcon li = input.getFluid().getStillIcon();
			bindTexture(input);
			int a = input.amount*40/entity.getTankInfo(ForgeDirection.UP)[1].capacity;
			drawTexturedModelRectFromIcon(xStart+46, yStart+60-a, li, 18, a);
		} 
		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/boiler.png"));
		if(!g)this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		if(!l)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		//heat			
		int h = (int) ((entity.heat*58)/1000f);
		this.drawTexturedModalRect(xStart+74, yStart+14+(58-h), 208, 58-h, 6, h);
		
		String s = ((int)this.entity.heat)+"C";
        this.fontRendererObj.drawString(s, xStart+125-fontRendererObj.getStringWidth(s), yStart+38, 4210752);

        //NAME
        this.drawCenteredString(fontRendererObj, "Boiler", xStart+110, yStart+5, UT_Utils.RGBtoInt(255, 255, 255));
        
        if(UT_Utils.isIn(i, j, xStart+45, yStart+20, 20, 40)){//storage
        	List<String> fluid1 = new ArrayList<String>();
        	if(entity.storage.getFluidAmount() == 0)fluid1.add("Empty");
        	else {
        		fluid1.add("Fluid: "+entity.storage.getFluid().getFluid().getName());
        		fluid1.add(""+entity.storage.getFluidAmount()+"/"+entity.storage.getCapacity());
        	}
        	this.drawHoveringText(fluid1, i, j, fontRendererObj);
        }
        if(UT_Utils.isIn(i, j, xStart+138, yStart+21, 20, 40)){//result
        	List<String> fluid1 = new ArrayList<String>();
        	if(entity.result.getFluidAmount() == 0)fluid1.add("Empty");
        	else {
        		fluid1.add("Fluid: "+entity.result.getFluid().getFluid().getName());
        		fluid1.add(""+entity.result.getFluidAmount()+"/"+entity.result.getCapacity());
        	}
        	this.drawHoveringText(fluid1, i, j, fontRendererObj);
        }
    	RenderHelper.enableGUIStandardItemLighting();
	}

	public static void bindTexture(FluidStack f) {
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
	}

}

package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.FluidGeneratorEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class FluidGenGui extends GuiContainer{

	public FluidGeneratorEntity entity;

	public FluidGenGui(Container par1Container, FluidGeneratorEntity tile) {
		super(par1Container);
		entity = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fluidgen.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//fluid
		FluidStack input = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean w = input == null;
		if(!w){
			BoilerGui.bindTexture(input);
			IIcon ic = input.getFluid().getStillIcon();
			int a = input.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
			this.drawTexturedModelRectFromIcon(xStart+139, yStart+61-a, ic, 18, a);
		}

		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fluidgen.png"));
		if(!w)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		if(!w){
			String name = ""+input.getFluid().getName();
			this.drawCenteredString(fontRendererObj, name, xStart+90, yStart+28, UT_Utils.RGBtoInt(255, 255, 255));
			String amount = ""+input.amount;
			this.drawCenteredString(fontRendererObj, amount, xStart+90, yStart+42, UT_Utils.RGBtoInt(255, 255, 255));
		}
		
		this.drawCenteredString(fontRendererObj, "Fluid Firebox", xStart+85, yStart+6, UT_Utils.RGBtoInt(255, 255, 255));
		//text
		if(UT_Utils.isIn(i, j, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, i, j, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
	
	
}

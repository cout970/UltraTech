package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.FluidGenerator;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class FluidGenGui extends GuiContainer{

	public FluidGenerator entity;

	public FluidGenGui(Container par1Container, FluidGenerator tile) {
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

		//water
		FluidStack water = entity.getTankInfo(ForgeDirection.UP)[0].fluid;
		boolean w = water == null;
		if(!w){
			BoilerGui.bindTexture(water);
			int a = water.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
			this.drawTexturedModalRect(xStart+139, yStart+61-a, 0, 0, 18, a);
		}
		
		//overlay
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/fluidgen.png"));
		if(!w)this.drawTexturedModalRect(xStart+138, yStart+21, 224, 0, 20, 40);
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		if(!w){
		String name = ""+water.getFluid().getName();
		this.drawCenteredString(fontRenderer, name, xStart+90, yStart+28, UT_Utils.RGBtoInt(255, 255, 255));
		String amount = ""+water.amount;
		this.drawCenteredString(fontRenderer, amount, xStart+90, yStart+42, UT_Utils.RGBtoInt(255, 255, 255));
		}
	}
}

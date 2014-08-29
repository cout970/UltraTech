package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.fluid.BoilerEntity;
import common.cout970.UltraTech.TileEntities.intermod.DynamoEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class Dynamo_Gui extends GuiContainer{

public DynamoEntity entity;
	
	public Dynamo_Gui(Container par1Container, DynamoEntity t) {
		super(par1Container);
		entity = t;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/dynamo.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//RF
		int r = entity.getEnergyStored(null)*40/entity.getMaxEnergyStored(null);
		this.drawTexturedModalRect(xStart+146, yStart+23+(40-r), 177, 41-r, 12, 40);
		
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getPower().getCharge()*50/entity.getPower().getCapacity());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {

		String s = "Electric Dynamo";
		this.drawCenteredString(fontRendererObj, s, 90, 6, UT_Utils.RGBtoInt(255, 255, 255));
		
		//text
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		if(UT_Utils.isIn(i, j, xStart+14, yStart+15, 25, 50)){
			List<String> energy = new ArrayList<String>();
			energy.add("Energy: "+UT_Utils.removeDecimals(entity.getPower().getCharge())+IPower.POWER_NAME);
			this.drawHoveringText(energy, i-xStart, j-yStart, fontRendererObj);
		}

		if(UT_Utils.isIn(i, j, xStart+145, yStart+22, 12, 40)){
			List<String> energy = new ArrayList<String>();
			energy.add("Energy: "+((int)entity.getEnergyStored(null))+"RF");
			this.drawHoveringText(energy, i-xStart, j-yStart, fontRendererObj);
		}
		RenderHelper.enableGUIStandardItemLighting();
	}
}

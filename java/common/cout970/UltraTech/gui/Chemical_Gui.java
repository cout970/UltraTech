package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.tiers.ChemicalPlant_Entity;
import common.cout970.UltraTech.TileEntities.electric.tiers.Heater_Entity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class Chemical_Gui extends MachineGuiBase{

	public Chemical_Gui(Container par1Container, TileEntity te) {
		super(par1Container, te);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/chemical.png"));
		xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		ChemicalPlant_Entity e = (ChemicalPlant_Entity) entity;
		int i1 = 0;
		if(e.maxProgres != 0)i1 = (int) e.Progres*25/e.maxProgres;
		this.drawTexturedModalRect(xStart + 120, yStart + 15, 177, 0, i1, 16);
		this.drawTexturedModalRect(xStart + 120, yStart + 33, 177, 0, i1, 16);
		this.drawTexturedModalRect(xStart + 120, yStart + 51, 177, 0, i1, 16);
		
		FluidStack output = e.getTank().getFluid();
		if(output != null){
			IIcon li = output.getFluid().getStillIcon();
			if(li != null){
				Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				int a = output.amount*40/e.getTank().getCapacity();
				drawTexturedModelRectFromIcon(xStart+46, yStart+60-a, li, 18, a);		
			}
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/chemical.png"));
			this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		}
		
		//energy bar
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		//name
		this.drawCenteredString(fontRendererObj, "Chemical Plant", 99, 6, UT_Utils.RGBtoInt(255, 255, 255));
		
		if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+(entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}
}

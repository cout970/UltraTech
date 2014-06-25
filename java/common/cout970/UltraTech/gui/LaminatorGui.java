package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.LaminatorEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class LaminatorGui extends GuiContainer{

	public LaminatorEntity entity;
	
	public LaminatorGui(Container par1Container,LaminatorEntity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/laminator.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		//progres
		int i1 = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 113, yStart + 32, 176, 14, i1 + 1, 16);
		int x = (int) entity.progres*7/1000;
//		if(x == 3)x = 2;if(x == 4)x = 2;if(x == 5)x = 1;if(x == 6)x = 0;
//		if(x == 7)x = 1;if(x == 8)x = 2;if(x == 9)x = 2;if(x == 10)x = 2;
//		if(x == 11)x = 1;
//		if(x == 12)x = 0;
		
		
		
		drawTexturedModalRect(xStart + 78, yStart + 12+x, 222, 10, 32, 20);
		drawTexturedModalRect(xStart + 78, yStart + 45-x, 222, 0, 32, 20);
	
		//energy
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	
		this.drawCenteredString(fontRendererObj, "Speed upgrades: "+entity.speedUpgrades+"/5", xStart+95, yStart+70, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

		String s = this.entity.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize - 70, 6, 4210752);
       
        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+14, yStart+15, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+(entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

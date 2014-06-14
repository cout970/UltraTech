package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class MinerGui extends GuiContainer{

	private MinerEntity entity;

	public MinerGui(Container par1Container,InventoryPlayer inventoryPlayer, MinerEntity tileEntity2) {
		super(par1Container);
		xSize = 248;
        ySize = 186;
        entity = tileEntity2;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/miner.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getEnergy()*50/entity.maxEnergy());
		this.drawTexturedModalRect(xStart+9, yStart+105+(50-p), 0, 0, 25, p);
		
		//speed
		this.drawCenteredString(fontRendererObj, (entity.speed/10)+"x", xStart+21, yStart+167, UT_Utils.RGBtoInt(255, 255, 255));
		
		//range
		this.drawCenteredString(fontRendererObj, (entity.height*2+1)+"x"+(entity.widht*2+1), xStart+225, yStart+167, UT_Utils.RGBtoInt(255, 255, 255));
		
		//upgrades
		String line1 = "Fortune Upgrades "+entity.fortuneUpgrades+"x"+"   "+"AutoEject "+entity.eject;
		int to = (entity.MinigSize()-entity.current);
		String line2 = "Blocks to Mine "+((to > 0)? to : 0)+" Mined "+entity.current;
		this.drawCenteredString(fontRendererObj, line1, xStart+124, yStart+82, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawCenteredString(fontRendererObj, line2, xStart+124, yStart+92, UT_Utils.RGBtoInt(255, 255, 255));
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+9, yStart+105, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+((int)entity.getEnergy())+EnergyCosts.E);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

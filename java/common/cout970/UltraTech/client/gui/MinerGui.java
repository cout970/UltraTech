package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPower;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.electric.MinerEntity;
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

		//silk touch
		if(entity.hasSilkUpgrade){
			this.drawTexturedModalRect(xStart+24, yStart+82, 0, ySize, 18, 18);
		}			
		if(entity.eject){
			this.drawTexturedModalRect(xStart+4, yStart+82, 0, ySize, 18, 18);
		}
		
		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) (entity.getCharge()*50/entity.getCapacity());
		this.drawTexturedModalRect(xStart+9, yStart+105+(50-p), 0, 0, 25, p);
		
		//speed
		this.drawCenteredString(fontRendererObj, (entity.speedUpgrades)+"", xStart+15, yStart+167, UT_Utils.RGBtoInt(255, 255, 255));
		
		//range
		this.drawCenteredString(fontRendererObj, ""+entity.rangeUpgrades, xStart+215, yStart+110, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawCenteredString(fontRendererObj, "Width", xStart+225, yStart+132, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawCenteredString(fontRendererObj, ""+(entity.height*2+1), xStart+225, yStart+144, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawCenteredString(fontRendererObj, "Dept", xStart+225, yStart+156, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawCenteredString(fontRendererObj, ""+(entity.widht*2+1), xStart+225, yStart+168, UT_Utils.RGBtoInt(255, 255, 255));
		
		//fortune
		this.drawCenteredString(fontRendererObj, ""+entity.fortuneUpgrades, xStart+215, yStart+87, UT_Utils.RGBtoInt(255, 255, 255));
		
		//upgrades
		String line1 = "Blocks mined in the last ";
		String line2 = "5 seconds : "+entity.minedLastSec;
		this.drawString(fontRendererObj, line1, xStart+48, yStart+82, UT_Utils.RGBtoInt(255, 255, 255));
		this.drawString(fontRendererObj, line2, xStart+48, yStart+92, UT_Utils.RGBtoInt(255, 255, 255));

	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+9, yStart+105, 25, 50)){
        	List<String> energy = new ArrayList<String>();
        	energy.add("Energy: "+UT_Utils.removeDecimals(entity.getCharge())+IPower.POWER_NAME);
        	this.drawHoveringText(energy, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.machines.tileEntities.MinerEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
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
		int p = (int) entity.getEnergy()*50/entity.EnergyMax;
		this.drawTexturedModalRect(xStart+9, yStart+105+(50-p), 0, 0, 25, p);
		
		//speed
		this.drawString(fontRenderer, (entity.speed/10)+"x", xStart+15, yStart+166, RenderUtil.RGBtoInt(255, 255, 255));
		
		//range
		this.drawString(fontRenderer, (entity.height*2+1)+"x"+(entity.widht*2+1), (entity.widht*2+1 < 10) ? xStart+216 : xStart+210, yStart+166, RenderUtil.RGBtoInt(255, 255, 255));
	}

}

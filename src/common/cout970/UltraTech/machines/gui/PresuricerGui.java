package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.tileEntities.PresuricerEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class PresuricerGui extends GuiContainer{

	private PresuricerEntity entity;


	public PresuricerGui(Container par1Container,InventoryPlayer inventory, PresuricerEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}


	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/presuricer.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int u = (int) entity.progres*24/1000;
		this.drawTexturedModalRect(xStart + 98, yStart + 34, 178, 0, 15, u);
		
		//energy bar  
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
		int p = (int) entity.getEnergy()*50/entity.maxEnergy();
		this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);

	}

}

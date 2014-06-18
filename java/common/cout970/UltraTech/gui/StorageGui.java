package common.cout970.UltraTech.gui;


import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.Wpower.Machine;
import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class StorageGui extends GuiContainer{

	private Machine entity;

	public StorageGui(Container par1Container,InventoryPlayer ip ,Machine entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/ids.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		if(entity != null){
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
			int p = (int) (((double)entity.getEnergy())*50/(entity.maxEnergy()));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		
		String e = (int)entity.getEnergy()+"";
		String m = (int)entity.maxEnergy()+""+EnergyCosts.E;
		
		this.drawCenteredString(fontRendererObj, e+" / "+m, 105, 36, UT_Utils.RGBtoInt(255, 255, 255));
		String f = "Interdimensional Storage";
		int c = UT_Utils.RGBtoInt(0, 0, 0);
		if(entity instanceof StorageTier1)c = UT_Utils.RGBtoInt(0, 120, 0);
		if(entity instanceof StorageTier2)c = UT_Utils.RGBtoInt(255, 0, 0);
		if(entity instanceof StorageTier3)c = UT_Utils.RGBtoInt(0, 0, 255);
		this.fontRendererObj.drawString(f, this.xSize - 130, 8, c);
	}

}

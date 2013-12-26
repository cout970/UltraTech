package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;
import common.cout970.UltraTech.machines.tileEntities.PurifierEntity;
import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class PurifierGui extends GuiContainer{

	private PurifierEntity entity;
	
	public PurifierGui(Container par1Container, InventoryPlayer inventory, PurifierEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/purifier.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = (int) this.entity.getSinc().getVar1();
		this.drawTexturedModalRect(xStart + 85, yStart + 31, 176, 14, i1 + 1, 16);


		//energy bar
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
			SyncObject s = entity.getSinc();
			int p = (int) ((((float)s.getVar2())*50/entity.EnergyMax));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}
}

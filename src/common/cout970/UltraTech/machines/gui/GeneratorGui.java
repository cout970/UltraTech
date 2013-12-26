package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.tileEntities.GeneratorEntity;
import common.cout970.UltraTech.misc.SyncObject;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GeneratorGui extends GuiContainer{

	private GeneratorEntity entity;
	
	public GeneratorGui(Container par1Container,InventoryPlayer ip,GeneratorEntity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/generator.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//progres bar
		int i1 = (int) this.entity.getSync().getVar1();
		this.drawTexturedModalRect(xStart + 82, yStart + 42 + 12 - i1, 176, 13 - i1, 14, i1 + 2);
	
		
		//energy
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/misc/energy.png"));
			SyncObject s = entity.getSync();
			int p = (int) ((((float)s.getVar2())*50/entity.EnergyMax));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
	}

	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
		String s = this.entity.isInvNameLocalized() ? this.entity.getInvName() : "Generator";
        this.fontRenderer.drawString(s, this.xSize - 100, 6, 4210752);
	}

}

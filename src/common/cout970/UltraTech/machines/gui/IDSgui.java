package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;
import common.cout970.UltraTech.machines.tileEntities.IDSentity;
import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class IDSgui extends GuiContainer{

	private IDSentity entity;

	public IDSgui(Container par1Container,InventoryPlayer ip ,IDSentity entity) {
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
			SyncObject s = entity.getSync();
			int p = (int) ((((float)s.getVar2())*50/(float)(s.getVar1())));
			this.drawTexturedModalRect(xStart+14, yStart+15+(50-p), 0, 0, 25, p);
		}}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
		SyncObject s = entity.getSync();
		String e = s.getVar2()+"";
        this.fontRenderer.drawString(e, this.xSize - 121+(-e.length()*5+35), 36, 4210752);
        this.fontRenderer.drawString(" / ", this.xSize - 80, 36, 4210752);
    	String m = s.getVar1()+"";
        this.fontRenderer.drawString(m, this.xSize - 68+(-m.length()*5+35), 36, 4210752);
        
        String f = "Interdimensional Storage";
        this.fontRenderer.drawString(f, this.xSize - 150, 4, 4210752);
	}

}

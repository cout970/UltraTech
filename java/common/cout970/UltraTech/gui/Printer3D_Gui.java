package common.cout970.UltraTech.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.utility.Painter3DEntity;
import common.cout970.UltraTech.containers.Printer3DContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class Printer3D_Gui extends GuiContainer{

	public Painter3DEntity entity;
	
	public Printer3D_Gui(Container par1Container,InventoryPlayer ip,Painter3DEntity entity) {
		super(par1Container);
		this.entity = entity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/printer.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		int p = entity.color;
		if(p < 7)this.drawTexturedModalRect(33+xStart, 8+yStart+p*10, 0, ySize, 68, 11);
		else this.drawTexturedModalRect(100+xStart, 8+yStart+(p-7)*10, 0, ySize, 68, 11);
	}
	
	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		for(int i=0;i<7;i++){
			if(isIn(mx, my, 35+xStart, 8+yStart+i*10, 62, 10)){
				entity.color = i;
				entity.sendUpdate();
				return;
			}
		}
		for(int i=7;i<14;i++){
			if(isIn(mx, my, 102+xStart, 8+yStart+(i-7)*10, 62, 10)){
				entity.color = i;
				entity.sendUpdate();
				return;
			}
		}
	}
	
	public boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
		}}
		return false;
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public void initGui(){
//		super.initGui();
//		int xStart = (width - xSize) / 2;
//		int yStart = (height - ySize) / 2;
//		//id, x, y, width, height, text
//		for(int i=0;i<7;i++){
//			buttonList.add(new GuiButton(i, 35+xStart, 8+yStart+i*10, 62, 10, UT_ItemBlockDeco.subNames[i]));
//		}
//		for(int i=7;i<14;i++){
//			buttonList.add(new GuiButton(i, 102+xStart, 8+yStart+(i-7)*10, 62, 10, UT_ItemBlockDeco.subNames[i]));
//		}
//	}
//
//	protected void actionPerformed(GuiButton b){
//		cont.color = b.id;
//		cont.sendUpdate();
//	}
}

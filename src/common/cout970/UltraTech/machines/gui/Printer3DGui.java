package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.itemBlock.UT_ItemBlockDeco;
import common.cout970.UltraTech.machines.containers.Printer3DContainer;
import common.cout970.UltraTech.machines.tileEntities.Printer3DEntity;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class Printer3DGui extends GuiContainer{

	public Printer3DContainer cont;
	
	public Printer3DGui(Container par1Container,InventoryPlayer ip,Printer3DEntity entity) {
		super(par1Container);
		cont = (Printer3DContainer) par1Container;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/printer.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui(){
		super.initGui();
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		//id, x, y, width, height, text
		for(int i=0;i<7;i++){
			buttonList.add(new GuiButton(i, 35+xStart, 8+yStart+i*10, 62, 10, UT_ItemBlockDeco.subNames[i]));
		}
		for(int i=7;i<14;i++){
			buttonList.add(new GuiButton(i, 102+xStart, 8+yStart+(i-7)*10, 62, 10, UT_ItemBlockDeco.subNames[i]));
		}
	}

	protected void actionPerformed(GuiButton b){
		cont.color = b.id;
		cont.sendUpdate();
	}
}

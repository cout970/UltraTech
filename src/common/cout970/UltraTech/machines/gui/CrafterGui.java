package common.cout970.UltraTech.machines.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier1.CrafterEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class CrafterGui extends GuiContainer{

	public CrafterEntity entity;
	public InventoryPlayer p;
	
	public CrafterGui(Container par1Container, InventoryPlayer inventory, CrafterEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
		p = inventory;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/crafter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		if(entity.getStackInSlot(-1) != null)this.drawTexturedModalRect(xStart+85, yStart+67, 177, 0, 9, 9);
		//draw needs not haves
		GL11.glEnable(GL11.GL_BLEND);
		//craft grid
		int h = 10;
		if(entity.found.containsKey(0) && entity.found.get(0))this.drawTexturedModalRect(xStart+13, yStart+26, 177, h, 16, 16);
		if(entity.found.containsKey(1) && entity.found.get(1))this.drawTexturedModalRect(xStart+31, yStart+26, 177, h, 16, 16);
		if(entity.found.containsKey(2) && entity.found.get(2))this.drawTexturedModalRect(xStart+49, yStart+26, 177, h, 16, 16);
		
		if(entity.found.containsKey(3) && entity.found.get(3))this.drawTexturedModalRect(xStart+13, yStart+44, 177, h, 16, 16);
		if(entity.found.containsKey(4) && entity.found.get(4))this.drawTexturedModalRect(xStart+31, yStart+44, 177, h, 16, 16);
		if(entity.found.containsKey(5) && entity.found.get(5))this.drawTexturedModalRect(xStart+49, yStart+44, 177, h, 16, 16);
		
		if(entity.found.containsKey(6) && entity.found.get(6))this.drawTexturedModalRect(xStart+13, yStart+62, 177, h, 16, 16);
		if(entity.found.containsKey(7) && entity.found.get(7))this.drawTexturedModalRect(xStart+31, yStart+62, 177, h, 16, 16);
		if(entity.found.containsKey(8) && entity.found.get(8))this.drawTexturedModalRect(xStart+49, yStart+62, 177, h, 16, 16);
		
		//craft result
		if(entity.getStackInSlot(0) != null && !entity.allFound()){
			this.drawTexturedModalRect(xStart+81, yStart+44, 177, h, 16, 16);
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		entity.update();
		if(entity.getStackInSlot(-1) != null){
			if(isIn(mx,my, xStart+85, yStart+67, 9, 9)){
				UT_Utils.sendPacket(entity, 0, 2);
			}
		}
		for(int d =0;d<9;d++){
			if(isIn(mx, my, xStart+8 + d * 18, yStart+6, 18,18))if(entity.saves.getStackInSlot(d) != null){
				if(b == 0)UT_Utils.sendPacket(entity, d, 3);
				if(b == 1){
					UT_Utils.sendPacket(entity, d, 4);
				}
			}
		}
		
		if(isIn(mx,my,xStart+80, yStart+43,18,18)){
			if(b == 0){
				UT_Utils.sendPacket(entity, 0, 1);
			}else if(b == 1){
				entity.emptyCraft();
			}
		}
		ItemStack i = null;
		if(p.getItemStack() != null){
		i = p.getItemStack().copy();
		i .stackSize = 1;
		}
		for(int x=0;x<3;x++){
			for(int y=0;y<3;y++){
				if(isIn(mx,my,xStart+12+x*18, yStart+25+y*18,18,18)){
					if(b == 0)entity.craft.setInventorySlotContents(x+y*3, i);
					if(b == 1)entity.craft.setInventorySlotContents(x+y*3, null);
					UT_Utils.sendPacket(entity, x+y*3, 0);
				}
			}
		}
	}

	
	public boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
			}
		}
		return false;
	}

}

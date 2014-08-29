package common.cout970.UltraTech.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.utility.CrafterEntity;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageCrafter;

public class Crafter_Gui extends GuiContainer{

	public CrafterEntity entity;
	public InventoryPlayer p;
	
	public Crafter_Gui(Container par1Container, InventoryPlayer inventory, CrafterEntity tileEntity) {
		super(par1Container);
		entity = tileEntity;
		p = inventory;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/crafter.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		if(entity.getStackInSlot(-1) != null)this.drawTexturedModalRect(xStart+71, yStart+28, 176, 28, 15, 10);//save buttom
		else this.drawTexturedModalRect(xStart+71, yStart+28, 192, 28, 15, 10);
		if(entity.restrictMode)this.drawTexturedModalRect(xStart+89, yStart+28, 176, 39, 15, 10);// mode buttom
		else this.drawTexturedModalRect(xStart+89, yStart+28, 192, 39, 15, 10);
		//draw needs not haves
		GL11.glEnable(GL11.GL_BLEND);
		//craft grid

		if(entity.isLoaded()){
			int h = 10;
			if(!entity.found[0])this.drawTexturedModalRect(xStart+13, yStart+26, 177, h, 16, 16);
			if(!entity.found[1])this.drawTexturedModalRect(xStart+31, yStart+26, 177, h, 16, 16);
			if(!entity.found[2])this.drawTexturedModalRect(xStart+49, yStart+26, 177, h, 16, 16);

			if(!entity.found[3])this.drawTexturedModalRect(xStart+13, yStart+44, 177, h, 16, 16);
			if(!entity.found[4])this.drawTexturedModalRect(xStart+31, yStart+44, 177, h, 16, 16);
			if(!entity.found[5])this.drawTexturedModalRect(xStart+49, yStart+44, 177, h, 16, 16);

			if(!entity.found[6])this.drawTexturedModalRect(xStart+13, yStart+62, 177, h, 16, 16);
			if(!entity.found[7])this.drawTexturedModalRect(xStart+31, yStart+62, 177, h, 16, 16);
			if(!entity.found[8])this.drawTexturedModalRect(xStart+49, yStart+62, 177, h, 16, 16);

			//craft result
			if(entity.getStackInSlot(-1) != null && !entity.allFound()){
				this.drawTexturedModalRect(xStart+80, yStart+44, 177, h, 16, 16);
			}
		}
		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		entity.update();
		if(entity.getStackInSlot(-1) != null){//save buttom
			if(isIn(mx,my, xStart+71, yStart+28, 15, 10)){
				Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,0,0));
			}
		}
		if(isIn(mx,my, xStart+89, yStart+28, 15, 10)){//mode buttom
			Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,4,!entity.restrictMode));
		}
		
		for(int d =0;d<9;d++){//saves
			if(isIn(mx, my, xStart+8 + d * 18, yStart+6, 18,18))if(entity.saves.getStackInSlot(d) != null){
				if(b == 0){
					Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,1,d));
				}
				if(b == 1){
					Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,2,d));
				}
			}
		}
		
		if(isIn(mx,my,xStart+80, yStart+43,18,18)){//craft
			if(b == 0){
				Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,true));//craft item
			}else if(b == 1){
				Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,false));//clear craft grid
			}
		}
		ItemStack i = null;
		if(p.getItemStack() != null){
			i = p.getItemStack().copy();
			i .stackSize = 1;
		}
		for(int x=0;x<3;x++){//grid
			for(int y=0;y<3;y++){
				if(isIn(mx,my,xStart+12+x*18, yStart+25+y*18,18,18)){
					if(b == 0)Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,i,x+y*3));
					if(b == 1)Net_Utils.INSTANCE.sendToServer(new MessageCrafter(entity,null,x+y*3));
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

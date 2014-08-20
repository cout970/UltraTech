package common.cout970.UltraTech.client.gui;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.client.renders.Map;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageHologram;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class HologramEmiterGui extends GuiContainer{

	public HologramEmiterEntity entity;
	
	public HologramEmiterGui(Container par1Container,TileEntity tile) {
		super(par1Container);
		entity = (HologramEmiterEntity) tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/hologram.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
		if(entity.rend instanceof Map){
			String mensage = "Height "+((Map)entity.rend).height+" Color "+!((Map)entity.rend).colorHeight;
			this.drawCenteredString(fontRendererObj,mensage, xStart+88, yStart+18, UT_Utils.RGBtoInt(255, 255, 255));
		}
		this.drawCenteredString(fontRendererObj, "Hologram Emiter", xStart+85, yStart+3, UT_Utils.RGBtoInt(255, 255, 255));
		String s = "X: "+entity.mX+" Y: "+entity.mY+" Z: "+entity.mZ+" "+entity.facing;
		this.drawCenteredString(fontRendererObj, s, xStart+85, yStart+18, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		//first colum
		if(entity.rend instanceof Map){
			Map map = (Map) entity.rend;
			if(isIn(mx, my, xStart+19, yStart+33, 16, 16)){
				map.height = !map.height;
			}
			if(isIn(mx, my, xStart+19, yStart+53, 16, 16)){
				map.colorHeight = !map.colorHeight;
			}
			//mode
			if(isIn(mx, my, xStart+39, yStart+33, 16, 16)){
				if(map.mode < 14)map.mode += 1;
			}
			if(isIn(mx, my, xStart+39, yStart+53, 16, 16)){
				if(map.mode > 0)map.mode -= 1;
			}
		}
		//y +-
		if(isIn(mx, my, xStart+59, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 1, entity.mY + 1/16f));
		}
		if(isIn(mx, my, xStart+59, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 1, entity.mY - 1/16f));
		}
		
		//x +-
		if(isIn(mx, my, xStart+77, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 0, entity.mX + 1/16f));
		}
		if(isIn(mx, my, xStart+77, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 0, entity.mX - 1/16f));
		}

		//z +-
		if(isIn(mx, my, xStart+95, yStart+33, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 2, entity.mZ + 1/16f));
		}
		if(isIn(mx, my, xStart+95, yStart+53, 16, 16)){
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 2, entity.mZ - 1/16f));
		}
		
		//facing
		if(isIn(mx, my, xStart+113, yStart+33, 16, 16)){
			int dir = entity.facing.ordinal()+1;
			if(dir >= 6)dir = 0;
			Net_Utils.INSTANCE.sendToServer(new MessageHologram(entity, 3, dir));
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

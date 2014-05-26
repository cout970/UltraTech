package common.cout970.UltraTech.gui;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier3.HologramEmiterEntity;
import common.cout970.UltraTech.lib.UT_Utils;
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
		String mensage = "Height "+entity.map.height+" Color "+!entity.map.colorHeight;
		this.drawCenteredString(fontRendererObj,mensage, xStart+88, yStart+18, UT_Utils.RGBtoInt(255, 255, 255));
		
		this.drawCenteredString(fontRendererObj, "Hologram emiter", xStart+85, yStart+3, UT_Utils.RGBtoInt(255, 255, 255));
	}

	@Override
	protected void mouseClicked(int mx, int my, int b)
	{
		super.mouseClicked(mx, my, b);
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		//first colum
		if(isIn(mx, my, xStart+19, yStart+33, 16, 16)){
			entity.map.height = !entity.map.height;
		}
		if(isIn(mx, my, xStart+19, yStart+53, 16, 16)){
			entity.map.colorHeight = !entity.map.colorHeight;
		}
		//mode
		if(isIn(mx, my, xStart+39, yStart+33, 16, 16)){
			if(entity.map.mode < 14)entity.map.mode += 1;
		}
		if(isIn(mx, my, xStart+39, yStart+53, 16, 16)){
			if(entity.map.mode > 0)entity.map.mode -= 1;
		}
		//y +-
		if(isIn(mx, my, xStart+59, yStart+33, 16, 16)){
			entity.map.y += 1;
		}
		if(isIn(mx, my, xStart+59, yStart+53, 16, 16)){
			entity.map.y -= 1;
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

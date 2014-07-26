package common.cout970.UltraTech.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.IPower;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Core_Entity;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class Reactor_Gui extends MachineGuiBase{

	public Reactor_Core_Entity tile;
	
	public Reactor_Gui(Container par1Container, InventoryPlayer inventory, TileEntity te) {
		super(par1Container, te);
		ySize = 242;
		tile = (Reactor_Core_Entity) te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		//draw temp var
		int h = (int) (tile.heat*58/tile.maxheat);
		this.drawTexturedModalRect(xStart+9, yStart+39+(58-h), 176, 58-h, 5, h);
		
		//draw fluids
		if(tile.getTank(0).getFluidAmount() > 0){
			UT_Tank t = tile.getTank(0);
			if(t.getCapacity() > 0){
			this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon i1 = FluidRegistry.WATER.getIcon();
			int p = t.getFluidAmount()*60/t.getCapacity();
			this.drawTexturedModelRectFromIcon(xStart+26, yStart+38+(60-p), i1, 20, p);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+26, yStart+37, 182, 0, 20, 60);
			}
		}
		if(tile.getTank(1).getFluidAmount() > 0){
			UT_Tank t = tile.getTank(1);
			if(t.getCapacity() > 0){
			this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			IIcon i1 = FluidRegistry.getFluid("steam").getIcon();
			int p = t.getFluidAmount()*60/t.getCapacity();
			this.drawTexturedModelRectFromIcon(xStart+52, yStart+38+(60-p), i1, 20, p);
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/reactor.png"));
			this.drawTexturedModalRect(xStart+52, yStart+37, 182, 0, 20, 60);
			}
		}
	}
	
	@Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        //text
        xStart = (width - xSize) / 2;
		yStart = (height - ySize) / 2;
		
        if(UT_Utils.isIn(x, y, xStart+26, yStart+38, 20, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Water: "+tile.getTank(0).getFluidAmount()+" / "+tile.getTank(0).getCapacity());
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
        if(UT_Utils.isIn(x, y, xStart+52, yStart+38, 20, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Steam: "+tile.getTank(1).getFluidAmount()+" / "+tile.getTank(1).getCapacity());
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
        if(UT_Utils.isIn(x, y, xStart+7, yStart+38, 14, 60)){
        	List<String> info = new ArrayList<String>();
        	info.add("Heat: "+((int)tile.heat)+" / "+((int)tile.maxheat));
        	this.drawHoveringText(info, x-xStart, y-yStart, fontRendererObj);
        	RenderHelper.enableGUIStandardItemLighting();
        }
	}

}

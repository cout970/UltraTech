package common.cout970.UltraTech.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.TileEntities.multiblocks.refinery.Refinery_IO_Entity;

public class Refinery_Gui extends GuiContainer{

	public Refinery_IO_Entity entity;
	
	public Refinery_Gui(Container par1Container, Refinery_IO_Entity tileEntity) {
		super(par1Container);
		entity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/refinery.png"));
		int xStart = (width - xSize) / 2;
		int yStart = (height - ySize) / 2;
		this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);

		FluidStack output = entity.getTank().getFluid();
		boolean l = output == null;

		if(!l){
			IIcon li = output.getFluid().getStillIcon();
			if(li != null){
				Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
				int a = output.amount*40/entity.getTankInfo(ForgeDirection.UP)[0].capacity;
				drawTexturedModelRectFromIcon(xStart+46, yStart+60-a, li, 18, a);
			}
			this.mc.renderEngine.bindTexture(new ResourceLocation("ultratech:textures/gui/refinery.png"));
			this.drawTexturedModalRect(xStart+45, yStart+20, 224, 0, 20, 40);
		}
		
		String s = "Mode: ";
		if(entity.mode == 0)s += " Nothing";
		if(entity.mode == 1)s += " Input";
		if(entity.mode == 2)s += " Output 1";
		if(entity.mode == 3)s += " Output 2";
		if(entity.mode == 4)s += " Output 3";
		this.drawCenteredString(fontRendererObj, s, xStart+120, yStart+38, UT_Utils.RGBtoInt(255, 255, 255));
		String li = "Amount: "+entity.getTank().getFluidAmount()+" / "+entity.getTank().getCapacity();
		this.drawCenteredString(fontRendererObj, li, xStart+120, yStart+50, UT_Utils.RGBtoInt(255, 255, 255));
	}

}

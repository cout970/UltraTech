package common.cout970.UltraTech.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.lib.EnergyCosts;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.multiblocks.refinery.RefineryBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.Container;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public class RefineryGui extends GuiContainer{

	public RefineryBase entity;
	
	public RefineryGui(Container par1Container, RefineryBase e) {
		super(par1Container);
		entity = e;
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
		
		String s = "Mode: "+entity.mode;
		if(entity.mode == 0)s += " Nothing";
		if(entity.mode == 1)s += " Input";
		if(entity.mode == 2)s += " Output 1";
		if(entity.mode == 3)s += " Output 2";
		if(entity.mode == 4)s += " Output 3";
        this.fontRendererObj.drawString(s, xStart+155-fontRendererObj.getStringWidth(s), yStart+38, 4210752);

	}

}

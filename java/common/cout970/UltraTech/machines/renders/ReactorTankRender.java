package common.cout970.UltraTech.machines.renders;


import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.utility.ReactorTankEntity;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.misc.Renderer_Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class ReactorTankRender extends TileEntitySpecialRenderer{
	
	private Renderer_Util RF;
	
	public ReactorTankRender(){
		super();
		RF = new Renderer_Util();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		if(tileentity != null && Minecraft.getMinecraft().renderEngine != null && FluidRegistry.WATER != null){
			int a = ((ReactorTankEntity)tileentity).getFluidAmount();
			int b =	((ReactorTankEntity)tileentity).getCapacity();
			bindTexture(TextureMap.locationBlocksTexture);
			float fill = (float)a/(float)b;
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glColor3f(1f, 1f, 1f);
			IIcon i = FluidRegistry.WATER.getStillIcon();
			GL11.glTranslated(0.01f, 0.01f, 0.01f);
			RF.renderBox(i, 0.98f, fill-0.02f, 0.98f);
			GL11.glPopMatrix();
		}
	}
	
}

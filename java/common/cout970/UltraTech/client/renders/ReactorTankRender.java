package common.cout970.UltraTech.client.renders;


import org.lwjgl.opengl.GL11;

import ultratech.api.reactor.IReactorComponent;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Core_Entity;
import common.cout970.UltraTech.TileEntities.utility.ReactorTankEntity;
import common.cout970.UltraTech.util.render.RenderUtil;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;

public class ReactorTankRender extends TileEntitySpecialRenderer{
	
	private CubeRenderer_Util RF;
	
	public ReactorTankRender(){
		super();
		RF = new CubeRenderer_Util();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		if(tileentity != null && Minecraft.getMinecraft().renderEngine != null && FluidRegistry.WATER != null){
			if(((IReactorComponent) tileentity).getCore() == null)return;
			int a = ((Reactor_Core_Entity) ((IReactorComponent)tileentity).getCore()).getTank(0).getFluidAmount();//amount
			int b =	((Reactor_Core_Entity) ((IReactorComponent)tileentity).getCore()).getTank(0).getCapacity();//cap
			bindTexture(TextureMap.locationBlocksTexture);
			float fill = (float)a/(float)b;
			if(fill>0){
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glTranslated(x, y, z);
				GL11.glColor3f(1f, 1f, 1f);
				IIcon i = FluidRegistry.WATER.getStillIcon();
				GL11.glTranslated(0.01f, 0.01f, 0.01f);
				RF.renderBox(i, 0.98f, fill-0.02f, 0.98f);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glPopMatrix();
			}
		}
	}
	
}

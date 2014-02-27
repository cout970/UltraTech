package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.renders.holograms.Map;
import common.cout970.UltraTech.machines.tileEntities.HologramEmiterEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderHologram extends TileEntitySpecialRenderer{


	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
//		if(((HologramEmiterEntity)te).map == null){
//			((HologramEmiterEntity)te).map = new Map("test", te.worldObj, te.xCoord, te.yCoord, te.zCoord);
//		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(0, 2.0F, 0);
		this.bindTexture(new ResourceLocation("ultratech:textures/misc/tex.png"));
//		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.8f);
//		if(((HologramEmiterEntity)te).map != null)((HologramEmiterEntity)te).map.Render();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		
		GL11.glPopMatrix();	
		
	}
}

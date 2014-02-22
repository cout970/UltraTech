package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.renders.holograms.HologramEntity;
import common.cout970.UltraTech.machines.renders.holograms.Map;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderHologram extends Render{

	
	public RenderHologram(){
	}

	@Override
	public void doRender(Entity te, double x, double y,
			double z, float f, float f1) {
		Map m = ((HologramEntity)te).map;
		if(m == null){
			((HologramEntity)te).map = new Map("test", te.worldObj, te.posX, te.posY, te.posZ);
		}
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x-0.5f, (float) y-2, (float) z-0.5f);
		GL11.glTranslatef(0, 2.0F, 0);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		if(m != null)m.Render();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		
		GL11.glPopMatrix();		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation("ultratech:textures/misc/tex.png");
	}
}

package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderHologram extends TileEntitySpecialRenderer{

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z);
		bindTexture(new ResourceLocation("ultratech:textures/misc/tex.png"));
		GL11.glTranslatef(0, 1.0F, 0);
		
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.6f);
		
		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		t.addVertexWithUV(0, 0, 0, 0, 0);
		t.addVertexWithUV(1, 0, 0, 0, 1);
		t.addVertexWithUV(1, 0, 1, 1, 1);
		t.addVertexWithUV(0, 0, 1, 1, 0);
		t.draw();
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		
		GL11.glPopMatrix();
	}
}

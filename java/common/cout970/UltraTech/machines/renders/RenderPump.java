package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.misc.IconFactory;
import common.cout970.UltraTech.misc.Renderer_Util;
import common.cout970.UltraTech.models.ModelBoiler;
import common.cout970.UltraTech.models.ModelPump;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderPump extends TileEntitySpecialRenderer{

	private ModelPump model;
	private Renderer_Util FR = new Renderer_Util();

	public RenderPump(){
		super();
		model = new ModelPump();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		
		bindTexture(new ResourceLocation("ultratech:textures/misc/pump.png"));
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);		
		GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
		//render fluid
		
		
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
}

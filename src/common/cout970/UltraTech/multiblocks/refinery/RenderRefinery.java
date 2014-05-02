package common.cout970.UltraTech.multiblocks.refinery;

import org.lwjgl.opengl.GL11;


import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderRefinery extends TileEntitySpecialRenderer{

	private ModelRefinery model;
	
	public RenderRefinery(){
		super();
		model = new ModelRefinery();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		GL11.glColor4f(1, 1, 1, 1);
		bindTexture(new ResourceLocation("ultratech:textures/misc/refinery.png"));
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}

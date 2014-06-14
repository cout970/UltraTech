package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.models.ModelPipe;
import common.cout970.UltraTech.models.ModelPump;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderPipe extends TileEntitySpecialRenderer{

	private ModelPipe model;

	public RenderPipe(){
		super();
		model = new ModelPipe();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		bindTexture(new ResourceLocation("ultratech:textures/misc/pipe.png"));
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderModel(0.0625F, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		GL11.glPopMatrix();
	}

}

package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.microparts.MicroCable;
import common.cout970.UltraTech.models.ModelCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderCable extends TileEntitySpecialRenderer{

	private ModelCable model;
	
	public RenderCable(){
		model = new ModelCable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		int meta = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
		if(meta == 0)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable.png"));
		if(meta == 1)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable1.png"));
		if(meta == 2)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable2.png"));

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
//		model.renderModel(0.0625F, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		GL11.glPopMatrix();
	}

	public void render(MicroCable mc, Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/modelcable.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(model == null)model = new ModelCable();
		model.render(mc, pos, 0.0625F);
		GL11.glPopMatrix();
	}

}

package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.models.ModelCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
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
		int meta = te.worldObj.getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
		if(meta == 0)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable.png"));
		if(meta == 1)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable1.png"));
		if(meta == 2)bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable2.png"));

		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderModel(0.0625F, te.worldObj, te.xCoord, te.yCoord, te.zCoord);
		GL11.glPopMatrix();
	}

}

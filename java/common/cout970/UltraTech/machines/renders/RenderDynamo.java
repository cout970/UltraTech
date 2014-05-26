package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.DynamoEntity;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.models.ModelCable;
import common.cout970.UltraTech.models.ModelDynamo;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderDynamo extends TileEntitySpecialRenderer{

private ModelDynamo model;
	
	public RenderDynamo(){
		model = new ModelDynamo();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		DynamoEntity d = (DynamoEntity) te;
		if(d.isWorking())RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/dynamo_on.png"));
		else RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/dynamo.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
}

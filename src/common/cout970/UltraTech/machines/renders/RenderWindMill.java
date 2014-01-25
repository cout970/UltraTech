package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.models.ModelWindMill;
import common.cout970.UltraTech.machines.tileEntities.WindMillEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderWindMill extends TileEntitySpecialRenderer{

	public ModelWindMill model;
	
	public RenderWindMill(){
		this.model = new ModelWindMill();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTexture(new ResourceLocation("ultratech:textures/misc/windmill.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		((WindMillEntity)te).fan += ((WindMillEntity)te).speed;
		if(((WindMillEntity)te).fan >=360){
			((WindMillEntity)te).fan = 0;
		}
		float d = ((WindMillEntity)te).fan;
		((ModelRenderer)model.boxList.get(7)).rotateAngleZ = (float) Math.toRadians(120+d);
		((ModelRenderer)model.boxList.get(6)).rotateAngleZ = (float) Math.toRadians(0+d);
		((ModelRenderer)model.boxList.get(5)).rotateAngleZ = (float) Math.toRadians(-120+d);
		((ModelRenderer)model.boxList.get(4)).rotateAngleZ = (float) Math.toRadians(0+d);
		((ModelRenderer)model.boxList.get(3)).rotateAngleZ = (float) Math.toRadians(0+d);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

}

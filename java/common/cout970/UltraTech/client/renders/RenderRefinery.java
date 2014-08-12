package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;




import common.cout970.UltraTech.client.models.ModelRefinery;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
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
		bindTexture(ResourcesLocations.REFINERY);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glTranslatef(0,-1,0);
		this.model.render(0.0625F);
		GL11.glPopMatrix();
	}
}

package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier1;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier2;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier3;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;
import common.cout970.UltraTech.client.models.ModelBattery;
import common.cout970.UltraTech.client.models.ModelTesseract;
import common.cout970.UltraTech.client.textures.ModelResources;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderTesseract extends TileEntitySpecialRenderer{

	private ModelTesseract model;
	
	public RenderTesseract(){
		super();
		model = new ModelTesseract();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL11.GL_LIGHTING);
		bindTexture(ModelResources.TESSERACT);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		TileEntityTesseract t = (TileEntityTesseract) te;
		double dif = System.currentTimeMillis()-t.animationTime;
		t.animationTime = System.currentTimeMillis();
		if(dif > 50)dif = 0;
		dif /= 3500;
		if(t.animation >= 0.1f)t.animationUp = false;
		if(t.animation <= -0.2f)t.animationUp = true;
		if(t.animationUp)t.animation += dif;
		else			 t.animation -= dif;
		dif *= 20;
		t.rotation += dif;
		GL11.glRotatef((float) t.rotation%360, 0, 1f, 0);
		GL11.glTranslatef(0f,t.animation,0f);
		model.renderBall(0.0625F);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
}

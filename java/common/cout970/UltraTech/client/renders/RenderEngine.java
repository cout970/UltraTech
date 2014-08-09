package common.cout970.UltraTech.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.intermod.EngineEntity;
import common.cout970.UltraTech.client.models.ModelEngine;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.misc.IconFactory;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;

public class RenderEngine extends TileEntitySpecialRenderer{

	private ModelEngine model;
	private CubeRenderer_Util FR = new CubeRenderer_Util();
	
	public RenderEngine() {
		this.model = new ModelEngine();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		EngineEntity e = (EngineEntity) te;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		this.bindTexture(ResourcesLocations.ENGINE);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		setRotation(e);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		double dif = System.currentTimeMillis()-e.oldTime;
		e.oldTime = System.currentTimeMillis();
		float k = 0.0625F;
		if(e.engOn){
			if(dif > 50)dif = 0;
			dif /= 1000;
			if(e.animation >= 0.625)e.animationUp = false;
			else if(e.animation <= 0)e.animationUp = true;
			if(e.animationUp)	e.animation += dif;
			else 				e.animation -= dif;

			
		}else{
			if(e.animation < k*10)
			e.animation += 0.1*k;
			if(e.animation < 0)e.animation = 0;
			if(e.animation > k*10)e.animation = k*10;
		}
		GL11.glTranslatef(0f, e.animation, 0f);
		model.renderMove(k);
		GL11.glTranslatef(0f, -e.animation, 0f);

		GL11.glTranslatef(k*11-1f, -k*4+1.5f, -k*5);
		GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F);
		IIcon i = new IconFactory(128, 64, 0, 35/64f, 20/128f, 43/64f, "engine bellows");
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL11.GL_LIGHTING);
		FR.renderBoxWithExcludedParts(i, 10*k, 10*k, k*(10f-(e.animation*10)/0.625f),new int[]{4,5});
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void setRotation(EngineEntity e) {
		//rotation
		switch(e.direction){
		case NORTH:{
			GL11.glRotatef(180, 0, 1, 0);
		}
		case SOUTH:{
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(0, -1, 1);
			break;
		}
		case EAST:{
			GL11.glRotatef(-90, 0, 0, 1);
			GL11.glTranslated(-1, -1, 0);
			break;
		}
		case WEST:{
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslated(1, -1, 0);
			break;
		}
		case DOWN:{
			GL11.glRotatef(180, 1, 0, 0);
			GL11.glTranslated(0, -2, 0);
			break;
		}
		case UP:{
			break;
		}
		default:{
		}
		}

	}

	public long getDelta(EngineEntity te){
		long aux = System.currentTimeMillis();
		long delta = System.currentTimeMillis()-te.oldTime;
		te.oldTime = aux;
		return delta;
	}

}

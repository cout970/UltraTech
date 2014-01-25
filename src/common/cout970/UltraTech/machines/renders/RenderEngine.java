package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.machines.models.ModelEngine;
import common.cout970.UltraTech.machines.tileEntities.EngineEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderEngine extends TileEntitySpecialRenderer{

	private ModelEngine model;

	public RenderEngine() {
		this.model = new ModelEngine();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		
		EngineEntity e = (EngineEntity) te;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		this.bindTexture(new ResourceLocation("ultratech:textures/misc/engine.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		setRotation(e);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	private void setRotation(EngineEntity e) {
		//animation
		if(e.engOut){
			if(e.engPos < 40){
				e.engPos+=e.speed;
			}else e.engOut = false;
		}else{
			if(e.engPos > 0){
				e.engPos-=e.speed;
			}else e.engOut = true;
		}
		//rotation
		switch(e.direction){
		case NORTH:{
			GL11.glRotatef(0, 0, 1, 0);			((ModelRenderer) this.model.boxList.get(2)).offsetZ = 0.4f-((float) e.engPos)/100f;
			break;
		}
		case SOUTH:{
			GL11.glRotatef(180, 0, 1, 0);
			break;
		}
		case EAST:{
			GL11.glRotatef(90, 0, 1, 0);
			break;
		}
		case WEST:{
			GL11.glRotatef(-90, 0, 1, 0);
			break;
		}
		case DOWN:{
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslated(0, -1, -1);
			break;
		}
		case UP:{
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(0, -1, 1);
			break;
		}
		default:{
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(0, -1, 1);
		}
		}
		((ModelRenderer) this.model.boxList.get(2)).offsetZ = 0.4f-((float) e.engPos)/100f;

		
	}

}

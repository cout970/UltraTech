package api.cout970.UltraTech.microparts;

import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.models.ModelBigCable;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;

public class RenderCableBig {
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ultratech:textures/misc/bigcable.png");
	
	public RenderCableBig(){
		model = new ModelBigCable();
	}

	public void render(MicroCableBig mc, Vector3 pos) {
		if(mc.tile().getWorldObj().getTotalWorldTime()%20 == 0)mc.updateConnections();
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		RenderUtil.bindTexture(tex);
		model.render(0.0625f,pos,mc);
		GL11.glPopMatrix();
	}

	
	
}

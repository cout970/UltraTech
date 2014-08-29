package common.cout970.UltraTech.multipart.client;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;

import common.cout970.UltraTech.client.models.ModelBigCable;
import common.cout970.UltraTech.multipart.MultiPartCable_Big;
import common.cout970.UltraTech.util.render.ConectedTexture;
import common.cout970.UltraTech.util.render.RenderUtil;

public class RenderCableBig{
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ultratech:textures/misc/cable/bigcable.png");
	
	public RenderCableBig(){
		model = new ModelBigCable();
	}

	public void render(MultiPartCable_Big mc, Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		boolean[] b = mc.connections; 
		int i;
		if((i = ConectedTexture.getDir(b)) != -1){
			RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/bigcable_"+i+".png"));
		}else RenderUtil.bindTexture(tex);
		model.render(0.0625f,b);
		GL11.glPopMatrix();
	}


}

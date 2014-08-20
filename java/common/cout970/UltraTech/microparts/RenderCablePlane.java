package common.cout970.UltraTech.microparts;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import common.cout970.UltraTech.client.models.ModelPlaneCable;
import common.cout970.UltraTech.client.models.ModelPlaneCableBase;
import common.cout970.UltraTech.multipart.MultipartUtil;
import common.cout970.UltraTech.util.render.ConectedTexture;
import common.cout970.UltraTech.util.render.RenderUtil;

public class RenderCablePlane{

	private ModelPlaneCable model;
	private ModelPlaneCableBase base;
	
	public RenderCablePlane(){
		model = new ModelPlaneCable();
		base = new ModelPlaneCableBase();
	}
	public void render(MicroCablePlane mc, Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/plane.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		boolean[] a = new boolean[4];
		for(int d = 2; d<6;d++){
			a[d-2] = canConect(mc, ForgeDirection.getOrientation(d));
		}
		model.render(0.0625F,a);
		int i = ConectedTexture.getDir(a);
		if(i == -1)i = 0;
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/planecablebase_"+i+".png"));
		base.render(0.0625f);
		GL11.glPopMatrix();
	}
	private boolean canConect(MicroCablePlane c, ForgeDirection i) {
		boolean a = c.tile().canAddPart(new NormallyOccludedPart(c.boundingBoxes[i.ordinal()]));
		boolean b = false;
		TileEntity tile = UT_Utils.getRelative(c.tile(), i);
		if(MultipartUtil.canConect(c,tile,i))b = true;
		return a && b;
	}

}

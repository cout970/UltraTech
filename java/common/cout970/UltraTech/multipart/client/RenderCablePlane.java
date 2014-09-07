package common.cout970.UltraTech.multipart.client;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;
import common.cout970.UltraTech.client.models.ModelPlaneCable;
import common.cout970.UltraTech.client.models.ModelPlaneCableBase;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Down;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_East;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_North;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_South;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_Up;
import common.cout970.UltraTech.multipart.ribbon.MultiPartCable_Ribbon_West;
import common.cout970.UltraTech.util.render.RenderUtil;

public class RenderCablePlane{

	private ModelPlaneCable model;
	private ModelPlaneCableBase base;
	
	public RenderCablePlane(){
		model = new ModelPlaneCable();
		base = new ModelPlaneCableBase();
	}	
	
	public void render(MultiPartCable_Ribbon c,
			Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		String s = "planedown";
		if(c instanceof MultiPartCable_Ribbon_Up)s = "planeup";
		if(c instanceof MultiPartCable_Ribbon_South)s = "planeup";
		if(c instanceof MultiPartCable_Ribbon_West)s = "planeup";
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/"+s+"/plane.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(c instanceof MultiPartCable_Ribbon_North){ 
			GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0, -1, 1);
		}else if(c instanceof MultiPartCable_Ribbon_South){
			GL11.glRotatef(-90F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(0, -2+(c.pixel*3), 1);
		}else if(c instanceof MultiPartCable_Ribbon_East){
			GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0, -1, 1);
		}else if(c instanceof MultiPartCable_Ribbon_West){
			GL11.glRotatef(90F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0, -2+(c.pixel*3), 1);
		}
		boolean[] a = new boolean[4];
		a[0] = c.connections[2];
		a[1] = c.connections[3];
		a[2] = c.connections[4];
		a[3] = c.connections[5];
		model.render(0.0625F,a);
		int i = RenderCablePlane.getDir(a);
		if(i == -1)i = 0;
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/"+s+"/planecablebase_"+i+".png"));
		base.render(0.0625f);
		GL11.glPopMatrix();
	}

	public static int getDir(boolean[] b) {
		int count = 0;
		int last;
		for(boolean c : b)if(c){count++;}
		if(count != 1)return -1;
		if(b.length == 6)return b[0]? 0 : b[1]? 1 : b[2]? 5: b[3]? 3: b[4]?4: b[5]? 2  : -1;
		return b[0]? 4 : b[1]? 2 : b[2]? 1: b[3]? 3: -1;
	}

}

package common.cout970.UltraTech.lib;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;

public class RenderUtil {

	public static Tessellator t = Tessellator.instance;
	static float u = 0,u1 = 1,v = 0,v1 = 1;
	public static float tick = 0;
	public static float tex = 0;
	
	public static int scaled(int max,int current, int scale){
		return current * scale / max;
	}
	
	public static void cube(double x, double y, double z,float fill){
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GL11.glDisable(GL11.GL_CULL_FACE);
//		GL11.glDisable(GL11.GL_LIGHTING);

		t.setColorRGBA_F(1, 1, 1, 0.9f);
		GL11.glTranslated(0.01, 0.01, 0.01);
		tick += 0.09;
		if(tick > 1){
			tex += 1f/32f;
			tick = 0;
		}
		//texture dinamic
		v1 = tex + 1f/32f;
		u1 = 1f;
		u = 0;
		v = tex;

		if(tex > 1){
			tex = 0;
		}
		
		if(fill > 0.01) drawCube(0.98f, fill-0.02f, 0.98f);

		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	public static void drawCube(float x,float y,float z){
		
		
		t.startDrawingQuads();//-z
		t.addVertexWithUV(0, 0, 0, u, v);
		t.addVertexWithUV(0, y, 0, u1, v);
		t.addVertexWithUV(x, y, 0, u1, v1);
		t.addVertexWithUV(x, 0, 0, u, v1);
		t.draw();

		t.startDrawingQuads();//+z
		t.addVertexWithUV(0, 0, z, u, v);
		t.addVertexWithUV(0, y, z, u1, v);
		t.addVertexWithUV(x, y, z, u1, v1);
		t.addVertexWithUV(x, 0, z, u, v1);
		t.draw();

		t.startDrawingQuads();//-x
		t.addVertexWithUV(0, 0, 0, u, v);
		t.addVertexWithUV(0, y, 0, u1, v);
		t.addVertexWithUV(0, y, z, u1, v1);
		t.addVertexWithUV(0, 0, z, u, v1);
		t.draw();

		t.startDrawingQuads();//+x
		t.addVertexWithUV(x, 0, 0, u, v);
		t.addVertexWithUV(x, y, 0, u1, v);
		t.addVertexWithUV(x, y, z, u1, v1);
		t.addVertexWithUV(x, 0, z, u, v1);
		t.draw();

		t.startDrawingQuads();//-y
		t.addVertexWithUV(0, 0, 0, u, v);
		t.addVertexWithUV(0, 0, z, u1, v);
		t.addVertexWithUV(x, 0, z, u1, v1);
		t.addVertexWithUV(x, 0, 0, u, v1);
		t.draw();

		t.startDrawingQuads();//+y
		t.addVertexWithUV(0, y, 0, u, v);
		t.addVertexWithUV(0, y, z, u1, v);
		t.addVertexWithUV(x, y, z, u1, v1);
		t.addVertexWithUV(x, y, 0, u, v1);
		t.draw();
	}

	
	
}

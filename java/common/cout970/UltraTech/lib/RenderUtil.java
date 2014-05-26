package common.cout970.UltraTech.lib;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.ConectedTexture;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

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
//		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1f);
		
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
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
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
	public static void setGLColorFromInt(int color) {
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	public static void bindTexture(ResourceLocation resourceLocation) {
		if(Minecraft.getMinecraft().renderEngine != null){
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
		}
		
	}

	public static int getConectedTexturesIcon(IBlockAccess BA, int x, int y,
			int z, int side) {
		Block t = BA.getBlock(x, y, z);
		Block[] w = new Block[9];
		if(side == 0 || side == 1){
			int v = 0;
			for(int k=-1;k<2;k++){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y, z+k);
				}
			}
		}else if(side == 2 || side == 3){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y+j, z);
				}
			}
		}else if(side == 4 || side == 5){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int k=-1;k<2;k++){
					w[v++] = BA.getBlock(x, y+j, z+k);
				}
			}
		}
		boolean[] b = new boolean[9];
		for(int v=0;v<9;v++){
			b[v] = Block.isEqualTo(w[v], t);
		}
		return ConectedTexture.getTex(b);
	}
	
	
}

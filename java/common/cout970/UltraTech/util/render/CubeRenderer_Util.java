package common.cout970.UltraTech.util.render;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;

import org.lwjgl.opengl.GL11;

public class CubeRenderer_Util {
	
	IIcon[] icons = new IIcon[6];
	
	float[] dims = new float[3];
	int[] excluded;
	int listNumber;
	public boolean isCompiled;
	
	public  void renderBox(IIcon i,float width,float height, float deep){
		if(i == null)return;
		if(dims[0] != width || dims[1] != height || dims[2] != deep){
			isCompiled = false;
		}
		if(!isCompiled){
			listNumber = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(listNumber, GL11.GL_COMPILE);

		Tessellator t = Tessellator.instance;
		
		for(int g=0;g<6;g++)icons[g] = i;
		
		drawTop			(t,	0,		0,	deep,	width,	height);		//0
		drawBottom		(t,	0,		0,	deep,	width,	0);				//1
		drawNorth		(t,	0,		0,	deep,	height,	0);				//2
		drawSouth		(t,	0,		0,	deep,	height,	width);			//3
		drawEast		(t, 0,		0,  width, 	height, 0);				//4
		drawWest		(t, 0,		0,  width, 	height, deep);			//5
		
		GL11.glEndList();
		isCompiled = true;
		dims[0] = deep;
		dims[1] = height;
		dims[2] = width;
		}
		GL11.glPushMatrix();
		GL11.glCallList(listNumber);
		GL11.glPopMatrix();
	}

	public void renderBox(IIcon[] i, float width,float height, float deep) {
		if(i == null)return;
		if(dims[0] != width || dims[1] != height || dims[2] != deep){
			isCompiled = false;
		}
		if(!isCompiled){
			listNumber = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(listNumber, GL11.GL_COMPILE);

		Tessellator t = Tessellator.instance;
		
		icons = i;
		
		drawTop			(t,	0,		0,	deep,	width,	height);		//0
		drawBottom		(t,	0,		0,	deep,	width,	0);				//1
		drawNorth		(t,	0,		0,	deep,	height,	0);				//2
		drawSouth		(t,	0,		0,	deep,	height,	width);			//3
		drawEast		(t, 0,		0,  width, 	height, 0);				//4
		drawWest		(t, 0,		0,  width, 	height, deep);			//5
		
		GL11.glEndList();
		isCompiled = true;
		dims[0] = deep;
		dims[1] = height;
		dims[2] = width;
		}
		GL11.glPushMatrix();
		GL11.glCallList(listNumber);
		GL11.glPopMatrix();
	}
	
	public void renderBoxWithExcludedParts(IIcon i, float width,float height, float deep, int[] ex) {
		if(i == null)return;
		if(dims[0] != width || dims[1] != height || dims[2] != deep || excluded != ex){
			isCompiled = false;
		}
		if(!isCompiled){
			listNumber = GLAllocation.generateDisplayLists(1);
			GL11.glNewList(listNumber, GL11.GL_COMPILE);

		Tessellator t = Tessellator.instance;
		excluded = ex;
		for(int g=0;g<6;g++)icons[g] = i;
		List<Integer> l = new ArrayList<Integer>();
		for(int h : excluded)l.add(h);
		if(!l.contains(0))drawTop		(t,	0,		0,	deep,	width,	height);		//0
		if(!l.contains(1))drawBottom	(t,	0,		0,	deep,	width,	0);				//1
		if(!l.contains(2))drawNorth		(t,	0,		0,	deep,	height,	0);				//2
		if(!l.contains(3))drawSouth		(t,	0,		0,	deep,	height,	width);			//3
		if(!l.contains(4))drawWest		(t, 0,		0,  width, 	height, deep);			//4
		if(!l.contains(5))drawEast		(t, 0,		0,  width, 	height, 0);				//5
		
		GL11.glEndList();
		isCompiled = true;
		dims[0] = deep;
		dims[1] = height;
		dims[2] = width;
		}
		GL11.glPushMatrix();
		GL11.glCallList(listNumber);
		GL11.glPopMatrix();
	}
	
	private void drawBottom(Tessellator t, float x, float z, float x1, float z1, float y) {
		t.startDrawingQuads();
		t.addVertexWithUV(x1 ,y ,z ,icons[0].getMaxU() ,icons[0].getMinV());
		t.addVertexWithUV(x1 ,y ,z1 ,icons[0].getMinU() ,icons[0].getMinV());
		t.addVertexWithUV(x ,y ,z1 ,icons[0].getMinU() ,icons[0].getMaxV());
		t.addVertexWithUV(x ,y ,z ,icons[0].getMaxU() ,icons[0].getMaxV());
		t.draw();
	}

	private void drawTop(Tessellator t, float x, float z, float x1, float z1, float y) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,icons[1].getMaxU() ,icons[1].getMaxV());
		t.addVertexWithUV(x ,y ,z1 ,icons[1].getMinU() ,icons[1].getMaxV());
		t.addVertexWithUV(x1 ,y ,z1 ,icons[1].getMinU() ,icons[1].getMinV());
		t.addVertexWithUV(x1 ,y ,z ,icons[1].getMaxU() ,icons[1].getMinV());
		t.draw();
	}

	
	private void drawNorth(Tessellator t, float x, float y, float x1, float y1, float z) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,icons[2].getMaxU() ,icons[2].getMaxV());
		t.addVertexWithUV(x ,y1 ,z ,icons[2].getMinU() ,icons[2].getMaxV());
		t.addVertexWithUV(x1 ,y1 ,z ,icons[2].getMinU() ,icons[2].getMinV());
		t.addVertexWithUV(x1 ,y ,z ,icons[2].getMaxU() ,icons[2].getMinV());
		t.draw();
	}
	
	private void drawSouth(Tessellator t, float x, float y, float x1, float y1, float z) {
		t.startDrawingQuads();
		t.addVertexWithUV(x1 ,y ,z ,icons[3].getMaxU() ,icons[3].getMaxV());
		t.addVertexWithUV(x1 ,y1 ,z ,icons[3].getMinU() ,icons[3].getMaxV());
		t.addVertexWithUV(x ,y1 ,z ,icons[3].getMinU() ,icons[3].getMinV());
		t.addVertexWithUV(x ,y ,z ,icons[3].getMaxU() ,icons[3].getMinV());
		t.draw();
	}
	
	private void drawEast(Tessellator t, float z, float y, float z1, float y1, float x) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z1 ,icons[4].getMaxU() ,icons[4].getMaxV());
		t.addVertexWithUV(x ,y1 ,z1 ,icons[4].getMinU() ,icons[4].getMaxV());
		t.addVertexWithUV(x ,y1 ,z ,icons[4].getMinU() ,icons[4].getMinV());
		t.addVertexWithUV(x ,y ,z ,icons[4].getMaxU() ,icons[4].getMinV());
		t.draw();
	}
	
	private void drawWest(Tessellator t, float z, float y, float z1, float y1, float x) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,icons[5].getMaxU() ,icons[5].getMaxV());
		t.addVertexWithUV(x ,y1 ,z ,icons[5].getMinU() ,icons[5].getMaxV());
		t.addVertexWithUV(x ,y1 ,z1 ,icons[5].getMinU() ,icons[5].getMinV());
		t.addVertexWithUV(x ,y ,z1 ,icons[5].getMaxU() ,icons[5].getMinV());
		t.draw();
	}
}

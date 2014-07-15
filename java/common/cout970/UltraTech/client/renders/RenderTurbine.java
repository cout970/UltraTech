package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.client.models.ModelTurbine;
import common.cout970.UltraTech.managers.FluidManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderTurbine extends TileEntitySpecialRenderer{

	private ModelTurbine model;
	private float u;
	private float v;
	private float um;
	private float vm;

	public RenderTurbine(){
		model = new ModelTurbine();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z - 0.5F);
		bindTexture(new ResourceLocation("ultratech:textures/misc/turbine.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
		
		SteamTurbineEntity te = (SteamTurbineEntity) tile;
		setRotation(te.facing);
		model.renderStatic(0.0625F);
		
		//gire
		boolean w = te.work;
		long dif = te.getWorldObj().getTotalWorldTime() - te.updateTime;
		float d = ((float)getDelta(te))/2;
		int time = 200;
		float max = 1.3f;
		float a = 0;
		if(te.angle> 360)te.angle = 0;
		if (w) {
			if(dif < time && dif > 0){//starting
				a+= max*(((float)dif)/time);
			}else if(dif >= time){//runing
				a+= max;
			}
		}else{
			if (dif < time) {//stoping
				a+= max * (1 - ((float)dif)/time);
			}
		}
		if(te.speed < a)te.speed+=0.001f;
		else if(te.speed > a)te.speed -= 0.001f;
		if(te.speed > 0)te.angle   += te.speed*d;
		model.renderDynamic(0.0625F, (float) Math.toRadians(te.angle));

		//fluid
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		bindTexture(TextureMap.locationBlocksTexture);
		Tessellator t = Tessellator.instance;
		IIcon i = FluidManager.Steam.getIcon();
		float k = 14.99f*(1f/16f);
		u = i.getInterpolatedU(16);
		v = i.getInterpolatedV(16);
		um = i.getInterpolatedU(0);
		vm = i.getInterpolatedV(0);
		float percent = ((float)te.tank.getFluidAmount())/te.tank.getCapacity();
		GL11.glColor4f(1, 1, 1, percent);
		float h = 1;
		GL11.glTranslatef(-0.5f, +0.5f, -0.5f);
		drawNorth(t,k,0,1f-k,h,k);
		drawSouth(t,k,0,1f-k,h,1f-k);
		drawEast(t, 1f-k, 0, k, h, 1f-k);
		drawWest(t, 1f-k, 0, k, h, k);

		GL11.glPopMatrix();
		
	}
	
	public long getDelta(SteamTurbineEntity te){
		long aux = System.currentTimeMillis();
		long delta = System.currentTimeMillis()-te.oldTime;
		te.oldTime = aux;
		return delta;
	}
	
	private void setRotation(ForgeDirection dir) {
		//rotation
		switch(dir){
		case NORTH:{
			GL11.glRotatef(0, 0, 1, 0);
			break;
		}
		case SOUTH:{
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glTranslated(0, -2, 0);
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
	}
	
	public void drawNorth(Tessellator t, float x, float y, float x1, float y1, float z) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,um ,vm);
		t.addVertexWithUV(x ,y1 ,z ,u ,vm);
		t.addVertexWithUV(x1 ,y1 ,z ,u ,v);
		t.addVertexWithUV(x1 ,y ,z ,um ,v);
		t.draw();
	}
	
	public void drawSouth(Tessellator t, float x, float y, float x1, float y1, float z) {
		t.startDrawingQuads();
		t.addVertexWithUV(x1 ,y ,z ,um ,v);
		t.addVertexWithUV(x1 ,y1 ,z ,u ,v);
		t.addVertexWithUV(x ,y1 ,z ,u ,vm);
		t.addVertexWithUV(x ,y ,z ,um ,vm);
		t.draw();
	}
	
	public void drawEast(Tessellator t, float z, float y, float z1, float y1, float x) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z1 ,um ,vm);
		t.addVertexWithUV(x ,y1 ,z1 ,u ,vm);
		t.addVertexWithUV(x ,y1 ,z ,u ,v);
		t.addVertexWithUV(x ,y ,z ,um ,v);
		t.draw();
	}
	
	public void drawWest(Tessellator t, float z, float y, float z1, float y1, float x) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,um ,v);
		t.addVertexWithUV(x ,y1 ,z ,u ,v);
		t.addVertexWithUV(x ,y1 ,z1 ,u ,vm);
		t.addVertexWithUV(x ,y ,z1 ,um ,vm);
		t.draw();
	}
}

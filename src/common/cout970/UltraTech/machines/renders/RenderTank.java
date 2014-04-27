package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier2.TankEntity;
import common.cout970.UltraTech.machines.models.ModelFluidTank;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;

public class RenderTank  extends TileEntitySpecialRenderer{

	private ModelFluidTank model;
	public float k = 0.063f;
	float um;
	float vm;
	float u;
	float v;

	public RenderTank() {
		this.model = new ModelFluidTank();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluidtank.png"));
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		renderFluid((TankEntity)te);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	private void renderFluid(TankEntity te) {
		if(te.storage == null || te.storage.getFluid() == null)return;
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		Tessellator t = Tessellator.instance;
		Icon i = te.storage.getFluid().getFluid().getIcon();
		u = i.getInterpolatedU(16);
		v = i.getInterpolatedV(16);
		um = i.getInterpolatedU(0);
		vm = i.getInterpolatedV(0);
		float h = ((float) te.storage.getFluidAmount())/((float) te.storage.getCapacity());
		GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
		drawNorth(t,k,0,1f-k,h,k);
		drawSouth(t,k,0,1f-k,h,1f-k);
		drawTop(t,k,k,1f-k,1f-k,h-0.001f);
		drawBottom(t,k,k,1f-k,1f-k,0.001f);
		drawEast(t, 1f-k, 0, k, h, 1f-k);
		drawWest(t, 1f-k, 0, k, h, k);
	}

	public void drawTop(Tessellator t, float x, float z, float x1, float z1, float y) {
		t.startDrawingQuads();
		t.addVertexWithUV(x ,y ,z ,um ,vm);
		t.addVertexWithUV(x ,y ,z1 ,u ,vm);
		t.addVertexWithUV(x1 ,y ,z1 ,u ,v);
		t.addVertexWithUV(x1 ,y ,z ,um ,v);
		t.draw();
	}
	
	public void drawBottom(Tessellator t, float x, float z, float x1, float z1, float y) {
		t.startDrawingQuads();
		t.addVertexWithUV(x1 ,y ,z ,um ,v);
		t.addVertexWithUV(x1 ,y ,z1 ,u ,v);
		t.addVertexWithUV(x ,y ,z1 ,u ,vm);
		t.addVertexWithUV(x ,y ,z ,um ,vm);
		t.draw();
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

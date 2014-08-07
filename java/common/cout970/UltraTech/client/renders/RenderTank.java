package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.fluid.TankEntity;
import common.cout970.UltraTech.client.models.ModelFluidTank;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderTank  extends TileEntitySpecialRenderer{

	private ModelFluidTank model;
	public float k = 0.063f;

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
		bindTexture(ResourcesLocations.TANK);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		renderFluid((TankEntity)te);
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}

	private void renderFluid(TankEntity te) {
		if(te.getTank() == null || te.getTank().getFluid() == null)return;
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		Tessellator t = Tessellator.instance;
		IIcon i = te.getTank().getFluid().getFluid().getIcon();
		if(i == null)return;
		float h = ((float) te.getTank().getFluidAmount())/((float) te.getTank().getCapacity());
		if(h<0.01)h = 0;
		if(h > 0){
		GL11.glTranslatef(-0.5F, -1.5F, -0.5F);
		GL11.glTranslatef(k, 0.01f, k);
		te.FR.renderBox(i, 1f-k*2, h-0.02f, 1f-k*2);
		}
	}

}

package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.PumpEntity;
import common.cout970.UltraTech.client.models.ModelBoiler;
import common.cout970.UltraTech.client.models.ModelPump;
import common.cout970.UltraTech.client.models.ModelPumpJack;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.misc.IconFactory;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class RenderPump extends TileEntitySpecialRenderer{

	private ModelPump model;
	private CubeRenderer_Util FR = new CubeRenderer_Util();
//	public ModelPumpJack model;

	public RenderPump(){
		super();
		model = new ModelPump();
//		model = new ModelPumpJack();
	}

	@Override
	public void renderTileEntityAt(TileEntity e, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
//		PumpEntity te = (PumpEntity) e;		
//		bindTexture(new ResourceLocation("ultratech:textures/misc/pumpjack.png"));
//
//		GL11.glTranslatef((float) x + 0.5F, (float) y + 1F, (float) z + 0.5F);
//		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
//		GL11.glScalef(4, 4, 4);
//		GL11.glDisable(GL11.GL_LIGHTING);
//		model.render(0.0625f/2, te.p);
//		te.p += 1.5f;
//		if(te.p > 1000){
//			te.p = 0;
//		}
//		GL11.glEnable(GL11.GL_LIGHTING);
		
		bindTexture(ResourcesLocations.PUMP);
		GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		//aninmation
		PumpEntity te = (PumpEntity) e;
		double dif = System.currentTimeMillis()-te.animationTime;
		te.animationTime = System.currentTimeMillis();
		if(dif > 50)dif = 0;
		dif /= 10;
		if(te.up)te.p += dif;
		else te.p -= dif;
		if(te.p >= 100) te.up = false;
		else if(te.p <= 0) te.up = true;
		GL11.glPushMatrix();
		GL11.glTranslatef(0, -0.0075f*te.p, 0);
		model.renderMotor(0.0625F);
		GL11.glPopMatrix();
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);		
		GL11.glTranslatef(-0.5F+0.01f, -1.5F+0.01f, -0.5F+0.01f);
		GL11.glEnable(GL11.GL_CULL_FACE);
		//render fluid
		FluidStack fl = te.getTank().getFluid();
		if(fl != null && fl.getFluid().getStillIcon() != null){
			float height = ((float)fl.amount)/te.getTank().getCapacity();
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glTranslatef(0f,1f-height,0f);
			FR.renderBox(fl.getFluid().getStillIcon(), 1-0.02f, height-0.02f, 1-0.02f);
		}
		GL11.glPopAttrib();
		GL11.glPopMatrix();
	}
}

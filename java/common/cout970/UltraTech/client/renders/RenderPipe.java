package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.client.models.ModelCenterPipe;
import common.cout970.UltraTech.client.models.ModelPipe;
import common.cout970.UltraTech.client.models.ModelPipeBase;
import common.cout970.UltraTech.client.models.ModelPipeIn;
import common.cout970.UltraTech.client.models.ModelPump;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.fluids.TankConection;
import common.cout970.UltraTech.util.fluids.UT_Tank;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidHandler;

public class RenderPipe extends TileEntitySpecialRenderer{

	private ModelPipe model;
	private ModelPipeIn in;
	private ModelCenterPipe base;
	private CubeRenderer_Util FR = new CubeRenderer_Util();

	public RenderPipe(){
		super();
		model = new ModelPipe();
		in = new ModelPipeIn();
		base = new ModelCenterPipe();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		boolean[] a = {true,true,true,true,true,true};
		boolean[] b = new boolean[6];
		CopperPipeEntity p = (CopperPipeEntity) te;
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			TileEntity g = UT_Utils.getRelative(te, d);
			if(g instanceof IFluidHandler && !(g instanceof IFluidTransport))b[d.getOpposite().ordinal()] = true;
			if(g instanceof IFluidTransport || g instanceof IFluidHandler)a[d.ordinal()] = false;
		}
		int c = 0;
		for(boolean a1 : a)if(!a1)c++;
		boolean[] t = {true,true,true,true,true,true};
		
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/base.png"));
		if(c != 2)	base.render(0.0625f, t);//render centeter
		else 		base.render(0.0625f, a);//render centeter
		
//		if(p.getNetwork() != null){
//			UT_Tank tk = p.getNetwork().getBuffer();
//			if(tk.getFluidAmount() > 0){
//				GL11.glPushMatrix();
//				float percent = ((float)tk.getFluidAmount())/tk.getCapacity();
//				float k = 1/16f;
//				int[] ar = {0,1,2,3,4,5};
//				for(int arr=0;arr<6;arr++){if(a[arr])ar[arr] = -1;}
//				GL11.glDisable(GL11.GL_LIGHTING);
//				bindTexture(TextureMap.locationBlocksTexture);
//				GL11.glTranslatef(-2*k,14*k, -2*k);
//				GL11.glTranslatef(0f, 4*k*(1f-percent), 0f);
//				IIcon i = tk.getFluid().getFluid().getIcon();
//				FR.renderBoxWithExcludedParts(i, k*4, k*4*percent, k*4,ar);//base
//				if(!a[0]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0, 4*k, 0);
//					FR.renderBoxWithExcludedParts(i, k*4, k*6, k*4, new int[]{0,1});
//					GL11.glPopMatrix();}
//				if(!a[1]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0, -6*k, 0);
//					FR.renderBoxWithExcludedParts(i, k*4, k*6, k*4, new int[]{0,1});
//					GL11.glPopMatrix();
//				}
//				if(!a[2]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0, 0, -6*k);
//					FR.renderBoxWithExcludedParts(i, k*6, k*4*percent, k*4, new int[]{2,3});
//					GL11.glPopMatrix();
//				}
//				if(!a[3]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(0, 0, 4*k);
//					FR.renderBoxWithExcludedParts(i, k*6, k*4*percent, k*4, new int[]{2,3});
//					GL11.glPopMatrix();
//				}
//				if(!a[4]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(4*k, 0, 0);
//					FR.renderBoxWithExcludedParts(i, k*4, k*4*percent, k*6, new int[]{4,5});
//					GL11.glPopMatrix();
//				}
//				if(!a[5]){
//					GL11.glPushMatrix();
//					GL11.glTranslatef(-6*k, 0, 0);
//					FR.renderBoxWithExcludedParts(i, k*4, k*4*percent, k*6, new int[]{4,5});
//					GL11.glPopMatrix();
//				}
//				GL11.glEnable(GL11.GL_LIGHTING);
//				GL11.glPopMatrix();
//			}
//		}
		//render pipe conections
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		//
		//render in
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipein.png"));
		in.render(1/16f, a);

		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebase.png"));
		model.render(0.0625f,a);
		if(!p.mode)	bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/conduit.png"));
		else 		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/conduit2.png"));
		model.render(0.0625f,b,true);//render conections
		GL11.glPopMatrix();
	}

}

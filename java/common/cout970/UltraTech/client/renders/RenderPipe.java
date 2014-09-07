package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.client.models.ModelCenterPipe;
import common.cout970.UltraTech.client.models.ModelPipe;
import common.cout970.UltraTech.client.models.ModelPipeBase;
import common.cout970.UltraTech.client.models.ModelPipeIn;
import common.cout970.UltraTech.client.models.ModelPump;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.multipart.remplace.CopperPipeEntity;
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
		
		bindTexture(ResourcesLocations.PIPE_BASE);
		if(c != 2)	base.render(0.0625f, t);//render centeter
		else 		base.render(0.0625f, a);//render centeter
		
		//render pipe conections
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		//
		//render in
		bindTexture(ResourcesLocations.PIPE_IN);
		in.render(1/16f, a);

		bindTexture(ResourcesLocations.PIPE_BASE_2);
		model.render(0.0625f,a);
		if(!p.mode)	bindTexture(ResourcesLocations.PIPE_CONECTION1);
		else 		bindTexture(ResourcesLocations.PIPE_CONECTION2);
		model.render(0.0625f,b,true);//render conections
		GL11.glPopMatrix();
	}

}

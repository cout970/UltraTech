package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.fluids.IFluidTransport;
import api.cout970.UltraTech.fluids.TankConection;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import common.cout970.UltraTech.models.ModelCenterPipe;
import common.cout970.UltraTech.models.ModelPipe;
import common.cout970.UltraTech.models.ModelPipeBase;
import common.cout970.UltraTech.models.ModelPipeIn;
import common.cout970.UltraTech.models.ModelPump;
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
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebase.png"));
		base.render(0.0625f, a);//render centeter
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebase.png"));
		model.render(0.0625f,a);//render pipe conections
		if(!p.mode)bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/conduit.png"));
		else bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/conduit2.png"));
		model.render(0.0625f,b,true);//render conections
		//render in
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebase.png"));
		GL11.glColor3f(0.7f, 0.7f, 0.7f);
		in.render(0.0625F,a);
		GL11.glPopMatrix();
	}

}

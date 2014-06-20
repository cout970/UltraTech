package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.fluids.TankConection;
import common.cout970.UltraTech.TileEntities.fluid.CopperPipeEntity;
import common.cout970.UltraTech.models.ModelCenterPipe;
import common.cout970.UltraTech.models.ModelPipe;
import common.cout970.UltraTech.models.ModelPipeBase;
import common.cout970.UltraTech.models.ModelPipeIn;
import common.cout970.UltraTech.models.ModelPump;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

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
		for(TankConection t : p.connections){
			b[t.side.ordinal()] = true;
		}
		for(ForgeDirection t : p.pipes){
			a[t.ordinal()] = false;
		}
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipebase.png"));
		base.render(0.0625f, a);
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/pipe.png"));
		model.render(0.0625f,a, b);
		GL11.glPopMatrix();
	}

}

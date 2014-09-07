package common.cout970.UltraTech.multipart.client;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.client.models.ModelCenterPipe;
import common.cout970.UltraTech.client.models.ModelPipe;
import common.cout970.UltraTech.client.models.ModelPipeIn;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.multipart.MultiPartPipe_Copper;
import common.cout970.UltraTech.multipart.MultiPartPipe_Copper.ConnectionMode;
import common.cout970.UltraTech.multipart.remplace.CopperPipeEntity;
import common.cout970.UltraTech.util.fluids.FluidUtils;
import common.cout970.UltraTech.util.fluids.IFluidTransport;
import common.cout970.UltraTech.util.render.RenderUtil;
import codechicken.lib.vec.Vector3;

public class RenderPipeCopper {

	private ModelPipe model;
	private ModelPipeIn in;
	private ModelCenterPipe base;

	public RenderPipeCopper(){
		super();
		model = new ModelPipe();
		in = new ModelPipeIn();
		base = new ModelCenterPipe();
	}
	public void render(MultiPartPipe_Copper mc, Vector3 pos) {
		GL11.glPushMatrix();

		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		boolean[] a = {true,true,true,true,true,true};
		for(int h=0;h<6;h++){
			a[h] = !mc.connections[h];
			if(mc.side[h] == null || mc.side[h] == ConnectionMode.NOTHING)
				a[h] = true;
		}
		
		int c = 0;
		for(boolean a1 : a)if(!a1)c++;
		boolean[] t = {true,true,true,true,true,true};
		
		RenderUtil.bindTexture(ResourcesLocations.PIPE_BASE);
		if(c != 2)	base.render(0.0625f, t);//render centeter
		else 		base.render(0.0625f, a);//render centeter
		
		//render pipe connections
		for(int j=0;j<a.length;j++)a[j] = !a[j];
		//
		//render in
		RenderUtil.bindTexture(ResourcesLocations.PIPE_IN);
		in.render(1/16f, a);

		RenderUtil.bindTexture(ResourcesLocations.PIPE_BASE_2);
		model.render(0.0625f,a);
		//render connectors
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			if(mc.tanks.get(d) != null){
				boolean[] b2 = new boolean[6];
				b2[d.getOpposite().ordinal()] = true;
				if(mc.side[d.ordinal()] == ConnectionMode.OUTPUT)RenderUtil.bindTexture(ResourcesLocations.PIPE_CONECTION1);
				else RenderUtil.bindTexture(ResourcesLocations.PIPE_CONECTION2);
				if(mc.side[d.ordinal()] != ConnectionMode.NOTHING)
					model.render(0.0625f,b2,true);//render connections
			}
		}
		GL11.glPopMatrix();
	}

}

package common.cout970.UltraTech.microparts;

import java.util.Arrays;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.PowerUtils;
import ultratech.api.power.multipart.MicroPartUtil;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.util.render.RenderUtil;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import codechicken.multipart.NormallyOccludedPart;
import codechicken.multipart.TMultiPart;
import cpw.mods.fml.common.Optional;

public class RenderCableBig{
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ultratech:textures/misc/cable/bigcable.png");
	
	public RenderCableBig(){
		model = new ModelBigCable();
	}

	@Optional.Method(modid = "ForgeMultipart")
	public void render(MicroCableBig mc, Vector3 pos) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		boolean[] b = new boolean[6];
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			b[d.ordinal()] = canConnectTo(mc, d);
		}
		int i;
		if((i = RenderUtil.getDir(b)) != -1){
			RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/bigcable_"+i+".png"));
			
		}else RenderUtil.bindTexture(tex);
		model.render(0.0625f,b);
		GL11.glPopMatrix();
	}
	
	private boolean canConnectTo(MicroCableBig f, ForgeDirection o) {
		boolean a = f.tile().canAddPart(new NormallyOccludedPart(f.boundingBoxes[o.ordinal()]));
		boolean b = false;
		TileEntity tile = UT_Utils.getRelative(f.tile(), o);
		if(MicroPartUtil.canConect(f,tile,o))b = true;
		if(o == ForgeDirection.DOWN){
			for(TMultiPart t : f.tile().jPartList())if(t instanceof MicroCablePlane){b = true; a = true;}
		}
		return a && b;
	}


}

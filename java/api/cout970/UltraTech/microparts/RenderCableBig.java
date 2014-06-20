package api.cout970.UltraTech.microparts;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.Wpower.PowerUtils;
import common.cout970.UltraTech.lib.RenderUtil;
import codechicken.lib.render.RenderUtils;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Vector3;
import cpw.mods.fml.common.Optional;

public class RenderCableBig extends TileEntitySpecialRenderer{
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ultratech:textures/misc/bigcable.png");
	
	public RenderCableBig(){
		model = new ModelBigCable();
	}

	@Optional.Method(modid = "ForgeMultipart")
	public void render(MicroCableBig mc, Vector3 pos) {
		if(mc.tile().getWorldObj().getTotalWorldTime()%20 == 0)mc.updateConnections();
		GL11.glPushMatrix();
		GL11.glTranslated(pos.x, pos.y, pos.z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		RenderUtil.bindTexture(tex);
		model.render(0.0625f,pos,mc);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y,
			double z, float f5) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		RenderUtil.bindTexture(tex);
		boolean[] b = new boolean[6];
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			b[d.ordinal()] = PowerUtils.canConnectTo(t, PowerUtils.getRelative(t, d));
		}
		model.render(0.0625f,b);
		GL11.glPopMatrix();
	}
	
}

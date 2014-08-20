package common.cout970.UltraTech.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.PowerUtils;
import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.client.models.ModelBigCable;
import common.cout970.UltraTech.util.render.ConectedTexture;
import common.cout970.UltraTech.util.render.RenderUtil;
import cpw.mods.fml.common.Optional;

public class RenderCableWithoutMP extends TileEntitySpecialRenderer{
	
	private ModelBigCable model;
	private ResourceLocation tex = new ResourceLocation("ultratech:textures/misc/cable/bigcable.png");
	
	public RenderCableWithoutMP(){
		model = new ModelBigCable();
	}

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y,
			double z, float f5) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0.5f,-0.5f,0.5);
		boolean[] b = new boolean[6];
		for(ForgeDirection d : ForgeDirection.VALID_DIRECTIONS){
			b[d.ordinal()] = PowerUtils.canConnectTo(t, PowerUtils.getRelative(t, d));
		}
		int i;
		if((i = ConectedTexture.getDir(b)) != -1){
			RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/cable/bigcable_"+i+".png"));
			
		}else RenderUtil.bindTexture(tex);
		model.render(0.0625f,b);
		GL11.glPopMatrix();
	}
}
package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier1.AluminumPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.CopperPipeEntity;
import common.cout970.UltraTech.TileEntities.Tier1.LeadPipeEntity;
import common.cout970.UltraTech.machines.models.ModelFluidPipe;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderFluidPipe extends TileEntitySpecialRenderer{
	
	private ModelFluidPipe model;
	
	public RenderFluidPipe(){
		model = new ModelFluidPipe();
	}
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		if(te instanceof AluminumPipeEntity)bindTexture(new ResourceLocation("ultratech:textures/misc/aluminumpipe.png"));
		if(te instanceof CopperPipeEntity)bindTexture(new ResourceLocation("ultratech:textures/misc/copperpipe.png"));
		if(te instanceof LeadPipeEntity)bindTexture(new ResourceLocation("ultratech:textures/misc/leadpipe.png"));

		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderModel(0.0625F, te.worldObj, te.xCoord, te.yCoord, te.zCoord);
		GL11.glPopMatrix();
	}

}

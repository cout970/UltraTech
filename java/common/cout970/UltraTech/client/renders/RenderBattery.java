package common.cout970.UltraTech.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.StorageTier1;
import common.cout970.UltraTech.TileEntities.electric.StorageTier2;
import common.cout970.UltraTech.TileEntities.electric.StorageTier3;
import common.cout970.UltraTech.TileEntities.electric.StorageTier4;
import common.cout970.UltraTech.client.models.ModelBattery;

public class RenderBattery extends TileEntitySpecialRenderer{

	private ModelBattery model;
	
	public RenderBattery(){
		super();
		model = new ModelBattery();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		if(te instanceof StorageTier1)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery0.png"));
		if(te instanceof StorageTier2)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery1.png"));
		if(te instanceof StorageTier3)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery2.png"));
		if(te instanceof StorageTier4)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery3.png"));
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}

}

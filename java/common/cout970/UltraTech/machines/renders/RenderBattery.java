package common.cout970.UltraTech.machines.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.Tier1.StorageTier1;
import common.cout970.UltraTech.TileEntities.Tier2.StorageTier2;
import common.cout970.UltraTech.TileEntities.Tier3.StorageTier3;
import common.cout970.UltraTech.models.ModelBattery;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderBattery extends TileEntitySpecialRenderer{

	private ModelBattery model;
//	private ModelCable cable;
	
	public RenderBattery(){
		super();
		model = new ModelBattery();
//		cable = new ModelCable();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		if(te instanceof StorageTier1)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery0.png"));
		if(te instanceof StorageTier2)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery1.png"));
		if(te instanceof StorageTier3)bindTexture(new ResourceLocation("ultratech:textures/misc/battery/battery2.png"));
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		
		if(te != null){
		bindTexture(new ResourceLocation("ultratech:textures/misc/cable/cable.png"));
//		cable.renderModel(0.0625f, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		}
		GL11.glPopMatrix();
	}

}

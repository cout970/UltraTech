package common.cout970.UltraTech.microparts;

import org.lwjgl.opengl.GL11;

import codechicken.lib.vec.Vector3;
import common.cout970.UltraTech.lib.RenderUtil;
import common.cout970.UltraTech.models.ModelCable;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderCable{

	private ModelCable model;
	
	public RenderCable(){
		model = new ModelCable();
	}
	public void render(MicroCable mc, Vector3 pos) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) pos.x + 0.5F, (float) pos.y + 1.5F, (float) pos.z + 0.5F);
		RenderUtil.bindTexture(new ResourceLocation("ultratech:textures/misc/modelcable.png"));
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(model == null)model = new ModelCable();
		model.render(mc, pos, 0.0625F);
		GL11.glPopMatrix();
	}

}

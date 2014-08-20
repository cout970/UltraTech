package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.client.models.ModelBoiler;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.misc.IconFactory;
import common.cout970.UltraTech.util.render.CubeRenderer_Util;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public class RenderBoiler extends TileEntitySpecialRenderer{

	private ModelBoiler model;
	
	public RenderBoiler(){
		super();
		model = new ModelBoiler();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		bindTexture(ResourcesLocations.BOILER);
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

}

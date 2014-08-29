package common.cout970.UltraTech.client.renders;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;


import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import ultratech.api.power.interfaces.IPowerConductor;
import common.cout970.UltraTech.TileEntities.electric.tiers.SolarPanelEntity_T2;
import common.cout970.UltraTech.client.models.ModelSolarPanel;
import common.cout970.UltraTech.client.textures.ResourcesLocations;


public class RenderSolarPanel extends TileEntitySpecialRenderer{

	private ModelSolarPanel model;

	
	public RenderSolarPanel() {
		this.model = new ModelSolarPanel();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.75F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		if(te instanceof SolarPanelEntity_T2){
			bindTexture(ResourcesLocations.SOLAR_PANEL_T2);
		}else{
			bindTexture(ResourcesLocations.SOLAR_PANEL_T1);
		}
		
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
}

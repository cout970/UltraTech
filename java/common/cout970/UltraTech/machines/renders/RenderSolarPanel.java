package common.cout970.UltraTech.machines.renders;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;


import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import api.cout970.UltraTech.FTpower.IPowerConductor;
import common.cout970.UltraTech.models.ModelCable;
import common.cout970.UltraTech.models.ModelSolarPanel;


public class RenderSolarPanel extends TileEntitySpecialRenderer{

	private ModelSolarPanel model;
	private ResourceLocation texture = new ResourceLocation("ultratech:textures/misc/solarpanel.png");
	private ResourceLocation cabtex = new ResourceLocation("ultratech:textures/misc/cable/cable.png");
	private ModelCable cable;
	
	public RenderSolarPanel() {
		this.model = new ModelSolarPanel();
		this.cable = new ModelCable();
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.125F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		bindTexture(texture);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if(te.getWorldObj().getTileEntity(te.xCoord, te.yCoord-1, te.zCoord) instanceof IPowerConductor){
			ForgeDirection[] a = ((IPowerConductor)te.getWorldObj().getTileEntity(te.xCoord, te.yCoord-1, te.zCoord)).getPower().getConnectableSides();
			boolean c = false;
			for(ForgeDirection b: a){
				if(b == ForgeDirection.UP)c = true;
			}
			if(c){
				bindTexture(cabtex);
				GL11.glTranslatef(0f,-0.37f,0f);
				((ModelRenderer) cable.boxList.get(6)).render(0.0625F);
			}
		}

		GL11.glPopMatrix();
	}
}

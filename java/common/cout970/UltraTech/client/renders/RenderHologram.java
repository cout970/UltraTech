package common.cout970.UltraTech.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.utility.TileEntityHologramEmiter;

public class RenderHologram extends TileEntitySpecialRenderer{


	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		
		TileEntityHologramEmiter h = (TileEntityHologramEmiter) te;
		if(h.rend == null)return;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(h.mX, h.mY, h.mZ);
		applyRotation(h.facing);
		this.bindTexture(h.rend.getResourceLocation());
		if(h.rend.enableBlend()){
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_LIGHTING);
		h.rend.setColor();
		h.rend.setRotation();
		h.rend.render(0.0625f);
		
		if(h.rend.enableBlend())GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();	
	}

	private void applyRotation(ForgeDirection facing) {
		if(facing == ForgeDirection.NORTH)return;
		if(facing == ForgeDirection.SOUTH){
			GL11.glRotatef(180, 0, 1, 0);
			GL11.glTranslatef(-1, 0, -1);
		}
		if(facing == ForgeDirection.EAST){
			GL11.glRotatef(-90, 0, 1, 0);
			GL11.glTranslatef(0, 0, -1);
		}
		if(facing == ForgeDirection.WEST){
			GL11.glRotatef(90, 0, 1, 0);
			GL11.glTranslatef(-1, 0, 0);
		}
		if(facing == ForgeDirection.DOWN){
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslatef(0, -1, 0);
		}
		if(facing == ForgeDirection.UP){
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(0, 0, -1);
		}
	}
}

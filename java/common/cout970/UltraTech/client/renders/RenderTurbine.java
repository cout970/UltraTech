package common.cout970.UltraTech.client.renders;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.SteamTurbineEntity;
import common.cout970.UltraTech.client.models.ModelSteamTurbine;
import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.util.LogHelper;

public class RenderTurbine extends TileEntitySpecialRenderer{

	private ModelSteamTurbine model;

	public RenderTurbine(){
		model = new ModelSteamTurbine();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y,
			double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z - 0.5F);
		bindTexture(ResourcesLocations.STEAM_TURBINE);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
		SteamTurbineEntity te = (SteamTurbineEntity) tile;
		setRotation(te.facing);
		model.render(0.0625F);
		float delta = getDelta(te);
		if(te.on){
			te.angle += delta*0.5;
			if(te.angle >= 90)te.angle = te.angle-90;
			if(te.angle > 360)te.angle = 0;
			GL11.glRotatef(te.angle, 0, 1, 0);
		}
		model.renderRotor(0.0625F);
		GL11.glPopMatrix();
	}


	
	public long getDelta(SteamTurbineEntity te){
		long aux = System.currentTimeMillis();
		long delta = System.currentTimeMillis()-te.oldTime;
		te.oldTime = aux;
		return delta;
	}
	
	private void setRotation(ForgeDirection dir) {
		//rotation
		switch(dir){
		case NORTH:{
			GL11.glRotatef(0, 0, 1, 0);
			break;
		}
		case SOUTH:{
			GL11.glRotatef(180, 0, 0, 1);
			GL11.glTranslated(0, -2, 0);
			break;
		}
		case EAST:{
			GL11.glRotatef(-90, 0, 0, 1);
			GL11.glTranslated(-1, -1, 0);
			break;
		}
		case WEST:{
			GL11.glRotatef(90, 0, 0, 1);
			GL11.glTranslated(1, -1, 0);
			break;
		}
		case DOWN:{
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslated(0, -1, -1);
			break;
		}
		case UP:{
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(0, -1, 1);
			break;
		}
		default:{
			GL11.glRotatef(-90, 1, 0, 0);
			GL11.glTranslated(0, -1, 1);
		}
		}
	}
}

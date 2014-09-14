package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.TileEntityWindMill;
import common.cout970.UltraTech.client.models.ModelWindMill;
import common.cout970.UltraTech.client.textures.ModelResources;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderWindMill extends TileEntitySpecialRenderer{

	public ModelWindMill model;
	
	public RenderWindMill(){
		this.model = new ModelWindMill();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float scale) {
		if(te == null)return;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		bindTexture(ModelResources.WIND_MILL);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		((TileEntityWindMill)te).fan += ((TileEntityWindMill)te).speed*getDelta(((TileEntityWindMill)te))/10;
		if(((TileEntityWindMill)te).fan >=360){
			((TileEntityWindMill)te).fan = 0;
		}
		float d = ((TileEntityWindMill)te).fan;
		((ModelRenderer)model.boxList.get(7)).rotateAngleZ = (float) Math.toRadians(120+d);
		((ModelRenderer)model.boxList.get(6)).rotateAngleZ = (float) Math.toRadians(0+d);
		((ModelRenderer)model.boxList.get(5)).rotateAngleZ = (float) Math.toRadians(-120+d);
		((ModelRenderer)model.boxList.get(4)).rotateAngleZ = (float) Math.toRadians(0+d);
		((ModelRenderer)model.boxList.get(3)).rotateAngleZ = (float) Math.toRadians(0+d);
		//NEW
		((ModelRenderer)model.boxList.get(8)).rotateAngleZ = (float) Math.toRadians(60+d);
		((ModelRenderer)model.boxList.get(9)).rotateAngleZ = (float) Math.toRadians(180+d);
		((ModelRenderer)model.boxList.get(10)).rotateAngleZ = (float) Math.toRadians(-60+d);
		if(((TileEntityWindMill)te).facing == ForgeDirection.NORTH)GL11.glRotatef(180, 0, 1, 0);
		if(((TileEntityWindMill)te).facing == ForgeDirection.WEST)GL11.glRotatef(90, 0, 1, 0);
		if(((TileEntityWindMill)te).facing == ForgeDirection.EAST)GL11.glRotatef(-90, 0, 1, 0);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}
	
	public long getDelta(TileEntityWindMill te){
		long aux = System.currentTimeMillis();
		long delta = System.currentTimeMillis()-te.oldTime;
		te.oldTime = aux;
		return delta;
	}

}

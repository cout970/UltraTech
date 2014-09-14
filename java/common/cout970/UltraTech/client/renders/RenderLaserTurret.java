package common.cout970.UltraTech.client.renders;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier1;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier2;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier3;
import common.cout970.UltraTech.TileEntities.electric.TileEntityBatteryTier4;
import common.cout970.UltraTech.TileEntities.utility.TileEntityLaserTurret;
import common.cout970.UltraTech.client.models.ModelBattery;
import common.cout970.UltraTech.client.models.ModelBatteryIO;
import common.cout970.UltraTech.client.models.ModelLaserTurret;
import common.cout970.UltraTech.client.textures.ModelResources;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.VecUT;
import common.cout970.UltraTech.util.power.IBatteryBlock;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class RenderLaserTurret extends TileEntitySpecialRenderer{
	
	private ModelLaserTurret model;
	
	public RenderLaserTurret(){
		super();
		model = new ModelLaserTurret();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y,
			double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		TileEntityLaserTurret lt = (TileEntityLaserTurret) te;
		bindTexture(ModelResources.LASER_TURRET);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if(lt.target != null){
			VecUT pos = VecUT.sumeVec(lt.target, VecUT.inverseVec(new VecUT(lt.xCoord+0.5, lt.yCoord+0.5, lt.zCoord+0.5)));
			lt.curAngleY = (float) (180f-Math.toDegrees(Math.atan2(pos.x, pos.z)));
		}
		GL11.glRotatef(lt.curAngleY, 0, 1, 0);
		model.renderDynamic(0.0625F);
		GL11.glPopMatrix();
	}

}

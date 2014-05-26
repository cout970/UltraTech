package common.cout970.UltraTech.machines.renders;


import common.cout970.UltraTech.TileEntities.Tier3.ReactorTankEntity;
import common.cout970.UltraTech.lib.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class ReactorTankRender extends TileEntitySpecialRenderer{

	public static float w = 1,l=1,h=1;
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

		if(tileentity != null && Minecraft.getMinecraft().renderEngine != null){
			int a = ((ReactorTankEntity)tileentity).getFluidAmount();
			int b =	((ReactorTankEntity)tileentity).getCapacity();
			bindTexture(new ResourceLocation("ultratech:textures/misc/fluids/water.png"));
			float fill = (float)a/(float)b;
			RenderUtil.cube(x, y, z,fill);
		}
	}
	
}

package common.cout970.UltraTech.util.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.managers.BlockManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class RenderUtil {

	
	public static int scaled(int max,int current, int scale){
		return current * scale / max;
	}

	public static void setGLColorFromInt(int color) {
		float red = (float) (color >> 16 & 255) / 255.0F;
		float green = (float) (color >> 8 & 255) / 255.0F;
		float blue = (float) (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}

	public static void bindTexture(ResourceLocation resourceLocation) {
		if(Minecraft.getMinecraft().renderEngine != null){
			Minecraft.getMinecraft().renderEngine.bindTexture(resourceLocation);
		}
	}

	
}

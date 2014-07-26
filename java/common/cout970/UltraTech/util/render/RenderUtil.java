package common.cout970.UltraTech.util.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.util.ConectedTexture;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class RenderUtil {

	public static Tessellator t = Tessellator.instance;
	static float u = 0,u1 = 1,v = 0,v1 = 1;
	public static float tick = 0;
	public static float tex = 0;
	
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

	public static int getConectedTexturesIcon(IBlockAccess BA, int x, int y,
			int z, int side) {
		Block t = BA.getBlock(x, y, z);
		Block[] w = new Block[9];
		if(side == 0 || side == 1){
			int v = 0;
			for(int k=-1;k<2;k++){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y, z+k);
				}
			}
		}else if(side == 2 || side == 3){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y+j, z);
				}
			}
		}else if(side == 4 || side == 5){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int k=-1;k<2;k++){
					w[v++] = BA.getBlock(x, y+j, z+k);
				}
			}
		}
		boolean[] b = new boolean[9];
		for(int v=0;v<9;v++){
			b[v] = Block.isEqualTo(w[v], t);
		}
		return ConectedTexture.getTex(b);
	}

	public static int getDir(boolean[] b) {
		int count = 0;
		int last;
		for(boolean c : b)if(c){count++;}
		if(count != 1)return -1;
		if(b.length == 6)return b[0]? 0 : b[1]? 1 : b[2]? 5: b[3]? 3: b[4]?4: b[5]? 2  : -1;
		return b[0]? 4 : b[1]? 2 : b[2]? 1: b[3]? 3: -1;
	}

	
}

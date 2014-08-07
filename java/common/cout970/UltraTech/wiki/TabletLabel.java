package common.cout970.UltraTech.wiki;

import ultratech.api.util.UT_Utils;
import net.minecraft.client.renderer.texture.TextureManager;

public class TabletLabel {

	public String info = "";
	public int color = UT_Utils.RGBtoInt(0, 0, 0);
	public int posX,posY;

	public TabletLabel(int x,int y){
		posX = x;
		posY = y;
	}
	public void drawLabel(TextureManager t){

	}
}

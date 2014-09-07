package common.cout970.UltraTech.wiki;

import java.util.ArrayList;
import java.util.List;

import ultratech.api.util.UT_Utils;
import net.minecraft.client.renderer.texture.TextureManager;

public class TabletLabel extends TabletButtom{

	public List<String> lines = new ArrayList<String>();
	public int color = UT_Utils.RGBtoInt(0, 0, 0);

	public TabletLabel(IPage in, int x, int y, int xs, int ys){
		super(in, x, y, xs, ys);
	}

	@Override
	public void doSomething() {}

	@Override
	public void drawButtom(TextureManager t, int x, int y) {
		int site = 0;
		for(String s : lines){
			Page.drawString(s, x+posX, y+posY+site, color, false);
			site += 10;
		}
	}

	public void allLine(String string) {
		lines.add(string);
	}
}

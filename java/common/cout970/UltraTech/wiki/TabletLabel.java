package common.cout970.UltraTech.wiki;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import ultratech.api.util.UT_Utils;
import net.minecraft.client.renderer.texture.TextureManager;

public class TabletLabel extends TabletButtom{

	public List<String> lines = new ArrayList<String>();
	public int color = UT_Utils.RGBtoInt(0,0,0);

	public TabletLabel(IPage in, int x, int y, int xs, int ys){
		super(in, x, y, xs, ys);
	}

	@Override
	public void doSomething() {}

	@Override
	public void drawButtom(TextureManager t, int x, int y) {
		float tam = 0.825f;
		GL11.glPushMatrix();
		GL11.glScalef(tam, tam, tam);
		GL11.glTranslatef((x+posX)/tam, (y+posY)/tam, 0);
		for(String s : lines){
			GL11.glTranslatef(0, 10, 0);
			Page.drawString(s, 0, 0, color, false);
		}
		GL11.glPopMatrix();
	}

	public void allLine(String string) {
		lines.add(string);
	}
}

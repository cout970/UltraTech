package common.cout970.UltraTech.wiki.pages;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;
import net.minecraft.client.renderer.texture.TextureManager;

public class IndexPage extends Page {
	
	public TabletButtom[] list;

	public IndexPage(TabletGui c) {
		super(c);
		list = new TabletButtom[7];
		list[0] = new TabletButtom(this,15,20,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Start", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("start");
			}
		};
		list[1] = new TabletButtom(this,15,35,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Decorative", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("deco");
			}
		};
		list[2] = new TabletButtom(this,15,50,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Multiblocks", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("multi");
			}
		};
		list[3] = new TabletButtom(this,15,65,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Machines", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("machine");
			}
		};
		list[4] = new TabletButtom(this,15,80,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Generation", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("world");
			}
		};
		list[5] = new TabletButtom(this,15,95,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Intermod", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("inter");
			}
		};
		list[6] = new TabletButtom(this,15,110,120,12) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Energy", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
			}

			@Override
			public void doSomething() {
				cont.loadPage("energy");
			}
		};
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		for(TabletButtom tb : list){
			GL11.glColor3f(1, 1, 1);
			tb.drawButtom(t, x, y);
		}
	}

	@Override
	public void mouseClick(int i, int j) {
		for(TabletButtom tb : list)tb.checkClick(i, j);
	}

}

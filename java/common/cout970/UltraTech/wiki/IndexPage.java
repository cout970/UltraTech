package common.cout970.UltraTech.wiki;

import common.cout970.UltraTech.util.UT_Utils;
import net.minecraft.client.renderer.texture.TextureManager;

public class IndexPage extends Page {
	
	public TabletButtom mach;

	public IndexPage(TabletGui cont) {
		mach = new TabletButtom(this,0,0,0,0) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {

			}

			@Override
			public void doSomething() {

			}
		};
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		this.drawString("WIP", x, y, UT_Utils.RGBtoInt(0, 0, 0));
	}

	@Override
	public void mouseClick(int i, int j) {
		
	}

}

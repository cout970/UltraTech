package common.cout970.UltraTech.wiki;

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

	}

	@Override
	public void mouseClick(int i, int j) {
		
	}

}

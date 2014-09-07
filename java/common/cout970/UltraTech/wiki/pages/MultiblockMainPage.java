package common.cout970.UltraTech.wiki.pages;

import net.minecraft.client.renderer.texture.TextureManager;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;

public class MultiblockMainPage extends Page{

	private TabletButtom refinery;
	
	public MultiblockMainPage(TabletGui c) {
		super(c);
		
		refinery = new TabletButtom(this,10,20,130,12){

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				t.bindTexture(WikiResources.butt);
				Page.drawTextureBox(x+posX, y+posY, Xsize, Ysize);
				Page.drawString("Refinery Multiblock", x+75, y+25, 0, true);
			}
			@Override
			public void doSomething() {
				cont.loadPage("multi-refinery");
			}
		};
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		refinery.drawButtom(t, x, y);
	}

	@Override
	public void mouseClick(int i, int j) {
		refinery.checkClick(i, j);
	}

}

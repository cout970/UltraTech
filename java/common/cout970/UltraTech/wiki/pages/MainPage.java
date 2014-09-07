package common.cout970.UltraTech.wiki.pages;

import ultratech.api.util.UT_Utils;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;

public class MainPage extends Page{

	public TabletButtom toIndex;
	
	public MainPage(TabletGui c){
		super(c);
		toIndex = new TabletButtom(this, 35, 120, 80, 20){
			@Override
			public void doSomething() {
				cont.loadPage("index");
			}
			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				this.renderBack(t, x, y);
				Page.drawString("Index", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0), true);
			}};
	}
	
	@Override
	public void renderPage(TextureManager t, int x, int y) {
		t.bindTexture(WikiResources.logo);
		Page.drawTextureBox(x+8, y+8, 134, 80);
		toIndex.drawButtom(t,x,y);
	}

	@Override
	public void mouseClick(int i, int j) {
		toIndex.checkClick(i, j);
	}

}

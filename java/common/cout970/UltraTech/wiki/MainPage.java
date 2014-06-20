package common.cout970.UltraTech.wiki;

import common.cout970.UltraTech.gui.TabletGui;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;

public class MainPage extends Page{

	public TabletButtom toIndex;
	public TabletGui cont;
	
	public MainPage(TabletGui cont){
		this.cont = cont;
		toIndex = new TabletButtom(this, 35, 120, 80, 20){
			@Override
			public void doSomething() {
//				System.out.println("do something");
				((MainPage)this.in).cont.curr = new IndexPage(((MainPage)this.in).cont);
			}
			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				t.bindTexture(WikiResources.butt);
				Page.drawTextureBox(x+posX, y+posY, Xsize, Ysize);
				Page.drawString("Index", x+posX+Xsize/2, y+posY+Ysize/2, UT_Utils.RGBtoInt(0, 0, 0));
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

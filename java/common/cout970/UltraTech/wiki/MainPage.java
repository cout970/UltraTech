package common.cout970.UltraTech.wiki;

import common.cout970.UltraTech.gui.TabletGui;

import net.minecraft.client.renderer.texture.TextureManager;

public class MainPage extends Page{

	public TabletButtom toIndex;
	public TabletGui cont;
	
	public MainPage(TabletGui cont){
		this.cont = cont;
		toIndex = new TabletButtom(this, 0, 0, 0, 0){
			@Override
			public void doSomething() {
				((MainPage)this.in).cont.curr = new IndexPage(((MainPage)this.in).cont);
			}
			@Override
			public void drawButtom(TextureManager t, int x, int y) {

			}};
	}
	
	@Override
	public void renderPage(TextureManager t, int x, int y) {
		t.bindTexture(WikiResources.logo);
		this.drawTextureBox(x+8, y+8, 134, 80);
		toIndex.drawButtom(t,x,y);
	}

	@Override
	public void mouseClick(int i, int j) {
		toIndex.checkClick(i, j);
	}

}

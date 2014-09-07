package common.cout970.UltraTech.wiki.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;

public class EnergyMainPage extends Page{

	private TabletButtom capacity;
	
	public EnergyMainPage(TabletGui c) {
		super(c);
		
		capacity = new TabletButtom(this,10,10,130,12){

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				t.bindTexture(WikiResources.butt);
				Page.drawTextureBox(x+posX, y+posY, Xsize, Ysize);
				Page.drawString("Capacity of machines", x+75, y+15, 0, true);
			}
			@Override
			public void doSomething() {
				cont.loadPage("e-cap");
			}
		};
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		capacity.drawButtom(t, x, y);
	}

	@Override
	public void mouseClick(int i, int j) {
		capacity.checkClick(i, j);
	}

}

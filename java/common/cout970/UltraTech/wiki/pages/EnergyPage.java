package common.cout970.UltraTech.wiki.pages;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.util.UT_Utils;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletButtom;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;

public class EnergyPage extends Page{

	private TabletButtom table;

	public EnergyPage(TabletGui c) {
		super(c);
		c.maxpages = MachineData.values().length/10;
		table = new TabletButtom(this,10,10,130,120) {

			@Override
			public void drawButtom(TextureManager t, int x, int y) {
				t.bindTexture(WikiResources.table);
				Page.drawTextureBox(x+posX, y+posY, Xsize, 10);
				
				for(int g = 0;g<10;g++) Page.drawTextureBox(x+posX, y+posY+24+g*12, Xsize, 10);
				
				Page.drawString("Tier   Machine   Capacity", x+75, y+14, 0);
				FontRenderer f = Minecraft.getMinecraft().fontRenderer;
				for(MachineData m : MachineData.values()){
					int pos = m.ordinal();
					if(pos < 10*cont.page && pos >= 10*(cont.page-1)){
						int topos = pos % 10;
						String s = ((int)m.cap)+"";
						f.drawString(m.tier+""	, x+14, y+36+topos*12, 0);
						f.drawString(m.name()	, x+24, y+36+topos*12, 0);
						f.drawString(s	, x+138-f.getStringWidth(s), y+36+topos*12, 0);
					}
				}
			}

			@Override
			public void doSomething() {}
		};
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		table.drawButtom(t, x, y);
	}

	@Override
	public void mouseClick(int i, int j) {
		table.checkClick(i, j);
	}

}

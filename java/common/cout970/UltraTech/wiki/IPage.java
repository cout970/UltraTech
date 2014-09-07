package common.cout970.UltraTech.wiki;

import net.minecraft.client.renderer.texture.TextureManager;

public interface IPage {

	void renderPage(TextureManager t,int x,int y);
	
	TabletButtom[] getButtoms();
	TabletLabel[] getLabels();
	
	void mouseClick(int x, int y);
}

package common.cout970.UltraTech.wiki.pages;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.texture.TextureManager;
import common.cout970.UltraTech.client.renders.DecoBlocksRender;
import common.cout970.UltraTech.wiki.Page;
import common.cout970.UltraTech.wiki.TabletGui;
import common.cout970.UltraTech.wiki.WikiResources;

public class DecoPage extends Page{

	public DecoPage(TabletGui c) {
		super(c);
		c.maxpages = 2*8;
	}

	@Override
	public void renderPage(TextureManager t, int x, int y) {
		boolean renderblack = cont.page <= 8;
		int renderid = cont.page % 8;
			for(int j=0;j<4;j++){
				for(int i=0;i<3;i++){
				if(i+j*3 < 11){
					
					t.bindTexture(WikiResources.decobase);
					float[] r = DecoBlocksRender.getColorByMetadata(j*4+i);
					GL11.glColor3f(r[0], r[1], r[2]);
					this.drawTextureBox(x+i*32+25, y+j*32+20, 32, 32);
					GL11.glColor3f(1, 1, 1);
					if(renderblack)	t.bindTexture(WikiResources.decoblack[renderid]);
					else 			t.bindTexture(WikiResources.decowhite[renderid]);
					this.drawTextureBox(x+i*32+25, y+j*32+20, 32, 32);
				}
			}
		}
	}

	@Override
	public void mouseClick(int i, int j) {
		
	}

}

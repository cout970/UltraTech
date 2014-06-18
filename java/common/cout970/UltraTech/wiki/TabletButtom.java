package common.cout970.UltraTech.wiki;

import net.minecraft.client.renderer.texture.TextureManager;

public abstract class TabletButtom {

	public int posX,posY;
	public int Xsize,Ysize;
	public IPage in;
	
	public TabletButtom(IPage in, int x, int y, int xs, int ys){
		posX = x;
		posY = y;
		Xsize = xs;
		Ysize = ys;
		this.in = in;
	}
	
	public void checkClick(int x, int y){
		if(isIn(x,y,posX,posY,Xsize,Ysize)){
			doSomething();
		}
	}
	
	public abstract void doSomething();

	public boolean isIn(int mx, int my, int x, int y, int w, int h){
		if(mx > x && mx < x+w){
			if(my > y && my < y+h){
				return true;
			}
		}
		return false;
	}
	
	public abstract void drawButtom(TextureManager t, int x, int y);
}

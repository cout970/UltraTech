package common.cout970.UltraTech.misc;

import net.minecraft.util.IIcon;

public class IconFactory implements IIcon{

	float u;
	float v;
	float um;
	float vm;
	
	int widht;
	int height;
	
	String name;
	
	public IconFactory(int xSize,int ySize,float u, float v, float um, float vm,String name){
		this.u = u;
		this.um = um;
		this.v = v;
		this.vm = vm;
		this.name = name;
		widht = (int) ((um-u)*(1d/xSize));
		height = (int) ((vm-v)*(1d/ySize));
	}
	
	@Override
	public int getIconWidth() {
		return widht;
	}

	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public float getMinU() {
		return u;
	}

	@Override
	public float getMaxU() {
		return um;
	}

	@Override
	public float getInterpolatedU(double a) {
		return (float) (a*((um-u)/16));
	}

	@Override
	public float getMinV() {
		return v;
	}

	@Override
	public float getMaxV() {
		return vm;
	}

	@Override
	public float getInterpolatedV(double a) {
		return (float) (a*((vm-v)/16));
	}

	@Override
	public String getIconName() {
		return name;
	}

}

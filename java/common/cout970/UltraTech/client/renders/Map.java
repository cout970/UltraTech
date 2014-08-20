package common.cout970.UltraTech.client.renders;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import common.cout970.UltraTech.client.textures.ResourcesLocations;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.render.IHologram;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class Map implements IHologram{

	public List<int[]> p = new ArrayList<int[]>();
	public int[][] terrain = new int[51][51];
	public MapColor[][] colorRGB = new MapColor[51][51];
	public boolean colorHeight = true;
	public int mode = GL11.GL_POINTS;
	public boolean height;
	public int y;
	
	
	public Map(World w, double posX, double posY, double posZ){
		genTerrein(w, posX, posY, posZ);
		for(int z=0;z<50;z++){
			for(int x=0;x<50;x++){
			
				p.add(new int[]{x   ,getH(x,z)   , z});
				p.add(new int[]{x   ,getH(x,z+1)   , z+1});
			}
		}
	}
	
	public void genTerrein(World w,double x,double y, double z){
		for(int i=0;i<51;i++){
			for(int j=0;j<51;j++){
				int v;
				terrain[i][j] = w.getHeightValue((int)(x-25+i), (int)(z-25+j));
				if(w.getBlock((int)(x-25+i), terrain[i][j],(int)(z-25+j)) == Blocks.air)v = 1; else v = 0;
				colorRGB[i][j] = w.getBlock((int)(x-25+i), terrain[i][j]-v,(int)(z-25+j)).getMaterial().getMaterialMapColor();
			}	
		}
		
	}

	private int getH(int i, int j) {
		return terrain[i][j];
	}

	private void setColorByHeight(Tessellator t, int[] a) {
		float e;
		int i = a[1];
		if(colorHeight){
			if(i < 128){
				e = i/128f;
				t.setColorRGBA_F( 0, e, 0, 1f);
			}else{
				e = i/256f;
				t.setColorRGBA_F( e, e, e, 1f);
			}
		}else{
			t.setColorOpaque_I(colorRGB[a[0]][a[2]].colorValue);
		}
	}

	@Override
	public void render(float f5) {
		Tessellator t = Tessellator.instance;
		int scala = 50;//50;
		t.startDrawing(mode);
		GL11.glPointSize(20);
		t.setBrightness(12000);
		int i = 0;
		for(int[] a:p){
			i++;
			setColorByHeight(t,a);
			if(height)t.addVertex((float)a[0]/scala, (float)a[1]/64, (float)a[2]/scala);
			if(!height)t.addVertex((float)a[0]/scala, 0.0f, (float)a[2]/scala);

			if(i == 100){
				i = 0;
				t.draw();
				t.startDrawing(mode);
				t.setBrightness(12000);
			}
		}
		t.draw();
	}

	@Override
	public void renderPart(float f5, int part) {}

	@Override
	public boolean enableBlend() {
		return false;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return ResourcesLocations.HOLOGRAM;
	}

	@Override
	public void setColor() {
		GL11.glColor4f(1, 1, 1, 1);
	}

	@Override
	public void setRotation() {
		GL11.glRotatef(90f, 0, 0, 1);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNeigUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}
	
}

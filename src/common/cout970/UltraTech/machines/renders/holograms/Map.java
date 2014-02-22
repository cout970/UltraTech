package common.cout970.UltraTech.machines.renders.holograms;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class Map{

	public String name = "";
	public List<int[]> p = new ArrayList<int[]>();
	public int[][] terrain = new int[51][51];
	
	
	public Map(String name, World w, double posX, double posY, double posZ){
		this.name = name;
		genTerrein(w, posX, posY, posZ);
		for(int x=0;x<50;x++){
			for(int z=0;z<50;z++){
				
				p.add(new int[]{x   ,getH(x,z)   , z});
				p.add(new int[]{x   ,getH(x,z)   , z+1});
				p.add(new int[]{x+1 ,getH(x,z)   , z+1});
				p.add(new int[]{x+1 ,getH(x,z)   , z});

				if(getH(x+1,z) != getH(x,z)){
					p.add(new int[]{x+1   ,getH(x,z)    , z});
					p.add(new int[]{x+1   ,getH(x+1,z)  , z});
					p.add(new int[]{x+1 ,getH(x+1,z)  , z+1});
					p.add(new int[]{x+1 ,getH(x,z)    , z+1});
				}
				if(getH(x,z+1) != getH(x,z)){
					p.add(new int[]{x   ,getH(x,z)   , z+1});
					p.add(new int[]{x   ,getH(x,z+1) , z+1});
					p.add(new int[]{x+1 ,getH(x,z+1)  , z+1});
					p.add(new int[]{x+1 ,getH(x,z)    , z+1});
				}
			}
		}
	}
	
	public void genTerrein(World w,double x,double y, double z){
		for(int i=0;i<51;i++){
			for(int j=0;j<51;j++){
				terrain[i][j] = w.getHeightValue((int)(x-25+i), (int)(z-25+j));
			}	
		}
		
	}

	private int getH(int i, int j) {
		return terrain[i][j];
	}

	public void Render(){
		Tessellator t = Tessellator.instance;
		int scala = 50;
		t.startDrawing(7);
		t.setBrightness(12000);
		for(int[] a:p){
			t.setColorRGBA(0, (int) 256*a[1]/128,0,  256);
			t.addVertex((float)a[0]/scala, (float)a[1]/50, (float)a[2]/scala);
		}
		t.draw();
	}
	
}

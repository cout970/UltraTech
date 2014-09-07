package common.cout970.UltraTech.util.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;

public class ConectedTexture {
	
	public static List<ST> COMMON = new ArrayList<ST>();
	public static List<ST> EXCEPTION = new ArrayList<ST>();
	public static ConectedTexture INSTANCE = new ConectedTexture().init();
	
	public ConectedTexture init(){
		COMMON.add(new ST(2,0,2, 0,1,0, 2,0,2));//0 - 0 
		COMMON.add(new ST(2,1,2, 1,1,1, 2,1,2));//1 - all sides
		COMMON.add(new ST(2,1,2, 0,1,0, 2,0,2));//2
		COMMON.add(new ST(2,0,2, 0,1,0, 2,1,2));//3
		COMMON.add(new ST(2,0,2, 1,1,0,	2,0,2));//4 change
		COMMON.add(new ST(2,0,2, 0,1,1, 2,0,2));//5 change
		COMMON.add(new ST(2,1,2, 0,1,0, 2,1,2));//6 - 2 sides perpendicular
		COMMON.add(new ST(2,0,2, 1,1,1, 2,0,2));//7 - 2 sides perpendicular
		COMMON.add(new ST(2,1,2, 1,1,1, 2,0,2));//8  - 3 sides
		COMMON.add(new ST(2,0,2, 1,1,1, 2,1,2));//9	 - 3 sides
		COMMON.add(new ST(2,1,2, 0,1,1, 2,1,2));//10 - 3 sides
		COMMON.add(new ST(2,1,2, 1,1,0, 2,1,2));//11 - 3 sides
		COMMON.add(new ST(2,1,1, 0,1,1, 2,0,2));//12 - corners without
		COMMON.add(new ST(1,1,2, 1,1,0, 2,0,2));//13 - corners without
		COMMON.add(new ST(2,0,2, 1,1,0, 1,1,2));//14 - corners without
		COMMON.add(new ST(2,0,2, 0,1,1, 2,1,1));//15 - corners without
		COMMON.add(new ST(2,1,0, 0,1,1, 2,0,2));//16 - corners with
		COMMON.add(new ST(0,1,2, 1,1,0, 2,0,2));//17 - corners with
		COMMON.add(new ST(2,0,2, 1,1,0, 0,1,2));//18 - corners with
		COMMON.add(new ST(2,0,2, 0,1,1, 2,1,0));//19 - corners with
		COMMON.add(new ST(0,1,0, 1,1,1, 0,1,0));//20 - 4 sides with
		
		EXCEPTION.add(new ST(2,0,2, 0,1,0, 2,0,2));//0 - 0 
		EXCEPTION.add(new ST(2,1,2, 1,1,1, 2,1,2));//1 - all sides
		EXCEPTION.add(new ST(2,1,2, 0,1,0, 2,0,2));//2
		EXCEPTION.add(new ST(2,0,2, 0,1,0, 2,1,2));//3
		EXCEPTION.add(new ST(2,0,2, 0,1,1, 2,0,2));//4 change
		EXCEPTION.add(new ST(2,0,2, 1,1,0, 2,0,2));//5 change
		EXCEPTION.add(new ST(2,1,2, 0,1,0, 2,1,2));//6 - 2 sides perpendicular
		EXCEPTION.add(new ST(2,0,2, 1,1,1, 2,0,2));//7 - 2 sides perpendicular
		EXCEPTION.add(new ST(2,1,2, 1,1,1, 2,0,2));//8  - 3 sides
		EXCEPTION.add(new ST(2,0,2, 1,1,1, 2,1,2));//9	- 3 sides
		EXCEPTION.add(new ST(2,1,2, 1,1,0, 2,1,2));//10 - 3 sides
		EXCEPTION.add(new ST(2,1,2, 0,1,1, 2,1,2));//11 - 3 sides
		EXCEPTION.add(new ST(1,1,2, 1,1,0, 2,0,2));//12 - corners without
		EXCEPTION.add(new ST(2,1,1, 0,1,1, 2,0,2));//13 - corners without
		EXCEPTION.add(new ST(2,0,2, 0,1,1, 2,1,1));//14 - corners without
		EXCEPTION.add(new ST(2,0,2, 1,1,0, 1,1,2));//15 - corners without
		EXCEPTION.add(new ST(0,1,2, 1,1,0, 2,0,2));//16 - corners with
		EXCEPTION.add(new ST(2,1,0, 0,1,1, 2,0,2));//17 - corners with
		EXCEPTION.add(new ST(2,0,2, 0,1,1, 2,1,0));//18 - corners with
		EXCEPTION.add(new ST(2,0,2, 1,1,0, 0,1,2));//19 - corners with
		EXCEPTION.add(new ST(0,1,0, 1,1,1, 0,1,0));//20 - 4 sides with
		return this;
	}

	public static int getTex(int side,boolean... a){
		List<ST> use = null;
		if(side == 0 || side == 1 || side == 4 || side == 3){
			use = COMMON;
		}else{
			use = EXCEPTION;
		}
		for(ST b : use)
			if(b.isThis(a))return use.indexOf(b);
		return 0;
	}

	public static int getConectedTexturesIcon(IBlockAccess BA, int x, int y,
			int z, int side) {
		Block t = BA.getBlock(x, y, z);
		Block[] w = new Block[9];
		if(side == 0 || side == 1){
			int v = 0;
			for(int k=-1;k<2;k++){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y, z+k);
				}
			}
		}else if(side == 2 || side == 3){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int i=-1;i<2;i++){
					w[v++] = BA.getBlock(x+i, y+j, z);
				}
			}
		}else if(side == 4 || side == 5){
			int v = 0;
			for(int j=1;j>-2;j--){
				for(int k=-1;k<2;k++){
					w[v++] = BA.getBlock(x, y+j, z+k);
				}
			}
		}
		boolean[] b = new boolean[9];
		for(int v=0;v<9;v++){
			b[v] = Block.isEqualTo(w[v], t);
		}
		return getTex(side,b);
	}
	
	public class ST{
		
		public int[] a;
		
		public ST(int... a){
			this.a = a;
		}
		
		public boolean isThis(boolean[] b){
			for(int f = 0;f<a.length;f++){
				if(b[f] != (a[f]==1)){
					if(a[f] != 2)return false;
				}
			}
			return true;
		}
	}
	
}

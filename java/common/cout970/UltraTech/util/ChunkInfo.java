package common.cout970.UltraTech.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class ChunkInfo {

	public LinkedList<LayerInfo> heights = new LinkedList<LayerInfo>();

	public class LayerInfo{
		List<BlockPos> blocks = new ArrayList<BlockPos>();
		int tipe;
		
		public LayerInfo(int tipe){
			this.tipe = tipe;
		}
		
		public void generateLayer(BlockPos b,World w){
			if(tipe == 0){
				for(int i=0;i<16;i++){
					for(int j=0;j<16;j++){
						Block bl = w.getBlock(b.x+i, b.y, b.z+j);
						if(UT_Utils.canBreak(bl, w, b.x+i, b.y, b.z+j)){
							blocks.add(new BlockPos(b.x+i, b.y, b.z+j));
						}
					}
				}
			}
		}
	}
}

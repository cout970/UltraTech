package common.cout970.UltraTech.TileEntities.multiblocks;

import common.cout970.UltraTech.blocks.reactor.Reactor_Chamber_Block;
import common.cout970.UltraTech.util.LogHelper;
import ultratech.api.reactor.IReactorBlock;
import ultratech.api.reactor.IReactorComponent;
import ultratech.api.reactor.IReactorCoreBlock;
import ultratech.api.reactor.IReactorCoreEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ReactorMultiblockTweak {

	public static void onBlockUpdate(TileEntity r) {}
	
	public static int checkStructure(World w,int x,int y,int z){
		if(!(w.getBlock(x, y, z) instanceof IReactorCoreBlock))return 0;
		boolean not1 = false;
		boolean not2 = false;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				for(int k=0;k<3;k++){
					Block b = w.getBlock(x+i-1, y+j-1, z+k-1);
					if(!(b instanceof IReactorBlock))return 0;//mising a block
					else if(i != 1 || j !=1 || k!=1){//if is not the reactor
						if(((IReactorBlock) b).getLayer() == 0){//check if any component can't be in the surface
							not1 = true;//can't be the size 1
							break;
						}
					}
				}
				if(not1)break;
			}
			if(not1)break;
		}
		//1
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				for(int k=0;k<5;k++){
					Block b = w.getBlock(x+i-2, y+j-2, z+k-2);
					if(!(b instanceof IReactorBlock)){//if is mising a block for size 2
						if(not1)return 0;//if can't be 1
						return 1;//create size 1
					}else{
						if((i > 0 && i < 4) && (j > 0 && j < 4) && (k > 0 && k < 4)){//check the inside blocks
							if(i==2 && j ==2 && k==2){//check the core
								if(!(b instanceof IReactorCoreBlock))return 0;//error core missing
							}else if(((IReactorBlock) b).getLayer() == 2){//chech if the inside blocks can be in inside
								if(not1)return 0;//if can't be 1
								return 1;//if can be 1 but not 2
							}
						}else{
							if(((IReactorBlock) b).getLayer() == 0){//check if can't be 2
								not2 = true;
								break;
							}
						}
					}
				}
				if(not2)break;
			}
			if(not2)break;
		}
		//2
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				for(int k=0;k<7;k++){
					Block b = w.getBlock(x+i-3, y+j-3, z+k-3);
					if(!(b instanceof IReactorBlock)){//if is mising a block for size 3
						if(not2)return 0;//not create
						return 2;//create size 2
					}else{
						if((i > 0 && i < 6) && (j > 0 && j < 6) && (k > 0 && k < 6)){//check the inside blocks
							if(i==3 && j ==3 && k==3){//check the core
								if(!(b instanceof IReactorCoreBlock))return 0;//error core missing
							}else if(((IReactorBlock) b).getLayer() == 2){//chech if the inside blocks can be in inside
								if(not2)return 0;//if can't be 1
								return 2;//if can be 1 but not 2
							}
						}else{
							if(((IReactorBlock) b).getLayer() == 0){//check if can't be 3
								if(not2)return 0;//if can't be 1
								return 2;//if can be 1 but not 2
							}
						}
					}
				}
			}
		}
		return 3;
	}
	
	public static void setStructure(World w,int x,int y,int z,int size){
		if(size == 1){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					for(int k=0;k<3;k++){
						w.setBlockMetadataWithNotify(x+i-1, y+j-1, z+k-1, 1, 2);
					}
				}
			}
		}else if(size == 2){
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					for(int k=0;k<5;k++){
						w.setBlockMetadataWithNotify(x+i-2, y+j-2, z+k-2, 1, 2);
					}
				}
			}
		}else if(size == 3){
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
					for(int k=0;k<7;k++){
						w.setBlockMetadataWithNotify(x+i-3, y+j-3, z+k-3, 1, 2);
					}
				}
			}
		}
		if(size > 0){
			IReactorCoreEntity c = (IReactorCoreEntity) w.getTileEntity(x, y, z);
			c.setSize(size);
			c.updateComponents();
		}
	}
	
	public static void removeStructure(World w,int x,int y,int z){
		TileEntity ti = w.getTileEntity(x, y, z);
		if(ti == null || !(ti instanceof Reactor_Core_Entity))return;
		
		int size = ((Reactor_Core_Entity)ti).size;
		
		if(size == 1){
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					for(int k=0;k<3;k++){
						TileEntity t = w.getTileEntity(x+i-1, y+j-1, z+k-1);
						if(t instanceof IReactorComponent)((IReactorComponent) t).RestaureBlock();
					}
				}
			}
		}else if(size == 2){
			for(int i=0;i<5;i++){
				for(int j=0;j<5;j++){
					for(int k=0;k<5;k++){
						TileEntity t = w.getTileEntity(x+i-2, y+j-2, z+k-2);
						if(t instanceof IReactorComponent)((IReactorComponent) t).RestaureBlock();
					}
				}
			}
		}else if(size == 3){
			for(int i=0;i<7;i++){
				for(int j=0;j<7;j++){
					for(int k=0;k<7;k++){
						TileEntity t = w.getTileEntity(x+i-3, y+j-3, z+k-3);
						if(t instanceof IReactorComponent)((IReactorComponent) t).RestaureBlock();
					}
				}
			}
		}
	}

	public static void onStructureActivate(World w, int x, int y, int z, Block b) {
		if(w.isRemote)return;
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				for(int k=0;k<7;k++){
					if(w.getBlock(x+i-3, y+j-3, z+k-3) instanceof IReactorCoreBlock){
						int t = checkStructure(w, x+i-3, y+j-3, z+k-3);
						if(t > 0){
							setStructure(w, x+i-3, y+j-3, z+k-3, t);
							return;
						}
					}
				}
			}
		}
	}

	public static void onStructureDesactivate(World w, int x, int y, int z, Block b) {
		if(w.isRemote)return;
		for(int i=0;i<7;i++){
			for(int j=0;j<7;j++){
				for(int k=0;k<7;k++){
					if(w.getBlock(x+i-3, y+j-3, z+k-3) instanceof IReactorCoreBlock){
						removeStructure(w, x+i-3, y+j-3, z+k-3);
						return;
					}
				}
			}
		}
	}
	
	

}

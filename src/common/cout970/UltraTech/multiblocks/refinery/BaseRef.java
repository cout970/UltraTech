package common.cout970.UltraTech.multiblocks.refinery;

import common.cout970.UltraTech.managers.BlockManager;

public class BaseRef extends TileRefinery{

	public void refresh(){

		int id = BlockManager.Refinery.blockID;
		
		if(worldObj.getBlockId(xCoord+1, yCoord, zCoord-1) != id)return;
		if(worldObj.getBlockId(xCoord-1, yCoord, zCoord+1) != id)return;
		if(worldObj.getBlockId(xCoord+1, yCoord, zCoord+1) != id)return;
		if(worldObj.getBlockId(xCoord-1, yCoord, zCoord-1) != id)return;
		
		boolean hasStructure = true;
		for(int i=-1;i<2;i++){
			for(int j=0;j<8;j++){
				for(int k=-1;k<2;k++){
					if(worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k) != id){
						hasStructure = false;
					}
				}
			}
		}
		if(hasStructure){
			
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 1);
		}
	}
	
}

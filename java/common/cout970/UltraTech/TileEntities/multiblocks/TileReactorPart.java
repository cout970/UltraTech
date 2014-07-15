package common.cout970.UltraTech.TileEntities.multiblocks;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.utility.ReactorEntity;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import common.cout970.UltraTech.network.SyncTile;

public class TileReactorPart extends SyncTile implements IReactorPart{

	
		//Reactor Part

		public boolean found = false;
		public ReactorEntity Reactor;
		public boolean Structure = false;


		@Override
		public void SearchReactor() {
			int current = 0;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(worldObj.getBlock(xCoord+i, yCoord+j, zCoord+k) == BlockManager.Reactor_Core && worldObj.getBlockMetadata(xCoord+i, yCoord+j, zCoord+k) == 0){
							Reactor = (ReactorEntity) worldObj.getTileEntity(xCoord+i,yCoord+j,zCoord+k);
							found = true;
							return;
						}
						current++;
					}
				}
			}
			found = false;
		}

		@Override
		public ReactorEntity getReactor() {
			return this.Reactor;
		}

		@Override
		public void onNeighChange() {
			SearchReactor();
			if(found){
				checkStructure();
				if(Structure){
					activateBlocks();
				}
			}else{
				Structure = false;
			}
		}

		@Override
		public void checkStructure() {
			TileEntity[] ids = new TileEntity[27];
			int current = 0;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						ids[current] = worldObj.getTileEntity(Reactor.xCoord+i, Reactor.yCoord+j, Reactor.zCoord+k);
						current++;
					}
				}
			}

			this.Structure = false;

			for(TileEntity t : ids) {
				if(!(t instanceof IReactorPart))return;
			}
			this.Structure = true;
		}

		@Override
		public void activateBlocks() {
			Reactor.setStructure(true);
			Reactor.activateBlocks();
		}

		@Override
		public void desactivateBlocks() {
			if(Reactor != null){
				Reactor.desactivateBlocks();
			}		
		}

		@Override
		public void setStructure(boolean structure) {
			Structure = structure;		
		}

		@Override
		public void setReactor(ReactorEntity e) {
			Reactor = e;
		}

		@Override
		public boolean isStructure() {
			return this.Structure;
		}
		
		//Save & Load

		@Override
		public void readFromNBT(NBTTagCompound nbtTagCompound) {
			super.readFromNBT(nbtTagCompound);
			Structure = nbtTagCompound.getBoolean("Structure");
		}

		@Override
		public void writeToNBT(NBTTagCompound nbtTagCompound) {
			super.writeToNBT(nbtTagCompound);
			nbtTagCompound.setBoolean("Structure", Structure);
		}

}

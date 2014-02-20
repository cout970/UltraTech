package common.cout970.UltraTech.machines.tileEntities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class ReactorWallEntity extends TileEntity implements IReactorPart{

	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;
	public boolean update;

	public ReactorWallEntity(){
		super();
	}
	
	public void updateEntity(){
		if(!update){
			setUp();
			update = true;
		}
	}

	public void setUp(){
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
	public void SearchReactor() {
		int[] ids = new int[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockId(xCoord+i, yCoord+j, zCoord+k);
					if(ids[current] == BlockManager.Reactor.blockID && worldObj.getBlockMetadata(xCoord+i, yCoord+j, zCoord+k) == 0){
						Reactor = (ReactorEntity) worldObj.getBlockTileEntity(xCoord+i,yCoord+j,zCoord+k);
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
		setUp();	
	}

	@Override
	public void checkStructure() {
		TileEntity[] ids = new TileEntity[27];
		int current = 0;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					ids[current] = worldObj.getBlockTileEntity(Reactor.xCoord+i, Reactor.yCoord+j, Reactor.zCoord+k);
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
		int x,y,z;
		x = Reactor.xCoord;
		y = Reactor.yCoord;
		z = Reactor.zCoord;
		for(int j = -1;j<2;j++){
			for(int i = -1;i<2;i++){
				for(int k = -1;k<2;k++){
					if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(true);
						((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setReactor(Reactor);
						worldObj.markBlockForUpdate(x+i, y+j, z+k);
					}
				}
			}
		}
	}

	@Override
	public void desactivateBlocks() {
		if(Reactor != null){
			Structure = false;
			int x,y,z;
			x = Reactor.xCoord;
			y = Reactor.yCoord;
			z = Reactor.zCoord;
			for(int j = -1;j<2;j++){
				for(int i = -1;i<2;i++){
					for(int k = -1;k<2;k++){
						if(this.worldObj.getBlockTileEntity(x+i, y+j, z+k) instanceof IReactorPart){
							((IReactorPart)worldObj.getBlockTileEntity(x+i, y+j, z+k)).setStructure(false);
							worldObj.markBlockForUpdate(x+i, y+j, z+k);
							ByteArrayOutputStream bytes = new ByteArrayOutputStream();
							DataOutputStream data = new DataOutputStream(bytes);
							try {
								data.writeInt(x+i);
								data.writeInt(y+j);
								data.writeInt(z+k);
								data.writeInt(0);
								data.writeInt(this.isStructure() ? 1 : 0);
							} catch (IOException e) {
								e.printStackTrace();
							}
							PacketDispatcher.sendPacketToAllPlayers(PacketDispatcher.getPacket("UltraTech1", bytes.toByteArray()));
							
						}
					}
				}
			}
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
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		Structure = nbtTagCompound.getBoolean("Structure");
		int x,y,z;
			x = nbtTagCompound.getInteger("xR");
			y = nbtTagCompound.getInteger("yR");
			z = nbtTagCompound.getInteger("zR");
			if(worldObj != null){
				Reactor = (ReactorEntity) worldObj.getBlockTileEntity(x,y,z);
			}
	}
	
	  @Override
	    public void writeToNBT(NBTTagCompound nbtTagCompound) {

	        super.writeToNBT(nbtTagCompound);
	        nbtTagCompound.setBoolean("Structure", Structure);
	        if(Reactor != null){
	        nbtTagCompound.setInteger("xR", Reactor.xCoord);
	        nbtTagCompound.setInteger("yR", Reactor.yCoord);
	        nbtTagCompound.setInteger("zR", Reactor.zCoord);
	        }
	  }
	  
	  public boolean receiveClientEvent(int par1, int par2)
	    {
		  if(par1 == 0){
			  if(par2 == 0)this.setStructure(false);else this.setStructure(true);
		  }
		  this.worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	        return false;
	    }
}

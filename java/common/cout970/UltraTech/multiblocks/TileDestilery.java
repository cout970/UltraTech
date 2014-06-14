package common.cout970.UltraTech.multiblocks;

import api.cout970.UltraTech.network.Net_Utils;
import api.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.TileEntities.fluid.DestileryInEntity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileDestilery extends SyncTile implements IDestilery{

	public MultiBlock structure;
	public boolean multi = false;
	private int height = -1;
	public boolean update = false;
	
	public void updateEntity(){
		if(update == false){
			if(!searchMulti())DellMulti();
			update = true;
		}
	}
	
	@Override
	public MultiBlock getMulti() {
		return structure;
	}

	@Override
	public void setMulti(MultiBlock i) {
		if(i != null)multi = true; else multi = false;
		structure = i;
		Net_Utils.sendUpdate(this);
	}

	@Override
	public boolean searchMulti(){
		int height = 0;
		if(this instanceof DestileryInEntity){
			for(int h=0;h<8;h++)if(worldObj.getTileEntity(xCoord, yCoord+h, zCoord) instanceof IDestilery)height++;
			else break;
		}else{
			for(int j=-8;j<8;j++)if(worldObj.getTileEntity(xCoord, yCoord+j, zCoord) instanceof DestileryInEntity){
				return ((DestileryInEntity) worldObj.getTileEntity(xCoord, yCoord+j, zCoord)).searchMulti();
			}
			return false;
		}
		if(height < 2)return false;
		if(height %2 != 0)return false;
		
		for(int h = 0;h<height;h++){
			if(!(worldObj.getTileEntity(xCoord, yCoord+h, zCoord)instanceof IDestilery)){
				return false;
			}
		}
		if(structure == null){
			setMulti(MultiBlock.create(this));
		}else{
			MultiBlock multi = MultiBlock.create(this);
			multi.mergeWith(structure);
			setMulti(multi);
		}	
		
		for(int h = 0;h<height;h++){
			prepareRefBlock(structure,h);
		}
		return true;
	}
	
	public void prepareRefBlock(MultiBlock m, int i){
		IDestilery ref = (IDestilery) worldObj.getTileEntity(xCoord, yCoord+i, zCoord);
		if(ref == null)return;
		ref.setMulti(structure);
		ref.setHeight(i);
		m.addComponent(ref);
		worldObj.markBlockForUpdate(xCoord, yCoord+i, zCoord);
	}

	@Override
	public void DellMulti() {
		if(this instanceof DestileryInEntity){
			for(int v=0;v<8;v++){
				if(worldObj.getTileEntity(xCoord, yCoord+v, zCoord) instanceof IDestilery){
					IDestilery y = (IDestilery) worldObj.getTileEntity(xCoord, yCoord+v, zCoord);
					y.setMulti(null);
					y.setHeight(-1);
					if(y instanceof TileDestilery)((TileDestilery)y).update = false;
				}
			}
		}else if(structure != null)
			for(IDestilery ref : structure.comp){
				if(ref instanceof DestileryInEntity){
					ref.DellMulti();
				}
			}
		setMulti(null);
		setHeight(-1);
		update = false;
	}	

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int h) {
		height = h;
	}

	@Override
	public boolean isMulti() {
		return multi;
	}
	
    
    @Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		multi = nbtTagCompound.getBoolean("Multi");
		if(worldObj != null)worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("Multi", multi);
	}

}

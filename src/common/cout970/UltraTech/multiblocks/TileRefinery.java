package common.cout970.UltraTech.multiblocks;

import common.cout970.UltraTech.TileEntities.Tier1.RefineryInEntity;
import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileRefinery extends TileEntity implements IRefinery{

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
		UT_Utils.sendPacket(this);
	}

	@Override
	public boolean searchMulti(){
		int height = 0;
		if(this instanceof RefineryInEntity){
			for(int h=0;h<8;h++)if(worldObj.getBlockTileEntity(xCoord, yCoord+h, zCoord) instanceof IRefinery)height++;
			else break;
		}else{
			for(int j=-8;j<8;j++)if(worldObj.getBlockTileEntity(xCoord, yCoord+j, zCoord) instanceof RefineryInEntity){
				return ((RefineryInEntity) worldObj.getBlockTileEntity(xCoord, yCoord+j, zCoord)).searchMulti();
			}
			return false;
		}
		if(height < 2)return false;
		if(height %2 != 0)return false;
		
		for(int h = 0;h<height;h++){
			if(!(worldObj.getBlockTileEntity(xCoord, yCoord+h, zCoord)instanceof IRefinery)){
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
		IRefinery ref = (IRefinery) worldObj.getBlockTileEntity(xCoord, yCoord+i, zCoord);
		if(ref == null)return;
		ref.setMulti(structure);
		ref.setHeight(i);
		m.addComponent(ref);
		worldObj.markBlockForRenderUpdate(xCoord, yCoord+i, zCoord);
	}

	@Override
	public void DellMulti() {
		if(this instanceof RefineryInEntity){
			for(int v=0;v<8;v++){
				if(worldObj.getBlockTileEntity(xCoord, yCoord+v, zCoord) instanceof IRefinery){
					IRefinery y = (IRefinery) worldObj.getBlockTileEntity(xCoord, yCoord+v, zCoord);
					y.setMulti(null);
					y.setHeight(-1);
					if(y instanceof TileRefinery)((TileRefinery)y).update = false;
				}
			}
		}else if(structure != null)
			for(IRefinery ref : structure.comp){
				if(ref instanceof RefineryInEntity){
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
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
	}
    
    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    	this.readFromNBT(pkt.data);
    }
    
    @Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		multi = nbtTagCompound.getBoolean("Multi");
		if(worldObj != null)worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("Multi", multi);
	}

}

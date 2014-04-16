package common.cout970.UltraTech.multiblocks;

import common.cout970.UltraTech.lib.UT_Utils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

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
	public boolean searchMulti() {
		int top = 0;
		int botton = 0;
		//direction
		ForgeDirection[] vec ={null,null};
		int v = 0;
		ForgeDirection[] sides = {ForgeDirection.NORTH,ForgeDirection.SOUTH,ForgeDirection.WEST,ForgeDirection.EAST};
		for(ForgeDirection d : sides)if(UT_Utils.getRelative(this, d) instanceof IRefinery) { if(vec[0] == null)vec[0]=d; else vec[1]=d; v++;}
		if(v != 2)return false;
		if(vec[0] == vec[1].getOpposite())return false;
		//height
		for(int j=1;j<6;j++)if(worldObj.getBlockTileEntity(xCoord, yCoord+j, zCoord) instanceof IRefinery)top++;
		for(int j=1;j<6;j++)if(worldObj.getBlockTileEntity(xCoord, yCoord-j, zCoord) instanceof IRefinery)botton++;
		if(top+botton >= 6)top-=((top+botton)-5);
		//1 layer
		if(!(worldObj.getBlockTileEntity(xCoord+vec[0].offsetX, yCoord, zCoord+vec[0].offsetZ)instanceof IRefinery))return false;
		if(!(worldObj.getBlockTileEntity(xCoord+vec[1].offsetX, yCoord, zCoord+vec[1].offsetZ)instanceof IRefinery))return false;
		if(!(worldObj.getBlockTileEntity(xCoord+vec[0].offsetX+vec[1].offsetX, yCoord, zCoord+vec[0].offsetZ+vec[1].offsetZ)instanceof IRefinery))return false;
		//layers
		for(int h = -botton;h<top;h++){
			if(!(worldObj.getBlockTileEntity(xCoord+vec[0].offsetX, yCoord+h, zCoord+vec[0].offsetZ)instanceof IRefinery))return false;
			if(!(worldObj.getBlockTileEntity(xCoord+vec[1].offsetX, yCoord+h, zCoord+vec[1].offsetZ)instanceof IRefinery))return false;
			if(!(worldObj.getBlockTileEntity(xCoord+vec[0].offsetX+vec[1].offsetX, yCoord+h, zCoord+vec[0].offsetZ+vec[1].offsetZ)instanceof IRefinery))return false;
		}
		if(structure == null){
			setMulti(MultiBlock.create(this));
		}else{
			MultiBlock multi = MultiBlock.create(this);
			multi.mergeWith(structure);
			setMulti(multi);
		}	
		
		for(int h = -botton;h<top;h++){
			prepareRefBlock(structure, 0, h, 0 ,-botton);
			prepareRefBlock(structure, vec[0].offsetX, h, vec[0].offsetZ, -botton);
			prepareRefBlock(structure, vec[1].offsetX, h, vec[1].offsetZ, -botton);
			prepareRefBlock(structure, vec[0].offsetX+vec[1].offsetX, h, vec[0].offsetZ+vec[1].offsetZ,-botton);
		}
		return true;
	}
	
	public void prepareRefBlock(MultiBlock m, int i,int j,int k,int min){
		IRefinery ref = (IRefinery) worldObj.getBlockTileEntity(xCoord+i, yCoord+j, zCoord+k);
		if(ref == null)return;
		ref.setMulti(structure);
		ref.setHeight(j-min);
		m.addComponent(ref);
		worldObj.markBlockForRenderUpdate(xCoord+i, yCoord+j, zCoord+k);
	}

	@Override
	public void DellMulti() {
		if(structure != null)
			for(IRefinery ref : structure.comp){
				ref.setHeight(-1);
				ref.setMulti(null);
				if(ref instanceof TileRefinery)((TileRefinery) ref).update = false;
			}
		setMulti(null);
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

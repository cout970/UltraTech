package common.cout970.UltraTech.TileEntities.Tier3;

import common.cout970.UltraTech.machines.containers.ControllerContainer;
import common.cout970.UltraTech.managers.BlockManager;
import common.cout970.UltraTech.misc.IReactorPart;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class ReactorControllerEntity extends TileEntity implements IReactorPart{

	public boolean found = false;
	public ReactorEntity Reactor;
	public boolean Structure = false;
	public boolean update = false;
	public int meta;
	public boolean on;
	public boolean useHeat = false;
	public int maxheat = 1000;
	public int minheat = 900;
	
	public void updateEntity(){
		if(!Structure && meta == 1){
			meta = 0;
		}else if(Structure && meta == 0){
			meta = 1;
		}
		if(Reactor != null && !worldObj.isRemote && useHeat){
			if(Reactor.heat > maxheat){
				on = false;
				Reactor.control = null;
			}else if(Reactor.heat < minheat  && !on){
				on = true;
				Reactor.control = null;
			}
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
		if(Structure){
			SearchReactor();
			if(Reactor != null && !useHeat){
				on = !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
				Reactor.control = null;
			}
			if(found){
				checkStructure();
				if(Structure){
					activateBlocks();
					meta = 1;
				}else{
					meta = 0;
				}
			}else{
				Structure = false;
			}	
		}
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

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		Structure = nbtTagCompound.getBoolean("Structure");
		on = nbtTagCompound.getBoolean("on");
		useHeat = nbtTagCompound.getBoolean("heat");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {

		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setBoolean("Structure", Structure);
		nbtTagCompound.setBoolean("on", on);
		nbtTagCompound.setBoolean("heat", useHeat);
	}
	
	//Synchronization
	
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

	public void sendGUINetworkData(ControllerContainer controllerContainer,
			ICrafting iCrafting) {
		iCrafting.sendProgressBarUpdate(controllerContainer, 0, on ? 1:0);
		iCrafting.sendProgressBarUpdate(controllerContainer, 1, useHeat ? 1:0);
	}

	public void getGUINetworkData(int i, int j) {
		if(i == 0)on = j == 1;
		if(i == 1)useHeat = j == 1;
	}

}

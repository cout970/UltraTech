package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.energy.api.Machine;

import net.minecraft.nbt.NBTTagCompound;


public class ReciverEntity extends Machine{
		
	public Machine[] machines;
	public SenderEntity from;
	private int[] FCoords = {0,0,0}; 
	
	public ReciverEntity(){
		super();
		this.tipe = MachineTipe.Output;
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(from != null){
			Machine.passEnergy(from, this);
		}else{
			setFrom(FCoords);
		}
		
	}
	
	public void onNetworkUpdate() {
		super.onNetworkUpdate();
	}

	public void setFrom(int[] i) {
		if(i != null && i.length == 3)
		if(worldObj.getBlockTileEntity(i[0], i[1], i[2]) instanceof SenderEntity){
			from = (SenderEntity) worldObj.getBlockTileEntity(i[0], i[1], i[2]);	
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		if(from != null)nbt.setIntArray("From", new int[]{from.xCoord,from.yCoord,from.zCoord});
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		FCoords = nbt.getIntArray("From");
		
	}
}
package common.cout970.UltraTech.machines.tileEntities;

import net.minecraft.nbt.NBTTagCompound;


public class ReciverEntity extends Machine{
		
	public Machine[] machines;
	public SenderEntity from;
	
	public ReciverEntity(){
		super();
		this.EnergyMax = 1000;
		this.tipe = MachineTipe.Output;
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		Machine.passEnergy(from, this);
	}
	
	public void onNeighChange(){}

	public void setFrom(int[] i) {
		if(worldObj.getBlockTileEntity(i[0], i[1], i[2]) != null && worldObj.getBlockTileEntity(i[0], i[1], i[2]) instanceof SenderEntity)
			from = (SenderEntity) worldObj.getBlockTileEntity(i[0], i[1], i[2]);	
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		if(from != null)nbt.setIntArray("From", new int[]{from.xCoord,from.yCoord,from.zCoord});
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt) {

		super.readFromNBT(nbt);
		int[] a = nbt.getIntArray("From");
		if(a != null)
		from = (SenderEntity) worldObj.getBlockTileEntity(a[0], a[1], a[2]);
	}
}
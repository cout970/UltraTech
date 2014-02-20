package common.cout970.UltraTech.machines.tileEntities;

import net.minecraft.nbt.NBTTagCompound;


public class ReciverEntity extends Machine{
		
	public Machine[] machines;
	public SenderEntity from;
	public boolean update = false;
	private int[] FCoords = {0,0,0}; 
	
	public ReciverEntity(){
		super();
		this.EnergyMax = 1000;
		this.tipe = MachineTipe.Output;
	}

	@Override
	public void updateEntity(){
		super.updateEntity();
		if(!update){
			update = true;
			setFrom(FCoords );
		}
		Machine.passEnergy(from, this);
		
	}
	
	public void onNeighChange(){
		this.updateMachine(worldObj, xCoord, yCoord, zCoord);
		}

	public void setFrom(int[] i) {
		if(i != null && i.length == 3)
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
		FCoords = nbt.getIntArray("From");
		
	}
}
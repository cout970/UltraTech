package common.cout970.UltraTech.machines.tileEntities;


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
	
	public void onNeighChange() {
	}

	public void setFrom(int[] i) {
		if(worldObj.getBlockTileEntity(i[0], i[1], i[2]) != null && worldObj.getBlockTileEntity(i[0], i[1], i[2]) instanceof SenderEntity)
			from = (SenderEntity) worldObj.getBlockTileEntity(i[0], i[1], i[2]);	
	}

}
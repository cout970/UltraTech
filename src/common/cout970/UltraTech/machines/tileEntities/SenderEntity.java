package common.cout970.UltraTech.machines.tileEntities;


import net.minecraft.tileentity.TileEntity;

public class SenderEntity extends Machine{

	public Machine[] machines;

	public SenderEntity(){
		super();
		this.EnergyMax = 1000;
	}

	@Override
	public void updateEntity(){
		if(machines == null){
			machines = new Machine[6];
			check();
		}
		if(!worldObj.isRemote)
		for(Machine m :machines){
			if(m != null && !(m instanceof SenderEntity) && !worldObj.isRemote){
				int e = m.getEnergy();
				if(e > 0 && (EnergyMax-Energy)>64){
					if(e <= 64){
						m.loseEnergy(e);
						this.gainEnergy(e);
					}else{
						m.loseEnergy(64);
						gainEnergy(64);
					}
				}
			}
		}
	}

	

	public void check(){
		TileEntity[] t = new TileEntity[6];
		t[0] = this.worldObj.getBlockTileEntity(xCoord, yCoord-1, zCoord);
		t[1] = this.worldObj.getBlockTileEntity(xCoord, yCoord+1, zCoord);
		t[2] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord+1);
		t[3] = this.worldObj.getBlockTileEntity(xCoord+1, yCoord, zCoord);
		t[4] = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord-1);
		t[5] = this.worldObj.getBlockTileEntity(xCoord-1, yCoord, zCoord);

		int i= 0;
		for(TileEntity y : t){
			if(y instanceof Machine){
				machines[i] = (Machine) y;
			}else{
				machines[i] = null;
			}
			i++;
		}
	}



	public void onNeighChange() {
	this.check();	
	}

}

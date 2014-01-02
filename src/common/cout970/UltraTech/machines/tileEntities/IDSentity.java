package common.cout970.UltraTech.machines.tileEntities;


import common.cout970.UltraTech.misc.SyncObject;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.tileentity.TileEntity;

public class IDSentity extends Machine{

	
	public IDSentity(){
		super();
		this.EnergyMax = 100000;
	}

	
	@Override
	public void updateEntity(){
		if(machines == null){
			machines = new Machine[6];
			check();
		}
		refill();
	}

	public Machine[] machines;
	public int speed = 64;

	public void refill(){
		for(Machine a : machines){
			if(a != null){
				if(a instanceof IDSentity || a instanceof EnergyIOentity || a instanceof GeneratorEntity || a instanceof ReactorEntity || a instanceof SateliteEntity || a instanceof ReciverEntity){

				}else{
					if(a.Energy+speed <= a.EnergyMax && this.Energy >= speed){
						float e = a.gainEnergy(speed);
						this.Energy -= speed-e;
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

	
	public SyncObject getSync(){
		SyncObject s = new SyncObject();
		s.setVar1(EnergyMax);
		s.setVar2(Energy);
		return s;
	}
	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
    	iCrafting.sendProgressBarUpdate(container, 1, Energy);
    }
    
    public void getGUINetworkData(int id, int value) {
    	switch(id){
    	case 1:{
    		Energy = value;
    		break;
    	}
    	}
	}
}

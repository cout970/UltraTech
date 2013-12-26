package common.cout970.UltraTech.machines.tileEntities;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;

public class ReciverEntity extends Machine{
		
	public Machine[] machines;
	public int speed = 64;
	
	public ReciverEntity(){
		super();
		this.EnergyMax = 1000;
	}

	@Override
	public void updateEntity(){
		if(machines == null){
			machines = new Machine[6];
			check();
		}
		refill();
		Random r = new Random();
		if(r.nextInt(10)==5)check();
	}



	public void refill(){
		for(Machine a : machines){
			if(a != null){
				if(a instanceof ReciverEntity){
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
}
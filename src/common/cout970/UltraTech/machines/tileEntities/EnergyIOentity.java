package common.cout970.UltraTech.machines.tileEntities;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class EnergyIOentity extends Machine{

	public Machine[] machines;
	public int speed = 64;

	int x=0,y=0,z=0;

	public EnergyIOentity(){
		super();
		this.EnergyMax = 500;
	}

	@Override
	public void updateEntity(){
		if(machines == null){
			machines = new Machine[6];
			check();
		}
		refill();
		getE();
	}

	private void getE() {
		if(this.worldObj.getBlockTileEntity(x, y, z) != null && this.worldObj.getBlockTileEntity(x, y, z) instanceof IDSentity){
			IDSentity a = (IDSentity) this.worldObj.getBlockTileEntity(x, y, z);
			if(a != null && a.Energy-speed >= 0 && this.Energy <= EnergyMax+speed){
				a.loseEnergy(speed);
				this.Energy += speed;
			}
		}

	}

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

	public void setCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		
		super.readFromNBT(nbtTagCompound);
		x = nbtTagCompound.getInteger("xFrom");
		y = nbtTagCompound.getInteger("yFrom");
		z = nbtTagCompound.getInteger("zFrom");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("xFrom", x);
		nbtTagCompound.setInteger("yFrom", y);
		nbtTagCompound.setInteger("zFrom", z);
	}

}

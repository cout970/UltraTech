package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.machines.renders.holograms.HologramEntity;

public class HologramEmiterEntity extends Machine{

	public HologramEntity holo;
	public boolean update = false;

	public HologramEmiterEntity(){
		super();
	}
	
	public void updateEntity(){
		if(!update){
			update = true;
			holo = new HologramEntity(worldObj);
			holo.posX = xCoord;
			holo.posY = yCoord;
			holo.posZ = zCoord;
			worldObj.spawnEntityInWorld(holo);
		}
	}
}

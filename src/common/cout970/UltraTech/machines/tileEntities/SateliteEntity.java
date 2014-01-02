package common.cout970.UltraTech.machines.tileEntities;

import common.cout970.UltraTech.core.UltraTech;

public class SateliteEntity extends Machine{
	
	public Machine receptor;
	private boolean hasBase = false;
	public int SolarPanels=0;
	public int tick=1000;
	public float power= 0;

	public SateliteEntity(){
		super();
		this.EnergyMax = 5000;
	}

	@Override
	public void updateEntity(){
		tick++;
		if(tick >= 1000){
			SolarPanels = this.getSolarPanels();
		}
		if(!this.worldObj.isRemote){			
			power += (SolarPanels/10f)*(yCoord/50f);
			while(power > 1){
				this.gainEnergy(1);
				power--;
			}
			
			if(hasBase){
				passEnergy();
			}
		}
	}

	private void passEnergy() {
		
		if(receptor != null && this.getEnergy() >= 64){
			receptor.gainEnergy(64);
			loseEnergy(64);
		}
	}

	public void checkBase(){
		for(int y=this.yCoord-1;y>1;y--){
			int id = this.worldObj.getBlockId(xCoord, y, zCoord);
			if(id == UltraTech.Reciver.blockID){
				receptor = (Machine) this.worldObj.getBlockTileEntity(xCoord, y, zCoord);
				this.hasBase = true;
				return;
			}
		}
		hasBase = false;
	}

	public int getSolarPanels(){
		int s = 0;
		for(int x= -5;x<5;x++){
			for(int z= -5;z<5;z++){
				if(this.worldObj.getBlockId(xCoord+x, yCoord, zCoord+z)== UltraTech.SolarPanel.blockID){
					s++;
				}
			}
		}
		return s;
	}
}

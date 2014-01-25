package common.cout970.UltraTech.machines.tileEntities;

import net.minecraftforge.common.ForgeDirection;

public class EngineEntity extends Machine{

	public ForgeDirection direction = ForgeDirection.UP;
	public float speed = 0;
	public float engPos = 0;
	public boolean engOut;
	private float speedMax;
//	private int MJ;
	
	public void updateEntity(){
		if(this.Energy > 1){
			this.loseEnergy(1);
//			MJ+=1;
			speedMax = 1.5f;
		}else{
			speedMax = 0;
		}
		if(speed != speedMax){
			if(speed < speedMax){
				speed+=0.1f;
			}else{
				speed-=0.1f;
				if(speed < 0)speed = 0;
			}
		}
	}

//	@Override
//	public boolean canEmitPowerFrom(ForgeDirection side) {
//		return side == direction;
//	}

}

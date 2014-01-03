package common.cout970.UltraTech.machines.tileEntities;

public class WindMillEntity extends Machine{

	public float fan;
	public float speed = 0;
	public float wind = 1;

	
	public void updateEntity()
	{
		float time = 0;
		float bioma = 0.5f;
		time = worldObj.getSunBrightness(1);
		String b = worldObj.getBiomeGenForCoords(xCoord, zCoord).biomeName;
		if(b == "Desert")bioma = 0.8f;
		if(b == "Ocean")bioma = 1f;
		if(b == "Jungle")bioma = 0.2f;
		if(b == "Forest")bioma = 0.3f;
		if(b == "ExtremeHills")bioma = 1f;
		wind = (this.yCoord/128)+time+bioma;
		if(speed > wind){
			speed -= 0.01;
		}else if(speed < wind){
			speed += 0.01f;
		}
		gainEnergy((int)speed);
	}

}

package common.cout970.UltraTech.energy.api;

public interface IEnergy {
	
	float addEnergy(float amount);
	
	void removeEnergy(float amount);
	
	float getEnergy();
	
	float maxEnergy();
	
	float maxFlow();
}

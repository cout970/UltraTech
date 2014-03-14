package common.cout970.UltraTech.energy.api;

public interface IEnergy {
	
	int addEnergy(int amount);
	
	void removeEnergy(int amount);
	
	int getEnergy();
	
	int maxEnergy();
	
	int maxFlow();
}

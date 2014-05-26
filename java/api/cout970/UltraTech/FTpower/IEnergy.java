package api.cout970.UltraTech.FTpower;

public interface IEnergy {
	
	double addEnergy(double amount);
	
	void removeEnergy(double amount);
	
	double getEnergy();
	
	double maxEnergy();
	
	double maxFlow();
}

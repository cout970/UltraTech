package api.cout970.UltraTech.MeVpower;
/**
 * 
 * @author Cout970
 * old api
 */
public interface IEnergy {
	
	double addEnergy(double amount);
	
	void removeEnergy(double amount);
	
	double getEnergy();
	
	double maxEnergy();
	
	double maxFlow();
}

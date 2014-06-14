package api.cout970.UltraTech.Vpower;
/**
 * 
 * @author Cout970
 *
 */
public interface IPower {
	
	double addCharge(double amount);
	
	void removeCharge(double amount);
	
	double getCharge();
	
	double maxCharge();
	
	double getFlow();
}

package ultratech.api.power;
/**
 * 
 * @author Cout970
 *
 */
public interface IPower {
	
	public static final String POWER_NAME = " MeV";
	
	double addCharge(double amount);
	
	void removeCharge(double amount);
	
	double getCharge();
	
	double getCapacity();
	
	double getFlow();
}

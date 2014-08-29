package ultratech.api.power.interfaces;
/**
 * 
 * @author Cout970
 *
 */
public interface IPower {
	
	public static final String POWER_NAME = " QP";
	
	double addCharge(double amount);
	
	void removeCharge(double amount);
	
	double getCharge();
	
	double getCapacity();
	
	double getFlow();
}

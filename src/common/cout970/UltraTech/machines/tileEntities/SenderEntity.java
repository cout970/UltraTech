package common.cout970.UltraTech.machines.tileEntities;


public class SenderEntity extends Machine{

	public SenderEntity(){
		super();
		this.EnergyMax = 1000;
		this.tipe = MachineTipe.Input;
	}
	public void onNeighChange(){}
}

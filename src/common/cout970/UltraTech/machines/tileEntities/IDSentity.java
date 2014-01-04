package common.cout970.UltraTech.machines.tileEntities;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

public class IDSentity extends Machine{

	
	public IDSentity(){
		super();
		this.EnergyMax = 100000;
		this.tipe = MachineTipe.Output;
	}

	


	
	public void sendGUINetworkData(Container container, ICrafting iCrafting) {
    	iCrafting.sendProgressBarUpdate(container, 1, Energy);
    }
    
    public void getGUINetworkData(int id, int value) {
    	switch(id){
    	case 1:{
    		Energy = value;
    		break;
    	}
    	}
	}
}

package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

public class EnergyColectorEntity extends Machine{
	

	public EnergyColectorEntity(){
		super();
		this.EnergyMax = 5000;
		this.tipe = MachineTipe.Output;
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			List<Machine> s = getMachines();
			for(Machine p : s){
				Machine.passEnergy(p, this);
			}
		}
		super.updateEntity();
	}

	public List<Machine> getMachines(){
		List<Machine> s = new ArrayList<Machine>();
		for(int x= -5;x<=5;x++){
			for(int z= -5;z<=5;z++){
				TileEntity t = this.worldObj.getBlockTileEntity(xCoord+x, yCoord, zCoord+z);
				if(t instanceof Machine){
					if(((Machine)t).tipe == MachineTipe.Output && !(t instanceof IDSentity) && !(t instanceof EnergyColectorEntity))s.add((Machine) t);
				}
			}
		}
		return s;
	}
}

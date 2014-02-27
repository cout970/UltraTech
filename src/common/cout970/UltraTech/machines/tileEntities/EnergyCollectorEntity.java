package common.cout970.UltraTech.machines.tileEntities;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;

public class EnergyCollectorEntity extends Machine{
	
	public List<Machine> s;
	private int timer;

	public EnergyCollectorEntity(){
		super();
		this.EnergyMax = 1000;
		this.tipe = MachineTipe.Output;
		s = getMachines();
	}

	@Override
	public void updateEntity(){
		if(!this.worldObj.isRemote){			
			if(s != null)
			for(Machine p : s){
				Machine.passEnergy(p, this);
			}
			else{
			s = getMachines();	
			}
			if(timer++ >=200){
				timer = 0;
				s = getMachines();
			}
		}
		super.updateEntity();
	}

	public List<Machine> getMachines(){
		if(worldObj == null)return null;
		List<Machine> s = new ArrayList<Machine>();
		for(int x= -5;x<=5;x++){
			for(int z= -5;z<=5;z++){
				TileEntity t = this.worldObj.getBlockTileEntity(xCoord+x, yCoord, zCoord+z);
				if(t instanceof Machine){
					if(((Machine)t).tipe == MachineTipe.Output && !(t instanceof IDSentity) && !(t instanceof EnergyCollectorEntity))s.add((Machine) t);
				}
			}
		}
		return s;
	}
}

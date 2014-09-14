package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import ultratech.api.power.StorageInterface;
import ultratech.api.power.interfaces.IPowerConductor;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.network.messages.MessageTesseract;
import common.cout970.UltraTech.util.ConfigurableTile;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.Machine;

public class TileEntityTesseract extends ConfigurableTile{
	
	public static List<TileEntityTesseract> tes = new ArrayList<TileEntityTesseract>();
	public int frequency = 0;
	public boolean writing = false;
	public float animation = 0;
	public boolean animationUp;
	public double animationTime;
	public float rotation;
	public Machine target;
	
	public TileEntityTesseract(){
		super();
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(!tes.contains(this))tes.add(this);
		if(target != null){	
			for(TileEntityTesseract t : tes){
				if(t.frequency == frequency && t!=this && t.target != null){
					float ownpercent = (float) (target.getCharge()*100/target.getCapacity());
					float otherpercent = (float) (t.target.getCharge()*100/t.target.getCapacity());
					if(otherpercent < ownpercent){
						MoveCharge(target, t.target);
					}
				}
			}
		}else if(worldObj.getTotalWorldTime()% 20 == 1){
			onNeigUpdate();
		}
		super.updateEntity();
	}
	
	//to move charge without conection check
	public static void MoveCharge(IPowerConductor a, IPowerConductor b){
		if(a == null || b == null)return;
		StorageInterface from = (StorageInterface) a.getPower();
		StorageInterface to = (StorageInterface) b.getPower();
		if(from == null || to == null)return;
		if(from.equals(to))return;
		if(from.getCharge() > 0){
			double space = to.getCapacity()-to.getCharge();
			double flow = Math.min(from.getFlow(), to.getFlow());
			if(space > 0){
				if(from.getCharge() > flow && space > flow){
					from.removeCharge(flow);
					to.addCharge(flow);
				}else if(from.getCharge() >= space){
					from.removeCharge(space);
					to.addCharge(space);
				}else{
					to.addCharge(from.getCharge());
					from.removeCharge(from.getCharge());
				}
			}
		}
	}

	@Override
	public void onNeigUpdate() {
		super.onNeigUpdate();
		TileEntity t = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
		if(t instanceof Machine){
			target = (Machine) t;
		}
	}
	
	public void setFrequency(int f){
		if(worldObj != null && worldObj.isRemote){
			Net_Utils.INSTANCE.sendToServer(new MessageTesseract(this, frequency));
		}
		if(!tes.contains(this) && worldObj != null && !worldObj.isRemote)tes.add(this);
		frequency = f;
		this.markDirty();
	}


	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		frequency = nbtTagCompound.getInteger("code");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("code", frequency);
	}
	
	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
	}
	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
	}

}

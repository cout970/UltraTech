package common.cout970.UltraTech.TileEntities.electric.tiers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import ultratech.api.power.IPowerConductor;
import ultratech.api.power.PowerUtils;
import ultratech.api.power.StorageInterface;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.managers.MachineData;
import common.cout970.UltraTech.network.Net_Utils;
import common.cout970.UltraTech.packets.PacketTesseract;
import common.cout970.UltraTech.util.ConfigurableMachine;
import common.cout970.UltraTech.util.ConfigurableTile;
import common.cout970.UltraTech.util.power.Machine;

public class Tesseract_Entity extends ConfigurableTile{
	
	public static List<Tesseract_Entity> tes = new ArrayList<Tesseract_Entity>();
	public int frequency = 0;
	public boolean writing = false;
	public float animation = 0;
	public boolean animationUp;
	public double animationTime;
	public float rotation;
	
	public Tesseract_Entity(){
		super();
	}
	
	public void updateEntity(){
//		if(worldObj.isRemote)return;
//		if(mode == T_Mode.RECEIVE){
//			cond.tipe = StorageInterface.PowerIO.Output;
//		}else if(mode == T_Mode.SEND){
//			cond.tipe = StorageInterface.PowerIO.Nothing;
//		}else{
//			cond.tipe = StorageInterface.PowerIO.Storage;
//		}
//		if(!tes.contains(this))tes.add(this);
//		if(mode == T_Mode.BOTH || mode == T_Mode.SEND)
//			for(Tesseract_Entity t : tes){
//				if(t.frequency == frequency && t!=this){
//					if(t.mode == T_Mode.RECEIVE || t.mode == T_Mode.BOTH){
//						this.MoveCharge(this, t);
//					}
//				}
//			}
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
	
	public void setFrequency(int f){
//		if(worldObj != null && worldObj.isRemote){
//			sendPacket();
//		}
//		if(!tes.contains(this) && worldObj != null && !worldObj.isRemote)tes.add(this);
//		frequency = f;
	}


	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		setFrequency(nbtTagCompound.getInteger("Freq"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Freq", frequency);
	}
	
	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
	}
	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
	}

}

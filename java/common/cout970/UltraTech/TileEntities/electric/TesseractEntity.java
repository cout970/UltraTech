package common.cout970.UltraTech.TileEntities.electric;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import api.cout970.UltraTech.Vpower.Machine;
import api.cout970.UltraTech.Vpower.PowerUtils;
import api.cout970.UltraTech.Vpower.StorageInterface;
import api.cout970.UltraTech.network.Net_Utils;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.packets.PacketTesseract;

public class TesseractEntity extends Machine{
	
	public static List<TesseractEntity> tes = new ArrayList<TesseractEntity>();
	public int frequency = 0;
	public T_Mode mode = T_Mode.BOTH;
	public boolean writing = false;
	
	public TesseractEntity(){
		super(10000,3);
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(mode == T_Mode.RECEIVE){
			cond.tipe = StorageInterface.MachineTipe.Output;
		}else if(mode == T_Mode.SEND){
			cond.tipe = StorageInterface.MachineTipe.Nothing;
		}else{
			cond.tipe = StorageInterface.MachineTipe.Storage;
		}
		if(!tes.contains(this))tes.add(this);
		if(mode == T_Mode.BOTH || mode == T_Mode.SEND)
			for(TesseractEntity t : tes){
				if(t.frequency == frequency && t!=this){
					if(t.mode == T_Mode.RECEIVE || t.mode == T_Mode.BOTH){
						PowerUtils.MoveCharge(this, t);
					}
				}
			}
		super.updateEntity();
	}
	
	public void setFrequency(int f){
		if(worldObj != null && worldObj.isRemote){
			sendPacket();
		}
		if(!tes.contains(this) && worldObj != null && !worldObj.isRemote)tes.add(this);
		frequency = f;
	}
	
	private void sendPacket() {
		PacketTesseract p = new PacketTesseract(this, mode.ordinal(), frequency);
		Net_Utils.PipeLine.sendToServer(p);
	}

	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		setFrequency(nbtTagCompound.getInteger("Freq"));
		mode = T_Mode.getMode(nbtTagCompound.getInteger("Mode"));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("Freq", frequency);
		nbtTagCompound.setInteger("Mode", mode.ordinal());
	}
	
	public enum T_Mode{
		BOTH,SEND,RECEIVE;

		public static int change(int o) {
			if(o == 0)return 1;
			if(o == 1)return 2;
			if(o == 2)return 0;
			return 0;
		}

		public static T_Mode getMode(int o) {
			if(o == 0)return BOTH;
			if(o == 1)return SEND;
			if(o == 2)return RECEIVE;
			return BOTH;
		}
	}

	public void changeMode(T_Mode mode2) {
		mode = mode2;
		if(worldObj.isRemote){
			sendPacket();
		}
	}
	
	//sync
	
	public void sendGUINetworkData(Container cont,
			ICrafting c) {
		super.sendGUINetworkData(cont, c);
	}


	public void getGUINetworkData(int id, int value) {
		super.getGUINetworkData(id, value);
	}

}

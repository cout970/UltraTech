package common.cout970.UltraTech.TileEntities.Tier3;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TesseractEntity extends Machine{
	
	public static List<TesseractEntity> tes = new ArrayList<TesseractEntity>();
	public int frequency = 0;
	public T_Mode mode = T_Mode.BOTH;
	public boolean writing = false;
	
	public TesseractEntity(){
		tipe = MachineTipe.Nothing;
		tier = MachineTier.Tier3;
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(mode == T_Mode.RECEIVE){
			tipe = MachineTipe.Output;
		}else if(mode == T_Mode.SEND){
			tipe = MachineTipe.Nothing;
		}else{
			tipe = MachineTipe.Storage;
		}
		if(!tes.contains(this))tes.add(this);
		if(mode == T_Mode.BOTH || mode == T_Mode.SEND)
			for(TesseractEntity t : tes){
				if(t.frequency == frequency && t!=this){
					if(t.mode == T_Mode.RECEIVE || t.mode == T_Mode.BOTH){
						passEnergy(this, t ,false);
					}
				}
			}
		super.updateEntity();
	}
	
	@Override
	public float maxFlow() {
		return 500;
	}
	
	public void setFrequency(int f){
		if(worldObj != null && worldObj.isRemote){
			sendPacket();
		}
		if(!tes.contains(this) && worldObj != null && !worldObj.isRemote)tes.add(this);
		frequency = f;
	}
	
	private void sendPacket() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(bytes);
		try {
			data.writeInt(-2);
			data.writeInt(xCoord);
			data.writeInt(yCoord);
			data.writeInt(zCoord);
			data.writeInt(mode.ordinal());
			data.writeInt(frequency);
		}catch(Exception e){}
		Packet p = new Packet250CustomPayload("UltraTech", bytes.toByteArray());
		PacketDispatcher.sendPacketToServer(p);
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

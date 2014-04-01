package common.cout970.UltraTech.TileEntities.Tier3;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.EnergyCosts.MachineTier;
import cpw.mods.fml.common.network.PacketDispatcher;

public class TesseractEntity extends Machine{

	public static Map<String,TesseractEntity> freqs = new HashMap<String,TesseractEntity>();
	
	public String frequency = "";
	public String To = "";
	public T_Mode mode = T_Mode.BOTH;
	public boolean writing = false;
	public boolean up;
	
	public TesseractEntity(){
		tipe = MachineTipe.Nothing;
		tier = MachineTier.Tier3;
		frequency = (new Random()).nextInt(1000000)+"";
	}
	
	public void updateEntity(){
		if(worldObj.isRemote)return;
		if(mode == T_Mode.RECEIVE || mode == T_Mode.BOTH){
			emptyMachine();
		}else{
			fillMachine();
		}
		if(To != "" && getEnergy() > 0){
			if(mode == T_Mode.SEND || mode == T_Mode.BOTH){
				if(freqs.containsKey(To)){
					int amount = Math.min(this.tier.getFlow(), freqs.get(To).maxEnergy()-freqs.get(To).getEnergy());
					amount = Math.min(amount, getEnergy());
					freqs.get(To).addEnergy(amount);
					this.removeEnergy(amount);
				}
			}
		}
	}
	
	public void setFrequency(String f){
		if(worldObj.isRemote){
			sendPacket();
		}
		if(freqs.containsKey(f)){
				return;
			}
		if(frequency != null){
			if(freqs.containsKey(frequency)){
				freqs.remove(frequency);
			}
		}
		frequency = f;
		freqs.put(frequency, this);
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
			data.writeUTF(frequency);
			data.writeUTF(To);
		}catch(Exception e){}
		Packet p = new Packet250CustomPayload("UltraTech", bytes.toByteArray());
		PacketDispatcher.sendPacketToServer(p);
	}

	//Save & Load
	
	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		setFrequency(nbtTagCompound.getString("Freq"));
		To = nbtTagCompound.getString("To");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setString(frequency, "Freq");
		nbtTagCompound.setString(To, "To");
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

	
	public void setDestine(String text) {
		To = text;
		if(worldObj.isRemote){
			sendPacket();
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

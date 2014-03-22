package common.cout970.UltraTech.TileEntities.Tier3;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import common.cout970.UltraTech.energy.api.Machine;
import common.cout970.UltraTech.lib.GraficCost.MachineTier;

public class TesseractEntity extends Machine{

	public static Map<String,TesseractEntity> freqs = new HashMap<String,TesseractEntity>();
	
	public String frequency = "";
	public String To;
	public T_Mode mode = T_Mode.BOTH;
	public boolean writing = false;
	public boolean up;
	
	public TesseractEntity(){
		tipe = MachineTipe.Storage;
		tier = MachineTier.Tier3;
	}
	
	public void updateEntity(){
		super.updateEntity();
		if(worldObj.isRemote)return;
		if(To != null){
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
}

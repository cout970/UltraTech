package api.cout970.UltraTech.network;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class Net_Utils {
	
	public static PacketPipeLine PipeLine = new PacketPipeLine();
	
	public static void sendUpdate(TileEntity t){
		if(t == null)return;			
		PacketUpdate a = new PacketUpdate(t);
		Net_Utils.PipeLine.sendToAll(a);
	}

	

}

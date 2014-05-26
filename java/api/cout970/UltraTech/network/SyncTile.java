package api.cout970.UltraTech.network;

import api.cout970.UltraTech.FTpower.IPowerConductor;
import api.cout970.UltraTech.FTpower.StorageInterface;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class SyncTile extends TileEntity{

	public void Sync(){
		Net_Utils.sendUpdate(this);
	}
	
	public Packet getDescriptionPacket(){
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		S35PacketUpdateTileEntity p = new S35PacketUpdateTileEntity(xCoord,yCoord,zCoord,0,nbt);
		return p;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		this.readFromNBT(pkt.func_148857_g());
	}
	
	public void sendGUINetworkData(Container cont, ICrafting c) {
		if(this instanceof IPowerConductor){
			c.sendProgressBarUpdate(cont, 0, (int) ((IPowerConductor)this).getPower().getCharge());
		}
	}

	public void getGUINetworkData(int id, int value) {
		if(id==0)if(this instanceof IPowerConductor){
			((StorageInterface)((IPowerConductor)this).getPower()).setCharge(value);
		}
	}
}

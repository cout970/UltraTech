package common.cout970.UltraTech.network;

import common.cout970.UltraTech.util.LogHelper;
import ultratech.api.power.IPowerConductor;
import ultratech.api.power.StorageInterface;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeInternalHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.network.ForgeNetworkHandler;
import net.minecraftforge.transformers.ForgeAccessTransformer;

public class SyncTile extends TileEntity{
	
	public void sendNetworkUpdate(){
		if(!this.worldObj.isRemote){
			Net_Utils.sendUpdate(this);
		}
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
			double ent = ((IPowerConductor)this).getPower().getCharge();
			float d = (float) (ent - ((int)ent));
			int deci = (int) (d*10);
			c.sendProgressBarUpdate(cont, -1, deci);
		}
		this.sendNetworkUpdate();
	}

	public void getGUINetworkData(int id, int value) {
		if(id==0)if(this instanceof IPowerConductor){
			((StorageInterface)((IPowerConductor)this).getPower()).setCharge(value);
		}
		if(id==-1)if(this instanceof IPowerConductor){
			((StorageInterface)((IPowerConductor)this).getPower()).setChargeDeci(value);
		}
	}
}

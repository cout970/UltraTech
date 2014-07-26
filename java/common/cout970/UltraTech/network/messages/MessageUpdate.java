package common.cout970.UltraTech.network.messages;

import common.cout970.UltraTech.network.SyncTile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageUpdate implements IMessage, IMessageHandler<MessageUpdate, IMessage>{

	public int x, y, z;
	public NBTTagCompound nbt;
	
	 public MessageUpdate(){}
	 
	 public MessageUpdate(SyncTile t){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 nbt = new NBTTagCompound();
		 t.writeToNBT(nbt);
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		try {
			nbt = PB.readNBTTagCompoundFromBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
        PB.writeInt(x);
        PB.writeShort(y);
        PB.writeInt(z);
        try{
        	PB.writeNBTTagCompoundToBuffer(nbt);
        }catch (Exception e){
        	e.printStackTrace();
        }
	}

	@Override
	public IMessage onMessage(MessageUpdate message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof SyncTile){
			tileEntity.readFromNBT(message.nbt);
		}
		return null;
	}
}

package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import ultratech.api.power.StorageInterface.PowerIO;
import common.cout970.UltraTech.TileEntities.utility.TileEntityPainter3D;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePainter implements IMessage, IMessageHandler<MessagePainter, IMessage>{

	public int x, y, z;
	public int color;
	
	 public MessagePainter(){}
	 
	 public MessagePainter(SyncTile t,int mode){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 this.color = mode;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		color = PB.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		PB.writeInt(x);
		PB.writeShort(y);
		PB.writeInt(z);
		PB.writeInt(color);
	}

	@Override
	public IMessage onMessage(MessagePainter message, MessageContext ctx) {
		TileEntity t = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(t instanceof TileEntityPainter3D){
			((TileEntityPainter3D)t).color = message.color;
			((TileEntityPainter3D)t).update = true;
		}
		return null;
	}
}

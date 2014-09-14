package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.electric.tiers.TileEntityTesseract;
import common.cout970.UltraTech.TileEntities.utility.TileEntityPainter3D;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageTesseract implements IMessage, IMessageHandler<MessageTesseract, IMessage>{

	public int x, y, z;
	public int freq;
	
	 public MessageTesseract(){}
	 
	 public MessageTesseract(SyncTile t,int mode){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 this.freq = mode;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		freq = PB.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		PB.writeInt(x);
		PB.writeShort(y);
		PB.writeInt(z);
		PB.writeInt(freq);
	}

	@Override
	public IMessage onMessage(MessageTesseract message, MessageContext ctx) {
		TileEntity t = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(t instanceof TileEntityTesseract){
			((TileEntityTesseract)t).setFrequency(message.freq);
		}
		return null;
	}
}

package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.multiblocks.Reactor_Core_Entity;
import common.cout970.UltraTech.network.SyncTile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageReactorConfig implements IMessage, IMessageHandler<MessageReactorConfig, IMessage> {

	 public int x, y, z;
	 public int type;
	 public int value;
		
	 public MessageReactorConfig(){}
	 
	 public MessageReactorConfig(SyncTile t,int c,int value){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 type = c;
		 this.value = value;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		type = PB.readInt();
		value = PB.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
       PB.writeInt(x);
       PB.writeShort(y);
       PB.writeInt(z);
       PB.writeInt(type);
       PB.writeInt(value);
	}

	@Override
	public IMessage onMessage(MessageReactorConfig message, MessageContext ctx) {
		TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof Reactor_Core_Entity){
			((Reactor_Core_Entity) tileEntity).applyConfig(message.type,message.value);
		}
		return null;
	}

}
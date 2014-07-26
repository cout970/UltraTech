package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.network.SyncTile;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageClimateStation implements IMessage, IMessageHandler<MessageClimateStation, IMessage> {

	 public int x, y, z;
	 public int type;
		
	 public MessageClimateStation(){}
	 
	 public MessageClimateStation(SyncTile t,int c){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 type = c;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		type = PB.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
        PB.writeInt(x);
        PB.writeShort(y);
        PB.writeInt(z);
        PB.writeInt(type);
	}

	@Override
	public IMessage onMessage(MessageClimateStation message, MessageContext ctx) {
		TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof ClimateEntity){
			((ClimateEntity) tileEntity).setClimate(message.type);
		}
		return null;
	}

}

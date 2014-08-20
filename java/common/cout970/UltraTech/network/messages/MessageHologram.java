package common.cout970.UltraTech.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.TileEntities.electric.ClimateEntity;
import common.cout970.UltraTech.TileEntities.utility.HologramEmiterEntity;
import common.cout970.UltraTech.network.SyncTile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageHologram implements IMessage, IMessageHandler<MessageHologram, IMessage> {

	 public int x, y, z;
	 public int type;
	 public float amount;
		
	 public MessageHologram(){}
	 
	 public MessageHologram(SyncTile t,int c, float amount){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 this.amount = amount;
		 type = c;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		type = PB.readInt();
		amount = PB.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
       PB.writeInt(x);
       PB.writeShort(y);
       PB.writeInt(z);
       PB.writeInt(type);
       PB.writeFloat(amount);
	}

	@Override
	public IMessage onMessage(MessageHologram message, MessageContext ctx) {
		TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof HologramEmiterEntity){
			HologramEmiterEntity h = (HologramEmiterEntity) tileEntity;
			if(message.type == 0){
				h.mX = message.amount;
			}else if(message.type == 1){
				h.mY = message.amount;
			}else if(message.type == 2){
				h.mZ = message.amount;
			}else if(message.type == 3){
				h.facing = ForgeDirection.getOrientation((int) message.amount);
			}
			h.sendNetworkUpdate();
		}
		return null;
	}

}
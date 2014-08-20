package common.cout970.UltraTech.network.messages;

import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import common.cout970.UltraTech.microparts.MicroCablePlane;
import common.cout970.UltraTech.misc.IUpdatedEntity;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageMicroPartUpdate implements IMessage, IMessageHandler<MessageMicroPartUpdate, IMessage>{

	public int x, y, z;
	
	 public MessageMicroPartUpdate(){}
	 
	 public MessageMicroPartUpdate(TMultiPart t){
		 x = t.x();
		 y = t.y();
		 z = t.z();
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
        PB.writeInt(x);
        PB.writeShort(y);
        PB.writeInt(z);
	}

	@Override
	public IMessage onMessage(MessageMicroPartUpdate message, MessageContext ctx) {
		TileEntity tileEntity = FMLClientHandler.instance().getClient().theWorld.getTileEntity(message.x, message.y, message.z);
		if(tileEntity instanceof TileMultipart){
			TileMultipart tm = (TileMultipart) tileEntity;
			for(TMultiPart t : tm.jPartList()){
				if(t instanceof IUpdatedEntity){
					((IUpdatedEntity) t).onNeigUpdate();
				}
			}
		}
		return null;
	}
}
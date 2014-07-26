package common.cout970.UltraTech.network.messages;

import ultratech.api.power.StorageInterface.PowerIO;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import common.cout970.UltraTech.network.SyncTile;
import common.cout970.UltraTech.util.LogHelper;
import common.cout970.UltraTech.util.power.Machine;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.server.FMLServerHandler;

public class MessageMachineMode implements IMessage, IMessageHandler<MessageMachineMode, IMessage>{

	public int x, y, z;
	public int mode;
	
	 public MessageMachineMode(){}
	 
	 public MessageMachineMode(SyncTile t,int mode){
		 x = t.xCoord;
		 y = t.yCoord;
		 z = t.zCoord;
		 this.mode = mode;
	 }

	@Override
	public void fromBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		x = PB.readInt();
		y = PB.readShort();
		z = PB.readInt();
		mode = PB.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		PacketBuffer PB = new PacketBuffer(buf);
		PB.writeInt(x);
		PB.writeShort(y);
		PB.writeInt(z);
		PB.writeInt(mode);
	}

	@Override
	public IMessage onMessage(MessageMachineMode message, MessageContext ctx) {
		TileEntity a = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		if(a instanceof Machine){
			((Machine) a).cond.configIO = PowerIO.values()[message.mode];
			((Machine) a).sendNetworkUpdate();
		}
		return null;
	}
}

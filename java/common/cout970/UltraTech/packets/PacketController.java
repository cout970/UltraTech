package common.cout970.UltraTech.packets;

import api.cout970.UltraTech.network.PacketBase;
import common.cout970.UltraTech.TileEntities.utility.ReactorControllerEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public class PacketController extends PacketBase{

	public int x,y,z;
	public boolean use;
	
	public PacketController(){}
	
	public PacketController(ReactorControllerEntity e, boolean useHeat) {
		x = e.xCoord;
		y = e.yCoord;
		z = e.zCoord;
		use = !useHeat;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer b = new PacketBuffer(buffer);
		b.writeInt(x);
		b.writeShort(y);
		b.writeInt(z);
		b.writeBoolean(use);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer b = new PacketBuffer(buffer);
		x = b.readInt();
		y = b.readShort();
		z = b.readInt();
		use = b.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ReactorControllerEntity c = (ReactorControllerEntity) player.worldObj.getTileEntity(x, y, z);
		c.useHeat = use;
		c.onNeighChange();
	}

}

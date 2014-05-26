package common.cout970.UltraTech.packets;

import api.cout970.UltraTech.network.PacketBase;
import common.cout970.UltraTech.TileEntities.Tier1.Printer3DEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;

public class PacketPainter extends PacketBase{

	public int color;
	public int x,y,z;
	public PacketPainter(){}
	
	public PacketPainter(TileEntity t,int color){
		this.color = color;
		x = t.xCoord;
		y = t.yCoord;
		z = t.zCoord;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer pb = new PacketBuffer(buffer);
		pb.writeInt(x);
		pb.writeShort(y);
		pb.writeInt(z);
		pb.writeInt(color);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		PacketBuffer pb = new PacketBuffer(buffer);
		x = pb.readInt();
		y = pb.readShort();
		z = pb.readInt();
		color = pb.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {}

	@Override
	public void handleServerSide(EntityPlayer player) {
		Printer3DEntity t = (Printer3DEntity) player.worldObj.getTileEntity(x, y, z);
		t.color = color;
		t.update = true;
	}

}
